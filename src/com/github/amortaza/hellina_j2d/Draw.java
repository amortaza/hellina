/*
Copyright 2014 Afshin Mortazavi-Nia

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.github.amortaza.hellina_j2d;

import com.github.amortaza.hellina.intf.IDraw;
import com.github.amortaza.hellina_j2d.font.FontKey;
import com.github.amortaza.hellina_j2d.font.FontManager;

import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.Area;
import java.util.*;

public class Draw implements IDraw {
    CCanvas canvas;
    final Graphics2D graphics2d;

    Map<Float, Stroke> strokeByFloat = new HashMap<Float, Stroke>();

    public Draw( CCanvas jcanvas ) {
        this.canvas = jcanvas;

        graphics2d = jcanvas.offG;
    }

    public void setStrokeThickness( float thickness ) {
        if ( !strokeByFloat.containsKey( thickness ) )
            strokeByFloat.put( thickness, new BasicStroke( thickness ) );

        graphics2d.setStroke( strokeByFloat.get( thickness ) );
    }

    // *******************************************************************
    // color
    // applies to drawLine, drawRect, fillRect

    public void setColor( float red, float green, float blue ) {
        graphics2d.setColor( new Color( red, green, blue ) );
    }

    // *******************************************************************
    // line

    public void drawLine( int x1, int y1, int x2, int y2 ) {
        graphics2d.drawLine( x1, y1, x2, y2 );
    }

    public void gradLine(   int x1, int y1, float red1, float green1, float blue1,
                            int x2, int y2, float red2, float green2, float blue2 ) {

        graphics2d.setPaint(    new GradientPaint( x1, y1, new Color( red1, green1, blue1 ),
                                x2, y2, new Color( red2, green2, blue2 ),
                                false ) );

        graphics2d.drawLine( x1, y1, x2, y2 );
    }

    // *******************************************************************
    // rectangle

    public void drawRect( int x, int y, int width, int height ) {
        // there is a bug in java2d...read spec of drawRect...it is right there in the spec
        // a rect at x with width will NOT have it's left side x and right side at x + width as doc suggests..
        // it SHOULD have left at x and right at width - 1! (dito for the y/height)
        // spec for fillRect is fine..
        // but the resulting rect is 1 pixel bigger than it should be...therefore we must subtract 1 here...

        graphics2d.drawRect( x, y, width - 1, height - 1 );
    }

    public void fillRect( int x, int y, int width, int height ) {
        graphics2d.fillRect( x, y, width, height );
    }

    public void gradRect(
            int x, int y, int width, int height,
            char axis, // one of 'x' or 'y'
            float red1, float green1, float blue1,
            float red2, float green2, float blue2 ) {
        if ( axis == 'x' ) {
            graphics2d.setPaint( new GradientPaint( x, y, new Color( red1, green1, blue1 ),
                    x + width - 1, y, new Color( red2, green2, blue2 ),
                    false ) );
        }
        else {
            graphics2d.setPaint( new GradientPaint( x, y, new Color( red1, green1, blue1 ),
                    x, y + height - 1, new Color( red2, green2, blue2 ),
                    false ) );
        }

        graphics2d.fillRect( x, y, width, height );
    }

    // *******************************************************************
    // font

    public Dimension getTextDims( String text, String fontname, String fontstyle, float fontsize ) {
        TextLayout layout = getTextLayout( text, fontname, fontstyle, fontsize, graphics2d );

        Dimension textdim = new Dimension();

        textdim.width = ( int ) ( layout.getAdvance() + .5f );
        textdim.height = ( int ) ( layout.getAscent() + layout.getDescent() + layout.getLeading() + .5f );

        return textdim;
    }

    // *******************************************************************
    // text

    public void drawText(
            int left, int top,
            String content,
            float red, float green, float blue,
            String fontname, String fontstyle, float fontsize ) {
        Dimension textdim = getTextDims( content, fontname, fontstyle, fontsize );

        Font font = FontManager.getFont( new FontKey( fontname,
                FontKey.toStyleId( fontstyle ),
                fontsize ) );

        graphics2d.setFont( font );

        graphics2d.setColor( new Color( red, green, blue ) );

        graphics2d.drawString( content, left, top + textdim.height );
    }

    // *******************************************************************
    // opacity

    private Stack<Composite> originalComposite = new Stack<Composite>();

    public void pushOpacity( float opacity ) {
        originalComposite.push( graphics2d.getComposite() );

        graphics2d.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, opacity ) );
    }

    public void popOpacity() {
        graphics2d.setComposite( originalComposite.pop() );
    }

    // *******************************************************************
    // clip

    private Stack<Area> clipStack = new Stack<Area>();

    public void pushClipRegion( int x, int y, int width, int height ) {
        Area clipRegion = new Area( new Rectangle( x, y, width, height ) );

        Area intersectRegion;

        if ( clipStack.size() > 0 && clipStack.peek() != null ) {
            intersectRegion = new Area( clipRegion );

            intersectRegion.intersect( clipStack.peek() );
        }
        else {
            intersectRegion = clipRegion;
        }

        clipStack.push( intersectRegion );

        graphics2d.setClip( intersectRegion );
    }

    public void pushInfiniteClipRegion() {
        clipStack.push( null );

        graphics2d.setClip( null );
    }

    public void popClipRegion() {
        Shape clipRegion;

        clipStack.pop();

        if ( clipStack.size() > 0 ) {
            clipRegion = clipStack.peek();
        }
        else {
            clipRegion = null;
        }

        graphics2d.setClip( clipRegion );
    }

    // *******************************************************************
    // other

    public void swap() {
        // future canvas needs to respect region...right now
        // region is treated as all or none...but it could have
        // sparse rectangular regions
        canvas.getGraphics().drawImage( canvas.offBuffer, 0, 0, canvas );
    }

    // note some clients who call getLayout will count on setFont...so if that line is ever removed
    // make sure to go back and fix clients
    private TextLayout getTextLayout( String text,
                                      String fontname,
                                      String fontstyle,
                                      float fontsize,
                                      Graphics2D g ) {
        Font font = FontManager.getFont( new FontKey( fontname,
                FontKey.toStyleId( fontstyle ),
                fontsize ) );

        g.setFont( font );

        return new TextLayout( text, font, g.getFontRenderContext() );
    }

    private Map<Integer, Image> imageById = new HashMap<Integer, Image>();

    // returns imageId
//    public int createImage() {
//        return 0;
//    }

//    public void drawImage( int imageId, int x, int y ) {
//        g.drawImage( image, x, y, null );
//    }
}


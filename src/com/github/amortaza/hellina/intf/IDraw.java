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

package com.github.amortaza.hellina.intf;

import java.awt.*;

public interface IDraw {
    void setStrokeThickness( float thickness );

    // *******************************************************************
    // color
    // applies to drawLine, drawRect, fillRect

    void setColor( float red, float green, float blue );

    // *******************************************************************
    // line
    void drawLine( int x1, int y1, int x2, int y2 );

    void gradLine( int x1, int y1, float red1, float green1, float blue1,
                   int x2, int y2, float red2, float green2, float blue2 );

    // *******************************************************************
    // rectangle
    void drawRect( int x, int y, int width, int height );

    void fillRect( int x, int y, int width, int height );

    void gradRect(
            int x, int y, int width, int height,
            char axis, // one of 'x' or 'y'
            float red1, float green1, float blue1,
            float red2, float green2, float blue2 );

    // *******************************************************************
    // font
    Dimension getTextDims( String text, String fontname, String fontstyle, float fontsize );

    // *******************************************************************
    // text
    void drawText(
            int left, int top,
            String content,
            float red, float green, float blue,
            String fontname, String fontstyle, float fontsize );

    // *******************************************************************
    // clip
    void pushClipRegion( int x, int y, int width, int height );

    void pushInfiniteClipRegion();

    void popClipRegion();

    // *******************************************************************
    // opacity
    // values are between 0.0 - 1.0
    void pushOpacity( float opacity );

    void popOpacity();

    // image
//    int createImage(); // returns imageID

//    void drawImage( int imageId, int x, int y );

    // *******************************************************************
    // other
    void swap();
}




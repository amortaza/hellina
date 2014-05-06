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

package sample;

import com.github.amortaza.hellina.intf.IDraw;
import com.github.amortaza.hellina.intf.IPlatform;
import com.github.amortaza.hellina.listener.IKeyListener;
import com.github.amortaza.hellina.listener.IMouseListener;
import com.github.amortaza.hellina.listener.ITickListener;
import com.github.amortaza.hellina.listener.IWindowListener;
import com.github.amortaza.hellina_j2d.Platform;

public class Run {
    public static void main( String[] args ) {
        final IPlatform p = new Platform();

        p.init(
                -1, -1, 640, 480,
                new IWindowListener() {
                    public void onMove( int x, int y ) {
                        System.out.println( "*** On CWindow moved: " + x + ", " + y );
                    }

                    public void onSize( int width, int height ) {
                        System.out.println( "*** On Size: " + width + ", " + height );

                    }

                    public void onClosed() {

                    }

                    public void onMinimize( boolean isMinimized ) {
                        System.out.println( "*** On Minimized" );
                    }

                    public void onFocus( boolean isFocus ) {
                        System.out.println( "*** On Focus: " + isFocus );

                    }
                },

                new IMouseListener() {
                    // cursor
                    public void onMove( int x, int y ) {
                        System.out.println( "*** On Mouse Moved: " + x + ", " + y );

                    }// left button

                    public void onLeftDown( int x, int y ) {
                        System.out.println( "*** On Mouse Left Down: " + x + ", " + y );
                    }

                    public void onLeftUp( int x, int y ) {
                        System.out.println( "*** On Mouse Left Up: " + x + ", " + y );

                    }// middle button

                    public void onMiddleDown( int x, int y ) {
                        System.out.println( "*** On Mouse Middle Down: " + x + ", " + y );
                    }

                    public void onMiddleUp( int x, int y ) {
                        System.out.println( "*** On Mouse Middle Up: " + x + ", " + y );
                    }// right button

                    public void onRightDown( int x, int y ) {
                        System.out.println( "*** On Mouse Right Down: " + x + ", " + y );
                    }

                    public void onRightUp( int x, int y ) {
                        System.out.println( "*** On Mouse Right Up: " + x + ", " + y );
                    }

                    public void onScrollDown( int x, int y ) {
                        System.out.println( "*** On Mouse Scroll Down: " + x + ", " + y );

                    }

                    public void onScrollUp( int x, int y ) {
                        System.out.println( "*** On Mouse Scroll Up: " + x + ", " + y );
                    }
                },

                new IKeyListener() {
                    public void onKeyDown( int code, boolean altDown, boolean controlDown, boolean shiftDown ) {
                        System.out.println( "*** On Key Down: " + code );
                    }

                    public void onKeyUp( int code, boolean altDown, boolean controlDown, boolean shiftDown ) {
                        System.out.println( "*** On Key Up: " + code );

                    }
                },
                new ITickListener() {
                    int x;

                    public void afterInit( IPlatform platform ) {
                        System.out.println( "*** On Post Init" );
                    }

                    public void onTick() {
                        IDraw d = p.getGraphicsDevice().getIDraw();

                        d.setColor( 0, 0, 0 );
                        d.fillRect( 0, 0, 639, 479 );

                        d.setColor( 1, 0, 0 );
                        d.drawRect( x, 50, 100, 100 );
                        d.swap();
                        x++;
                        if ( x > 200 )
                            x = 0;
                    }
                }
        );

        p.startLooping();
    }
}

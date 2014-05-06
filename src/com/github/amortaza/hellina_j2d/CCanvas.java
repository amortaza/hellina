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

import com.github.amortaza.hellina.listener.IKeyListener;
import com.github.amortaza.hellina.listener.IMouseListener;
import com.github.amortaza.hellina.listener.IWindowListener;

import java.awt.*;
import java.awt.event.*;

public class CCanvas extends java.awt.Canvas {
    public Image offBuffer;

    Graphics2D offG;

    public CCanvas(
            IMouseListener mouseListener,
            IKeyListener keyListener
    ) {
        setBackground( Color.white );

        setupMouseListener( mouseListener );
        setupKeyListener( keyListener );
    }

    public void paint( Graphics g ) {
        if ( offBuffer == null )
            createOffBuffer();

        g.drawImage( offBuffer, 0, 0, this );
    }

    private void createOffBuffer() {
        offBuffer = createImage( getWidth(), getHeight() );

        offG = ( Graphics2D ) offBuffer.getGraphics();
    }

    public void setupWindowListener( final IWindowListener windowListener ) {
        addComponentListener( new ComponentListener() {
            public void componentResized( ComponentEvent e ) {
                windowListener.onSize( getWidth(), getHeight() );
            }

            public void componentMoved( ComponentEvent e ) {
            }

            public void componentShown( ComponentEvent e ) {
            }

            public void componentHidden( ComponentEvent e ) {
            }
        } );
    }

    private void setupMouseListener( final IMouseListener mouseListener ) {
        addMouseMotionListener( new MouseMotionAdapter() {
            public void mouseDragged( MouseEvent e ) {
                Point p = e.getPoint();

                onFakeMove( p );
            }

            public void mouseMoved( MouseEvent e ) {
                Point p = e.getPoint();

                mouseListener.onMove( p.x, p.y );
            }

            public void onFakeMove( Point p ) {
                mouseListener.onMove( p.x, p.y );
            }
        } );

        addMouseListener( new MouseAdapter() {
            public void mousePressed( MouseEvent e ) {
                if ( e.getButton() == 1 ) {
                    mouseListener.onLeftDown( e.getPoint().x, e.getPoint().y );
                }
                else if ( e.getButton() == 2 ) {
                    mouseListener.onMiddleDown( e.getPoint().x, e.getPoint().y );
                }
                else if ( e.getButton() == 3 ) {
                    mouseListener.onRightDown( e.getPoint().x, e.getPoint().y );
                }
            }

            public void mouseReleased( MouseEvent e ) {
                if ( e.getButton() == 1 ) {
                    mouseListener.onLeftUp( e.getPoint().x, e.getPoint().y );
                }
                else if ( e.getButton() == 2 ) {
                    mouseListener.onMiddleUp( e.getPoint().x, e.getPoint().y );
                }
                else if ( e.getButton() == 3 ) {
                    mouseListener.onRightUp( e.getPoint().x, e.getPoint().y );
                }
            }
        } );

        addMouseWheelListener( new MouseWheelListener() {
            public void mouseWheelMoved( MouseWheelEvent e ) {
                if ( e.getWheelRotation() > 0 ) {
                    // scroll down
                    mouseListener.onScrollDown( e.getPoint().x, e.getPoint().y );
                }
                else {
                    mouseListener.onScrollUp( e.getPoint().x, e.getPoint().y );
                }
            }
        } );
    }

    private void setupKeyListener( final IKeyListener keyListener ) {
        addKeyListener( new KeyAdapter() {
            public void keyPressed( KeyEvent e ) {
                keyListener.onKeyDown( e.getKeyCode(), e.isAltDown(), e.isControlDown(), e.isShiftDown() );
            }

            public void keyReleased( KeyEvent e ) {
                keyListener.onKeyUp( e.getKeyCode(), e.isAltDown(), e.isControlDown(), e.isShiftDown() );
            }
        } );
    }
}

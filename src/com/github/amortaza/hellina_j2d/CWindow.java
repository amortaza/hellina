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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CWindow extends JFrame {
    public CCanvas canvas;

    public CWindow(
            int x, int y, int width, int height,
            IWindowListener windowListener,
            IMouseListener mouseListener,
            IKeyListener keyListener ) {

        super( "Helina - J2D Platform v1.0.0" );

        // this has to appear before setupWindowListener
        canvas = new CCanvas( mouseListener, keyListener );

        setupWindowListener( windowListener );

        setResizable( true );

        getContentPane().add( canvas, "Center" );

        addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent e ) {
                System.exit( 0 );
            }
        } );

        width += 8;
        height += 34;

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        if ( x == -1 && y == -1 )
            setLocation( ( int ) screen.getWidth() / 2 - width / 2, ( int ) screen.getHeight() / 4 - height / 4 );
        else
            setLocation( x, y );

        setSize( new Dimension( width, height ) );

        setVisible( true );
    }

    private void setupWindowListener( final IWindowListener windowListener ) {
        canvas.setupWindowListener( windowListener );

        addComponentListener( new ComponentListener() {
            public void componentMoved( ComponentEvent e ) {
                windowListener.onMove( getX(), getY() );
            }

            public void componentResized( ComponentEvent e ) {
            }

            public void componentShown( ComponentEvent e ) {
            }

            public void componentHidden( ComponentEvent e ) {
            }
        } );

        addWindowListener( new WindowListener() {
            public void windowOpened( WindowEvent e ) {
            }

            public void windowClosing( WindowEvent e ) {
            }

            public void windowClosed( WindowEvent e ) {
                windowListener.onClosed();
            }

            public void windowIconified( WindowEvent e ) {
                windowListener.onMinimize( true );
            }

            public void windowDeiconified( WindowEvent e ) {
                windowListener.onMinimize( false );
            }

            public void windowActivated( WindowEvent e ) {
            }

            public void windowDeactivated( WindowEvent e ) {
            }
        } );

        addWindowFocusListener( new WindowFocusListener() {
            public void windowGainedFocus( WindowEvent e ) {
                windowListener.onFocus( true );
            }

            public void windowLostFocus( WindowEvent e ) {
                windowListener.onFocus( false );
            }
        } );
    }
}

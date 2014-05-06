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

package com.github.amortaza.hellina_j2d.device;

import com.github.amortaza.hellina.device.IGraphicsDevice;
import com.github.amortaza.hellina.intf.IDraw;
import com.github.amortaza.hellina_j2d.CWindow;
import com.github.amortaza.hellina_j2d.Draw;

import java.awt.*;


public class GraphicsDevice implements IGraphicsDevice {
    CWindow window;
    IDraw draw;

    public GraphicsDevice( CWindow jwindow ) {
        this.window = jwindow;

        draw = new Draw( jwindow.canvas );
    }

    // physical screen
    public Dimension getScreenDims() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    // window
    public Rectangle getWindowBounds() {
        return window.getBounds();
    }

    public void setWindowBounds( int x, int y, int width, int height ) {
        window.setBounds( x, y, width, height );
    }

    public void setMinimized( boolean isMinimizedDesired ) {
        System.out.println( "setMinimized not implemented" );
    }

    public void isMinimized() {
        System.out.println( "isMinimized not implemented" );
    }

    public void setCursor( Cursor cursor ) {
        window.canvas.setCursor( cursor );

        Toolkit.getDefaultToolkit().sync();
    }

    // get the drawing intf so client and draw things.
    public IDraw getIDraw() {
        return draw;
    }
}

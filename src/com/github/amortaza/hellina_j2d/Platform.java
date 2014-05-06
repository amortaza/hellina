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

import com.github.amortaza.hellina.device.IGraphicsDevice;
import com.github.amortaza.hellina.device.IKeyDevice;
import com.github.amortaza.hellina.device.IMouseDevice;
import com.github.amortaza.hellina.intf.IPlatform;
import com.github.amortaza.hellina.listener.IKeyListener;
import com.github.amortaza.hellina.listener.IMouseListener;
import com.github.amortaza.hellina.listener.ITickListener;
import com.github.amortaza.hellina.listener.IWindowListener;
import com.github.amortaza.hellina_j2d.device.GraphicsDevice;
import com.github.amortaza.hellina_j2d.device.KeyDevice;
import com.github.amortaza.hellina_j2d.device.MouseDevice;

public class Platform implements IPlatform {
    CWindow window;

    ITickListener tickListener;

    MouseDevice mouseDevice;
    KeyDevice keyDevice;
    GraphicsDevice graphicsDevice;

    public void init(
            int x, int y, int width, int height, // width and height (in pixels) of the output window...
            IWindowListener windowListener,
            IMouseListener mouseListener,   // mouse listener is how the implemented platform will tell client what the mouse is doing
            IKeyListener keyListener,     // dito with the keyboard
            ITickListener tickListener )   // the application begins after all the setup and initialization when the
    {
        this.tickListener = tickListener;

        window = new CWindow( x, y, width, height, windowListener, mouseListener, keyListener );

        // wait for graphics to catch up
        while ( window.canvas.offBuffer == null || window.canvas.offG == null ) {
            try {
                Thread.sleep( 100 );
            }
            catch ( Exception e ) {
            }
        }

        // create the devices
        mouseDevice = new MouseDevice();
        keyDevice = new KeyDevice();
        graphicsDevice = new GraphicsDevice( window );

        // call the post init methods
        tickListener.afterInit( this );
    }

    public IMouseDevice getMouseDevice() {
        return mouseDevice;
    }

    public IKeyDevice getKeyDevice() {
        return keyDevice;
    }

    public IGraphicsDevice getGraphicsDevice() {
        return graphicsDevice;
    }

    /*
    *  the application begins after all the setup and initialization when the
    *  startLooping() function is called below...every loop the tick call back is fired.
    */
    public void startLooping() {
        new TickThread( tickListener ).start();
    }
}

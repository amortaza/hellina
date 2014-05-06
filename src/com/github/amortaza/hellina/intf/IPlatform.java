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

// any class which wants to be the rendering or graphics engine for a bellina type gui has to provide
// an implementation of IPlatform
// a defautl Java2D based platform is provided named Platform
// a future one may include OpenGL/Windows based or even an X-Windows based...hope I am alive to see it...

import com.github.amortaza.hellina.device.IGraphicsDevice;
import com.github.amortaza.hellina.device.IKeyDevice;
import com.github.amortaza.hellina.device.IMouseDevice;
import com.github.amortaza.hellina.listener.IKeyListener;
import com.github.amortaza.hellina.listener.IMouseListener;
import com.github.amortaza.hellina.listener.ITickListener;
import com.github.amortaza.hellina.listener.IWindowListener;

public interface IPlatform {
    void init(
            int x, int y,                       // use -1, -1 to use a default palcement scheme
            int width, int height,              // width and height (in pixels) of the output window...

            IWindowListener windowListener,
            IMouseListener mouseListener,    // mouse listener is how the implemented platform will tell client what the mouse is doing
            IKeyListener keyListener,      // dito with the keyboard
            ITickListener tickListener );   // the application begins after all the setup and initialization when the
    // startLooping() function is called below...every loop the tick call back is fired.

    IKeyDevice getKeyDevice();

    IMouseDevice getMouseDevice();

    IGraphicsDevice getGraphicsDevice();

    /*
     *  the application begins after all the setup and initialization when the
     *  startLooping() function is called below...every loop the tick call back is fired.
     */
    void startLooping();
}


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

package com.github.amortaza.hellina.device;

import com.github.amortaza.hellina.intf.IDraw;

import java.awt.*;

public interface IGraphicsDevice {
    // physical screen
    Dimension getScreenDims();

    // window
    Rectangle getWindowBounds();

    void setWindowBounds( int x, int y, int width, int height );

    // maximize is not necessary because, minimize also means 'give up cpu cycles'
    // minimize is NOT the same as setting widht/height to 0 because no resize calculations are given
    // whereas maximize IS equivalent to set size MAX, MAX

    // setMinimized( true )    - minimize window
    // setMinimized( false )   - unminimize window, if it was minimized else do nothing
    void setMinimized( boolean isMinimizedDesired );

    void isMinimized();

    // future - fullscreen
    // void    setFullScreen( boolean isFullscreenDesired, int screenWidth, int screenHeight );
    // boolean isFullScreen();

    // mouse cursor
    void setCursor( Cursor cursor );

    IDraw getIDraw();   // get the drawing intf so client and draw things.
}


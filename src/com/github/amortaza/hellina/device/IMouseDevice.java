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

import com.github.amortaza.hellina.adt.Point;

public interface IMouseDevice {
    Point getPosition();

    // success
    boolean setPosition( int x, int y );

    // true means pressed
    boolean getLeftButtonState();

    boolean getMiddleButtonState();

    boolean getRightButtonState();

    // success
    boolean setLeftButtonState( boolean state );

    boolean setMiddleButtonState( boolean state );

    boolean setRightButtonState( boolean state );

    boolean scrollUp();

    boolean scrollDown();

    boolean capture();

    boolean release();
}


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

import com.github.amortaza.hellina.adt.Point;
import com.github.amortaza.hellina.device.IMouseDevice;

public class MouseDevice implements IMouseDevice {
    public Point getPosition() {
        System.out.println( "not implemented" );
        return null;
    }

    public boolean setPosition( int x, int y ) {
        System.out.println( "not implemented" );
        return false;
    }

    public boolean getLeftButtonState() {
        System.out.println( "not implemented" );
        return false;
    }

    public boolean getMiddleButtonState() {
        System.out.println( "not implemented" );
        return false;
    }

    public boolean getRightButtonState() {
        System.out.println( "not implemented" );
        return false;
    }

    public boolean setLeftButtonState( boolean state ) {
        System.out.println( "not implemented" );
        return false;
    }

    public boolean setMiddleButtonState( boolean state ) {
        System.out.println( "not implemented" );
        return false;
    }

    public boolean setRightButtonState( boolean state ) {
        System.out.println( "not implemented" );
        return false;
    }

    public boolean scrollUp() {
        System.out.println( "not implemented" );
        return false;
    }

    public boolean scrollDown() {
        System.out.println( "not implemented" );
        return false;
    }

    public boolean capture() {
        System.out.println( "not implemented" );
        return false;
    }

    public boolean release() {
        System.out.println( "not implemented" );
        return false;
    }
}

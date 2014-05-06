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


import com.github.amortaza.hellina.device.IKeyDevice;

public class KeyDevice implements IKeyDevice {
    public boolean getState( int code ) {
        System.out.println( "getState not implemented" );
        return false;
    }

    public boolean setState( int code ) {
        System.out.println( "setState not implemented" );
        return false;
    }
}

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

package com.github.amortaza.hellina.listener;

public interface IMouseListener {

    // cursor
    void onMove( int x, int y );

    // left button
    void onLeftDown( int x, int y );

    void onLeftUp( int x, int y );

    // middle button
    void onMiddleDown( int x, int y );

    void onMiddleUp( int x, int y );

    // right button
    void onRightDown( int x, int y );

    void onRightUp( int x, int y );

    // Note symantics: right-down means right-button pressed down, but scroll down does NOT mean scroll pressed down
    // it means the scroll whell was ROLLED down (similarly for scroll up)
    // scroll button
    void onScrollDown( int x, int y );

    void onScrollUp( int x, int y );
}


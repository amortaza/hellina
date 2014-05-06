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

import com.github.amortaza.hellina.listener.ITickListener;

import java.awt.*;

public class TickThread extends Thread {
    private Robot robot;
    private ITickListener tickListener;

    public TickThread( ITickListener tickListener ) {
        try {
            robot = new Robot();
            this.tickListener = tickListener;
        }
        catch ( AWTException e ) {
            throw new RuntimeException( e.getMessage() );
        }
    }

    public void run() {
        while ( true ) {
            robot.waitForIdle();

            tickListener.onTick();

            try {
                sleep( 1 );
            }
            catch ( Exception e ) {
            }
        }
    }
}

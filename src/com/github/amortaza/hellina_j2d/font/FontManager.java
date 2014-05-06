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

package com.github.amortaza.hellina_j2d.font;

import java.awt.*;
import java.util.*;

public class FontManager {
    private static Map<FontKey, Font> fontByFontKey = new HashMap<FontKey, Font>();

    public static Font getFont( FontKey key ) {
        Font font = fontByFontKey.get( key );

        if ( font == null ) {
            int style;

            if ( key.fontstyle == FontKey.PLAIN )
                style = Font.PLAIN;
            else if ( key.fontstyle == FontKey.BOLD )
                style = Font.BOLD;
            else
                style = Font.ITALIC;

            font = new Font( key.fontname, style, ( int ) key.fontsize );

            fontByFontKey.put( key, font );
        }

        return font;
    }
}

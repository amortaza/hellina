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

public class FontKey {
    public static final int PLAIN = 0;
    public static final int ITALIC = 1;
    public static final int BOLD = 2;

    public final String fontname;
    public final int fontstyle;
    public final float fontsize;
    public final int hashcode;

    public FontKey( String fontname, int fontstyle, float fontsize ) {
        this.fontname = fontname;
        this.fontstyle = fontstyle;
        this.fontsize = fontsize;

        hashcode = ( int ) ( this.fontname.hashCode() + this.fontstyle * 100 + this.fontsize );
    }

    public int hashCode() {
        return hashcode;
    }

    public boolean equals( Object other ) {
        if ( this == other )
            return true;

        FontKey key = ( FontKey ) other;

        return key.fontsize == this.fontsize
                && key.fontstyle == this.fontstyle
                && key.fontname.equalsIgnoreCase( this.fontname );
    }

    public static int toStyleId( String fontstyle ) {
        fontstyle = fontstyle.trim().toLowerCase();

        if ( fontstyle.equals( "plain" ) )
            return PLAIN;

        if ( fontstyle.equals( "bold" ) )
            return BOLD;

        return ITALIC;
    }
}

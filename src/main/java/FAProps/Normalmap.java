/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FAProps;

import java.io.IOException;

/**
 *
 * @author bhofmann
 */
public class Normalmap {

    public char[] normalmap;

    public Normalmap(MapReader map) throws IOException {
        int i = 0, length = map.getInt32();
        normalmap = new char[length];
        while (i < length) {
            normalmap[i] = map.getChar();
            i++;
        }
    }
}

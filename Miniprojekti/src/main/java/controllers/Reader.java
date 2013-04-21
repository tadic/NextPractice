/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author mikahutt
 */
public class Reader {    

    public Reader() {
    }
    /**
     * Read content of the file
     * @param fileName
     * @return file content
     */
    public String fromFile(String fileName) {  
        File file = new File(fileName);
        String content = null;
        try {
           java.io.FileReader reader = new java.io.FileReader(file);
           char[] chars = new char[(int) file.length()];
           reader.read(chars);
           content = new String(chars);
           reader.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
        return content;
    }
}

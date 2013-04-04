/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Inproceedings;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author mikahutt
 */
public class FileSaver {
    private Converter converter;
    
    public FileSaver(Converter converter) {
        this.converter = converter;
    }
    
    public void saveToFile(String filename,Inproceedings inproceeding) throws IOException {
        FileWriter filewriter = new FileWriter(filename);
        filewriter.write(converter.toBibTex(inproceeding));
        filewriter.close();
        
    }
    
}

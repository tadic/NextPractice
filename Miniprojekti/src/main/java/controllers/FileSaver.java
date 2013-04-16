/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Inproceedings;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author mikahutt
 */
public class FileSaver {
    private Converter converter;
    
    /**
     * 
     * @param converter converts inproceeding to bibtex
     */
    public FileSaver(Converter converter) {
        this.converter = converter;
    }
    
    /**
     * Saves inproceeding to file as bibtex
     * @param filename
     * @param inproceeding
     * @throws IOException 
     */
    public void saveToFile(String filename,Inproceedings inproceeding) throws IOException {
        FileWriter filewriter = new FileWriter(filename);
        filewriter.write(converter.toBibTex(inproceeding));
        filewriter.close();
        
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Reference;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

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
     * @param ref
     * @throws IOException 
     */
    public void saveToFile(String filename,Reference ref) throws IOException {
        FileWriter filewriter = new FileWriter(filename);
        filewriter.write(converter.toBibTex(ref));
        filewriter.close();
        
    }
    
    public void saveToFile(String filename, List<Reference> references) throws IOException {
        FileWriter filewriter = new FileWriter(filename, true);
        for (Reference reference : references) {
            filewriter.write(converter.toBibTex(reference));
        }
        filewriter.close();
    }
    
}

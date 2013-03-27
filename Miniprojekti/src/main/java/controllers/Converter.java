
package controllers;

import entity.Inproceedings;

/**
 * Converts a Reference Instance to bibTex form.
 * 
 */
public class Converter {
    /**
     * 
     * @param inpro an Inproceedings instance.
     * @return bibTex form of Inproceedings instance.
     */
    public String toBibTex(Inproceedings inpro){
        StringBuilder text = new StringBuilder("@inproceedings{");
        text.append(inpro.getAuthor().substring(0,3)).append(":").append(inpro.getYear()); 
        text.append(",\n    ").append("author = {").append(inpro.getAuthor());
        text.append("},\n    ").append("title = {").append(inpro.getTitle());
        text.append("},\n    ").append("booktitle = {").append(inpro.getBooktitle());
        text.append("},\n    ").append("year = {").append(inpro.getYear());
        text.append("}\n}");
        return text.toString();
    }
    
}

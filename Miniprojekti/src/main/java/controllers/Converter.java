
package controllers;

import entity.Inproceedings;
import java.util.Calendar;

/**
 * Converts a Reference Instance to bibTex form.
 */
public class Converter {
    /**
     * Creates bibTeX form of Inproceedings instance.
     * @param inpro an Inproceedings instance.
     * @return bibTex form of Inproceedings instance.
     */
    public String toBibTex(Inproceedings inpro){ 
        if (!isRegular(inpro)){
            throw new IllegalArgumentException("Ilegal value of Reference");
        }
        if (inpro.getReferenceId()==null || inpro.getReferenceId().trim().length()==0){
            inpro.setReferenceId(inpro.getAuthor().substring(0,2) + ":" + inpro.getYear());
        }
        StringBuilder text = new StringBuilder("@inproceedings{");
        text.append(repSpecChars(inpro.getReferenceId())); 
        text.append(",\n    ").append("author = {").append(repSpecChars(inpro.getAuthor()));
        text.append("},\n    ").append("title = {").append(repSpecChars(inpro.getTitle()));
        text.append("},\n    ").append("booktitle = {").append(repSpecChars(inpro.getBooktitle()));
        text.append("},\n    ").append("year = {").append(inpro.getYear());
        if (inpro.getEditor()!=null && inpro.getEditor().trim().length()>0){
            text.append("},\n    ").append("editor = {").append(inpro.getEditor());
        }
        if (inpro.getVolumeNumber()!=null && inpro.getVolumeNumber().trim().length()>0){
            text.append("},\n    ").append("volume/number = {").append(inpro.getVolumeNumber());
        }
        if (inpro.getSeries()!=null && inpro.getSeries().trim().length()>0){
            text.append("},\n    ").append("series = {").append(inpro.getSeries());
        }
        if (inpro.getPages()!=null && inpro.getPages().trim().length()>0){
            text.append("},\n    ").append("pages = {").append(inpro.getPages());
        }
        if (inpro.getAddress()!=null && inpro.getAddress().trim().length()>0){
            text.append("},\n    ").append("address = {").append(inpro.getAddress());
        }
        if (inpro.getMonth()!=null && inpro.getMonth().trim().length()>0){
            text.append("},\n    ").append("month = {").append(inpro.getMonth());
        }
        if (inpro.getOrganization()!=null && inpro.getOrganization().trim().length()>0){
            text.append("},\n    ").append("organization = {").append(inpro.getOrganization());
        }
        if (inpro.getPublisher()!=null && inpro.getPublisher().trim().length()>0){
            text.append("},\n    ").append("publisher = {").append(inpro.getPublisher());
        }
        if (inpro.getNote()!=null && inpro.getNote().trim().length()>0){
            text.append("},\n    ").append("note = {").append(inpro.getNote());
        }
        if (inpro.getKey()!=null && inpro.getKey().trim().length()>0){
            text.append("},\n    ").append("key = {").append(inpro.getKey());
        }
        // plus optional fields
        //editor, volume/number, series, pages, address, month, organization, publisher, note, key
        text.append("}\n}");
        return text.toString();
    }
    
    public Inproceedings toInproceedings(String bibTex){
        Inproceedings inpro = new Inproceedings();
        
        
        return inpro;
    }
    /**
     * Search and replace special characters.
     * @param word is text which should be modified.
     * @return text with replaced special characters.
     */
    private boolean isRegular(Inproceedings in){
        if (in==null || in.getAuthor()==null || in.getBooktitle()==null ||in.getTitle()==null){
            return false;
        }
        if (in.getAuthor().trim().length()<2 || in.getBooktitle().trim().length()==0 ||
                in.getTitle().trim().length()==0||in.getYear()>Calendar.getInstance().get(Calendar.YEAR)||
                in.getYear()<0){
            return false;
        }
        return true;
    }
    
    private String repSpecChars(String word){
        StringBuilder str = new StringBuilder();
        for (char c: word.toCharArray()){
            switch (c){
                case 'Ä': str.append("\\\"{A}"); break;
                case 'Ö': str.append("\\\"{O}"); break;
                case 'Å': str.append("\\\"AA");  break;
                case 'ä': str.append("\\\"{a}"); break;
                case 'ö': str.append("\\\"{o}"); break;
                case 'å': str.append("\\\"aa");  break;
                default : str.append(c);       break;
            }         
        }
        return str.toString();
    }
    
}

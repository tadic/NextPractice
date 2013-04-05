
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
        text.append("}\n}");
        // plus optional fields
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

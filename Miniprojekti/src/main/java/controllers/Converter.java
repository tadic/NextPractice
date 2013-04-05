
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
        String apu = inpro.getAuthor();
        if (!isRegular(inpro)){
            throw new IllegalArgumentException("Ilegal value of Reference");
        }
        apu = apu.substring(0,2);
        StringBuilder text = new StringBuilder("@inproceedings{");
        text.append(repSpecChars(apu)).append(":").append(inpro.getYear()); 
        text.append(",\n    ").append("author = {").append(repSpecChars(inpro.getAuthor()));
        text.append("},\n    ").append("title = {").append(repSpecChars(inpro.getTitle()));
        text.append("},\n    ").append("booktitle = {").append(repSpecChars(inpro.getBooktitle()));
        text.append("},\n    ").append("year = {").append(inpro.getYear());
        text.append("}\n}");
        
        return text.toString();
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

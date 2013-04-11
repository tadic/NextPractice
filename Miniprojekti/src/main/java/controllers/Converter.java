
package controllers;

import entity.Inproceedings;
import entity.Reference;
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
    public String toBibTex(Reference inpro){ 
        if (!isRegular(inpro)){
            throw new IllegalArgumentException("Ilegal value of Reference");
        }
        if (inpro.getReferenceId()==null || inpro.getReferenceId().trim().length()==0){
            inpro.setReferenceId(inpro.getFieldValue("author").substring(0,2) + ":" + inpro.getFieldValue("year"));
        }
        StringBuilder text = new StringBuilder("@inproceedings{");
        text.append(repSpecChars(inpro.getReferenceId())); 
        text.append(",\n    ").append("author = {").append(repSpecChars(inpro.getFieldValue("author")));
        text.append("},\n    ").append("title = {").append(repSpecChars(inpro.getFieldValue("title")));
        text.append("},\n    ").append("booktitle = {").append(repSpecChars(inpro.getFieldValue("booktitle")));
        text.append("},\n    ").append("year = {").append(inpro.getFieldValue("year"));
        if (inpro.getFieldValue("editor")!=null && inpro.getFieldValue("editor").trim().length()>0){
            text.append("},\n    ").append("editor = {").append(inpro.getFieldValue("editor"));
        }
        if (inpro.getFieldValue("editor")!=null && inpro.getFieldValue("volume/number").trim().length()>0){
            text.append("},\n    ").append("volume/number = {").append(inpro.getFieldValue("volume/number"));
        }
        if (inpro.getFieldValue("series")!=null && inpro.getFieldValue("series").trim().length()>0){
            text.append("},\n    ").append("series = {").append(inpro.getFieldValue("series"));
        }
        if (inpro.getFieldValue("pages")!=null && inpro.getFieldValue("pages").trim().length()>0){
            text.append("},\n    ").append("pages = {").append(inpro.getFieldValue("pages"));
        }
        if (inpro.getFieldValue("address")!=null && inpro.getFieldValue("address").trim().length()>0){
            text.append("},\n    ").append("address = {").append(inpro.getFieldValue("address"));
        }
        if (inpro.getFieldValue("month")!=null && inpro.getFieldValue("month").trim().length()>0){
            text.append("},\n    ").append("month = {").append(inpro.getFieldValue("month"));
        }
        if (inpro.getFieldValue("organization")!=null && inpro.getFieldValue("organization").trim().length()>0){
            text.append("},\n    ").append("organization = {").append(inpro.getFieldValue("organization"));
        }
        if (inpro.getFieldValue("publisher")!=null && inpro.getFieldValue("publisher").trim().length()>0){
            text.append("},\n    ").append("publisher = {").append(inpro.getFieldValue("publisher"));
        }
        if (inpro.getFieldValue("note")!=null && inpro.getFieldValue("note").trim().length()>0){
            text.append("},\n    ").append("note = {").append(inpro.getFieldValue("note"));
        }
        if (inpro.getFieldValue("key")!=null && inpro.getFieldValue("key").trim().length()>0){
            text.append("},\n    ").append("key = {").append(inpro.getFieldValue("key"));
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
    private boolean isRegular(Reference in){
        if (in==null || in.getFieldValue("author")==null || in.getFieldValue("booktitle")==null ||in.getFieldValue("title")==null){
            return false;
        }
        if (in.getFieldValue("author").trim().length()<2 || in.getFieldValue("booktitle").trim().length()==0 ||
                in.getFieldValue("title").trim().length()==0||Integer.parseInt(in.getFieldValue("year"))>Calendar.getInstance().get(Calendar.YEAR)||
                Integer.parseInt(in.getFieldValue("year"))<0){
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

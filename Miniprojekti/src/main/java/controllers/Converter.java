
package controllers;

import entity.Field;
import entity.Inproceedings;
import entity.Reference;
import java.util.Calendar;
import java.util.List;

/**
 * Converts a Reference Instance to bibTex form.
 */
public class Converter {
    /**
     * Creates bibTeX form of Inproceedings instance.
     * @param inpro an Inproceedings instance.
     * @return bibTex form of Inproceedings instance.
     */
    public String toBibTex(Reference ref){ 
        StringBuilder text = new StringBuilder("@" + ref.getReferenceType() + "{");
        text.append(repSpecChars(ref.getFieldValue("referenceId"))); 
        for (int i=1; i<ref.getFields().size(); i++){
            if (ref.getFields().get(i).getValue().trim().length()>0){
                text.append(",\n    ").append(ref.getFields().get(i).getKey()).append(" = {").
                        append(repSpecChars(ref.getFields().get(i).getValue())).append("}");
            }
        }
        text.append("\n}");
        return text.toString();
    }
    
    public Reference toreference(String bibTex){
        return null;
    }
    
    /**
     * This method replace special characters ä, ö ja ö with appropriate text.
     * @param word
     * @return replaced text without spec characters.
     */
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

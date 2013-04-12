
package controllers;

import entity.Field;
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
    public String toBibTex(Reference ref){ 
        if (!isRegular(ref)){
            throw new IllegalArgumentException("Illegal value of Reference");
        }
        if (ref.getReferenceId()==null || ref.getReferenceId().trim().length()==0){
            ref.setReferenceId(ref.getFieldValue("author").substring(0,2) + ":" + ref.getFieldValue("year"));
        }
        StringBuilder text = new StringBuilder("@" + ref.getReferenceName() + "{");
        text.append(repSpecChars(ref.getReferenceId())); 
        for (Field field: ref.getFields()){
            if (field.getValue()!=null && field.getValue().trim().length()>0){
                text.append(",\n    ").append(field.getKey()).append(" = {").append(repSpecChars(field.getValue())).append("}");
            }
        }
        text.append("\n}");
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
    public boolean isRegular(Reference ref){
        if (ref==null){
            return false;                   // if ref parameter is null
        }
        for (Field field: ref.getFields()){
            if (field.getValue()==null) {
                return false;               // if field doesn't have value or it is shorter then 2
            }
            if ((field.isRequired() && field.getValue().length()<2)){
                return false;
            }
        }
        if (ref.getFieldValue("year").length()>0){      // if year field exists
            return checkYear(ref.getFieldValue("year"));
        }
        return true;
    }
    /**
     * Checks if year field is correct.
     * @param year as String
     * @return boolean parameter true if year is correct, else false.
     */
    public boolean checkYear(String year){                  //Checking if year field is correct
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i=0; i<year.length(); i++){
            if (year.charAt(i)>'9' || year.charAt(i)<'0'){ 
                return false;       // if year is not a number
            }
        }
        if ((Integer.parseInt(year) > currentYear)|| 
            (Integer.parseInt(year) < 0)){
            return false;       // if (year < 0) or (year > currentYear)
        }
        return true;
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

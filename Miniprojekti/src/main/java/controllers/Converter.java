
package controllers;

import entity.FType;
import entity.Field;
import entity.Inproceedings;
import entity.Reference;
import entity.ReferenceFactory;
import java.util.ArrayList;
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
        text.append(repSpecChars(ref.getFieldValue(FType.referenceId))); 
        for (int i=1; i<ref.getFields().size(); i++){
            if (ref.getFields().get(i).getValue().trim().length()>0){
                text.append(",\n    ").append(ref.getFields().get(i).getKey().toString()).append(" = {").
                        append(repSpecChars(ref.getFields().get(i).getValue())).append("}");
            }
        }
        text.append("\n}\n");
        return text.toString();
    }
    
    /*public Reference toReference(String bibTex){
        return null;
    }*/
    
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
    
    private ArrayList<String> getWords(String filter){
        ArrayList<String> list = new ArrayList<String>();
        System.out.println("Filer: " + filter);
        int j=0;
        for (int i=0; i<filter.length(); i++){
            if (filter.charAt(i)==' '){
                list.add(filter.substring(j,i));
                System.out.println(":" + filter.substring(j,i));
                j=i+1;
            }
        }
        list.add(filter.substring(j,filter.length()));
        return list;
    }



    public ArrayList<Reference> toReferenceList(String fileContent) {
        ArrayList<Reference> list = new ArrayList<Reference>();
        //System.out.println("All content\n" + fileContent + "\n-------------");
        String referenceAsText = null;
        int j= fileContent.indexOf("@");
        int i=j+1;
        while (i<fileContent.length()){
            if (fileContent.charAt(i)=='@'){
                referenceAsText = fileContent.substring(j, i);
                //System.out.println("odvojena ref: \n\n" +referenceAsText);
                Reference r = toReference(referenceAsText);
                if (r!=null){
                    list.add(r);
                }
                j=i;
            }
            i++;
        }
        referenceAsText = fileContent.substring(j, i);
        Reference r = toReference(referenceAsText);
        if (r!=null){
            list.add(r);
        }
        return list;
        
    }
    
       public String getFieldNameFromLine(String l){
        String line = l.trim();
        for (int i=0; i<line.length(); i++){
            if (line.charAt(i)==' ' || line.charAt(i)=='='){
                return line.substring(0, i);
            }
        }
        return null;
    }
    public String getFieldValueFromLine(String l){
        String line = l.trim();
        int from=0;
        int to = 0;
        for (int i=0; i<line.length(); i++){
            if (line.charAt(i)=='{'){
                from=i+1;
            }
        }
        for (int i=line.length()-1; i>from; i--){
            if (line.charAt(i)=='}'){
                to=i;
            }
        }
        if (from<to){
            return line.substring(from, to);
        }
        return null;
    }
    private String getReferenceType(String text){
        int i=1; 
        text = text.trim();
        if (text.charAt(0)!='@'){
           return null;
        }
        while (text.charAt(i)!=' ' && text.charAt(i)!='{' && i<text.length() ){
            i++;
        }
        if (text.charAt(i)==' ' || text.charAt(i)=='{'){
            return text.substring(1, i);
        }
        return null;
            
    }
        public String returnSpecChars(String text){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<text.length(); i++){
            if (text.length()>(i+3) && text.charAt(i)== '\\' && text.charAt(i+1)=='"'){
                    if (text.substring(i+2, i+4).equals("AA")){
                        sb.append('Å');
                        i+=3;
                    } else if (text.substring(i+2, i+4).equals("aa")){
                        sb.append('å');
                        i+=3;
                    } else if (text.length()==(i+4)){
                        sb.append(text.charAt(i));
                    } else  if (text.length()> (i+4)){  
                        if (text.substring(i+2, i+5).equals("{A}")){
                            sb.append('Ä');
                            i+=4;
                        } else if (text.substring(i+2, i+5).equals("{a}")){
                            sb.append('ä');
                            i+=4;
                        } else if (text.substring(i+2, i+5).equals("{O}")){
                            sb.append('Ö');
                            i+=4;
                        } else if (text.substring(i+2, i+5).equals("{o}")){
                            sb.append('ö');
                            i+=4;
                        } else {
                            sb.append(text.charAt(i));
                        }
                    }
            } else {
                sb.append(text.charAt(i));
            }
        }
        return sb.toString();
    }
        
    public ArrayList<String> getNonEmptyLines(String text){
        ArrayList<String> list = new ArrayList<String>();
        int from = 0;
        int to = 0;
        String txt = returnSpecChars(text.trim());
        while (txt.length()> to){
            if (txt.charAt(to)=='\n'){
                if (from!=to){
                    list.add(txt.substring(from, to));
                }
                from = to+1;
            }
            to++;
        }
        if (from!=to){
            list.add(txt.substring(from, to));
        }
        return list;
    }
    
    public Reference toReference(String bibTex){
        String refType = getReferenceType(bibTex);
        if (refType==null){
            return null;
        }
        Reference r = new ReferenceFactory().createReference(refType);
        ArrayList<String> lines = getNonEmptyLines(bibTex);
        int j=0;
        while (bibTex.charAt(j)!='{' && j<bibTex.length()){
            j++;
        }
        if (j==bibTex.length()){
            return null;
        }
        String apu = lines.get(0).trim().substring(j).trim().substring(1);
        apu = apu.substring(0, apu.length()-1);
        r.getFields().get(0).setValue(apu);
        for (int i=1; i<(lines.size()-1); i++){
            String fieldName = getFieldNameFromLine(lines.get(i));
            String fieldValue = getFieldValueFromLine(lines.get(i));
            r.setFieldValue(FType.valueOf(fieldName), fieldValue);
        }
        return r;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author jarno
 */
public abstract class Reference implements Serializable {

    protected int id;
    protected List<Field> fields;
    protected List <String> tags;
    protected String refType;

    protected abstract void initMyFields();

    public Reference() {
        initMyFields();
    }
    
    public Reference(List<Field> fields) {
        this.fields = fields;
    }    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFieldValue(FType fieldName, String fieldValue) {

        for (Field field : fields) {
            if (field.getKey().equals(fieldName)) {
                field.setValue(fieldValue);
                return;
            }
        }
    }

    public String getFieldValue(FType fieldName) {
        for (Field field : fields) {
            if (field.getKey().equals(fieldName)) {
                return field.getValue();
            }
        }
        return null;
    }

    public List<Field> getFields() {
        return fields;
    }
    
    public List<String> getTags(){
        return tags;
    }
    
    public String getReferenceType(){
        return refType;
    }
    
     private String generateNextValueForRefId(String refId){
        if (refId.length()==7){
            return refId + "_10";
        }
        //  if not length()= 7, means that it was already generated 
        //  which means that length() is 10
        String apu = refId.substring(0, 8);
        int n = Integer.parseInt(refId.substring(8, 10));
        n++;
        return apu + n;
    }
    
    private boolean isInTheList(List<Reference> list){
        if (list==null){
            return false;
        }
        for (Reference r: list){    
            if (this.getFieldValue(FType.referenceId).trim().equals(r.getFieldValue(FType.referenceId).trim())){
                 return true;
            }
        }
        return false;
    }
    /**
     * Check if referenceIf is correct. 
     * ReferenceId is correct if it is not given by user (then method gives default name), or
     * if value doesn't exist already in the list.
     * @param ref Reference object which referenceId value is to be checked.
     * @param list List of references in which all object must have different referenceId.
     * @return boolean True, if doesn't repeat in the list. False if it is already in the list.
     */
    private boolean checkReferenceId(List<Reference> list){
        if (this.getFieldValue(FType.referenceId).trim().length()==0){
            this.setFieldValue(FType.referenceId,this.getFieldValue(FType.author).substring(0,2) + ":" + this.getFieldValue(FType.year));
            while (isInTheList(list)){
                this.setFieldValue(FType.referenceId, generateNextValueForRefId(this.getFieldValue(FType.referenceId)));           
            }
            return true;
        } else {
            return (!isInTheList(list));
        }
    }
    /**
     * Search and replace special characters.
     * @param word is text which should be modified.
     * @return text with replaced special characters.
     */
    public boolean isRegular(List<Reference> list){
        if (!checkReferenceId(list)){
            throw new IllegalArgumentException("ReferenceId already exists!");
        }
        for (Field field: this.getFields()){
            if (field.isRequired() && field.getValue().trim().length()==0){
                throw new IllegalArgumentException("Must fill all required fields!");
            }
            if (field.isRequired() && field.getValue().length()<2){
                throw new IllegalArgumentException("Fields lenght must be greather then 1");
            }
        }
        if (!checkYear()){
            throw new IllegalArgumentException("Check format of year field");
        }
        return true;
    }
    
       
    /**
     * Checks if year field is correct.
     * @param year as String
     * @return boolean parameter true if year is correct, else false.
     */
    private boolean checkYear(){ 
        String year = this.getFieldValue(FType.year);
        if (year.trim().length()==0){
            return false;
        }
        for (int i=0; i<year.trim().length(); i++){
            if (year.charAt(i)>'9' || year.charAt(i)<'0'){ 
                return false;       // if year is not a number
            }
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if ((Integer.parseInt(year) > currentYear)|| 
            (Integer.parseInt(year) < 0)){
            return false;       // if (year < 0) or (year > currentYear)
        }
        return true;
    }
    
    
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reference other = (Reference) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}

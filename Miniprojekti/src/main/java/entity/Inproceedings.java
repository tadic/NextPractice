/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
//editor, volume/number, series, pages, address, month, organization, publisher, note, key

import java.util.ArrayList;
import java.util.List;

public class Inproceedings extends BaseEntity implements Reference {

    private String referenceId;
    private List<Field> fields;
    
    public Inproceedings(List<Field> fields) {
        this.fields = fields;
    }

    public Inproceedings() {
        fields = myFields();

    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    @Override
    public void setFieldValue(String fieldName, String fieldValue) {

        for (Field field : fields) {
            if (field.getKey().equals(fieldName)) {
                field.setValue(fieldValue);
                return;
            }
        }
    }

    @Override
    public String getFieldValue(String fieldName) {
        
        for (Field field : fields) {
            
            if (field.getKey().equals(fieldName)) {
                return field.getValue();
            }
        }
        return null;
    }

    @Override
    public List<Field> getFields() {
        return fields;
    }

    private List<Field> myFields() {
        List<Field> myFields = new ArrayList<Field>();
        myFields.add(new Field("author", true));
        myFields.add(new Field("title", true));
        myFields.add(new Field("booktitle", true));
        myFields.add(new Field("year", true));

        myFields.add(new Field("editor", false));
        myFields.add(new Field("volume/number", false));
        myFields.add(new Field("series", false));
        myFields.add(new Field("pages", false));
        myFields.add(new Field("address", false));
        myFields.add(new Field("month", false));
        myFields.add(new Field("organization", false));
        myFields.add(new Field("publisher", false));
        myFields.add(new Field("note", false));
        myFields.add(new Field("key", false));

        return myFields;
    }
}

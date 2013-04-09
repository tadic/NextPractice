/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
//editor, volume/number, series, pages, address, month, organization, publisher, note, key

import java.util.ArrayList;
import java.util.List;

public class Inproceedings extends BaseEntity {

    private String referenceId;
    private List<Field> fields;
//    private String author;
//    private String title;
//    private String booktitle;
//    private int year;
//    private String editor;
//    private String volumeNumber;
//    private String series;
//    private String pages;
//    private String address;
//    private String month;
//    private String organization;
//    private String publisher;
//    private String note;
//    private String key;

    public Inproceedings(List<Field> fields) {
        this.fields = fields;
    }

    public Inproceedings() {
        fields = new ArrayList<Field>();
        fields.add(new Field("author", true));
        fields.add(new Field("title", true));
        fields.add(new Field("booktitle", true));
        fields.add(new Field("year", true));

        fields.add(new Field("editor", false));
        fields.add(new Field("volume/number", false));
        fields.add(new Field("series", false));
        fields.add(new Field("pages", false));
        fields.add(new Field("address", false));
        fields.add(new Field("month", false));
        fields.add(new Field("organization", false));
        fields.add(new Field("publisher", false));
        fields.add(new Field("note", false));
        fields.add(new Field("key", false));
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setFieldValue(String fieldName, String fieldValue) {

        for (Field field : fields) {
            if (field.getKey().equals(fieldName)) {
                field.setKey(fieldValue);
                return;
            }
        }
    }

    public String getFieldValue(String fieldName) {

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

}

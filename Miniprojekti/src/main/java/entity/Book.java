/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ivantadic
 */
public class Book extends Reference{
     public Book(List<Field> fields) {
        super(fields);
    }

    public Book() {
        super();
    }

    @Override
    protected void initMyFields() {
        refType = "inproceedings";
        fields = myFields();
        tags = new ArrayList<String>();
    }



    private List<Field> myFields() {
        List<Field> myFields = new ArrayList<Field>();
        myFields.add(new Field("referenceId", true));
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

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
        fields = myFields();
    }
    @Override
    protected void initTypeAndTags() {
        tags = new ArrayList<String>();
        refType = "book";
    }



    private List<Field> myFields() {
        List<Field> myFields = new ArrayList<Field>();
        myFields.add(new Field(FType.referenceId, true));
        myFields.add(new Field(FType.author, true));
        myFields.add(new Field(FType.title, true));
        myFields.add(new Field(FType.publisher, true));
        myFields.add(new Field(FType.year, true));

        myFields.add(new Field(FType.volume, false));
        myFields.add(new Field(FType.series, false));
        myFields.add(new Field(FType.address, false));
        myFields.add(new Field(FType.edition, false));
        myFields.add(new Field(FType.month, false));
        myFields.add(new Field(FType.note, false));
        myFields.add(new Field(FType.key, false));
        
        

        return myFields;
    }


    
}
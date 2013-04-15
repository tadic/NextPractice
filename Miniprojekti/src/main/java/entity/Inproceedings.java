/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
//editor, volume/number, series, pages, address, month, organization, publisher, note, key

import java.util.ArrayList;
import java.util.List;

public class Inproceedings extends Reference{
    
    public Inproceedings(List<Field> fields) {
        super(fields);
    }

    public Inproceedings() {
        super();
    }


    @Override
    protected void initMyFields() {
        fields = myFields();
    }
    @Override
    protected void initTypeAndTags() {
        tags = new ArrayList<String>();
        refType = "inproceedings";
    }


    private List<Field> myFields() {
        List<Field> myFields = new ArrayList<Field>();
        myFields.add(new Field(FType.referenceId, true));
        myFields.add(new Field(FType.author, true));
        myFields.add(new Field(FType.title, true));
        myFields.add(new Field(FType.booktitle, true));
        myFields.add(new Field(FType.year, true));

        myFields.add(new Field(FType.editor, false));
        myFields.add(new Field(FType.volume, false));
        myFields.add(new Field(FType.series, false));
        myFields.add(new Field(FType.pages, false));
        myFields.add(new Field(FType.address, false));
        myFields.add(new Field(FType.month, false));
        myFields.add(new Field(FType.organization, false));
        myFields.add(new Field(FType.publisher, false));
        myFields.add(new Field(FType.note, false));
        myFields.add(new Field(FType.key, false));

        return myFields;
    }
}

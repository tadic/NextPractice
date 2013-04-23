
package entity;

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
    public String initType() {
        return "inproceedings";
    }


    @Override
    public List<Field> myFields() {
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

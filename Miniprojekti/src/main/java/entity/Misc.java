
package entity;

import java.util.ArrayList;
import java.util.List;


public class Misc extends Reference{
     public Misc(List<Field> fields) {
        super(fields);
    }

    public Misc() {
        super();
    }


    @Override
    public String initType() {
        return "misc";
    }

    @Override
    public List<Field> myFields() {
        List<Field> myFields = new ArrayList<Field>();
        myFields.add(new Field(FType.referenceId, true));
        
        myFields.add(new Field(FType.author, false));
        myFields.add(new Field(FType.title, false));
        myFields.add(new Field(FType.howpublished, false));
        myFields.add(new Field(FType.month, false));
        myFields.add(new Field(FType.year, false));
        myFields.add(new Field(FType.note, false));
        myFields.add(new Field(FType.key, false));
        
        return myFields;
    }
    
}


package entity;

import java.util.ArrayList;
import java.util.List;


public class Unpublished extends Reference{
     public Unpublished(List<Field> fields) {
        super(fields);
    }

    public Unpublished() {
        super();
    }


    @Override
    public String initType() {
        return "unpublished";
    }

    @Override
    public List<Field> myFields() {
        List<Field> myFields = new ArrayList<Field>();
        myFields.add(new Field(FType.referenceId, true));
        myFields.add(new Field(FType.author, true));
        myFields.add(new Field(FType.title, true));
        myFields.add(new Field(FType.note, true));

        myFields.add(new Field(FType.month, false));
        myFields.add(new Field(FType.year, false));
        myFields.add(new Field(FType.key, false));
        
        return myFields;
    }
    
}

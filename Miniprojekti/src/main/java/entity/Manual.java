
package entity;

import java.util.ArrayList;
import java.util.List;


public class Manual extends Reference{
     public Manual(List<Field> fields) {
        super(fields);
    }

    public Manual() {
        super();
    }


    @Override
    public String initType() {
        return "manual";
    }

    @Override
    public List<Field> myFields() {
        List<Field> myFields = new ArrayList<Field>();
        myFields.add(new Field(FType.referenceId, true));
        myFields.add(new Field(FType.title, true));
        
        myFields.add(new Field(FType.author, false));
        myFields.add(new Field(FType.organization, false));
        myFields.add(new Field(FType.address, false));
        myFields.add(new Field(FType.edition, false));
        myFields.add(new Field(FType.month, false));
        myFields.add(new Field(FType.year, false));
        myFields.add(new Field(FType.note, false));
        myFields.add(new Field(FType.key, false));
        
        return myFields;
    }
    
}

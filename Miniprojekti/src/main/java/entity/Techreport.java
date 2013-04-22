
package entity;

import java.util.ArrayList;
import java.util.List;


public class Techreport extends Reference{
     public Techreport(List<Field> fields) {
        super(fields);
    }

    public Techreport() {
        super();
    }


    @Override
    public String initType() {
        return "techreport";
    }

    @Override
    public List<Field> myFields() {
        List<Field> myFields = new ArrayList<Field>();
        myFields.add(new Field(FType.referenceId, true));
        myFields.add(new Field(FType.author, true));
        myFields.add(new Field(FType.title, true));
        myFields.add(new Field(FType.institution, true));
        myFields.add(new Field(FType.year, true));
        
        myFields.add(new Field(FType.type, false));
        myFields.add(new Field(FType.number, false));
        myFields.add(new Field(FType.address, false));
        myFields.add(new Field(FType.month, false));
        myFields.add(new Field(FType.note, false));
        myFields.add(new Field(FType.key, false));
        
        return myFields;
    }
    
}

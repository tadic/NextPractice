
package entity;

import java.util.ArrayList;
import java.util.List;


public class Inbook extends Reference{
     public Inbook(List<Field> fields) {
        super(fields);
    }

    public Inbook() {
        super();
    }


    @Override
    public String initType() {
        return "inbook";
    }

    @Override
    public List<Field> myFields() {
        List<Field> myFields = new ArrayList<Field>();
        myFields.add(new Field(FType.referenceId, true));
        myFields.add(new Field(FType.author, true));
        myFields.add(new Field(FType.pages, true));
        myFields.add(new Field(FType.publisher, true));
        myFields.add(new Field(FType.year, true));
        
        myFields.add(new Field(FType.volume, false));
        myFields.add(new Field(FType.series, false));
        myFields.add(new Field(FType.type, false));
        myFields.add(new Field(FType.address, false));
        myFields.add(new Field(FType.edition, false));
        myFields.add(new Field(FType.month, false));
        myFields.add(new Field(FType.note, false));
        myFields.add(new Field(FType.key, false));
        
        return myFields;
    }
    
}

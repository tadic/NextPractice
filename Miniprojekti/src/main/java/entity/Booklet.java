
package entity;

import java.util.ArrayList;
import java.util.List;

public class Booklet extends Reference{
    
    public Booklet(List<Field> fields) {
        super(fields);
    }

    public Booklet() {
        super();
    }

    @Override
    public String initType() {
        return "booklet";
    }


    @Override
    public List<Field> myFields() {
        List<Field> myFields = new ArrayList<Field>();
        myFields.add(new Field(FType.referenceId, true));
        myFields.add(new Field(FType.title, true));

        myFields.add(new Field(FType.author, false));
        myFields.add(new Field(FType.howpublished, false));
        myFields.add(new Field(FType.address, false));
        myFields.add(new Field(FType.month, false));
        myFields.add(new Field(FType.year, false));
        myFields.add(new Field(FType.note, false));
        myFields.add(new Field(FType.key, false));
        
        return myFields;
    }
 
}

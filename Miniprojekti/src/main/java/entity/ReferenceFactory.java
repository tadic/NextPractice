
package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ReferenceFactory {
    private HashMap<String, List<Field>> types;
    
    
    public ReferenceFactory() {
        types.put("inproceedings", inproceedings());
    }
    
    
    
    public List<Field> getFields(String referenceType) {
        return types.get(referenceType);
    }
    
    public Set<String> getReferenceTypes() {
        return types.keySet();
    }
    
    private List<Field> inproceedings() {
        List<Field> fields = new ArrayList<Field>();
        fields.add(new Field("author", true));
        fields.add(new Field("title", true));
        fields.add(new Field("booktitle", true));
        fields.add(new Field("year", true));

        fields.add(new Field("editor", false));
        fields.add(new Field("volume/number", false));
        fields.add(new Field("series", false));
        fields.add(new Field("pages", false));
        fields.add(new Field("address", false));
        fields.add(new Field("month", false));
        fields.add(new Field("organization", false));
        fields.add(new Field("publisher", false));
        fields.add(new Field("note", false));
        fields.add(new Field("key", false));
        
        return fields;
    }
    
}

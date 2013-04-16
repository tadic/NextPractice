
package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ReferenceFactory {
    private HashMap<String, Reference> types;
    
    
    public ReferenceFactory() {
        types = new HashMap<String, Reference>();
        types.put("inproceedings", new Inproceedings());
        types.put("book", new Book());
        types.put("article", new Article());
    }
       
    public List<Field> getFields(String referenceType) {
        return types.get(referenceType).getFields();
    }
    
    public List<Field> getFields(String referenceType, boolean required) {
        List<Field> requestedFields = new ArrayList<Field>();
        for (Field field : getFields(referenceType)) {
            if (field.isRequired()==required) {
                requestedFields.add(field);
            }
        }
        return requestedFields;
    }
    
    public Set<String> getReferenceTypes() {
        return types.keySet();
    }
    
    public Reference createReference(String referenceType){
        return types.get(referenceType);
    }
    
    public <T extends Reference> T createReference(String referenceType, List<Field> fields) {
        return (T) new Inproceedings(fields);
    }
   
}

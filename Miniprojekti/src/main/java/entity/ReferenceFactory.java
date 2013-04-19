
package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
/**
 * Manages the creation of new Reference objects.
 * 
 */
public class ReferenceFactory {
    /**
     * Contains reference type names and their corresponding objects.
     */
    private HashMap<String, Reference> types;
    
    public ReferenceFactory() {
        types = new HashMap<String, Reference>();
        types.put("inproceedings", new Inproceedings());
        types.put("book", new Book());
        types.put("article", new Article());
    }
    
    /**
     * Gives a list of all Fields for a reference type.
     * 
     * @param referenceType name of the reference type
     * @return List<Field> of all fields for referenceType
     */   
    public List<Field> getFields(String referenceType) {
        return types.get(referenceType).getFields();
    }
    /**
     * Gives a list of either required or optional Fields for a reference type.
     * 
     * @param referenceType name of the reference type
     * @param required returned fields are required
     * @return List<Field> list of required/optional fields for referenceType
     */
    public List<Field> getFields(String referenceType, boolean required) {
        List<Field> requestedFields = new ArrayList<Field>();
        for (Field field : getFields(referenceType)) {
            if (field.isRequired()==required) {
                requestedFields.add(field);
            }
        }
        return requestedFields;
    }
    
    /**
     * Gives all reference type names known to the factory.
     * 
     * @return List<String> names of all reference types
     */
    public List<String> getReferenceTypes() {
        return  new ArrayList<String>(types.keySet());
    }
    
    /**
     * Gives an empty Reference
     * @param referenceType name of the reference type to be created
     * @return empty Reference of type referenceType
     */
    public Reference createReference(String referenceType){
        return types.get(referenceType);
    }
    /**
     * Gives a new reference with supplied field values.
     * 
     * @param referenceType name of the reference type to be created
     * @param fields list of Field objects to populate the new Reference
     * @return new Reference with the field values supplied in fields
     */
    public Reference createReference(String referenceType, List<Field> fields) {
        Reference ref = types.get(referenceType);
        ref.setFields(fields);
        return ref;
    }
    
    public Class getClassOfype(String type) {
        Class ret = null;
        
        ret = types.get(type).getClass();
        
        return ret;
    }
   
}

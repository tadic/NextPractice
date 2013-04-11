
package controllers;

import entity.Field;
import entity.Inproceedings;
import entity.Reference;
import java.util.List;
import java.util.Set;

public interface LogicInterface {
    
    public Inproceedings getInproceedings();
    public List<Field> getFields(String referenceType);
    public Set<String> getReferenceTypes();
    public Reference createReference(String referenceType, List<Field> fields);
    
    public String[][] getRequiredFields();
    public String[][] getOptionalFields();
    public Inproceedings createReference(String[][] required, String[][] optional);
    
}

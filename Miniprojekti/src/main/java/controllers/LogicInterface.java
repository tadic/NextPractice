
package controllers;

import entity.BaseEntity;
import entity.Field;
import entity.Inproceedings;
import java.util.List;
import java.util.Set;

public interface LogicInterface {
    
    public Inproceedings getInproceedings();
    public List<Field> getFields(String referenceType);
    public Set<String> getReferenceTypes();
    public <T extends BaseEntity> T createReference(String referenceType, List<Field> fields);
    
}

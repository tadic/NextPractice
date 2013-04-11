
package controllers;

import entity.Field;
import entity.Inproceedings;
import entity.Reference;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface LogicInterface {
    
    public List<Inproceedings> getInproceedings();
    public List<Field> getFields(String referenceType);
    public Set<String> getReferenceTypes();
    public Reference createReference(String referenceType, List<Field> fields);
    public void convertLoadedToBibtex() throws IOException;
    public void saveToFile(String fileName);
    public void loadFile(String fileName);
    
    public String[][] getRequiredFields();
    public String[][] getOptionalFields();
    public Reference createReference(String[][] required, String[][] optional);
    
}

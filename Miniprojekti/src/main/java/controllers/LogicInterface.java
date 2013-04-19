package controllers;

import entity.FType;
import entity.Field;
import entity.Inproceedings;
import entity.Reference;
import exception.RepositoryException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface LogicInterface {
    
    // Referenssityyppit, referenssityypin kentät ja referenssin luonti.
    public List<String> getReferenceTypes();
    public List<Field> getFields(String referenceType);
    //createReference luo referenssin, antaa sen repositoriolle muistiin ja palauttaa sen guille
    public Reference createReference(String referenceType, List<Field> fields);
    public void updateReference(Reference ref);
    
    //Repositoriolla muistissa olevien Reference-olioiden tallettaminen
    public void convertAllToBibtex(String fileName) throws IOException;
    public void convertSelectedToBibtex(List<Reference> references, String fileName) throws IOException;
    //Repositoriolla muistissa olevien Reference-olioiden hakeminen
    public List<Reference> getAllReferences();
    public List<Reference> getReferencesByField(String type, FType ftype, Object value) throws RepositoryException;
    public List<Reference> getReferenceByType(String type);
    //Repositoriolla muistissa olevien Reference-olioiden tallettaminen tiedostoon
    public void saveAllToFile(String fileName) throws Exception;
    //Tiedostossa olevien Reference-olioiden tuominen tiedostosta repositoriolle muistiin
    public void loadFile(String fileName);
    public void clearAll();
    
    //THESE ARE GOING AWAYYYYYYY
    public String[][] getRequiredFields();
    public String[][] getOptionalFields();
    public Reference createReference(String[][] required, String[][] optional);
    
}
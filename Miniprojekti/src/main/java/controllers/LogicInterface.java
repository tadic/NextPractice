
package controllers;

import entity.Field;
import entity.Reference;
import entity.ReferenceFactory;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;


public interface LogicInterface {

    void createNewRef(String name);
    String currentRefToBibTex();
    Converter getConverter();
    int getCurrentRow();
    List<Field> getFields(String referenceType);
    List<Reference> getFilteredList();
    ArrayList<JTextField> getListOfFields();
    List<Reference> getListOfRef();
    List<Reference> getOldList();
    Reference getOldReference();
    List<Field> getOptionalFields();
    ReferenceFactory getRFactory();
    Reference getRef();
    List<String> getReferenceTypes();
    List<Field> getRequiredFields();
    void setConverter(Converter converter);
    void setCurrentRow(int currentRow);
    void setFilter(String filter);
    void setFilteredList(List<Reference> filteredList);
    void setListOfFields(ArrayList<JTextField> listOfFields);
    void setListOfRef(List<Reference> listOfRef);
    void setOldList(List<Reference> oldList);
    void setOldReference(Reference oldReference);
    void setRFactory(ReferenceFactory RFactory);
    void setRef(Reference r);
    
}

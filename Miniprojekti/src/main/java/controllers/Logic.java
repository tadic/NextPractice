/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Field;
import entity.Inproceedings;
import entity.ReferenceFactory;
import java.util.List;
import java.util.Set;
import repositories.GenericRepository;

/**
 *
 * @author mikahutt
 */
public class Logic implements LogicInterface{

    private GenericRepository repository;
    private ReferenceFactory RFactory;
    
    public Inproceedings getInproceedings() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Field> getFields(String referenceType) {
        return RFactory.getFields(referenceType);
    }
    
    public Set<String> getReferenceTypes() {
        return RFactory.getReferenceTypes();
    }

    public void createInproceedings(List<Field> fields) {
        
    }


    
}

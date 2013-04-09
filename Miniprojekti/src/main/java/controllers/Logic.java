/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.BaseEntity;
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

    public Logic() {
        RFactory = new ReferenceFactory();
    }
    
    public Inproceedings getInproceedings() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Field> getFields(String referenceType) {
        return RFactory.getFields(referenceType);
    }
    
    public Set<String> getReferenceTypes() {
        return RFactory.getReferenceTypes();
    }

    public <T extends BaseEntity> T createReference(String referenceType, List<Field> fields) {
//        Tässä oikea käyttö, jolloin metodin tyyppi void.
//        repository.create(RFactory.createReference(referenceType, fields));
        return RFactory.createReference(referenceType, fields);
    }


    
}

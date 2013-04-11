/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Inproceedings;
import java.util.List;
import repositories.GenericDataFileRepository;
import repositories.GenericRepository;

/**
 *
 * @author mikahutt
 */
public class Logic implements LogicInterface{

    private GenericRepository repository;    

    public Logic() {
        repository = GenericDataFileRepository.getInstance();
    }
    
    public List<Inproceedings> getInproceedings() {
        List<Inproceedings> findAll = repository.findAll(Inproceedings.class);
        return findAll;
    }

    public String[][] getRequiredFields() {
        return new String[][] {{"Author", ""}, {"Title", ""}, {"Booktitle", ""}, {"Year", ""}};
    }

    public String[][] getOptionalFields() {
        return new String [][] {{"Editor", ""}, {"Volume/Number", ""}, {"Series", ""}, {"Pages", ""}, {"Address", ""},
                    {"Month", ""}, {"Organization", ""}, {"Publisher", ""}, {"Note", ""}, {"Key", ""}};
    }

    public void createInproceedings(String[][] required, String[][] optional) {
        Inproceedings inpro = new Inproceedings(required, optional);
        repository.create(inpro);
    }


    
}

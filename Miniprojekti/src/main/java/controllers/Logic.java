/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entity.Inproceedings;
import repositories.GenericRepository;

/**
 *
 * @author mikahutt
 */
public class Logic implements LogicInterface{

    private GenericRepository repository;    
    
    public Inproceedings getInproceedings() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String[][] getRequiredFields() {
        return new String[][] {{"Author", ""}, {"Title", ""}, {"Booktitle", ""}, {"Year", ""}};
    }

    public String[][] getOptionalFields() {
        return new String [][] {{"Editor", ""}, {"Volume/Number", ""}, {"Series", ""}, {"Pages", ""}, {"Address", ""},
                    {"Month", ""}, {"Organization", ""}, {"Publisher", ""}, {"Note", ""}, {"Key", ""}};
    }

    public void createInproceedings(String[][] required, String[][] optional) {
        
    }


    
}

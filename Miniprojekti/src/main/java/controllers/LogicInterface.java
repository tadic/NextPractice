
package controllers;

import entity.Inproceedings;

public interface LogicInterface {
    
    public Inproceedings getInproceedings();
    public String[][] getRequiredFields();
    public String[][] getOptionalFields();
    public void createInproceedings(String[][] required, String[][] optional);
    
}

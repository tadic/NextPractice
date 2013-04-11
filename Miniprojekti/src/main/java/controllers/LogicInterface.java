
package controllers;

import entity.Inproceedings;
import java.util.List;

public interface LogicInterface {
    
    public List<Inproceedings> getInproceedings();
    public String[][] getRequiredFields();
    public String[][] getOptionalFields();
    public void createInproceedings(String[][] required, String[][] optional);
    
}

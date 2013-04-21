/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.beans.PropertyChangeEvent;

/**
 *
 * @author jarno
 */
public interface View {

    void modelPropertyChange(PropertyChangeEvent evt);
    void registerController(MainGUIController c);
}

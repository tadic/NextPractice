
package UI;

import java.beans.PropertyChangeEvent;

public interface View {

    void modelPropertyChange(PropertyChangeEvent evt);
    void registerController(MainGUIController c);
}

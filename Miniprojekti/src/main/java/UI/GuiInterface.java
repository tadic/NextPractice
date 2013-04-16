/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 *
 * @author mikahutt
 */
public interface GuiInterface extends ActionListener {

    /**
     * Handles saving the text from textfields and saving given information into
     * a file via save button.
     */
    void actionPerformed(ActionEvent ae);

    /**
     * Opens a window where user can choose to create new reference or load an
     * existing one.
     */
    void initGUI();

    /**
     * Opens an form for creating an inprociidings reference.
     */
    void openReferenceForm();

    /**
     * Reads the fields and saves inproceeding as bibtex.
     *
     * @param nameOfFile
     * @throws IOException
     */
    void saveAsBibtex(String nameOfFile) throws IOException;
    
}

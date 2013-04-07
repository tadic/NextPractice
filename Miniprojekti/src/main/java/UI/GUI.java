/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import controllers.LogicInterface;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mikahutt
 */
public class GUI implements ActionListener{
    private LogicInterface logic;
    private JPanel panel;
    private SpringLayout layout;
 
    
    public GUI(LogicInterface l) {
        logic = l;
    }
    
    /*
     * Creates the fields necessary for adding the information necessary for creating inproceedings entityes.
     * Initializes the Gui. Capability to open files will be implemented later.
     */
    public void initGUI() {
        layout = new SpringLayout();
        panel = new JPanel();
        panel.setLayout(layout);
        
        String[] labels = {"Author: ", "Publisher: ", "Title: ", "Booktitle: ",  "Year: ", 
                           "ReferenceId; ", "Editor: ", "Volume number: ", "Series: ", 
                           "Pages: ", "Address: ", "Month: ", "Organization: ",
                           "Publisher: ", "Note: ", "Key: "};
        
        for (int i=0;i<labels.length;i++) {
            JLabel label = new JLabel(labels[i], JLabel.TRAILING);
            panel.add(label);
            JTextField textfield = new JTextField(15);
            textfield.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    throw new UnsupportedOperationException("Not supported yet.");
                }
            });
            panel.add(textfield);
        }
        
        SpringUtilities.makeCompactGrid(panel, labels.length, 1, 6, 6, 6, 6);
        
    }

    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

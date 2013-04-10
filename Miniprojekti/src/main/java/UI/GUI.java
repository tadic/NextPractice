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
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author mikahutt
 */
public class GUI implements ActionListener{
    private LogicInterface logic;
    private JButton newReference;
    private JButton loadReference;
 
    
    public GUI(LogicInterface l) {
        logic = l;
        initGUI();
    }
    
    /*
     * Creates the fields necessary for adding the information necessary for creating inproceedings entityes.
     * Initializes the Gui. Capability to open files will be implemented later.
     */
    public void initGUI() {
        JFrame frame = new JFrame();
        Container panel = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
       // JPanel panel = new JPanel();
        panel.setLayout(layout);
        
        newReference = new JButton("New");
        newReference.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                openReferenceForm();
            }
        });
        
        loadReference = new JButton("Load");
        loadReference.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        
        panel.add(newReference);
        panel.add(loadReference);
        
        layout.putConstraint(SpringLayout.WEST, newReference, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, newReference, 5, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, loadReference, 5, SpringLayout.EAST, newReference);
        layout.putConstraint(SpringLayout.NORTH, loadReference, 5, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.EAST, panel, 5, SpringLayout.EAST, loadReference);
        layout.putConstraint(SpringLayout.SOUTH, panel, 5, SpringLayout.SOUTH, loadReference);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
    
    
    public void openReferenceForm() {
        JFrame frame = new JFrame();
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(layout);
//        JMenu menu = new JMenu();
//        JMenuBar menuBar = new JMenuBar();
//        JMenuItem save = new JMenuItem("Save");
//        
//        save.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ae) {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//        });
//        menuBar.add(save);
//        panel.add(menuBar);
        
        
        
        String [][] requiredLabels = logic.getRequiredFields();
        String [][] optionalLabels = logic.getOptionalFields();
        
        int length = requiredLabels.length + optionalLabels.length;
        
        for (int i=0;i<length + 1;i++) {
            if(i<requiredLabels.length) {
                JLabel label = new JLabel(requiredLabels[i][0], JLabel.TRAILING);
                final JTextField textfield = new JTextField(15);
                label.setLabelFor(textfield);
                panel.add(label);
                panel.add(textfield);
            }   else  if(i == length){
                JButton save = new JButton("Save");
                save.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                });
                panel.add(save);
            } else {
                int index = i  - requiredLabels.length;
                JLabel label = new JLabel(optionalLabels[index][0], JLabel.TRAILING);
                final JTextField textfield = new JTextField(15);
                label.setLabelFor(textfield);
                panel.add(label);
                panel.add(textfield);
            }

        }
        
        SpringUtilities.makeCompactGrid(panel, length, 2, 6, 6, 6, 6);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

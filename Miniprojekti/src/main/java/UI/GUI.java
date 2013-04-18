/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import controllers.Converter;
import controllers.FileSaver;
import controllers.LogicInterface;
import entity.FType;
import entity.Field;
import entity.Inproceedings;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Container;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 *
 * @author mikahutt
 */
public class GUI implements GuiInterface {

    private LogicInterface logic;
    private JButton newReference;
    private JButton loadReference;
    private JButton saveReference;
    private JButton addReference;
    private JButton convertToBibTex;
    private ArrayList<JTextField> textFields;
    private List<Field> fields;
    private JFrame referenceframe;

    public GUI(LogicInterface l) {
        logic = l;
        initGUI();
    }

    /**
     * Opens a window where user can choose to create new reference or load an
     * existing one.
     */
    @Override
    public void initGUI() {
        JFrame frame = new JFrame();
        Container panel = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        final JFileChooser fileOpener = new JFileChooser();
        panel.setLayout(layout);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        newReference = new JButton("New");
        newReference.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //openReferenceForm();
                openReferenceChooser();
            }
        });

        loadReference = new JButton("Load");
        loadReference.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                int returnVal = fileOpener.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File fileToOpen = fileOpener.getSelectedFile();
                    try {
                        Desktop.getDesktop().open(fileToOpen);
                    } catch (Exception e) {
                        System.out.println("Can't open file");
                    }
                }
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

    /**
     * Opens an form for creating an inprociidings reference.
     */
    @Override
    public void openReferenceForm(String referenceType) {

        referenceframe = new JFrame();
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        referenceframe.add(panel);
        panel.setLayout(layout);
        textFields = new ArrayList<JTextField>();
        fields = logic.getFields(referenceType);

        int length = fields.size();

        for (int i = 0; i < length + 2; i++) {
            if (i < length) {
                JLabel label = new JLabel(fields.get(i).getKey().toString(), JLabel.TRAILING);
                JTextField textfield = new JTextField(15);
                textfield.addActionListener(this);
                textFields.add(textfield);
                label.setLabelFor(textfield);
                panel.add(label);
                panel.add(textfield);
            } else if (i == length) {
                JLabel label = new JLabel("Name of file to save", JLabel.TRAILING);
                JTextField textfield = new JTextField(15);
                textfield.addActionListener(this);
                textFields.add(textfield);
                label.setLabelFor(textfield);
                panel.add(label);
                panel.add(textfield);
            } else if (i == length + 1) {
                saveReference = new JButton("Save");
                saveReference.addActionListener(this);
                addReference = new JButton("Add");
                addReference.addActionListener(this);
                convertToBibTex = new JButton("Convert");
                convertToBibTex.addActionListener(this);
                panel.add(saveReference);
                panel.add(addReference);
                panel.add(convertToBibTex);
            }

        }

        SpringUtilities.makeCompactGrid(panel, length + 2, 2, 6, 6, 6, 6);

        referenceframe.pack();
        referenceframe.setLocationRelativeTo(null);
        referenceframe.setVisible(true);

    }

    /**
     * Handles saving the text from textfields and saving given information into
     * a file via save button.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        String fileNameToSave = "default";
        for (int i = 0; i < textFields.size() - 1; i++) {
            if (ae.getSource() == textFields.get(i)) {
                fields.get(i).setValue(textFields.get(i).getText());
                System.out.println(textFields.get(i).getText());
                System.out.println(fields.get(i).getValue());
            } else if (ae.getSource() == saveReference) {
                fileNameToSave = textFields.get(textFields.size() - 1).getText();
                logic.createReference(fileNameToSave, fields);
                if (fileNameToSave.length() < 2) {
                    fileNameToSave = "default"; // default filename
                }
                try {
                    logic.saveAllToFile(fileNameToSave + ".ref");
                } catch (Exception ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (ae.getSource() == addReference) {
                fileNameToSave = textFields.get(textFields.size() - 1).getText();
                logic.createReference(fileNameToSave, fields);
            } else if (ae.getSource() == convertToBibTex) {
                fileNameToSave = textFields.get(textFields.size() - 1).getText();
                try {
                    saveAsBibtex(fileNameToSave);
                } catch (IOException ex) {
                    Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        try {
            this.saveAsBibtex(fileNameToSave + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
     * Opens a window for choosing a reference type for a new reference
     */
    public void openReferenceChooser() {
        JFrame chooserFrame = new JFrame();
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        chooserFrame.add(panel);
        panel.setLayout(layout);

        final JTextField textField = new JTextField(15);
        JLabel label = new JLabel("Give reference type:");
        JButton button = new JButton("Open");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (logic.getReferenceTypes().contains(textField.getText())) {
                    openReferenceForm(textField.getText());
                }
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(button);

        SpringUtilities.makeCompactGrid(panel, 1, 3, 6, 6, 6, 6);

        chooserFrame.pack();
        chooserFrame.setLocationRelativeTo(null);
        chooserFrame.setVisible(true);

    }

    /**
     * Reads the fields and saves inproceeding as bibtex.
     *
     * @param nameOfFile
     * @throws IOException
     */
    @Override
    public void saveAsBibtex(String nameOfFile) throws IOException {
        logic.convertAllToBibtex(nameOfFile);
    }
}

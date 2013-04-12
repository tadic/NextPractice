/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import controllers.Converter;
import controllers.FileSaver;
import controllers.LogicInterface;
import entity.FType;
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
public class GUI implements ActionListener {

    private LogicInterface logic;
    private JButton newReference;
    private JButton loadReference;
    private JButton saveReference;
    private JFileChooser fileOpener;
    private ArrayList<JTextField> textFields;
    private String[][] requiredLabels;
    private String[][] optionalLabels;
    private String[][] allLabels;
    private FileSaver fileSaver;
    private Converter converter;
    private JFrame referenceframe;

    public GUI(LogicInterface l, Converter c) {
        logic = l;
        converter = c;
        initGUI();
    }

    /**
     * Opens a window where user can choose to create new reference or load an
     * existing one.
     */
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
                openReferenceForm();
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
    public void openReferenceForm() {

        referenceframe = new JFrame();
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        referenceframe.add(panel);
        panel.setLayout(layout);
        textFields = new ArrayList<JTextField>();

        fileSaver = new FileSaver(new Converter());


        requiredLabels = logic.getRequiredFields();
        optionalLabels = logic.getOptionalFields();


        int length = requiredLabels.length + optionalLabels.length;

        for (int i = 0; i < length + 2; i++) {
            if (i < requiredLabels.length) {
                JLabel label = new JLabel(requiredLabels[i][0], JLabel.TRAILING);
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
                panel.add(saveReference);
            } else {
                int index = i - requiredLabels.length;
                JLabel label = new JLabel(optionalLabels[index][0], JLabel.TRAILING);
                JTextField textfield = new JTextField(15);
                textfield.addActionListener(this);
                textFields.add(textfield);
                label.setLabelFor(textfield);
                panel.add(label);
                panel.add(textfield);
            }

        }

        SpringUtilities.makeCompactGrid(panel, length + 1, 2, 6, 6, 6, 6);

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
        String fileNameToSave = "inpro";
        for (int i = 0; i < textFields.size() - 1; i++) {
            if (ae.getSource() == textFields.get(i) && i < requiredLabels.length) {
                requiredLabels[i][1] = textFields.get(i).getText();
                System.out.println(textFields.get(i).getText());
                System.out.println(requiredLabels[i][1]);
            } else if (ae.getSource() == textFields.get(i) && i >= requiredLabels.length) {
                optionalLabels[i - requiredLabels.length][1] = textFields.get(i).getText();
                System.out.println(textFields.get(i).getText());
                System.out.println(optionalLabels[i - requiredLabels.length][1]);
            } else if (ae.getSource() == saveReference) {
                logic.createReference(requiredLabels, optionalLabels);
                fileNameToSave = textFields.get(textFields.size() - 1).getText();
                if (fileNameToSave.length() < 2) {
                    fileNameToSave = "inpro"; // default filename
                }
                logic.saveToFile("inproceedings.txt");

            }
        }
        try {
            this.saveAsBibtex(fileNameToSave + ".txt");
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Reads the fields and saves inproceeding as bibtex.
     *
     * @param nameOfFile
     * @throws IOException
     */
    public void saveAsBibtex(String nameOfFile) throws IOException {
        Inproceedings inproceeding = new Inproceedings(logic.getFields("inproceedings"));


        inproceeding.setFieldValue(FType.author, textFields.get(0).getText());
        inproceeding.setFieldValue(FType.booktitle, textFields.get(1).getText());
        inproceeding.setFieldValue(FType.title, textFields.get(2).getText());
        inproceeding.setFieldValue(FType.year, textFields.get(3).getText());
        if (!inproceeding.isRegular(null)) {
            JOptionPane.showMessageDialog(referenceframe, "Fill the first 4 fields. Check that year and texts are correct");
            return;
        }

        fileSaver.saveToFile(nameOfFile, inproceeding);

    }
//    public void openFile(String nameOfFile) {
//        
//    }
}

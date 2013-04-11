/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import controllers.Converter;
import controllers.FileSaver;
import controllers.LogicInterface;
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
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
    private FileSaver fileSaver;

    public GUI(LogicInterface l) {
        logic = l;
        initGUI();
    }

    /*
     * Opens a window where user can choose to create new reference or load an existing one.
     */
    public void initGUI() {
        JFrame frame = new JFrame();
        Container panel = frame.getContentPane();
        SpringLayout layout = new SpringLayout();
        final JFileChooser fileOpener = new JFileChooser();
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

    /*
     * Opens an form for creating an inprociidings reference.
     */
    public void openReferenceForm() {

        JFrame frame = new JFrame();
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(layout);
        textFields = new ArrayList<JTextField>();

        fileSaver = new FileSaver(new Converter());


        requiredLabels = logic.getRequiredFields();
        optionalLabels = logic.getOptionalFields();

        int length = requiredLabels.length + optionalLabels.length;

        for (int i = 0; i < length + 1; i++) {
            if (i < requiredLabels.length) {
                JLabel label = new JLabel(requiredLabels[i][0], JLabel.TRAILING);
                JTextField textfield = new JTextField(15);
                textfield.addActionListener(this);
                textFields.add(textfield);
                label.setLabelFor(textfield);
                panel.add(label);
                panel.add(textfield);
            } else if (i == length) {
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

        SpringUtilities.makeCompactGrid(panel, length, 2, 6, 6, 6, 6);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /*
     * Handles saving the text from textfields and saving given information into a file
     * via save button.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        for (int i = 0; i < textFields.size(); i++) {
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
                logic.saveToFile("inproceedings.txt");

            }
        }
    }
}

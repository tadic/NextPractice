/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import controllers.Converter;
import entity.Reference;
import exception.RepositoryException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author jarno
 */
public class MainGUI extends JFrame implements View {

    private static final String TITLE = "Miniproject";
    private MainGUIController controller;
    private JMenuItem miOpen, miSave, miSaveAs, miExit;
    private JLabel lStatus;
    private ReferenceTablemodel referencesTablemodel = new ReferenceTablemodel();
    private UnifiedReferenceTablemodel collectedReferencesTablemodel = new UnifiedReferenceTablemodel();
    private JTable table;
    private JTextArea bibTextArea;
    private Converter converter = new Converter();
    final JFileChooser fc = new JFileChooser();

    public MainGUI() {
        initComponents();
    }

    
    @Override
    public void registerController(MainGUIController c) {
        this.controller = c;
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(MainGUIController.REFERENCES)) {
            referencesTablemodel.setReferences((List<Reference>) evt.getNewValue());
        } else if (evt.getPropertyName().equals(MainGUIController.COLLECTED_REFERENCES)) {
            collectedReferencesTablemodel.setReferences(new ArrayList<Reference>((Set<Reference>) evt.getNewValue()));
        } else if (evt.getPropertyName().equals(MainGUIController.CURRENTLY_SELECTED_REFERENCE)) {
            if (evt.getNewValue() != null) {
                Reference selected = (Reference) evt.getNewValue();
                statusMessage("Reference with id " + selected.getId() + " selected");
                bibTextArea.setText(converter.toBibTex(selected));
            } else {
                bibTextArea.setText("");
            }

        }
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(TITLE);

        JScrollPane treeView = new JScrollPane(initTreeNav());

        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        miOpen = new JMenuItem("Open");
        miOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(MainGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        controller.open(file);
                        setTitle(TITLE + " - " + file.getAbsolutePath());
                        statusMessage("Opened from " + file.getAbsolutePath());
                    } catch (RepositoryException ex) {
                        errorDialog(ex);
                    }
                }
            }
        });
        miSave = new JMenuItem("Save");
        miSaveAs = new JMenuItem("Save As");
        miSaveAs.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showSaveDialog(MainGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        controller.save(file);
                        statusMessage("Saved to file " + file.getAbsolutePath());
                    } catch (RepositoryException ex) {
                        errorDialog(ex);
                    }
                }
            }
        });
        JMenuItem export = new JMenuItem("Export");
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showSaveDialog(MainGUI.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                        controller.export(file);
                        statusMessage("Exported to file " + file.getAbsolutePath());
                    } catch (IOException ioe) {
                        errorDialog(ioe);
                    }
                }

            }
        });
        miExit = new JMenuItem("Exit");
        miExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(miOpen);
        file.add(miSave);
        file.add(miSaveAs);
        file.add(export);
        file.add(miExit);
        menubar.add(file);

        JMenu operations = new JMenu("Operations");
        JMenuItem miCreate = new JMenuItem("Create");
        miCreate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                callGUINewReferences();
            }
        });
        
        
        JMenuItem miDelete = new JMenuItem("Delete");
        miDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.deleteSelected();
                } catch (RepositoryException ex) {
                    errorDialog(ex);
                }
            }
        });
        operations.add(miCreate);
        operations.add(miDelete);
        menubar.add(operations);

        setJMenuBar(menubar);

        table = new JTable(referencesTablemodel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() && table.getModel().equals(referencesTablemodel)) {
                    int row = table.getSelectedRow();
                    if (row > -1) {
                        Reference selected = referencesTablemodel.getReferences().get(row);
                        controller.updateCurrentlySelectedReference(selected);
                    }
                }
            }
        });
        JScrollPane tableScroll = new JScrollPane(table);

        bibTextArea = new JTextArea(5, 20);
        JScrollPane singleBibScroll = new JScrollPane(bibTextArea);

        JSplitPane verticalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tableScroll, singleBibScroll);
        verticalSplit.setOneTouchExpandable(true);
        verticalSplit.setDividerLocation(400);

        JSplitPane horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeView, verticalSplit);
        horizontalSplit.setOneTouchExpandable(true);
        horizontalSplit.setDividerLocation(200);

        getContentPane().add(makeToolbar(), BorderLayout.NORTH);
        getContentPane().add(horizontalSplit, BorderLayout.CENTER);
        initStatusBar(this);
        setSize(800, 600);
        pack();
    }
    
//      Ideana oli, että oldList on repositoryList(tietokanta), koska pitää checkata isUnique()
//      Se toinen on se joka muuttuu GUINewReference:ssa, 
//      Se create metodi ottaa sun MainFrame:n parametrina koska sen pitää trigeroida joku MainFram:in metodi
//      joka tekisi homman kun GUINewReference on valmis!(mä käytin makeCollectedReferencesBibtexString())
    
//      EN onnistunut tehdä siten, että create palauttaa listan! Jos sulla on idea - sano tai korjaa itse!
//     Mutta mä testasin vähän ja se toimi näin.
    
private void callGUINewReferences(){
     ArrayList<Reference> resultList = new ArrayList<Reference>();
     ArrayList<Reference> oldList = new ArrayList<Reference>();
     GUINewReferences.create(oldList, resultList, this);
}



    public JToolBar makeToolbar() {
        JToolBar toolBar = new JToolBar("Still draggable");
        toolBar.setFloatable(false);
        JButton collect = new JButton("Collect");
        collect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table.getModel().equals(referencesTablemodel)) {
                    int row = table.getSelectedRow();
                    if (row > -1) {
                        Reference selected = referencesTablemodel.getReferences().get(row);
                        controller.addReferenceToCollectedReferences(selected);
                    }
                }
            }
        });
        toolBar.add(collect);
        return toolBar;
    }

    private void statusMessage(String s) {
        lStatus.setText(s);
    }

    private void errorDialog(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void makeCollectedReferencesBibtexString(List<Reference> list) {
        StringBuilder sb = new StringBuilder();
        for (Reference r : list) {
            sb.append(converter.toBibTex(r)).append("\n");
        }
        bibTextArea.setText(sb.toString());
    }

    private void initStatusBar(JFrame frame) {
        JPanel statusPanel = new JPanel();
        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 16));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        lStatus = new JLabel("Status");
        lStatus.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(lStatus);
        frame.add(statusPanel, BorderLayout.SOUTH);
    }

    private JTree initTreeNav() {
        DefaultMutableTreeNode references = new DefaultMutableTreeNode("References");
        DefaultMutableTreeNode database = new DefaultMutableTreeNode("References in database");
        final DefaultMutableTreeNode collected = new DefaultMutableTreeNode("Collected references");
        for (String className : MainGUIController.availableReferenceSubClasses.keySet()) {
            DefaultMutableTreeNode n = new DefaultMutableTreeNode(className);
            database.add(n);
        }

        references.add(database);
        references.add(collected);

        final JTree tree = new JTree(references);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode n = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (n != null && n.isLeaf()) {
                    if (n.equals(collected)) {
                        table.setModel(collectedReferencesTablemodel);
                        makeCollectedReferencesBibtexString(collectedReferencesTablemodel.getReferences());
                    } else {
                        if (n.getUserObject() instanceof String) {
                            String s = (String) n.getUserObject();
                            table.setModel(referencesTablemodel);
                            bibTextArea.setText("");
                            controller.updateReferences(MainGUIController.availableReferenceSubClasses.get(s));
                        }
                    }
                }
            }
        });
        return tree;

    }

    public static void main(String args[]) {
        final MainGUI g = new MainGUI();
        MainGUIModel m = new MainGUIModel();
        final MainGUIController c = new MainGUIController(g, m);

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                g.setVisible(true);
                c.populateReferencesWithTestData();
            }
        });
    }

    private class ReferenceTablemodel extends AbstractTableModel {

        private List<Reference> references = new ArrayList<Reference>();

        @Override
        public int getRowCount() {
            return references.size();
        }

        @Override
        public int getColumnCount() {
            return cols();
        }

        @Override
        public String getColumnName(int col) {
            return references.get(0).getFields().get(col).getKey().name();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return references.get(rowIndex).getFields().get(columnIndex).getValue();
        }

        private int cols() {
            int c = 0;
            for (Reference r : references) {
                int s = r.getFields().size();
                if (s > c) {
                    c = s;
                }
            }
            return c;
        }

        public List<Reference> getReferences() {
            return references;
        }

        public void setReferences(List<Reference> references) {
            this.references = references;
            fireTableStructureChanged();
        }
    }

    private class UnifiedReferenceTablemodel extends AbstractTableModel {

        private List<Reference> references = new ArrayList<Reference>();

        @Override
        public int getRowCount() {
            return references.size();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public String getColumnName(int col) {
            return "ID";
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return references.get(rowIndex).getId();
        }

        private int cols() {
            int c = 0;
            for (Reference r : references) {
                int s = r.getFields().size();
                if (s > c) {
                    c = s;
                }
            }
            return c;
        }

        public List<Reference> getReferences() {
            return references;
        }

        public void setReferences(List<Reference> references) {
            this.references = references;
            fireTableStructureChanged();
        }
    }
}

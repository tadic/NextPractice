package UI;

import controllers.Logic;
import controllers.LogicInterface;
import entity.FType;
import entity.Field;
import entity.Reference;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.uncommons.swing.SpringUtilities;

public class  GUINewReferences extends javax.swing.JFrame implements View {
    private MainGUIController controller;
    private LogicInterface logic;

    public GUINewReferences(MainGUIController controller, ArrayList<Reference> oldList, ArrayList<Reference> list) {
        this.controller = controller;
        logic = new Logic();
        logic.setListOfRef(list);
        logic.setOldList(oldList);
    }

    public GUINewReferences(ArrayList<Reference> oldList, ArrayList<Reference> list) {
        logic = new Logic();
        logic.setListOfRef(list);
        logic.setOldList(oldList);
        initComponents();
        this.setTitle("BibTeX creator");
        this.setVisible(true);
    }

    public void initAndShow() {
        initComponents();
        this.setTitle("BibTeX creator");
        this.setVisible(true);
    }
    
    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
        
    }

    @Override
    public void registerController(MainGUIController c) {
        this.controller = c;
    }
    
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        referenceArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        documentArea = new javax.swing.JTextArea();
        AddToDocument = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "article", "book", "booklet", "conference", "inbook", "incollection", "inproceedings", "manual", "mastersthesis", "misc", "phdthesis", "proceedings", "techreport", "unpublished" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 460, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 86, Short.MAX_VALUE)
        );

        jLabel1.setText("Required Fields");

        jLabel2.setText("Optional Fields");

        referenceArea.setEditable(false);
        referenceArea.setColumns(35);
        referenceArea.setRows(5);
        jScrollPane1.setViewportView(referenceArea);

        documentArea.setEditable(false);
        documentArea.setColumns(35);
        documentArea.setRows(5);
        jScrollPane2.setViewportView(documentArea);

        AddToDocument.setText("Add/Save to Document");
        AddToDocument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddReferenceActionPerformed(evt);
            }
        });

        jButton2.setText("Save Document");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSavePerformed(evt);
            }
        });

        jLabel3.setText("Reference:");

        jButton3.setText("Delete");

        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        documentArea.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                documentAreaFocusGained(evt);
            }
        });
        documentArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                documentAreaKeyRelesed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFilterKeyReleased(evt);
            }
        });
        //jLabel5.setText(logic.getDocumentName());
        jLabel6.setText("find:");
        jTextField1.setVisible(true);
        jPanel3.add(jTextField1);
        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .add(jButton3)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jButton2))
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(14, 14, 14)
                                .add(jLabel3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(AddToDocument))
                            .add(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                                    .add(jScrollPane1)))))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 230, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel6)
                        .add(jTextField1)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(3, 3, 3)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(AddToDocument))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                 .add(jLabel5)
                .add(jLabel6)
                .add(jTextField1))
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 289, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton2)
                    .add(jButton3))
                .addContainerGap())
        );

        jLabel4.setText("Reference type:");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 464, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 66, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(15, 15, 15)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 236, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(jLabel4)
                        .add(3, 3, 3)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 236, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(22, 22, 22)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel4))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(333, 333, 333))
                    .add(layout.createSequentialGroup()
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>                        
       /**
     * Set ups the form.
     * @param evt 
     */
    private void setUpForm(){
        logic.setCurrentRow(-1);
        SpringLayout layout = new SpringLayout();
        logic.createNewRef(jComboBox1.getSelectedItem().toString());
        setUpFields(jPanel1, layout, logic.getRequiredFields());
        setUpFields(jPanel2, layout, logic.getOptionalFields());
        this.setTitle("BibteX editor");
        this.setVisible(true);
    }

    private void setUpFields(JPanel jpane, SpringLayout layout, List<Field> listOfFields){
    jpane.removeAll();
    jpane.setLayout(layout);
    for (Field field: listOfFields){
            JLabel label = new JLabel(field.getKey().toString(), JLabel.TRAILING);
            final JTextField text = new JTextField(30);
            label.setPreferredSize(new Dimension(75,5));
            jpane.add(label);  
            text.setName(field.getKey().name());
            text.setText(field.getValue());
            label.setLabelFor(text);
            text.getDocument().addDocumentListener(new DocumentListener() {
                  public void changedUpdate(DocumentEvent documentEvent) {
                    printIt();
                  }
                  public void insertUpdate(DocumentEvent documentEvent) {
                    printIt(); 
                  }
                  public void removeUpdate(DocumentEvent documentEvent) {
                      printIt();

                  }
                  private void printIt() {
                     logic.getRef().setFieldValue(FType.valueOf(text.getName()), text.getText());
                     
                     referenceArea.setText(logic.currentRefToBibTex());
                  }
            });

        jpane.add(text);
            
        }
        referenceArea.setText(logic.currentRefToBibTex());
        
        SpringUtilities.makeCompactGrid(jpane,
                                listOfFields.size(), 2, //rows, cols
                                2, 2,        //initX, initY
                                2, 2);
        jpane.setVisible(true);
}
 
    private void formWindowActivated(java.awt.event.WindowEvent evt) {                                     
        if (logic.getRef()==null){  
            setUpForm();
        }
    }                                    
    /**
     * Set ups form for the current - chosen reference.
     * @param evt 
     */
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) { 
            setUpForm();
    }                                           
    /**
     * Passing list of created references to the MainGUI and closes frame.
     * @param evt 
     */
    private void jButtonSavePerformed(java.awt.event.ActionEvent evt) {  
//        this.mainFrame.makeCollectedReferencesBibtexString(logic.getListOfRef());
//        this.dispose();
        controller.saveReferenceFromCreateNewReferenceDialog(logic.getListOfRef());
    }       
    /**
     * Select first row - Reference from the filtered list of reference.
     * @param evt 
     */
    private void documentAreaFocusGained(java.awt.event.FocusEvent evt) {
        if (logic.getListOfRef()==null){
            return;
        }
       logic.setCurrentRow(0);
        String filterWord = jTextField1.getText().trim();
        logic.setFilter(filterWord);
        setDocumentArea(logic.getFilteredList());
       setGUIForCurrentRow(logic.getCurrentRow());
    }
    /**
     * Checks is Reference regular, and if it is - adds it into the list. If it isn't regular, it returns appropriate message.
     * @param evt 
     */
    private void jAddReferenceActionPerformed(java.awt.event.ActionEvent evt) {  
        try {
                logic.getRef().isRegular(logic.getListOfRef());
                logic.getRef().isUnique(logic.getOldList());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Field Checker",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (logic.getCurrentRow() == -1){      //if it's new
            logic.getListOfRef().add(logic.getRef());
        }
        logic.createNewRef(jComboBox1.getSelectedItem().toString());
        setDocumentArea(logic.getListOfRef());
        clearFields(jPanel1);
        clearFields(jPanel2);
    }                                        

    private void clearFields(JPanel jPanel){
        for (int i=1; i<jPanel.getComponentCount(); i+=2){
            JTextField jt = (JTextField) jPanel.getComponent(i);
            jt.setText(null);
        }
    }
    /**
     * Set up required fields from current - selected reference to form for editing.
     * @param jPanel 
     */
    private void setUpRequiredFileds(JPanel jPanel){
        for (int i=1; i<jPanel.getComponentCount(); i+=2){
            JTextField jt = (JTextField) jPanel.getComponent(i);
            jt.setText(logic.getRef().getFields().get((i-1)/2).getValue());
        }
    }
    /**
     * Sets document area to the list of references.
     * @param list 
     */
    private void setDocumentArea(List<Reference> list){
        documentArea.setText("");
        if (list==null){
            return;
        }
        for (Reference r:list){
           //System.out.println(r.getFields().get(1).getValue() + " " + r.getFields().get(2).getValue());
           documentArea.append(r.toString());
           documentArea.append("\n");
        }
    }
    /**
     * Clears current list of references and clears documentArea.
     * @param evt 
     */
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) { 
        logic.getListOfRef().clear();
        //logic.setDocumentsIsSaved(false);
        setDocumentArea(logic.getListOfRef());
    }  
    /**
     * Select row in the Document - TextArea
     * @param n 
     */
    private void selectRow(int n){
        documentArea.select(n*80, (n+1)*80);
    }
    
    /**
     * Get current row-Reference which is selected and setUp all GUI field to values of current Reference.
     * @param n is the number of row - Reference in the filtered list.
     */
    private void setGUIForCurrentRow(int n){
        logic.setRef(logic.getFilteredList().get(n));
        
        jComboBox1.setSelectedItem(logic.getRef().getReferenceType());
        logic.setRef(logic.getFilteredList().get(n));
        logic.setCurrentRow(n);
        selectRow(n);
        referenceArea.setText(logic.currentRefToBibTex());
        setUpRequiredFileds(jPanel1);
        
    }
    
    /**
     * pickUp current row and take it as current Reference
     * @param evt
     */
    private void documentAreaKeyRelesed(java.awt.event.KeyEvent evt) {
        int c = evt.getKeyCode();
        if (c==40 && logic.getCurrentRow()<logic.getFilteredList().size()-1){
             logic.setCurrentRow(logic.getCurrentRow()+1);
        } else  if (c==38 && logic.getCurrentRow()>0){
             logic.setCurrentRow(logic.getCurrentRow()-1);

        }
        setGUIForCurrentRow(logic.getCurrentRow());
    }
    /**
     * Applies filter to the list of reference and show filtered list.
     * @param evt 
     */
    private void jFilterKeyReleased(java.awt.event.KeyEvent evt) {
        String filterWord = jTextField1.getText().trim();
        logic.setFilter(filterWord);
        setDocumentArea(logic.getFilteredList());
    }
        
    /**
     * Create list of references and applies it to the MainGUI frame.
     * @param oldList   Database list of References.
     * @param list  Current list.
     * @param mainFrame Main frame from which create method is called.
     */
//    public static void create(ArrayList<Reference> oldList, ArrayList<Reference> list, MainGUI mainFrame){
//        GUINewReferences gui = new GUINewReferences(oldList, list, mainFrame);
//    }
    
    private javax.swing.JButton AddToDocument;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea referenceArea;
    private javax.swing.JTextArea documentArea;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration                   
}

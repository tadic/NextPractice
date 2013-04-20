package UI;


import controllers.Converter;
import controllers.FileSaver;
import controllers.Logic_007;
import controllers.LogicInterface;
import controllers.Reader;
import entity.FType;
import entity.Field;
import entity.Reference;
import entity.ReferenceFactory;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.uncommons.swing.SpringUtilities;
/**
 *
 * @author ivantadic
 */
public class  GUI_007 extends javax.swing.JFrame {
   // private int currentRow;
    //Converter convert;
    private Logic_007 logic;
   // private Reference ref;
//    private ArrayList<Reference> listOfRef; 
//    private ArrayList<Reference> filteredList; 
//    private String documentName = "newBibTex.bib";
//    private String documentPath;
//    private boolean documentsIsSaved;
    JTextField text;
 //   ArrayList<JTextField> listOfFields;

    /**
     * Creates new form NewJFrame
     */
    public GUI_007(Logic_007 l) {
        logic = l;
        initComponents();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
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
        jButtonNew = new javax.swing.JButton();
        jButtonOpen = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
                jButton1ActionPerformed(evt);
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
        jButtonNew.setText("New");
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });
        jButtonOpen.setText("Open");
        jButtonOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOpenActionPerformed(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
        jLabel5.setText(logic.getDocumentName());
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
                            .add(jButtonNew)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jButtonOpen)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
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
                    .add(jButtonNew)
                    .add(jButtonOpen)
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
private void setUpForm(){
        logic.setCurrentRow(-1);
       // ReferenceFactory rf = new ReferenceFactory();
        SpringLayout layout = new SpringLayout();
        logic.createNewRef(jComboBox1.getSelectedItem().toString());
        setUpFields(jPanel1, layout, logic.getRequiredFields());
        setUpFields(jPanel2, layout, logic.getOptionalFields());

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
             setUpForm();
         
    }                                    

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) { 
            setUpForm();
    }                                           

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {        
        if (!logic.isDocumentsIsSaved()){
            JOptionPane.showMessageDialog(this, "You must first save old document", "BibTex checker",JOptionPane.WARNING_MESSAGE);
            return;
        }
        logic.setDocumentName("newBibTex.bib");
        logic.setDocumentPath(null);
        logic.setListOfRef(null);
        logic.setDocumentsIsSaved(true);
        setDocumentArea(logic.getListOfRef());
        jLabel5.setText(logic.getDocumentName());
    }
    private void jButtonOpenActionPerformed(java.awt.event.ActionEvent evt) { 
        String fileContent = null;
        if (!logic.isDocumentsIsSaved()){
            JOptionPane.showMessageDialog(this, "You must first save old document", "BibTex checker",JOptionPane.WARNING_MESSAGE);
            return;
        }
        Reader read = new Reader();
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("BibTex Documents", "bib", "BIB");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showSaveDialog(jButton2);
            if(returnVal == JFileChooser.OPEN_DIALOG) {
            File file = chooser.getSelectedFile();
            String filePath = file.getPath();
            try {
                fileContent = read.fromFile(filePath);
                logic.setDocumentName(file.getName());
                logic.setDocumentPath(filePath);
                logic.setListOfRef(logic.getConverter().toReferenceList(fileContent));
                logic.setDocumentsIsSaved(true);
                setDocumentArea(logic.getListOfRef());
            } catch (Exception ioe) {
                // ... handle errors!
            }
             jLabel5.setText(logic.getDocumentName());
         }
            
    }
    
    private void jButtonSavePerformed(java.awt.event.ActionEvent evt) {  
        File file;
        String filePath = logic.getDocumentPath();

        if (!logic.getDocumentName().equals("newBibTex.bib")){     // if path exists
            filePath = logic.getDocumentPath();
        } else {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("BibTex Documents", "bib", "BIB");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(jButton2);
        
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                 file = chooser.getSelectedFile();
                filePath = file.getPath();
            }
        }
        
        logic.setDocumentPath(filePath);
        try {
            logic.saveDocument();
            JOptionPane.showMessageDialog(this, "Bibtex document " + logic.getDocumentName() + " is saved!", "Field Saver",JOptionPane.WARNING_MESSAGE);
        } catch (Exception ioe) {
            JOptionPane.showMessageDialog(this, "Bibtex document " + logic.getDocumentName() + " is not saved!", "Field Saver",JOptionPane.WARNING_MESSAGE);
        }
    }       
    
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {  
        try {
            if (logic.getCurrentRow() != -1){      // current reference is one from the list
                logic.getRef().isRegular(null);
            } else {                // current reference is new and refId is to be checked
                logic.getRef().isRegular(logic.getListOfRef());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Field Checker",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (logic.getListOfRef() ==null){
            logic.setListOfRef(new ArrayList<Reference>());
        }
        if (logic.getCurrentRow() == -1){      //if it's new
            logic.getListOfRef().add(logic.getRef());
        }
        logic.setDocumentsIsSaved(false);
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
    private void setUpRequiredFileds(JPanel jPanel){
        for (int i=1; i<jPanel.getComponentCount(); i+=2){
            JTextField jt = (JTextField) jPanel.getComponent(i);
            jt.setText(logic.getRef().getFields().get((i-1)/2).getValue());
        }
    }
    private void setDocumentArea(List<Reference> list){
        documentArea.setText("");
        if (list==null){
            return;
        }
        for (Reference r:list){
           System.out.println(r.getFields().get(1).getValue() + " " + r.getFields().get(2).getValue());
           documentArea.append(r.toString());
           documentArea.append("\n");
        }
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) { 
        logic.setListOfRef(null);
        logic.setDocumentsIsSaved(false);
        setDocumentArea(logic.getListOfRef());
    }     
    private void selectRow(int n){
        documentArea.select(n*80, (n+1)*80);
        System.out.println("Rwo selected "+ n);
    }
    
    private void setGUIForCurrentRow(int n){
        logic.setRef(logic.getFilteredList().get(n));
        
        jComboBox1.setSelectedItem(logic.getRef().getReferenceType());
        logic.setRef(logic.getFilteredList().get(n));
        logic.setCurrentRow(n);
        selectRow(n);
        referenceArea.setText(logic.currentRefToBibTex());
        setUpRequiredFileds(jPanel1);
        
    }
    private void documentAreaKeyRelesed(java.awt.event.KeyEvent evt) {
        int c = evt.getKeyCode();
        if (c==40 && logic.getCurrentRow()<logic.getFilteredList().size()-1){
             logic.setCurrentRow(logic.getCurrentRow()+1);
        } else  if (c==38 && logic.getCurrentRow()>0){
             logic.setCurrentRow(logic.getCurrentRow()-1);

        }
        setGUIForCurrentRow(logic.getCurrentRow());
        
    }

    private void jFilterKeyReleased(java.awt.event.KeyEvent evt) {
        String filterWord = jTextField1.getText().trim();
        logic.setFilter(filterWord);
        setDocumentArea(logic.getFilteredList());
    }
        
    private static void addTextField(Container container){
        JTextField text = new JTextField();
        container.add(text);
    }                    
    private javax.swing.JButton AddToDocument;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonOpen;
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

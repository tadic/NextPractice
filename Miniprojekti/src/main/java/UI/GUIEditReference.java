package UI;

import controllers.Logic;
import controllers.LogicInterface;
import entity.Book;
import entity.FType;
import entity.Field;
import entity.Reference;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.uncommons.swing.SpringUtilities;

public class GUIEditReference extends javax.swing.JFrame implements View {

    private LogicInterface logic;
    private MainGUIController controller;
   // private Reference ref;

    public GUIEditReference(Reference ref, List<Reference> oldList, MainGUIController controller) {
        logic = new Logic();
        logic.setOldList(oldList);
        logic.setRef(ref);
        logic.setOldId(ref.getFieldValue(FType.referenceId));
        logic.setOldReference(ref);
        this.controller = controller;

    }
    

    public void initAndShow() {
        initComponents();
        setGUIForReference(logic.getRef());
        this.setVisible(true);
    }

    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {
    }

    @Override
    public void registerController(MainGUIController c) {
        this.controller = c;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        //jComboBox1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        referenceArea = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        this.setTitle("BibTex editor");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
//        addWindowListener(new java.awt.event.WindowAdapter() {
//            public void windowActivated(java.awt.event.WindowEvent evt) {
//                formWindowActivated(evt);
//            }
//        });

 //       String[] names = new String[]{"article", "book", "booklet", "conference", "inbook", "incollection", "inproceedings", "manual", "mastersthesis", "misc", "phdthesis", "proceedings", "techreport", "unpublished" };
//        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(names));
//        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
//            public void itemStateChanged(java.awt.event.ItemEvent evt) {
//                jComboBox1ItemStateChanged(evt);
//            }
//        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(0, 460, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(0, 70, Short.MAX_VALUE));

        jLabel1.setText("Required Fields");

        jLabel2.setText("Optional Fields");

        referenceArea.setEditable(false);
        referenceArea.setColumns(20);
        referenceArea.setRows(5);
        jScrollPane1.setPreferredSize(new Dimension(400, 450));
        jScrollPane1.setViewportView(referenceArea);

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSavePerformed(evt);
            }
        });

        jLabel3.setText("Reference:");

        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelPerformed(evt);
            }
        });
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
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                //  .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .add(jScrollPane1)))))
                .add(jPanel3Layout.createSequentialGroup()
                .add(12, 12, 12)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel3Layout.createSequentialGroup()
                .add(3, 3, 3)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jLabel3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jButton2)
                .add(jButton3))
                .addContainerGap()));

        jLabel4.setText("Reference type: " + logic.getRef().getReferenceType());

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(0, 464, Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(0, 66, Short.MAX_VALUE));

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
                .add(3, 3, 3))
                //  .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 236, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                .add(22, 22, 22)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                //.add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));

        pack();
    }// </editor-fold>                        

    private void setUpForm(Reference r) {
        SpringLayout layout = new SpringLayout();
        //logic.createNewRef(r.getReferenceType());
        setUpFields(jPanel1, layout, logic.getRequiredFields());
        setUpFields(jPanel2, layout, logic.getOptionalFields());
        this.setTitle("BibteX editor");
        this.setVisible(true);
    }

    private void setUpFields(JPanel jpane, SpringLayout layout, List<Field> listOfFields) {
        jpane.removeAll();
        jpane.setLayout(layout);
        for (Field field : listOfFields) {
            JLabel label = new JLabel(field.getKey().toString(), JLabel.TRAILING);
            final JTextField text = new JTextField(30);
            label.setPreferredSize(new Dimension(75, 5));
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
                2, 2, //initX, initY
                2, 2);
        jpane.setVisible(true);
    }

    private void jButtonCancelPerformed(ActionEvent evt) {
        this.dispose();
    }

    private void jButtonSavePerformed(java.awt.event.ActionEvent evt) {
        try {
            
            logic.getRef().isUnique(logic.getOldList(), logic.getOldId());
            logic.setOldId(logic.getRef().getFieldValue(FType.referenceId));
            logic.getRef().isRegular();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Field Checker", JOptionPane.WARNING_MESSAGE);
            return;
        }
       controller.SaveEditetReference(logic.getRef());
    }

    private void setUpRequiredFileds(JPanel jPanel, Reference r) {
        for (int i = 1; i < jPanel.getComponentCount(); i += 2) {
            JTextField jt = (JTextField) jPanel.getComponent(i);
            jt.setText(r.getFields().get((i - 1) / 2).getValue());
        }
    }

    private void setGUIForReference(Reference r) {
        setUpForm(r);
        logic.setRef(r);
        referenceArea.setText(logic.currentRefToBibTex());
        setUpRequiredFileds(jPanel1, logic.getRef());
    }


    public static void main(String args[]) {
         Reference r = new Book();
        r.setFieldValue(FType.referenceId, "rrrrr");
        new GUIEditReference(r, null, null).initAndShow();
    }
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;    //private javax.swing.JButton jButtonOpen;
    //private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea referenceArea;
}

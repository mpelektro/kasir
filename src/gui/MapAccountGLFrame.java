/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.DefaultComboBoxModel;
import kasir.Clerk;
import pelajar.Level;
import org.netbeans.validation.api.Validator;
import org.netbeans.validation.api.ValidatorUtils;
import org.netbeans.validation.api.ui.swing.ValidationPanel;
import org.netbeans.validation.api.builtin.stringvalidation.StringValidators;
import org.openide.util.Exceptions;
import pelajar.Level.Level1;
import iuran.MapAccountGL;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import kasir.Control;
import sak.KasirException;
/**
 *
 * @author Master
 */
public class MapAccountGLFrame extends javax.swing.JFrame {
    private final ValidationPanel panelPrivate = new ValidationPanel();
    private Clerk clerk;
    /**
     * Creates new form MapAccountGLFrame
     */
    public MapAccountGLFrame(Clerk clerk) {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Level level = new Level();
        DefaultComboBoxModel level1ComboBoxModel = new DefaultComboBoxModel(level.level1.values());
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfNamaIuran = new javax.swing.JTextField();
        tfKeterangan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbLevel1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        tfAccountGLIuran = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfAccountGLKas = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfAccountGLBank = new javax.swing.JTextField();
        jButtonSave = new javax.swing.JButton();
        jButtonCance = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.jLabel1.text")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.jLabel2.text")); // NOI18N

        tfNamaIuran.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.tfNamaIuran.text")); // NOI18N

        tfKeterangan.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.tfKeterangan.text")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.jLabel3.text")); // NOI18N

        cbLevel1.setModel(level1ComboBoxModel);

        jLabel4.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.jLabel4.text")); // NOI18N

        tfAccountGLIuran.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.tfAccountGLIuran.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.jLabel5.text")); // NOI18N

        tfAccountGLKas.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.tfAccountGLKas.text")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.jLabel6.text")); // NOI18N

        tfAccountGLBank.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.tfAccountGLBank.text")); // NOI18N

        jButtonSave.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonCance.setText(org.openide.util.NbBundle.getMessage(MapAccountGLFrame.class, "MapAccountGLFrame.jButtonCance.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonCance))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(tfAccountGLBank, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                        .addComponent(tfAccountGLKas)
                        .addComponent(tfAccountGLIuran)
                        .addComponent(cbLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfKeterangan)
                        .addComponent(tfNamaIuran)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfNamaIuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(tfKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfAccountGLIuran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfAccountGLKas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfAccountGLBank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonCance))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        // TODO add your handling code here:
        String AccountGLBank = tfAccountGLBank.getText();
        String AccountGLKas = tfAccountGLKas.getText();
        String AccountGLIuran = tfAccountGLIuran.getText();
        String IuranName = tfNamaIuran.getText();
        String IuranLongName = tfKeterangan.getText();
        Level level = new Level();
        level.level1=(Level1) cbLevel1.getSelectedItem();
        MapAccountGL magl = new MapAccountGL(IuranName,level.level1, AccountGLIuran, IuranLongName, AccountGLKas, AccountGLBank,"", "", "");
        try {
            Control.insertMapAccGL(magl);
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
            JOptionPane.showMessageDialog(rootPane, "Connection to database error!\r\n".concat(ex.toString()));
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
            JOptionPane.showMessageDialog(rootPane, "Login Invalid!\r\n".concat(ex.toString()));
        }
        this.dispose();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MapAccountGLFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MapAccountGLFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MapAccountGLFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MapAccountGLFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
               
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbLevel1;
    private javax.swing.JButton jButtonCance;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField tfAccountGLBank;
    private javax.swing.JTextField tfAccountGLIuran;
    private javax.swing.JTextField tfAccountGLKas;
    private javax.swing.JTextField tfKeterangan;
    private javax.swing.JTextField tfNamaIuran;
    // End of variables declaration//GEN-END:variables
    private boolean validatePanel(){
        return !panelPrivate.isFatalProblem();
    }
}

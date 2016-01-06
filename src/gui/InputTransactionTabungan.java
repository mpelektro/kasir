/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import iuran.Iuran;
import iuran.Tabungan;
import iuran.TabunganTransactionDetail;
import iuran.TransactionDetail;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import kasir.Control;
import org.openide.util.Exceptions;
import pelajar.Profil;
import sak.Kalender;
import sak.KasirException;




/**
 *
 * @author Master
 */
public class InputTransactionTabungan extends javax.swing.JFrame {
    Profil profil;
    private Tabungan tabungan;
    private List<Tabungan> tabunganS;
    private List<Tabungan> tabunganSToDB;
    private float unpaidTabungan = 0f;
    private InputTransactionFrameSeparated itfs;
    private TableModel tableModelTabungan;
    /**
     * Creates new form InputTransactionTabungan
     */
    public InputTransactionTabungan(Profil profil, InputTransactionFrameSeparated itfs) {
        this.profil = profil;
        this.itfs = itfs;
        try{
            tableModelTabungan = buildTabunganTableModel(profil);
        }catch(SQLException ex){
            ex.printStackTrace();
        }catch(KasirException ex){
            ex.printStackTrace();
        }
        
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

        jPanelSeragam = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTabungan = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jUnpaidTabungan = new javax.swing.JTextField();
        jButtonBayarIll = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanelSeragam.setMinimumSize(new java.awt.Dimension(440, 550));
        jPanelSeragam.setPreferredSize(new java.awt.Dimension(480, 302));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setText(org.openide.util.NbBundle.getMessage(InputTransactionTabungan.class, "InputTransactionTabungan.jLabel10.text")); // NOI18N

        try{
            tableModelTabungan= buildTabunganTableModel(profil);
        }catch(Exception e){
            e.printStackTrace();
        }
        jTableTabungan.setModel(tableModelTabungan);
        jTableTabungan.getColumn("Detail / History").setCellRenderer(new TabunganButtonRenderer());
        jTableTabungan.getColumn("Detail / History").setCellEditor(
            new TabunganButtonEditor(new JCheckBox()));
        jTableTabungan.removeColumn(jTableTabungan.getColumn("IuranID"));
        jTableTabungan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableTabunganPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jTableTabungan);

        jLabel19.setText(org.openide.util.NbBundle.getMessage(InputTransactionTabungan.class, "InputTransactionTabungan.jLabel19.text")); // NOI18N

        jUnpaidTabungan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jUnpaidTabungan.setText(String.valueOf(unpaidTabungan));
        jUnpaidTabungan.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jUnpaidTabungan.setEnabled(false);

        jButtonBayarIll.setText(org.openide.util.NbBundle.getMessage(InputTransactionTabungan.class, "InputTransactionTabungan.jButtonBayarIll.text")); // NOI18N
        jButtonBayarIll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBayarIllActionPerformed(evt);
            }
        });

        jLabel1.setText(profil.biodata.nama);

        javax.swing.GroupLayout jPanelSeragamLayout = new javax.swing.GroupLayout(jPanelSeragam);
        jPanelSeragam.setLayout(jPanelSeragamLayout);
        jPanelSeragamLayout.setHorizontalGroup(
            jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSeragamLayout.createSequentialGroup()
                .addGroup(jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                    .addGroup(jPanelSeragamLayout.createSequentialGroup()
                        .addGroup(jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelSeragamLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(104, 104, 104)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jUnpaidTabungan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelSeragamLayout.createSequentialGroup()
                                .addGap(285, 285, 285)
                                .addComponent(jButtonBayarIll))
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelSeragamLayout.setVerticalGroup(
            jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSeragamLayout.createSequentialGroup()
                .addGroup(jPanelSeragamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel19)
                    .addComponent(jUnpaidTabungan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonBayarIll)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSeragam, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelSeragam, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableTabunganPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableTabunganPropertyChange
        // TODO add your handling code here:
        tabunganSToDB = new ArrayList<>();
        if (jTableTabungan.getRowCount() > 0) {
            for (int i = 0; i < jTableTabungan.getRowCount(); i++) {
//                if((Boolean)jTableTabungan.getValueAt(i,3) && (tabunganS.get(i).transactDetailIDs.size() == 0)){
                if(jTableTabungan.getModel().getValueAt(i,5) != null){
                    if((Float)jTableTabungan.getModel().getValueAt(i,5) > 0){
                        tabunganS.get(i).amount = (Float)jTableTabungan.getModel().getValueAt(i,5);
                        tabunganSToDB.add(tabunganS.get(i));
                    }
                }
                //                    try {
                //tabungan = Control.selectIuran(Iuran.Tipe.Tabungan, Tabungan.noIndukColName, false , profil.noInduk);
                //tabunganS.remove(i);
//                    } catch (SQLException ex) {
//                        Exceptions.printStackTrace(ex);
//                    } catch (KasirException ex) {
//                        Exceptions.printStackTrace(ex);
//                    }
//                }
            }
        }
    }//GEN-LAST:event_jTableTabunganPropertyChange

    private void jButtonBayarIllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBayarIllActionPerformed
        // TODO add your handling code here:
        float tabunganAmountTemp = 0f;

        for (int i = 0; i < tabunganSToDB.size(); i++) {
            if (tabunganSToDB.get(i) != null) {

                //tabunganAmountTemp = tabunganAmountTemp + (Float) jTableTabungan.getModel().getValueAt(i, 3);
                tabunganAmountTemp = tabunganAmountTemp + tabunganSToDB.get(i).amount;

            }
        }
        itfs.jTextFieldTabunganAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        itfs.jTextFieldTabunganAmountSimple.setValue(tabunganAmountTemp);
        try {
            itfs.jTextFieldTabunganAmountSimple.commitEdit();
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        itfs.tabunganSToDB = tabunganSToDB;
        itfs.tabunganS = tabunganS;
        this.setVisible(false);
    }//GEN-LAST:event_jButtonBayarIllActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public TableModel buildTabunganTableModel(Profil profil) throws SQLException, KasirException{
       String columnNames[] = {"Kelas","Detail / History", "Transaksi Tabungan", "Biaya", "Sisa", "Pembayaran", "IuranID"};
       Set<Tabungan> tabunganFilters = new HashSet<>();
       tabunganFilters.add(new Tabungan(profil.noInduk,null, null, 0F, null));
       Map<Long, Tabungan> searchResultMap = new HashMap<>();
       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Tabungan, tabunganFilters);
       
       Set<Long> transactDetailIDsTemp = new HashSet<Long>();
       int j = 0;
       tabunganS = new ArrayList<>();
       ArrayList<Long> tabunganSPaid = new ArrayList<Long>();
       ArrayList<String> tabunganSTransactionDate = new ArrayList<String>();
       ArrayList<TabunganTransactionDetail> stds = new ArrayList<TabunganTransactionDetail>();
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, Tabungan> entry: searchResultMap.entrySet()){
            transactDetailIDsTemp.clear();
            transactDetailIDsTemp = entry.getValue().transactDetailIDs;
            long paidTabungan = 0l;
            String lastTransactionDate = "";
            SimpleDateFormat formatIndo = new SimpleDateFormat("dd-MM-yyyy");
            TabunganTransactionDetail std = new TabunganTransactionDetail();
            stds.clear();
            for(Long l : transactDetailIDsTemp){
                std = Control.selectTDetail(TransactionDetail.Tipe.TabunganTransaction, l);
                paidTabungan += std.amount;
                lastTransactionDate = formatIndo.format(std.lastUpdateDate.toDate());
                tabunganSTransactionDate.add(lastTransactionDate);
                stds.add(std);
            }
            
            if(entry.getValue().amount > paidTabungan){
                tabunganS.add(entry.getValue());
                tabunganSPaid.add(paidTabungan);
            }
            j++;
        }
       }
       Object[][] data = new Object[tabunganS.size()][columnNames.length];
       for(int i =0; i < tabunganS.size(); i++){
            
            data[i][0]= tabunganS.get(i).chargedLevel;
            data[i][1]= "Detail";
            data[i][2]= tabunganS.get(i).transactName;
            data[i][3]= tabunganS.get(i).amount;
            data[i][4]= tabunganS.get(i).amount-tabunganSPaid.get(i);
            data[i][5]= 0f;
            data[i][6]= tabunganS.get(i).id;
//            if(tabunganS.get(i).transactDetailIDs.size()>0){
//                data[i][3] = new Boolean(true);
//            }else{
//                data[i][3] = new Boolean(false);
//            }
       }
       itfs.unpaidTabungan = calculateUnpaidTabungan(profil);
       if(data.length > 0){
       TableModel tm = new DefaultTableModel(data, columnNames){
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false for editable
               if(itfs.jDialogTransactionSummary.isVisible()){
                    return false;
               }else{
                    return true;
               }
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
       };
       
       return tm;
       }else{
           TableModel tm = new DefaultTableModel(columnNames, columnNames.length);
           return tm;
       }
   }
    
    public TableModel buildTabunganSubmitTableModel(Profil profil, List<Tabungan> stdb) throws SQLException, KasirException{
       String columnNames[] = {"Kelas","Detail / History", "Transaksi Tabungan", "Biaya", "Sisa", "Pembayaran", "IuranID"};
       
       Set<Tabungan> tabunganFilters = new HashSet<>();
       Set<Long> tabunganIDs = new HashSet<>();
       for(int i = 0 ; i < stdb.size(); i++){
           tabunganIDs.add(stdb.get(i).id);
       }
       tabunganFilters.addAll(stdb);
       Map<Long, Tabungan> searchResultMap = new HashMap<>();
       //searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Tabungan, tabunganFilters);
       searchResultMap = Control.selectIurans(Iuran.Tipe.Tabungan, tabunganIDs);
       Set<Long> transactDetailIDsTemp = new HashSet<Long>();
       int j = 0;
       long paidTabungan = 0l;
       tabunganS = new ArrayList<>();  
       ArrayList<Long> tabunganSPaid = new ArrayList<Long>();
       ArrayList<Kalender> tabunganSTransactionDate = new ArrayList<Kalender>();
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, Tabungan> entry: searchResultMap.entrySet()){
            transactDetailIDsTemp.clear();
            transactDetailIDsTemp = entry.getValue().transactDetailIDs;
            paidTabungan = 0l;
            Kalender lastTransactionDate = new Kalender();
            TabunganTransactionDetail std = new TabunganTransactionDetail();
            for(Long l : transactDetailIDsTemp){
                std = Control.selectTDetail(TransactionDetail.Tipe.TabunganTransaction, l);
                paidTabungan += std.amount;
                lastTransactionDate = std.lastUpdateDate;
                tabunganSTransactionDate.add(lastTransactionDate);
            }
            if(transactDetailIDsTemp.isEmpty()){
                tabunganSTransactionDate.add(new Kalender());
            }
            
            if(entry.getValue().amount > paidTabungan){
                tabunganS.add(entry.getValue());
                tabunganSPaid.add(paidTabungan);
                
            }
            j++;
        }
       }
       Object[][] data = new Object[tabunganS.size()][columnNames.length];
       for(int i =0; i < tabunganS.size(); i++){
            data[i][0]= tabunganS.get(i).chargedLevel;
            data[i][1]= tabunganSTransactionDate.get(i);
            data[i][2]= tabunganS.get(i).transactName;
            data[i][3] = tabunganS.get(i).amount;
            data[i][4] = tabunganS.get(i).amount - stdb.get(i).amount - tabunganSPaid.get(i);
            data[i][5] = stdb.get(i).amount;
            data[i][6] = tabunganS.get(i).id;
            itfs.unpaidTabungan = itfs.unpaidTabungan - stdb.get(i).amount;
       }
       
      
       if(data.length > 0){
       TableModel tm = new DefaultTableModel(data, columnNames){
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false for editable
               if(itfs.jDialogTransactionSummary.isVisible()){
                    return false;
               }else{
                    return true;
               }
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
       };
       
       return tm;
       }else{
           TableModel tm = new DefaultTableModel(columnNames, columnNames.length);
           return tm;
       }
   }
    
    private float calculateUnpaidTabungan(Profil profil) throws SQLException, KasirException {
       float retVal = 0;
       Set<Tabungan> tabunganFilters = new HashSet<>();
       tabunganFilters.add(new Tabungan(profil.noInduk,null, null, 0F, null));
       Map<Long, Tabungan> searchResultMap = new HashMap<>();
       Set<Long> transactDetailIDsTemp = new HashSet<Long>();
       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.Tabungan, tabunganFilters);
       tabunganS = new ArrayList<>();
       
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, Tabungan> entry: searchResultMap.entrySet()){
            transactDetailIDsTemp.clear();
            transactDetailIDsTemp = entry.getValue().transactDetailIDs;
            long paidTabungan = 0l;
            for(Long l : transactDetailIDsTemp){
                paidTabungan += Control.selectTDetail(TransactionDetail.Tipe.TabunganTransaction, l).amount;
            }
            
            if(entry.getValue().amount > paidTabungan){
                tabunganS.add(entry.getValue());
            }
            retVal += entry.getValue().amount - paidTabungan;
        }
       }
       unpaidTabungan = retVal;
       System.out.println("unpaid Tabungan");
       System.out.println(unpaidTabungan);
       return retVal;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBayarIll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanelSeragam;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTableTabungan;
    private javax.swing.JTextField jUnpaidTabungan;
    // End of variables declaration//GEN-END:variables
}
class TabunganButtonRenderer extends JButton implements TableCellRenderer {

  public TabunganButtonRenderer() {
    setOpaque(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText((value == null) ? "" : value.toString());
    return this;
  }
}

/**
 * @version 1.0 11/09/98
 */

class TabunganButtonEditor extends DefaultCellEditor {
  protected JButton button;

  private String label;
  private String detail;
  private boolean isPushed;
  private JTable detailTable;
  private JScrollPane scrollPane;
  private ArrayList<TabunganTransactionDetail> stds;
  public TabunganButtonEditor(JCheckBox checkBox) {
    super(checkBox);
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    SimpleDateFormat formatIndo = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    Set<Long> transactDetailIDsTemp = new HashSet<Long>();
    Tabungan s;
    Long tabunganID = (Long)table.getModel().getValueAt(row, 6);
    try {
        s = Control.selectIuran(Iuran.Tipe.Tabungan, tabunganID);
        transactDetailIDsTemp = s.transactDetailIDs;
    } catch (SQLException ex) {
        Exceptions.printStackTrace(ex);
    } catch (KasirException ex) {
        Exceptions.printStackTrace(ex);
    }
    TabunganTransactionDetail std = new TabunganTransactionDetail();
    detail = "";
    
    String columnNames[] = {"No", "Tanggal", "Pembayaran"};
    Object[][] data = new Object[transactDetailIDsTemp.size()][columnNames.length];
    int i = 0;
    for(Long l : transactDetailIDsTemp){
            try {
                std = Control.selectTDetail(TransactionDetail.Tipe.TabunganTransaction, l);
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            } catch (KasirException ex) {
                Exceptions.printStackTrace(ex);
            }
            
            detail += String.valueOf(i);
            detail += ". Rp ";
            detail += String.valueOf(std.amount);
            detail += "\t";
            detail += formatIndo.format(std.lastUpdateDate.toDate());
            detail += "\r\n";
            
            data[i][0] = i+1;
            data[i][1] = formatIndo.format(std.lastUpdateDate.toDate());
            data[i][2] = "Rp ".concat(String.valueOf((Float)std.amount));
            i++;
    }
    
    TableModel tm = new DefaultTableModel(data, columnNames){
           @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false for editable
               return false;
            }
            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }
       };
    
    detailTable = new JTable(tm){
        public Class getColumnClass(int c){
            return getValueAt(0, c).getClass();
        }
    };
    
    
    scrollPane = new JScrollPane(detailTable);
    
    
    label = (value == null) ? "" : value.toString();
    
    button.setText(label);
    isPushed = true;
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed) {
      // 
      // 
      JOptionPane.showMessageDialog(button, scrollPane, "Tabungan", JOptionPane.INFORMATION_MESSAGE);
      
      // System.out.println(label + ": Ouch!");
    }
    isPushed = false;
    return new String(label);
  }

  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}

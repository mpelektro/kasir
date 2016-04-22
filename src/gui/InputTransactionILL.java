/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import iuran.Iuran;
import iuran.ILL;
import iuran.ILLTransactionDetail;
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
public class InputTransactionILL extends javax.swing.JFrame {
    Profil profil;
    private ILL ill;
    private List<ILL> illS;
    private List<ILL> illSToDB;
    private float unpaidILL = 0f;
    private InputTransactionFrameSeparated itfs;
    private TableModel tableModelILL;
    /**
     * Creates new form InputTransactionILL
     */
    public InputTransactionILL(Profil profil, InputTransactionFrameSeparated itfs) {
        this.profil = profil;
        this.itfs = itfs;
        try{
            tableModelILL = buildILLTableModel(profil);
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
        jTableILL = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jUnpaidIll = new javax.swing.JTextField();
        jButtonBayarIll = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jPanelSeragam.setMinimumSize(new java.awt.Dimension(440, 550));
        jPanelSeragam.setPreferredSize(new java.awt.Dimension(480, 302));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setText(org.openide.util.NbBundle.getMessage(InputTransactionILL.class, "InputTransactionILL.jLabel10.text")); // NOI18N

        try{
            tableModelILL = buildILLTableModel(profil);
        }catch(Exception e){
            e.printStackTrace();
        }
        jTableILL.setModel(tableModelILL);
        jTableILL.getColumn("Detail / History").setCellRenderer(new ILLButtonRenderer());
        jTableILL.getColumn("Detail / History").setCellEditor(
            new ILLButtonEditor(new JCheckBox()));
        jTableILL.removeColumn(jTableILL.getColumn("IuranID"));
        jTableILL.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableILLPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jTableILL);

        jLabel19.setText(org.openide.util.NbBundle.getMessage(InputTransactionILL.class, "InputTransactionILL.jLabel19.text")); // NOI18N

        jUnpaidIll.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jUnpaidIll.setText(String.valueOf(unpaidILL));
        jUnpaidIll.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jUnpaidIll.setEnabled(false);

        jButtonBayarIll.setText(org.openide.util.NbBundle.getMessage(InputTransactionILL.class, "InputTransactionILL.jButtonBayarIll.text")); // NOI18N
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
                                .addComponent(jUnpaidIll, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jUnpaidIll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jTableILLPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableILLPropertyChange
        // TODO add your handling code here:
        illSToDB = new ArrayList<>();
        if (jTableILL.getRowCount() > 0) {
            for (int i = 0; i < jTableILL.getRowCount(); i++) {
//                if((Boolean)jTableILL.getValueAt(i,3) && (illS.get(i).transactDetailIDs.size() == 0)){
                if(jTableILL.getModel().getValueAt(i,5) != null){
                    if((Float)jTableILL.getModel().getValueAt(i,5) > 0){
                        illS.get(i).amount = (Float)jTableILL.getModel().getValueAt(i,5);
                        illSToDB.add(illS.get(i));
                    }
                }
                //                    try {
                //ill = Control.selectIuran(Iuran.Tipe.ILL, ILL.noIndukColName, false , profil.noInduk);
                //illS.remove(i);
//                    } catch (SQLException ex) {
//                        Exceptions.printStackTrace(ex);
//                    } catch (KasirException ex) {
//                        Exceptions.printStackTrace(ex);
//                    }
//                }
            }
        }
    }//GEN-LAST:event_jTableILLPropertyChange

    private void jButtonBayarIllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBayarIllActionPerformed
        // TODO add your handling code here:
        float illAmountTemp = 0f;

        for (int i = 0; i < illSToDB.size(); i++) {
            if (illSToDB.get(i) != null) {

                //illAmountTemp = illAmountTemp + (Float) jTableILL.getModel().getValueAt(i, 3);
                illAmountTemp = illAmountTemp + illSToDB.get(i).amount;

            }
        }
        itfs.jTextFieldILLAmountSimple.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));
        itfs.jTextFieldILLAmountSimple.setValue(illAmountTemp);
        try {
            itfs.jTextFieldILLAmountSimple.commitEdit();
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        itfs.illSToDB = illSToDB;
        itfs.illS = illS;
        this.setVisible(false);
    }//GEN-LAST:event_jButtonBayarIllActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public TableModel buildILLTableModel(Profil profil) throws SQLException, KasirException{
       String columnNames[] = {"Kelas","Detail / History", "Transaksi ILL", "Biaya", "Sisa", "Pembayaran", "IuranID"};
       Set<ILL> illFilters = new HashSet<>();
       illFilters.add(new ILL(profil.noInduk,null, null, 0F, null));
       Map<Long, ILL> searchResultMap = new HashMap<>();
       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.ILL, illFilters);
       
       Set<Long> transactDetailIDsTemp = new HashSet<Long>();
       int j = 0;
       illS = new ArrayList<>();
       ArrayList<Long> illSPaid = new ArrayList<Long>();
       ArrayList<String> illSTransactionDate = new ArrayList<String>();
       ArrayList<ILLTransactionDetail> stds = new ArrayList<ILLTransactionDetail>();
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, ILL> entry: searchResultMap.entrySet()){
            transactDetailIDsTemp.clear();
            transactDetailIDsTemp = entry.getValue().transactDetailIDs;
            long paidILL = 0l;
            String lastTransactionDate = "";
            SimpleDateFormat formatIndo = new SimpleDateFormat("dd-MM-yyyy");
            ILLTransactionDetail std = new ILLTransactionDetail();
            stds.clear();
            for(Long l : transactDetailIDsTemp){
                std = Control.selectTDetail(TransactionDetail.Tipe.ILLTransaction, l);
                paidILL += std.amount;
                lastTransactionDate = formatIndo.format(std.lastUpdateDate.toDate());
                illSTransactionDate.add(lastTransactionDate);
                stds.add(std);
            }
            
            if(entry.getValue().amount > paidILL){
                illS.add(entry.getValue());
                illSPaid.add(paidILL);
            }
            j++;
        }
       }
       Object[][] data = new Object[illS.size()][columnNames.length];
       for(int i =0; i < illS.size(); i++){
            
            data[i][0]= illS.get(i).chargedLevel;
            data[i][1]= "Detail";
            data[i][2]= illS.get(i).transactName;
            data[i][3]= illS.get(i).amount;
            data[i][4]= illS.get(i).amount-illSPaid.get(i);
            data[i][5]= 0f;
            data[i][6]= illS.get(i).id;
//            if(illS.get(i).transactDetailIDs.size()>0){
//                data[i][3] = new Boolean(true);
//            }else{
//                data[i][3] = new Boolean(false);
//            }
       }
       itfs.unpaidILL = calculateUnpaidILL(profil);
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
    
    public TableModel buildILLSubmitTableModel(Profil profil, List<ILL> stdb) throws SQLException, KasirException{
       String columnNames[] = {"Kelas","Detail / History", "Transaksi ILL", "Biaya", "Sisa", "Pembayaran", "IuranID"};
       
       Set<ILL> illFilters = new HashSet<>();
       Set<Long> illIDs = new HashSet<>();
       for(int i = 0 ; i < stdb.size(); i++){
           illIDs.add(stdb.get(i).id);
       }
       illFilters.addAll(stdb);
       Map<Long, ILL> searchResultMap = new HashMap<>();
       //searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.ILL, illFilters);
       searchResultMap = Control.selectIurans(Iuran.Tipe.ILL, illIDs);
       Set<Long> transactDetailIDsTemp = new HashSet<Long>();
       int j = 0;
       long paidILL = 0l;
       illS = new ArrayList<>();  
       ArrayList<Long> illSPaid = new ArrayList<Long>();
       ArrayList<Kalender> illSTransactionDate = new ArrayList<Kalender>();
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, ILL> entry: searchResultMap.entrySet()){
            transactDetailIDsTemp.clear();
            transactDetailIDsTemp = entry.getValue().transactDetailIDs;
            paidILL = 0l;
            Kalender lastTransactionDate = new Kalender();
            ILLTransactionDetail std = new ILLTransactionDetail();
            for(Long l : transactDetailIDsTemp){
                std = Control.selectTDetail(TransactionDetail.Tipe.ILLTransaction, l);
                paidILL += std.amount;
                lastTransactionDate = std.lastUpdateDate;
                illSTransactionDate.add(lastTransactionDate);
            }
            if(transactDetailIDsTemp.isEmpty()){
                illSTransactionDate.add(new Kalender());
            }
            
            if(entry.getValue().amount > paidILL){
                illS.add(entry.getValue());
                illSPaid.add(paidILL);
                
            }
            j++;
        }
       }
       Object[][] data = new Object[illS.size()][columnNames.length];
       for(int i =0; i < illS.size(); i++){
            data[i][0]= illS.get(i).chargedLevel;
            data[i][1]= illSTransactionDate.get(i);
            data[i][2]= illS.get(i).transactName;
            data[i][3] = illS.get(i).amount;
            data[i][4] = illS.get(i).amount - stdb.get(i).amount - illSPaid.get(i);
            data[i][5] = stdb.get(i).amount;
            data[i][6] = illS.get(i).id;
            itfs.unpaidILL = itfs.unpaidILL - stdb.get(i).amount;
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
    
    private float calculateUnpaidILL(Profil profil) throws SQLException, KasirException {
       float retVal = 0;
       Set<ILL> illFilters = new HashSet<>();
       illFilters.add(new ILL(profil.noInduk,null, null, 0F, null));
       Map<Long, ILL> searchResultMap = new HashMap<>();
       Set<Long> transactDetailIDsTemp = new HashSet<Long>();
       searchResultMap = Control.exactFilterSelectIurans(Iuran.Tipe.ILL, illFilters);
       illS = new ArrayList<>();
       
       if(searchResultMap.size() > 0){
        for(Map.Entry<Long, ILL> entry: searchResultMap.entrySet()){
            transactDetailIDsTemp.clear();
            transactDetailIDsTemp = entry.getValue().transactDetailIDs;
            long paidILL = 0l;
            for(Long l : transactDetailIDsTemp){
                paidILL += Control.selectTDetail(TransactionDetail.Tipe.ILLTransaction, l).amount;
            }
            
            if(entry.getValue().amount > paidILL){
                illS.add(entry.getValue());
            }
            retVal += entry.getValue().amount - paidILL;
        }
       }
       unpaidILL = retVal;
       System.out.println("unpaid ILL");
       System.out.println(unpaidILL);
       return retVal;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonBayarIll;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JPanel jPanelSeragam;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTableILL;
    private javax.swing.JTextField jUnpaidIll;
    // End of variables declaration//GEN-END:variables
}


class ILLButtonRenderer extends JButton implements TableCellRenderer {

  public ILLButtonRenderer() {
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

class ILLButtonEditor extends DefaultCellEditor {
  protected JButton button;

  private String label;
  private String detail;
  private boolean isPushed;
  private JTable detailTable;
  private JScrollPane scrollPane;
  private ArrayList<ILLTransactionDetail> stds;
  public ILLButtonEditor(JCheckBox checkBox) {
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
    ILL s;
    Long illID = (Long)table.getModel().getValueAt(row, 6);
    try {
        s = Control.selectIuran(Iuran.Tipe.ILL, illID);
        transactDetailIDsTemp = s.transactDetailIDs;
    } catch (SQLException ex) {
        Exceptions.printStackTrace(ex);
    } catch (KasirException ex) {
        Exceptions.printStackTrace(ex);
    }
    ILLTransactionDetail std = new ILLTransactionDetail();
    detail = "";
    
    String columnNames[] = {"No", "Tanggal", "Pembayaran"};
    Object[][] data = new Object[transactDetailIDsTemp.size()][columnNames.length];
    int i = 0;
    for(Long l : transactDetailIDsTemp){
            try {
                std = Control.selectTDetail(TransactionDetail.Tipe.ILLTransaction, l);
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
      JOptionPane.showMessageDialog(button, scrollPane, "ILL", JOptionPane.INFORMATION_MESSAGE);
      
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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.*;
import java.util.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import pelajar.*;
import sak.*;

/**
 *
 * @author kedra
 */
public abstract class IuranRegular<IuranType extends IuranRegular, TDetailType extends TransactionDetailRegular> extends Iuran<IuranType>{
    public static final String transactNameColName = "TransactionName", amountColName = "Amount", debtColName = "Debt", noteColName = "Note", transactDetailIDsColName = "IDsTransactionDetail";
    
    //public enum Tipe {SERAGAM, PASB, BUKU, SUMBANGAN, ILL, TABUNGAN, IDD, BEASISWA, BEASISWACOST}
    
    public String transactName;
    public float amount;
    public float debt;
    public String note;
    public Set<Long> transactDetailIDs;
    
    //create filter
    protected IuranRegular(String noInduk, Level chargedLevel, String tName, float amount, float debt, String note, Set<Long> tDetailID){
        super(noInduk, chargedLevel);
        transactName = tName;
        this.amount = amount;
        this.debt = debt;
        this.note = note;
        transactDetailIDs = tDetailID;
    }
    
    //create for insertion
    protected IuranRegular(String noInduk, Level chargedLevel, String tName, float amount, String note){
        super(noInduk, chargedLevel);
        transactName = tName;
        this.amount = amount;
        debt = amount;
        this.note = note;
    }
    
    //create from db
    protected IuranRegular(){
        transactDetailIDs = new HashSet<>();
    }
    
    protected IuranRegular(IuranType ir){
        super(ir);
        
        transactName = ir.transactName;
        amount = ir.amount;
        note = ir.note;
    }
    
    public static String toStringHeader(){
        return Iuran.toStringHeader() + "|" + transactNameColName + "|" + amountColName + "|" + debtColName + "|" + noteColName + "|" + transactDetailIDsColName;
    }
    public String toString(){
        return super.toString() + "|" + transactName + "|" + amountColName + "|" + debtColName + "|" + noteColName + "|" + transactDetailIDs;
    }
    public boolean equals(IuranType iuran){
        return super.equals(iuran) && ObjectUtils.equals(transactName, iuran.transactName) && amount == iuran.amount && debt == iuran.debt && ObjectUtils.equals(note, iuran.note) && transactDetailIDs == iuran.transactDetailIDs;
    }
    
    
    protected IuranType dynFromResultSet(ResultSet rs) throws SQLException{
        super.dynFromResultSet(rs);
        
        transactName = rs.getString(transactNameColName);
        amount = rs.getFloat(amountColName);
        debt = rs.getFloat(debtColName);
        note = rs.getString(noteColName);
        
        String tDetailIDs[] = StringUtils.split(rs.getString(transactDetailIDsColName), ",");
        if(tDetailIDs != null){
            for(String tDetailID : tDetailIDs)
                transactDetailIDs.add(Long.valueOf(tDetailID));
        }
        
        return (IuranType) this;
    }
    
    public boolean flushResultSet(ResultSet rs) throws SQLException{
        super.flushResultSet(rs);
        
        rs.updateString(transactNameColName, transactName);
        rs.updateFloat(amountColName, amount);
        rs.updateFloat(debtColName, debt);
        rs.updateString(noteColName, note);
        if(transactDetailIDs != null)
            rs.updateString(transactDetailIDsColName, StringUtils.join(transactDetailIDs, ","));
        
        return true;
    }
    
    //ret empty if all vars are empty
    public String asWhereClause(){
        LinkedList<String> whereClause = new LinkedList<>();
        
        String superWC = super.asWhereClause();
        if(superWC != null && !superWC.isEmpty())
            whereClause.add(super.asWhereClause());
        
        if(transactName != null && !transactName.isEmpty())
            whereClause.add(transactNameColName + " LIKE '%" + transactName + "%'");
        if(amount > 0)
            whereClause.add(amountColName + " = '" + amount + "'");
        if(debt > 0)
            whereClause.add(debtColName + " = '" + debt + "'");
        if(note != null && !note.isEmpty())
            whereClause.add(noteColName + " LIKE '%" + note + "%'");
        if(transactDetailIDs != null && !transactDetailIDs.isEmpty())
            whereClause.add(transactDetailIDsColName + " = '" + transactDetailIDs + "'");
        
        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    public String asWhereClauseExact(){
        LinkedList<String> whereClause = new LinkedList<>();
        
        String superWC = super.asWhereClauseExact();
        if(superWC != null && !superWC.isEmpty())
            whereClause.add(super.asWhereClauseExact());
        
        if(transactName != null && !transactName.isEmpty())
            whereClause.add(transactNameColName + " = '" + transactName + "'");
        if(amount > 0)
            whereClause.add(amountColName + " = '" + amount + "'");
        if(debt > 0)
            whereClause.add(debtColName + " = '" + debt + "'");
        if(note != null && !note.isEmpty())
            whereClause.add(noteColName + " LIKE '%" + note + "%'");
        if(transactDetailIDs != null && !transactDetailIDs.isEmpty())
            whereClause.add(transactDetailIDsColName + " = '" + transactDetailIDs + "'");
        
        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    
    //=====================================
    public void retrieveTransactionDetail() throws SQLException, KasirException{
        //transactDetailID = Control.selectTDetail(getTipeTDetail(), transactDetailIDs.id);
    }
    public void storeTransactionDetail() throws SQLException, KasirException{
        //Control.updateInsertTDetail(getTipeTDetail(), transactDetailIDs);
    }
}

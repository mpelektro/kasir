package iuran;

import java.sql.*;
import java.util.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import pelajar.*;
import sak.*;

public abstract class IuranOnce<IuranType extends IuranOnce, TDetailType extends TransactionDetailOnce> extends Iuran<IuranType> {
    public static final String amountColName = "Amount", debtColName = "Debt", totalInstallmentColName = "TotalInstallment", settledDateColName = "SettledDate";
    public static final String noteColName = "Note", transactDetailIDsColName = "IDsTransactionDetail";

    //public enum Tipe {IPSP, IPS, IUA, IPSB}
    
    public float amount;
    public float debt;
    public float totalInstallment;
    public Kalender settledDate;
    public String note;
    public Set<Long> transactDetailIDs;
    
    //create filter
    protected IuranOnce(String noInduk, Level chargedLevel, float amount, float debt, float totalInstallment, Kalender settledDate, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel);
        
        this.amount = amount;
        this.debt = debt;
        this.totalInstallment = totalInstallment;
        this.settledDate = settledDate;
        this.note = note;
        transactDetailIDs = tDetailIDs;
    }
    
    //create for insertion
    protected IuranOnce(String noInduk, Level chargedLevel, float amount, String note){
        this(noInduk, chargedLevel, amount, amount, 0, null, note, null);
    }
    
    //create from db
    protected IuranOnce(){
        transactDetailIDs = new HashSet<>();
    }
    
    protected IuranOnce(IuranType io){
        super(io);
        amount = io.amount;
        debt = io.debt;
        totalInstallment = io.totalInstallment;
        settledDate = io.settledDate;
        note = io.note;
        transactDetailIDs = io.transactDetailIDs;
    }
    
    
    public static String toStringHeader(){
        return Iuran.toStringHeader() + "|" + amountColName + "|" + debtColName + "|" + totalInstallmentColName + "|" + settledDateColName + "|" + noteColName + "|" + transactDetailIDsColName;
    }
    public String toString(){        
        return super.toString() + "|" + amount + "|" + debt + "|" + totalInstallment + "|" + settledDate + "|" + note + "|" + StringUtils.join(transactDetailIDs, ", ");
    }
    public boolean equals(IuranType iuran){
        boolean tmp = super.equals(iuran) && amount == iuran.amount && debt == iuran.debt && totalInstallment == iuran.totalInstallment;
        return tmp && ObjectUtils.equals(settledDate, iuran.settledDate) && ObjectUtils.equals(note, iuran.note) && ObjectUtils.equals(transactDetailIDs, iuran.transactDetailIDs);
    }
    
    
    protected IuranType dynFromResultSet(ResultSet rs) throws SQLException{
        super.dynFromResultSet(rs);
        
        amount = rs.getFloat(amountColName);
        debt = rs.getFloat(debtColName);
        totalInstallment = rs.getFloat(totalInstallmentColName);
        settledDate = Kalender.fromResultSet(rs,settledDateColName);
        note = rs.getString(noteColName);
        
        String ids[] = StringUtils.split(rs.getString(transactDetailIDsColName), ",");
        if (ids != null) {
            for (String tDetailID : ids)
                transactDetailIDs.add(Long.valueOf(tDetailID));
        }
        
        return (IuranType) this;
    }

    /* caller of this method must ensure that isDBValid = true & RS cursor points to the intended row
     * true, never ret false
     */
    public boolean flushResultSet(ResultSet rs) throws SQLException {
        super.flushResultSet(rs);
        
        rs.updateFloat(amountColName, amount);
        rs.updateFloat(debtColName, debt);
        rs.updateFloat(totalInstallmentColName, totalInstallment);
        if(settledDate != null)
            settledDate.flushResultSet(rs, settledDateColName);
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
        
        if (amount > 0)
            whereClause.add(amountColName + " = '" + amount + "'");
        
        if(debt > 0)
            whereClause.add(debtColName + " = '" + debt + "'");
        
        if (totalInstallment > 0)
            whereClause.add(totalInstallmentColName + " = '" + totalInstallment + "'");
        
        if (settledDate != null)
                whereClause.add(settledDateColName + " LIKE '%" + settledDate.asWhereClause(true) + "%'");
        
        if (note != null && !note.isEmpty())
            whereClause.add(noteColName + " LIKE '%" + note + "%'");
        
        if (transactDetailIDs != null && !transactDetailIDs.isEmpty())
            whereClause.add(transactDetailIDsColName + " LIKE '%" + StringUtils.join(transactDetailIDs, ",") + "%'");

        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    public String asWhereClauseExact(){
        LinkedList<String> whereClause = new LinkedList<>();
        
        String superWC = super.asWhereClauseExact();
        if(superWC != null && !superWC.isEmpty())
            whereClause.add(super.asWhereClauseExact());
        
        if (amount > 0)
            whereClause.add(amountColName + " = '" + amount + "'");
        
        if(debt > 0)
            whereClause.add(debtColName + " = '" + debt + "'");
        
        if (totalInstallment > 0)
            whereClause.add(totalInstallmentColName + " = '" + totalInstallment + "'");
        
        if (settledDate != null)
                whereClause.add(settledDateColName + " = '" + settledDate.asWhereClause(true) + "'");
        
        if (note != null && !note.isEmpty())
            whereClause.add(noteColName + " LIKE '%" + note + "%'");
        
        if (transactDetailIDs != null && !transactDetailIDs.isEmpty())
            whereClause.add(transactDetailIDsColName + " LIKE '%" + StringUtils.join(transactDetailIDs, ",") + "%'");

        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    
    //=========================================
    public void retrieveTransactionDetail() throws SQLException, KasirException{
        //transactDetails = TransactionDetail.selectS(getTipeTDetail(), transactDetails.keySet());
    }
    
    /* call this when there's a new TDetail added to transactTDetails
     * caller can also keep refs to new tDetails added to transactDetails, in this case, caller may have the same effect of this method by
     * inserting those new tDetails & updating existing tDetails in trasactDetails
     */
    public void storeTransactionDetail() throws SQLException, KasirException{
        //ArrayList<TDetailType> transactDetailsList = new ArrayList<>(transactDetails.values());
        
        //TransactionDetail.updateInsertS(getTipeTDetail(), transactDetailsList);
    }
}
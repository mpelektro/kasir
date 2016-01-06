package iuran;

import java.sql.*;
import java.util.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import pelajar.*;

public abstract class IuranPeriodic<IuranType extends IuranPeriodic, TDetailType extends TransactionDetailPeriodic> extends Iuran<IuranType>{
    public static final int colIndexOffset = 4;
    
    public static final String amountColName = "Amount", debtColName = "Debt", IDTransactionDetailColName = "IDsTransactionDetail";
    
    //public enum Tipe {IPP, IUS, IUAP, OSIS, IKS, PVT, CicilanHutang}
    
    public ArrayList<Entry> entries;
    
    //create filter
    public IuranPeriodic(String noInduk, Level chargedLevel, ArrayList<Entry> entries){
        super(noInduk, chargedLevel);
        this.entries = entries;
    }
    
    //create for insertion
    public IuranPeriodic(String noInduk, Level chargedLevel){
        this(noInduk, chargedLevel, null);
    }
    
    //create from db
    public IuranPeriodic(){
        entries = new ArrayList<>();
    }
    public IuranPeriodic(IuranType ip){
        super(ip);
        entries = ip.entries;
    }
    
    public abstract int getPeriodInMonth();
    
    public String toString(){
        String tmp = super.toString();
        for(int i = 0; i < entries.size(); ++i){
            Entry entry = entries.get(i);
            if(entry != null)
                tmp += "|" + entry.amount + "|" + entry.debt + "|" + entry.transactDetailIDs;
        }
        return tmp;
    }
    public boolean equals(IuranType iuran){
        return super.equals(iuran) && ObjectUtils.equals(entries, iuran.entries);
    }
    
    protected IuranType dynFromResultSet(ResultSet rs) throws SQLException{
        super.dynFromResultSet(rs);
        
        for(int i = 0; i < (12/getPeriodInMonth()); ++i){
            Set<Long> tDetailIDsTmp = new HashSet<>();
            String tDetailIDs[] = StringUtils.split(rs.getString((3*i) + colIndexOffset + 2), ",");
            if(tDetailIDs != null){
                for(String tDetailID : tDetailIDs)
                        tDetailIDsTmp.add(Long.valueOf(tDetailID));
            }
            
            entries.add(new Entry(i+1, rs.getFloat((3*i) + colIndexOffset), rs.getFloat((3*i) + colIndexOffset + 1), tDetailIDsTmp));
        }
        
        return (IuranType) this;
    }
    
    public boolean flushResultSet(ResultSet rs) throws SQLException{
        super.flushResultSet(rs);
        
        for(int i = 0, j = colIndexOffset; i < entries.size(); ++i, j += 3){
            Entry entry = entries.get(i);
            if(entry != null){
                rs.updateFloat(j, entry.amount);
                rs.updateFloat(j+1, entry.debt);
                if(entry.transactDetailIDs != null)
                    rs.updateString(j+2, StringUtils.join(entry.transactDetailIDs, ","));
            }
        }
        
        return true;
    }
    
    //==================================
    /*
    public void retrieveTransactionDetail() throws SQLException, KasirException{        
        Set<Long> tDetailIDs = new HashSet<>();
        for(int i = 0; i < entries.size(); ++i)
            tDetailIDs.add(entries.get(i).transactDetailIDs);

        Map<Long, TDetailType> tDetails = Control.selectTDetails(getTipeTDetail(), tDetailIDs);
        for(int i = 0; i < entries.size(); ++i)
            entries.get(i).transactDetail = tDetails.get(entries.get(i).transactDetailIDs);
    }*/
    /*
    public void storeTransactionDetail() throws SQLException, KasirException{
        ArrayList<TDetailType> transactDetailsList = new ArrayList<>();
        
        for(int i = 0; i < entries.size(); ++i)
            transactDetailsList.add((TDetailType)entries.get(i).transactDetail);
        
        Control.updateInsertTDetails(getTipeTDetail(), transactDetailsList);
    }*/
}


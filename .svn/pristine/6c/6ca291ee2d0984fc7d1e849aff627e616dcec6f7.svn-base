/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.util.*;
import java.sql.*;
import sak.*;
import pelajar.Level;

/**
 *
 * @author kedra
 */
public class IUS extends IuranPeriodic<IUS, IUSTransactionDetail>{
    public static final String tableName = Tipe.IUS.toString();
    
    public static final int periodInMonth = 3;
    
    //create filter & for insertion
    public IUS(String noInduk, Level chargedLevel, ArrayList<Entry> entries){
        super(noInduk, chargedLevel, entries);
    }
    
    //create from db
    public IUS(){}
    
    public IUS(IUS ius){
        super(ius);
    }
    
    public int getPeriodInMonth(){
        return periodInMonth;
    }
    
    public Tipe getTipe(){
        return Tipe.IUS;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.IUSTransaction;
    }
    
    public static String toStringHeader(){
        String tmp = IuranPeriodic.toStringHeader();
        for(int i = 0; i < (12/periodInMonth); ++i)
            tmp += "|" + amountColName + i + "|" + debtColName + i + "|" + IDTransactionDetailColName + i;
        return tmp;
    }
    
    //==========================
    public IUS dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        IUS ius = onCallingObj? this : new IUS();
        ius.dynFromResultSet(rs);
        
        if(ius.isDBValid())
            return ius;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

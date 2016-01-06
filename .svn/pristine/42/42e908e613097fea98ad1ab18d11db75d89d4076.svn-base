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
public class IUAP extends IuranPeriodic<IUAP, IUAPTransactionDetail>{
    public static final String tableName = Tipe.IUAP.toString();
    
    public static final int periodInMonth = 1;
    
    //create filter & for insertion
    public IUAP(String noInduk, Level chargedLevel, ArrayList<Entry> entries){
        super(noInduk, chargedLevel, entries);
    }
    
    //create from db
    public IUAP(){}
    
    public IUAP(IUAP iuap){
        super(iuap);
    }
    
    public int getPeriodInMonth(){
        return periodInMonth;
    }
    
    public Tipe getTipe(){
        return Tipe.IUAP;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.IUAPTransaction;
    }
    
    public static String toStringHeader(){
        String tmp = IuranPeriodic.toStringHeader();
        for(int i = 0; i < (12/periodInMonth); ++i)
            tmp += "|" + amountColName + i + "|" + debtColName + i + "|" + IDTransactionDetailColName + i;
        return tmp;
    }
    
    //==========================
    public IUAP dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        IUAP iuap = onCallingObj? this : new IUAP();
        iuap.dynFromResultSet(rs);
        
        if(iuap.isDBValid())
            return iuap;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}
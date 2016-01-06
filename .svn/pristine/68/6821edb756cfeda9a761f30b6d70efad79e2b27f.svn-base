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
public class OSIS extends IuranPeriodic<OSIS, OSISTransactionDetail>{
    public static final String tableName = Tipe.OSIS.toString();
    
    public static final int periodInMonth = 12;
    
    //create filter & for insertion
    public OSIS(String noInduk, Level chargedLevel, ArrayList<Entry> entries){
        super(noInduk, chargedLevel, entries);
    }
    
    //create from db
    public OSIS(){}
    
    public OSIS(OSIS osis){
        super(osis);
    }
    
    public int getPeriodInMonth(){
        return periodInMonth;
    }
    
    public Tipe getTipe(){
        return Tipe.OSIS;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.OSISTransaction;
    }
    
    public static String toStringHeader(){
        String tmp = IuranPeriodic.toStringHeader();
        for(int i = 0; i < (12/periodInMonth); ++i)
            tmp += "|" + amountColName + i + "|" + debtColName + i + "|" + IDTransactionDetailColName + i;
        return tmp;
    }
    
    //==========================
    public OSIS dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        OSIS osis = onCallingObj? this : new OSIS();
        osis.dynFromResultSet(rs);
        
        if(osis.isDBValid())
            return osis;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}
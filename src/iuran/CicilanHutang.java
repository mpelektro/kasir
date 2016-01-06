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
public class CicilanHutang extends IuranPeriodic<CicilanHutang, CicilanHutangTransactionDetail>{
    public static final String tableName = Tipe.CicilanHutang.toString();
    
    public static final int periodInMonth = 1;
    
    //create filter & for insertion
    public CicilanHutang(String noInduk, Level chargedLevel, ArrayList<Entry> entries){
        super(noInduk, chargedLevel, entries);
    }
    
    //create from db
    public CicilanHutang(){}
    
    public CicilanHutang(CicilanHutang cicilanhutang){
        super(cicilanhutang);
    }
    
    public int getPeriodInMonth(){
        return periodInMonth;
    }
    
    public Tipe getTipe(){
        return Tipe.CicilanHutang;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.CicilanHutangTransaction;
    }
    
    public static String toStringHeader(){
        String tmp = IuranPeriodic.toStringHeader();
        for(int i = 0; i < (12/periodInMonth); ++i)
            tmp += "|" + amountColName + i + "|" + debtColName + i + "|" + IDTransactionDetailColName + i;
        return tmp;
    }
    
    //==========================
    public CicilanHutang dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        CicilanHutang cicilanhutang = onCallingObj? this : new CicilanHutang();
        cicilanhutang.dynFromResultSet(rs);
        
        if(cicilanhutang.isDBValid())
            return cicilanhutang;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}
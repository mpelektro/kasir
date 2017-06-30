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
public class TunggakanPasca extends IuranPeriodic<TunggakanPasca, TunggakanPascaTransactionDetail>{
    public static final String tableName = Tipe.TunggakanPasca.toString();
    
    public static final int periodInMonth = 1;
    
    //create filter & for insertion
    public TunggakanPasca(String noInduk, Level chargedLevel, ArrayList<Entry> entries){
        super(noInduk, chargedLevel, entries);
    }
    
    //create from db
    public TunggakanPasca(){}
    
    public TunggakanPasca(TunggakanPasca cicilanhutang){
        super(cicilanhutang);
    }
    
    public int getPeriodInMonth(){
        return periodInMonth;
    }
    
    public Tipe getTipe(){
        return Tipe.TunggakanPasca;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.TunggakanPascaTransaction;
    }
    
    public static String toStringHeader(){
        String tmp = IuranPeriodic.toStringHeader();
        for(int i = 0; i < (12/periodInMonth); ++i)
            tmp += "|" + amountColName + i + "|" + debtColName + i + "|" + IDTransactionDetailColName + i;
        return tmp;
    }
    
    //==========================
    public TunggakanPasca dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        TunggakanPasca cicilanhutang = onCallingObj? this : new TunggakanPasca();
        cicilanhutang.dynFromResultSet(rs);
        
        if(cicilanhutang.isDBValid())
            return cicilanhutang;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}
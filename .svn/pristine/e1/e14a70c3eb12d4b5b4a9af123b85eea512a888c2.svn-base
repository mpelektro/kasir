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
public class PVT extends IuranPeriodic<PVT, PVTTransactionDetail>{
    public static final String tableName = Tipe.PVT.toString();
    
    public static final int periodInMonth = 12;
    
    //create filter & for insertion
    public PVT(String noInduk, Level chargedLevel, ArrayList<Entry> entries){
        super(noInduk, chargedLevel, entries);
    }
    
    //create from db
    public PVT(){}
    
    public PVT(PVT pvt){
        super(pvt);
    }
    
    public int getPeriodInMonth(){
        return periodInMonth;
    }
    
    public Tipe getTipe(){
        return Tipe.PVT;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.PVTTransaction;
    }
    
    public static String toStringHeader(){
        String tmp = IuranPeriodic.toStringHeader();
        for(int i = 0; i < (12/periodInMonth); ++i)
            tmp += "|" + amountColName + i + "|" + debtColName + i + "|" + IDTransactionDetailColName + i;
        return tmp;
    }
    
    //==========================
    public PVT dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        PVT pvt = onCallingObj? this : new PVT();
        pvt.dynFromResultSet(rs);
        
        if(pvt.isDBValid())
            return pvt;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}
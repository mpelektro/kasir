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
public class IPP extends IuranPeriodic<IPP, IPPTransactionDetail>{
    public static final String tableName = Tipe.IPP.toString();
    
    public static final int periodInMonth = 1;
    
    //create filter & for insertion
    public IPP(String noInduk, Level chargedLevel, ArrayList<Entry> entries){
        super(noInduk, chargedLevel, entries);
    }
    
    //create from db
    public IPP(){}
    
    public IPP(IPP ipp){
        super(ipp);
    }
    
    public int getPeriodInMonth(){
        return periodInMonth;
    }
    
    public Tipe getTipe(){
        return Tipe.IPP;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.IPPTransaction;
    }
    
    public static String toStringHeader(){
        String tmp = IuranPeriodic.toStringHeader();
        for(int i = 0; i < (12/periodInMonth); ++i)
            tmp += "|" + amountColName + i + "|" + debtColName + i + "|" + IDTransactionDetailColName + i;
        return tmp;
    }
    
    //==========================
    public IPP dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        IPP ipp = onCallingObj? this : new IPP();
        ipp.dynFromResultSet(rs);
        
        if(ipp.isDBValid())
            return ipp;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}
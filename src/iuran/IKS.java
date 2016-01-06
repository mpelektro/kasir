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
public class IKS extends IuranPeriodic<IKS, IKSTransactionDetail>{
    public static final String tableName = Tipe.IKS.toString();
    
    public static final int periodInMonth = 12;
    
    //create filter & for insertion
    public IKS(String noInduk, Level chargedLevel, ArrayList<Entry> entries){
        super(noInduk, chargedLevel, entries);
    }
    
    //create from db
    public IKS(){}
    
    public IKS(IKS iks){
        super(iks);
    }
    
    public int getPeriodInMonth(){
        return periodInMonth;
    }
    
    public Tipe getTipe(){
        return Tipe.IKS;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.IKSTransaction;
    }
    
    public static String toStringHeader(){
        String tmp = IuranPeriodic.toStringHeader();
        for(int i = 0; i < (12/periodInMonth); ++i)
            tmp += "|" + amountColName + i + "|" + debtColName + i + "|" + IDTransactionDetailColName + i;
        return tmp;
    }
    
    //==========================
    public IKS dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        IKS iks = onCallingObj? this : new IKS();
        iks.dynFromResultSet(rs);
        
        if(iks.isDBValid())
            return iks;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}
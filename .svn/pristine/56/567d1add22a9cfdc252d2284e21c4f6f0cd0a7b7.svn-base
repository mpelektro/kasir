/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.*;
import java.util.*;
import sak.*;
import pelajar.*;

/**
 *
 * @author kedra
 */
public class IPSB extends IuranOnce<IPSB, IPSBTransactionDetail>{
    public static final String tableName = Tipe.IPSB.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public IPSB(String noInduk, Level chargedLevel, float amount, float debt, float totalInstallment, Kalender settledDate, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, amount, debt, totalInstallment, settledDate, note, tDetailIDs);
    }   
    
    //create for insertion
    public IPSB(String noInduk, Level chargedLevel, float amount, String note){
       this(noInduk, chargedLevel, amount, 0, 0, null, note, null);
    }
    
    //create from db
    public IPSB(){}
    
    public IPSB(IPSB ipsb){
        super(ipsb);
    }
    
    
    public Tipe getTipe(){
        return Tipe.IPSB;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.IPSBTransaction;
    }
    
    //=============================    
    /* rs.isBeforeFirst must be false
     * throws KasirException(BAD_RECORD, getTipe()) if the resulting ipsp.isDBValid = false
     * never ret null
     */
    public IPSB dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst() : "rs.isBeforeFirst = true";
        
        IPSB ipsb = onCallingObj? this : new IPSB();
        ipsb.dynFromResultSet(rs);
        
        if(ipsb.isDBValid())
            return ipsb;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

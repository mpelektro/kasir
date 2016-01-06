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
public class IPSP extends IuranOnce<IPSP, IPSPTransactionDetail>{
    public static final String tableName = Tipe.IPSP.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public IPSP(String noInduk, Level chargedLevel, float amount, float debt, float totalInstallment, Kalender settledDate, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, amount, debt, totalInstallment, settledDate, note, tDetailIDs);
    }   
    
    //create for insertion
    public IPSP(String noInduk, Level chargedLevel, float amount, String note){
       this(noInduk, chargedLevel, amount, 0, 0, null, note, null);
    }
    
    //create from db
    public IPSP(){}
    
    public IPSP(IPSP ipsp){
        super(ipsp);
    }
    
    
    public Tipe getTipe(){
        return Tipe.IPSP;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.IPSPTransaction;
    }
    
    //=============================    
    /* rs.isBeforeFirst must be false
     * throws KasirException(BAD_RECORD, getTipe()) if the resulting ipsp.isDBValid = false
     * never ret null
     */
    public IPSP dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst() : "rs.isBeforeFirst = true";
        
        IPSP ipsp = onCallingObj? this : new IPSP();
        ipsp.dynFromResultSet(rs);
        
        if(ipsp.isDBValid())
            return ipsp;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

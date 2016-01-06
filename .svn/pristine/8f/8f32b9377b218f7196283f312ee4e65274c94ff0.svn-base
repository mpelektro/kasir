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
public class IPS extends IuranOnce<IPS, IPSTransactionDetail>{
    public static final String tableName = Tipe.IPS.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public IPS(String noInduk, Level chargedLevel, float amount, float debt, float totalInstallment, Kalender settledDate, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, amount, debt, totalInstallment, settledDate, note, tDetailIDs);
    }   
    
    //create for insertion
    public IPS(String noInduk, Level chargedLevel, float amount, String note){
       this(noInduk, chargedLevel, amount, 0, 0, null, note, null);
    }
    
    //create from db
    public IPS(){}
    
    public IPS(IPS ips){
        super(ips);
    }
    
    
    public Tipe getTipe(){
        return Tipe.IPS;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.IPSTransaction;
    }
    
    //=============================    
    /* rs.isBeforeFirst must be false
     * throws KasirException(BAD_RECORD, getTipe()) if the resulting ipsp.isDBValid = false
     * never ret null
     */
    public IPS dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst() : "rs.isBeforeFirst = true";
        
        IPS ips = onCallingObj? this : new IPS();
        ips.dynFromResultSet(rs);
        
        if(ips.isDBValid())
            return ips;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

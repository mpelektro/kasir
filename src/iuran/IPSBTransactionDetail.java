/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.*;
import java.util.UUID;
import pelajar.Level;
import sak.*;

/**
 *
 * @author kedra
 */
public class IPSBTransactionDetail extends TransactionDetailOnce<IPSBTransactionDetail, IPSB>{
    public static final String tableName = Tipe.IPSBTransaction.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public IPSBTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public IPSBTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public IPSBTransactionDetail(){}
    
    public IPSBTransactionDetail(IPSBTransactionDetail ipsbTDetail){
        super(ipsbTDetail);
    }
    
    
    public Tipe getTipe(){
        return Tipe.IPSBTransaction;
    }
    public Iuran.Tipe getTipeIuran(){
        return Iuran.Tipe.IPSB;
    }
    
    //===============================================    
    /* rs.isBeforeFirst must be false
     * throws KasirException(BAD_RECORD, getTipe()) if the resulting ipspTDetail.isDBValid = false
     * never ret null
     */
    public IPSBTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst() : "rs.isBeforeFirst = true";
        
        IPSBTransactionDetail ipsbTDetail = onCallingObj? this : new IPSBTransactionDetail();
        ipsbTDetail.dynFromResultSet(rs);
        
        if(ipsbTDetail.isDBValid()){
            System.out.println(ipsbTDetail.id);
            return ipsbTDetail;
        }else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

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
public class IPSPTransactionDetail extends TransactionDetailOnce<IPSPTransactionDetail, IPSP>{
    public static final String tableName = Tipe.IPSPTransaction.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public IPSPTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public IPSPTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public IPSPTransactionDetail(){}
    
    public IPSPTransactionDetail(IPSPTransactionDetail ipspTDetail){
        super(ipspTDetail);
    }
    
    
    public Tipe getTipe(){
        return Tipe.IPSPTransaction;
    }
    public Iuran.Tipe getTipeIuran(){
        return Iuran.Tipe.IPSP;
    }
    
    //===============================================    
    /* rs.isBeforeFirst must be false
     * throws KasirException(BAD_RECORD, getTipe()) if the resulting ipspTDetail.isDBValid = false
     * never ret null
     */
    public IPSPTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        //assert !rs.isBeforeFirst() : "rs.isBeforeFirst = true";
        assert !rs.isBeforeFirst();
        IPSPTransactionDetail ipspTDetail = onCallingObj? this : new IPSPTransactionDetail();
        ipspTDetail.dynFromResultSet(rs);
        
        if(ipspTDetail.isDBValid()){
            System.out.println(ipspTDetail.id);
            return ipspTDetail;
        }else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

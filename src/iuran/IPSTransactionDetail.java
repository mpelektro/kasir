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
public class IPSTransactionDetail extends TransactionDetailOnce<IPSTransactionDetail, IPS>{
    public static final String tableName = Tipe.IPSTransaction.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public IPSTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    public IPSTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public IPSTransactionDetail(){}
    
    public IPSTransactionDetail(IPSTransactionDetail ipsTDetail){
        super(ipsTDetail);
        idIuran = ipsTDetail.idIuran;
    }
    
    
    public Tipe getTipe(){
        return Tipe.IPSTransaction;
    }
    public Iuran.Tipe getTipeIuran(){
        return Iuran.Tipe.IPS;
    }
    
    //===============================================    
    /* rs.isBeforeFirst must be false
     * throws KasirException(BAD_RECORD, getTipe()) if the resulting ipspTDetail.isDBValid = false
     * never ret null
     */
    public IPSTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst() : "rs.isBeforeFirst = true";
        
        IPSTransactionDetail ipsTDetail = onCallingObj? this : new IPSTransactionDetail();
        ipsTDetail.dynFromResultSet(rs);
        
        if(ipsTDetail.isDBValid())
            return ipsTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

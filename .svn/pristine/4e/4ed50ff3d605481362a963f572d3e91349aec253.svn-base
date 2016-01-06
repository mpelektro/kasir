/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.*;
import java.util.*;
import pelajar.Level;
import sak.*;
/**
 *
 * @author kedra
 */
public class PVTTransactionDetail extends TransactionDetailPeriodic<PVTTransactionDetail, PVT>{
    public static final String tableName = Tipe.PVTTransaction.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public PVTTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public PVTTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public PVTTransactionDetail(){}
    
    public PVTTransactionDetail(PVTTransactionDetail pvtTDetail){
        super(pvtTDetail);
    }
    
    
    public Tipe getTipe(){
        return Tipe.PVTTransaction;
    }
    public Iuran.Tipe getTipeIuran(){
        return Iuran.Tipe.PVT;
    }
    
    
    public PVTTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        PVTTransactionDetail pvtTDetail = onCallingObj? this : new PVTTransactionDetail();
        pvtTDetail.dynFromResultSet(rs);
        
        if(pvtTDetail.isDBValid())
            return pvtTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

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
public class IUAPTransactionDetail extends TransactionDetailPeriodic<IUAPTransactionDetail, IUAP>{
    public static final String tableName = Tipe.IUAPTransaction.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public IUAPTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public IUAPTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public IUAPTransactionDetail(){}
    
    public IUAPTransactionDetail(IUAPTransactionDetail iuapTDetail){
        super(iuapTDetail);
    }
    
    
    public Tipe getTipe(){
        return Tipe.IUAPTransaction;
    }
    public Iuran.Tipe getTipeIuran(){
        return Iuran.Tipe.IUAP;
    }
    
    
    public IUAPTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        IUAPTransactionDetail iuapTDetail = onCallingObj? this : new IUAPTransactionDetail();
        iuapTDetail.dynFromResultSet(rs);
        
        if(iuapTDetail.isDBValid())
            return iuapTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

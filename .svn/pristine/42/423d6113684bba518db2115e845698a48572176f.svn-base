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
public class IUSTransactionDetail extends TransactionDetailPeriodic<IUSTransactionDetail, IUS>{
    public static final String tableName = Tipe.IUSTransaction.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public IUSTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public IUSTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public IUSTransactionDetail(){}
    
    public IUSTransactionDetail(IUSTransactionDetail iusTDetail){
        super(iusTDetail);
    }
    
    
    public Tipe getTipe(){
        return Tipe.IUSTransaction;
    }
    public Iuran.Tipe getTipeIuran(){
        return Iuran.Tipe.IUS;
    }
    
    
    public IUSTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        IUSTransactionDetail iusTDetail = onCallingObj? this : new IUSTransactionDetail();
        iusTDetail.dynFromResultSet(rs);
        
        if(iusTDetail.isDBValid())
            return iusTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

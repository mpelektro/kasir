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
public class IKSTransactionDetail extends TransactionDetailPeriodic<IKSTransactionDetail, IKS>{
    public static final String tableName = Tipe.IKSTransaction.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public IKSTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public IKSTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public IKSTransactionDetail(){}
    
    public IKSTransactionDetail(IKSTransactionDetail ikkTDetail){
        super(ikkTDetail);
    }
    
    
    public Tipe getTipe(){
        return Tipe.IKSTransaction;
    }
    public Iuran.Tipe getTipeIuran(){
        return Iuran.Tipe.IKS;
    }
    
    
    public IKSTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        IKSTransactionDetail iksTDetail = onCallingObj? this : new IKSTransactionDetail();
        iksTDetail.dynFromResultSet(rs);
        
        if(iksTDetail.isDBValid())
            return iksTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

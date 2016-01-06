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
public class IPPTransactionDetail extends TransactionDetailPeriodic<IPPTransactionDetail, IPP>{
    public static final String tableName = Tipe.IPPTransaction.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public IPPTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public IPPTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public IPPTransactionDetail(){}
    
    public IPPTransactionDetail(IPPTransactionDetail ippTDetail){
        super(ippTDetail);
    }
    
    
    public Tipe getTipe(){
        return Tipe.IPPTransaction;
    }
    public Iuran.Tipe getTipeIuran(){
        return Iuran.Tipe.IPP;
    }
    
    
    public IPPTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        IPPTransactionDetail ippTDetail = onCallingObj? this : new IPPTransactionDetail();
        ippTDetail.dynFromResultSet(rs);
        
        if(ippTDetail.isDBValid())
            return ippTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

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
public class SumbanganTransactionDetail extends TransactionDetailRegular<SumbanganTransactionDetail, Sumbangan>{
    public static final String tableName = Tipe.SumbanganTransaction.toString();
    
    //create filter
    public SumbanganTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public SumbanganTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public SumbanganTransactionDetail(){}
    
    public SumbanganTransactionDetail(SumbanganTransactionDetail sumbanganTDetail){
        super(sumbanganTDetail);
    }
    

    public Tipe getTipe() {
        return Tipe.SumbanganTransaction;
    }
    public Iuran.Tipe getTipeIuran() {
        return Iuran.Tipe.Sumbangan;
    }

    
    public SumbanganTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException {
        assert !rs.isBeforeFirst();
        
        SumbanganTransactionDetail sumbanganTDetail = onCallingObj? this : new SumbanganTransactionDetail();
        sumbanganTDetail.dynFromResultSet(rs);
        
        if(sumbanganTDetail.isDBValid())
            return sumbanganTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

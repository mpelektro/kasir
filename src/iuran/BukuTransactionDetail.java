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
public class BukuTransactionDetail extends TransactionDetailRegular<BukuTransactionDetail, Buku>{
    public static final String tableName = Tipe.BukuTransaction.toString();
    
    //create filter
    public BukuTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public BukuTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public BukuTransactionDetail(){}
    
    public BukuTransactionDetail(BukuTransactionDetail bTDetail){
        super(bTDetail);
    }
    

    public Tipe getTipe() {
        return Tipe.BukuTransaction;
    }
    public Iuran.Tipe getTipeIuran() {
        return Iuran.Tipe.Buku;
    }

    
    public BukuTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException {
        assert !rs.isBeforeFirst();
        
        BukuTransactionDetail bTDetail = onCallingObj? this : new BukuTransactionDetail();
        bTDetail.dynFromResultSet(rs);
        
        if(bTDetail.isDBValid())
            return bTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }    
}

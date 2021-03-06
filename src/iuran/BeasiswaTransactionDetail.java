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
public class BeasiswaTransactionDetail extends TransactionDetailRegular<BeasiswaTransactionDetail, Beasiswa>{
    public static final String tableName = Tipe.BeasiswaTransaction.toString();
    
    //create filter
    public BeasiswaTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public BeasiswaTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, false);
    }
    
    //create from db
    public BeasiswaTransactionDetail(){}
    
    public BeasiswaTransactionDetail(BeasiswaTransactionDetail iksTDetail){
        super(iksTDetail);
    }
    

    public Tipe getTipe() {
        return Tipe.BeasiswaTransaction;
    }
    public Iuran.Tipe getTipeIuran() {
        return Iuran.Tipe.Beasiswa;
    }

    
    public BeasiswaTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException {
        assert !rs.isBeforeFirst();
        
        BeasiswaTransactionDetail beasiswaTDetail = onCallingObj? this : new BeasiswaTransactionDetail();
        beasiswaTDetail.dynFromResultSet(rs);
        
        if(beasiswaTDetail.isDBValid())
            return beasiswaTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }    
}

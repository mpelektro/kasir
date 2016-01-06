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
public class BeasiswaCostTransactionDetail extends TransactionDetailRegular<BeasiswaCostTransactionDetail, BeasiswaCost>{
    public static final String tableName = Tipe.BeasiswaCostTransaction.toString();
    
    //create filter
    public BeasiswaCostTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public BeasiswaCostTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, false);
    }
    
    //create from db
    public BeasiswaCostTransactionDetail(){}
    
    public BeasiswaCostTransactionDetail(BeasiswaCostTransactionDetail iksTDetail){
        super(iksTDetail);
    }
    

    public Tipe getTipe() {
        return Tipe.BeasiswaCostTransaction;
    }
    public Iuran.Tipe getTipeIuran() {
        return Iuran.Tipe.BeasiswaCost;
    }

    
    public BeasiswaCostTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException {
        assert !rs.isBeforeFirst();
        
        BeasiswaCostTransactionDetail beasiswaTDetail = onCallingObj? this : new BeasiswaCostTransactionDetail();
        beasiswaTDetail.dynFromResultSet(rs);
        
        if(beasiswaTDetail.isDBValid())
            return beasiswaTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }    
}

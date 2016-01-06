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
public class IDDTransactionDetail extends TransactionDetailRegular<IDDTransactionDetail, Seragam>{
    public static final String tableName = Tipe.IDDTransaction.toString();
    
    //create filter
    public IDDTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public IDDTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, false);
    }
    
    //create from db
    public IDDTransactionDetail(){}
    
    public IDDTransactionDetail(IDDTransactionDetail iddTDetail){
        super(iddTDetail);
    }
    

    public Tipe getTipe() {
        return Tipe.IDDTransaction;
    }
    public Iuran.Tipe getTipeIuran() {
        return Iuran.Tipe.Seragam;
    }

    
    public IDDTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException {
        assert !rs.isBeforeFirst();
        
        IDDTransactionDetail iddTDetail = onCallingObj? this : new IDDTransactionDetail();
        iddTDetail.dynFromResultSet(rs);
        
        if(iddTDetail.isDBValid())
            return iddTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }    
}

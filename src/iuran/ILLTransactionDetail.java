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
public class ILLTransactionDetail extends TransactionDetailRegular<ILLTransactionDetail, ILL>{
    public static final String tableName = Tipe.ILLTransaction.toString();
    
    //create filter
    public ILLTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public ILLTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public ILLTransactionDetail(){}
    
    public ILLTransactionDetail(ILLTransactionDetail illTDetail){
        super(illTDetail);
    }
    

    public Tipe getTipe() {
        return Tipe.ILLTransaction;
    }
    public Iuran.Tipe getTipeIuran() {
        return Iuran.Tipe.ILL;
    }

    
    public ILLTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException {
        assert !rs.isBeforeFirst();
        
        ILLTransactionDetail illTDetail = onCallingObj? this : new ILLTransactionDetail();
        illTDetail.dynFromResultSet(rs);
        
        if(illTDetail.isDBValid())
            return illTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }    
}

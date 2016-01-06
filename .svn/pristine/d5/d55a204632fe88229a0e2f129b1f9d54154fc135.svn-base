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
public class AttributeTransactionDetail extends TransactionDetailRegular<AttributeTransactionDetail, Attribute>{
    public static final String tableName = Tipe.AttributeTransaction.toString();
    
    //create filter
    public AttributeTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public AttributeTransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public AttributeTransactionDetail(){}
    
    public AttributeTransactionDetail(AttributeTransactionDetail sTDetail){
        super(sTDetail);
    }
    

    public Tipe getTipe() {
        return Tipe.AttributeTransaction;
    }
    public Iuran.Tipe getTipeIuran() {
        return Iuran.Tipe.Attribute;
    }

    
    public AttributeTransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException {
        assert !rs.isBeforeFirst();
        
        AttributeTransactionDetail sTDetail = onCallingObj? this : new AttributeTransactionDetail();
        sTDetail.dynFromResultSet(rs);
        
        if(sTDetail.isDBValid())
            return sTDetail;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }    
}

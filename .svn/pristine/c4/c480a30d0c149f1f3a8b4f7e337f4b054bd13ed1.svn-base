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
public class IUATransactionDetail extends TransactionDetailOnce<IUATransactionDetail, IUA>{
    public static final String tableName = Tipe.IUATransaction.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public IUATransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    public IUATransactionDetail(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    public IUATransactionDetail(){}
    
    public IUATransactionDetail(IUATransactionDetail iuaTDetail){
        super(iuaTDetail);
    }
    
    
    public Tipe getTipe(){
        return Tipe.IUATransaction;
    }
    public Iuran.Tipe getTipeIuran(){
        return Iuran.Tipe.IUA;
    }
    
    //===============================================    
    /* rs.isBeforeFirst must be false
     * throws KasirException(BAD_RECORD, getTipe()) if the resulting ipspTDetail.isDBValid = false
     * never ret null
     */
    public IUATransactionDetail dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst() : "rs.isBeforeFirst = true";
        
        IUATransactionDetail iuaTDetail = onCallingObj? this : new IUATransactionDetail();
        iuaTDetail.dynFromResultSet(rs);
        
        if(iuaTDetail.isDBValid()){
            System.out.println(iuaTDetail.id);
            return iuaTDetail;
        }else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}
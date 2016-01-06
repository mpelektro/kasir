/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.*;
import java.util.Set;
import pelajar.Level;
import sak.KasirException;

/**
 *
 * @author kedra
 */
public class PASB extends IuranRegular<PASB, PASBTransactionDetail>{
    public static final String tableName = Tipe.PASB.toString();
    
    //create filter
    public PASB(String noInduk, Level chargedLevel, String tName, float amount, float debt, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, tName, amount, debt, note, tDetailIDs);
    }
    
    //create for insertion
    public PASB(String noInduk, Level chargedLevel, String tName, float amount, String note){
        super(noInduk, chargedLevel, tName, amount, note);
    }
    
    //create from db
    public PASB(){}
    
    public PASB(PASB pasb){
        super(pasb);
        transactDetailIDs = pasb.transactDetailIDs;
    }
    
    
    public Tipe getTipe(){
        return Tipe.PASB;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.PASBTransaction;
    }
    
    public PASB dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        PASB pasb = onCallingObj? this : new PASB();
        pasb.dynFromResultSet(rs);
        
        if(pasb.isDBValid())
            return pasb;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

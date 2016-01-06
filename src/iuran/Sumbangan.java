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
public class Sumbangan extends IuranRegular<Sumbangan, SumbanganTransactionDetail>{
    public static final String tableName = Tipe.Sumbangan.toString();
    
    //create filter
    public Sumbangan(String noInduk, Level chargedLevel, String tName, float amount, float debt, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, tName, amount, debt, note, tDetailIDs);
    }
    
    //create for insertion
    public Sumbangan(String noInduk, Level chargedLevel, String tName, float amount, String note){
        super(noInduk, chargedLevel, tName, amount, note);
    }
    
    //create from db
    public Sumbangan(){}
    
    public Sumbangan(Sumbangan sumbangan){
        super(sumbangan);
        transactDetailIDs = sumbangan.transactDetailIDs;
    }
    
    
    public Tipe getTipe(){
        return Tipe.Sumbangan;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.SumbanganTransaction;
    }
    
    public Sumbangan dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        Sumbangan sumbangan = onCallingObj? this : new Sumbangan();
        sumbangan.dynFromResultSet(rs);
        
        if(sumbangan.isDBValid())
            return sumbangan;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

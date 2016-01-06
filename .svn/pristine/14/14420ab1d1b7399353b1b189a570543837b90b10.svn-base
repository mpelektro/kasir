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
public class Seragam extends IuranRegular<Seragam, SeragamTransactionDetail>{
    public static final String tableName = Tipe.Seragam.toString();
    
    //create filter
    public Seragam(String noInduk, Level chargedLevel, String tName, float amount, float debt, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, tName, amount, debt, note, tDetailIDs);
    }
    
    //create for insertion
    public Seragam(String noInduk, Level chargedLevel, String tName, float amount, String note){
        super(noInduk, chargedLevel, tName, amount, note);
    }
    
    //create from db
    public Seragam(){}
    
    public Seragam(Seragam s){
        super(s);
        transactDetailIDs = s.transactDetailIDs;
    }
    
    
    public Tipe getTipe(){
        return Tipe.Seragam;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.SeragamTransaction;
    }
    
    public Seragam dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        Seragam s = onCallingObj? this : new Seragam();
        s.dynFromResultSet(rs);
        
        if(s.isDBValid())
            return s;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

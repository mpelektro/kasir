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
public class Almamater extends IuranRegular<Almamater, AlmamaterTransactionDetail>{
    public static final String tableName = Tipe.Almamater.toString();
    
    //create filter
    public Almamater(String noInduk, Level chargedLevel, String tName, float amount, float debt, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, tName, amount, debt, note, tDetailIDs);
    }
    
    //create for insertion
    public Almamater(String noInduk, Level chargedLevel, String tName, float amount, String note){
        super(noInduk, chargedLevel, tName, amount, note);
    }
    
    //create from db
    public Almamater(){}
    
    public Almamater(Almamater s){
        super(s);
        transactDetailIDs = s.transactDetailIDs;
    }
    
    
    public Tipe getTipe(){
        return Tipe.Almamater;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.AlmamaterTransaction;
    }
    
    public Almamater dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        Almamater s = onCallingObj? this : new Almamater();
        s.dynFromResultSet(rs);
        
        if(s.isDBValid())
            return s;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

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
public class Attribute extends IuranRegular<Attribute, AttributeTransactionDetail>{
    public static final String tableName = Tipe.Attribute.toString();
    
    //create filter
    public Attribute(String noInduk, Level chargedLevel, String tName, float amount, float debt, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, tName, amount, debt, note, tDetailIDs);
    }
    
    //create for insertion
    public Attribute(String noInduk, Level chargedLevel, String tName, float amount, String note){
        super(noInduk, chargedLevel, tName, amount, note);
    }
    
    //create from db
    public Attribute(){}
    
    public Attribute(Attribute s){
        super(s);
        transactDetailIDs = s.transactDetailIDs;
    }
    
    
    public Tipe getTipe(){
        return Tipe.Attribute;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.AttributeTransaction;
    }
    
    public Attribute dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        Attribute s = onCallingObj? this : new Attribute();
        s.dynFromResultSet(rs);
        
        if(s.isDBValid())
            return s;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

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
public class Buku extends IuranRegular<Buku, BukuTransactionDetail>{
    public static final String tableName = Tipe.Buku.toString();
    
    //create filter
    public Buku(String noInduk, Level chargedLevel, String tName, float amount, float debt, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, tName, amount, debt, note, tDetailIDs);
    }
    
    //create for insertion
    public Buku(String noInduk, Level chargedLevel, String tName, float amount, String note){
        super(noInduk, chargedLevel, tName, amount, note);
    }
    
    //create from db
    public Buku(){}
    
    public Buku(Buku b){
        super(b);
        transactDetailIDs = b.transactDetailIDs;
    }
    
    
    public Tipe getTipe(){
        return Tipe.Buku;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.BukuTransaction;
    }
    
    public Buku dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        Buku b = onCallingObj? this : new Buku();
        b.dynFromResultSet(rs);
        
        if(b.isDBValid())
            return b;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

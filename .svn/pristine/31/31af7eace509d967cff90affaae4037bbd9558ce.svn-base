/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import pelajar.Level;
import sak.KasirException;

/**
 *
 * @author kedra
 */
public class Tabungan extends IuranRegular<Tabungan, TabunganTransactionDetail>{
    public static final String tableName = Tipe.Tabungan.toString();
    
    //create filter
    public Tabungan(String noInduk, Level chargedLevel, String tName, float amount, float debt, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, tName, amount, debt, note, tDetailIDs);
    }
    
    //create for insertion
    public Tabungan(String noInduk, Level chargedLevel, String tName, float amount, String note){
        super(noInduk, chargedLevel, tName, amount, note);
    }
    
    //create from db
    public Tabungan(){}
    
    public Tabungan(Tabungan tabungan){
        super(tabungan);
        transactDetailIDs = tabungan.transactDetailIDs;
    }
    
    
    public Tipe getTipe(){
        return Tipe.Tabungan;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.TabunganTransaction;
    }
    
    public Tabungan dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        Tabungan tabungan = onCallingObj? this : new Tabungan();
        tabungan.dynFromResultSet(rs);
        
        if(tabungan.isDBValid())
            return tabungan;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

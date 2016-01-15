/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.*;
import java.util.Set;
import java.util.UUID;
import kasir.Clerk;
import kasir.Control;
import pelajar.Level;
import pelajar.Profil;
import sak.KasirException;

/**
 *
 * @author kedra
 */
public class Beasiswa extends IuranRegular<Beasiswa, BeasiswaTransactionDetail>{
    public static final String tableName = Tipe.Beasiswa.toString();
    
    //create filter
    public Beasiswa(String noInduk, Level chargedLevel, String tName, float amount, float debt, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, tName, amount, debt, note, tDetailIDs);
    }
    
    //create for insertion
    public Beasiswa(String noInduk, Level chargedLevel, String tName, float amount, String note){
        super(noInduk, chargedLevel, tName, amount, note);
    }
    
    //create from db
    public Beasiswa(){}
    
    public Beasiswa(Beasiswa Beasiswa){
        super(Beasiswa);
        transactDetailIDs = Beasiswa.transactDetailIDs;
    }
    
    
    public Tipe getTipe(){
        return Tipe.Beasiswa;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.BeasiswaTransaction;
    }
    
    public Beasiswa dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        Beasiswa beasiswa = onCallingObj? this : new Beasiswa();
        beasiswa.dynFromResultSet(rs);
        
        if(beasiswa.isDBValid())
            return beasiswa;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
    
    public static boolean transactOut(Profil profil, long tSummaryID, float amount) throws SQLException, KasirException{
        Beasiswa beasiswa = Control.selectIuran(Tipe.Beasiswa, Iuran.noIndukColName, false, profil.noInduk);
        if(beasiswa.amount < amount){
            return false;
        }
        //UUID uuid = UUID.randomUUID();
       // BeasiswaTransactionDetail beasiswaTDetail = new BeasiswaTransactionDetail(uuid, beasiswa.id, Clerk.current.id, tSummaryID, profil.noInduk, profil.currentLevel.level1, -amount, TransactionDetail.PaymentMethod.CASH, null);
        //Control.insertTDetail(TransactionDetail.Tipe.BeasiswaTransaction, beasiswaTDetail);
        
        //beasiswaTDetail = Control.selectTDetail(TransactionDetail.Tipe.BeasiswaTransaction, TransactionDetail.uuidColName, false, uuid.toString());
        //beasiswa.transactDetailIDs.add(beasiswaTDetail.id);
        beasiswa.amount -= amount;
        return Control.updateIuran(Tipe.Beasiswa, beasiswa);
    }
}

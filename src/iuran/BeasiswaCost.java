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
public class BeasiswaCost extends IuranRegular<BeasiswaCost, BeasiswaCostTransactionDetail>{
    public static final String tableName = Tipe.BeasiswaCost.toString();
    
    //create filter
    public BeasiswaCost(String noInduk, Level chargedLevel, String tName, float amount, float debt, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, tName, amount, debt, note, tDetailIDs);
    }
    
    //create for insertion
    public BeasiswaCost(String noInduk, Level chargedLevel, String tName, float amount, String note){
        super(noInduk, chargedLevel, tName, amount, note);
    }
    
    //create from db
    public BeasiswaCost(){}
    
    public BeasiswaCost(BeasiswaCost BeasiswaCost){
        super(BeasiswaCost);
        transactDetailIDs = BeasiswaCost.transactDetailIDs;
    }
    
    
    public Tipe getTipe(){
        return Tipe.BeasiswaCost;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.SeragamTransaction;
    }
    
    public BeasiswaCost dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        BeasiswaCost beasiswaCost = onCallingObj? this : new BeasiswaCost();
        beasiswaCost.dynFromResultSet(rs);
        
        if(beasiswaCost.isDBValid())
            return beasiswaCost;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
    
    public static boolean transactOut(Profil profil, long tSummaryID, float amount) throws SQLException, KasirException{
        BeasiswaCost beasiswaCost = Control.selectIuran(Tipe.BeasiswaCost, Iuran.noIndukColName, false, profil.noInduk);
        if(beasiswaCost.amount < amount){
            return false;
        }
        UUID uuid = UUID.randomUUID();
        BeasiswaCostTransactionDetail beasiswaTDetail = new BeasiswaCostTransactionDetail(uuid, beasiswaCost.id, Clerk.current.id, tSummaryID, profil.noInduk, profil.currentLevel.level1, amount, TransactionDetail.PaymentMethod.CASH, null);
        Control.insertTDetail(TransactionDetail.Tipe.BeasiswaCostTransaction, beasiswaTDetail);
        
        beasiswaTDetail = Control.selectTDetail(TransactionDetail.Tipe.BeasiswaCostTransaction, TransactionDetail.uuidColName, false, uuid.toString());
        beasiswaCost.transactDetailIDs.add(beasiswaTDetail.id);
        beasiswaCost.amount += beasiswaTDetail.amount;
        return Control.updateIuran(Tipe.BeasiswaCost, beasiswaCost);
    }
}

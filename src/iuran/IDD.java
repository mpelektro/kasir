/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.ResultSet;
import java.sql.SQLException;
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
public class IDD extends IuranRegular<IDD, IDDTransactionDetail>{
    public static final String tableName = Tipe.IDD.toString();
    
    //create filter
    public IDD(String noInduk, Level chargedLevel, String tName, float amount, float debt, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, tName, amount, debt, note, tDetailIDs);
    }
    
    //create for insertion
    public IDD(String noInduk, Level chargedLevel, String tName, float amount, String note){
        super(noInduk, chargedLevel, tName, amount, note);
    }
    
    //create from db
    public IDD(){}
    
    public IDD(IDD IDD){
        super(IDD);
        transactDetailIDs = IDD.transactDetailIDs;
    }
    
    
    public Tipe getTipe(){
        return Tipe.IDD;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.IDDTransaction;
    }
    
    public IDD dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        IDD IDD = onCallingObj? this : new IDD();
        IDD.dynFromResultSet(rs);
        
        if(IDD.isDBValid())
            return IDD;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
    
    //---------------------------------
    public static boolean transactOut(Profil profil, long tSummaryID, float amount) throws SQLException, KasirException{
        IDD idd = Control.selectIuran(Tipe.IDD, Iuran.noIndukColName, false, profil.noInduk);
        if(idd.amount < amount){
            return false;
        }
        //UUID uuid = UUID.randomUUID();
        //IDDTransactionDetail iddTDetail = new IDDTransactionDetail(uuid, idd.id, Clerk.current.id, tSummaryID, profil.noInduk, profil.currentLevel.level1, -amount, TransactionDetail.PaymentMethod.CASH, null);
        //Control.insertTDetail(TransactionDetail.Tipe.IDDTransaction, iddTDetail);
        
        //iddTDetail = Control.selectTDetail(TransactionDetail.Tipe.IDDTransaction, TransactionDetail.uuidColName, false, uuid.toString());
        //idd.transactDetailIDs.add(iddTDetail.id);
        idd.amount -= amount;
        return Control.updateIuran(Tipe.IDD, idd);
    }
    public static boolean transactIn(Profil profil, long tSummaryID, float amount) throws SQLException, KasirException{
        IDD idd = Control.selectIuran(Tipe.IDD, Iuran.noIndukColName, false, profil.noInduk);
        
        UUID uuid = UUID.randomUUID();
        IDDTransactionDetail iddTDetail = new IDDTransactionDetail(uuid, idd.id, Clerk.current.id, tSummaryID, profil.noInduk, profil.currentLevel.level1, amount, TransactionDetail.PaymentMethod.CASH, null);
        Control.insertTDetail(TransactionDetail.Tipe.IDDTransaction, iddTDetail);
        
        iddTDetail = Control.selectTDetail(TransactionDetail.Tipe.IDDTransaction, TransactionDetail.uuidColName, false, uuid.toString());
        idd.transactDetailIDs.add(iddTDetail.id);
        idd.amount += iddTDetail.amount;
        return Control.updateIuran(Tipe.IDD, idd);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import pelajar.Level;
import sak.Kalender;
import sak.KasirException;

/**
 *
 * @author kedra
 */
public class IUA extends IuranOnce<IUA, IUATransactionDetail>{
    public static final String tableName = Tipe.IUA.toString();
    public static final String defColNamePrefix = tableName + ".";
    
    //create filter
    public IUA(String noInduk, Level chargedLevel, float amount, float debt, float totalInstallment, Kalender settledDate, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, amount, debt, totalInstallment, settledDate, note, tDetailIDs);
    }   
    
    //create for insertion
    public IUA(String noInduk, Level chargedLevel, float amount, String note){
       this(noInduk, chargedLevel, amount, 0, 0, null, note, null);
    }
    
    //create from db
    public IUA(){}
    
    public IUA(IUA iua){
        super(iua);
    }
    
    
    public Tipe getTipe(){
        return Tipe.IUA;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.IUATransaction;
    }
    
    //=============================    
    /* rs.isBeforeFirst must be false
     * throws KasirException(BAD_RECORD, getTipe()) if the resulting ipsp.isDBValid = false
     * never ret null
     */
    public IUA dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst() : "rs.isBeforeFirst = true";
        
        IUA iua = onCallingObj? this : new IUA();
        iua.dynFromResultSet(rs);
        
        if(iua.isDBValid())
            return iua;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

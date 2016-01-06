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
public class ILL extends IuranRegular<ILL, ILLTransactionDetail>{
    public static final String tableName = Tipe.ILL.toString();
    
    //create filter
    public ILL(String noInduk, Level chargedLevel, String tName, float amount, float debt, String note, Set<Long> tDetailIDs){
        super(noInduk, chargedLevel, tName, amount, debt, note, tDetailIDs);
    }
    
    //create for insertion
    public ILL(String noInduk, Level chargedLevel, String tName, float amount, String note){
        super(noInduk, chargedLevel, tName, amount, note);
    }
    
    //create from db
    public ILL(){}
    
    public ILL(ILL ill){
        super(ill);
        transactDetailIDs = ill.transactDetailIDs;
    }
    
    
    public Tipe getTipe(){
        return Tipe.ILL;
    }
    public TransactionDetail.Tipe getTipeTDetail(){
        return TransactionDetail.Tipe.ILLTransaction;
    }
    
    public ILL dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst();
        
        ILL ill = onCallingObj? this : new ILL();
        ill.dynFromResultSet(rs);
        
        if(ill.isDBValid())
            return ill;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, getTipe());
    }
}

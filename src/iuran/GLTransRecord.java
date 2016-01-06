/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.ResultSet;
import java.sql.SQLException;
import sak.Kalender;
import sak.KasirException;
import kasir.Clerk;
/**
 *
 * @author kedra
 */
public class GLTransRecord {   
    public long type_no;
    public Kalender tran_date;
    public String account;
    public String memo_;
    public double amount;
    
    public GLTransRecord(long typeNo, Kalender tranDate, String account, String memo, double amount){
        type_no = typeNo;
        tran_date = tranDate;
        this.account = account;
        memo_ = memo;
        this.amount = amount;
    }
    public GLTransRecord(long typeNo, String account, TransactionDetail tDetail){
        this(typeNo, tDetail.lastUpdateDate, account, tDetail.noIndukProfil.concat(" "+tDetail.getTipeIuran().toString()).concat(" "+tDetail.note+" "), tDetail.amount * -1);
    }
    
    public boolean isInsertDBValid(){
        return type_no > 0 && tran_date != null && account != null && !account.isEmpty() && amount != 0;
    }
    
    public String asInsertClause() throws KasirException{
        //assert isInsertDBValid();
        
        if(isInsertDBValid()){
            String insertClause = "(" + type_no + ", '" + tran_date.toDate() + "', '" + account + "'";
            if(memo_ != null)
                insertClause += ", '" + memo_ + "'";
            else
                insertClause += ", ''";
            insertClause += ", " + amount + ",'"+Clerk.current.nama+" (Kasir)')";
            return insertClause;
        }else
            throw new KasirException(KasirException.Tipe.DB_INVALID, this);
    }
}

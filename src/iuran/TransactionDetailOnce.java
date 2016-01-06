/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.util.UUID;
import pelajar.Level;
import sak.Kalender;
/**
 *
 * @author kedra
 */
public abstract class TransactionDetailOnce<TDetailType extends TransactionDetailOnce, IuranType extends IuranOnce> extends TransactionDetail<TDetailType, IuranType>{  
    //public enum Tipe {IPSPTransaction, IPSTransaction, IUATransaction, IPSBTransaction}    
    
    //create filter
    protected TransactionDetailOnce(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
    
    //create for insertion
    protected TransactionDetailOnce(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    protected TransactionDetailOnce(){
        super();
    }
    
    protected TransactionDetailOnce(TDetailType tdo){
        super(tdo);
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.util.UUID;
import kasir.Clerk;
import pelajar.Level;
import pelajar.Profil;
import sak.Kalender;
/**
 *
 * @author kedra
 */
public abstract class TransactionDetailPeriodic<TDetailType extends TransactionDetailPeriodic, IuranType extends IuranPeriodic> extends TransactionDetail<TDetailType, IuranType>{
    //public enum Tipe {IPPTransaction, IUSTransaction, OSISTransaction, IKKTransaction}
    
    //create filter
    protected TransactionDetailPeriodic(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, Kalender cDate, Kalender luDate, String note, boolean settled, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, cDate, luDate, note, settled, piutang);
    }
        
    //create for insertion
    protected TransactionDetailPeriodic(UUID uuid, long iuranID, int clerkID, long tSummaryID, String noInduk, Level.Level1 lv1, float amount, PaymentMethod pm, String note, boolean piutang){
        super(uuid, iuranID, clerkID, tSummaryID, noInduk, lv1, amount, pm, note, piutang);
    }
    
    //create from db
    protected TransactionDetailPeriodic(){}
    
    protected TransactionDetailPeriodic(TDetailType tdo){
        super(tdo);
    }
}

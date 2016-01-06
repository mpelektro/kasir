/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pelajar.Level;
import sak.KasirException;

/**
 *
 * @author kedra
 */
public class GLTransRecordSet {
    public ArrayList<GLTransRecord> tDetailsRec;
    public GLTransRecord kasBankRec;
    
    
    //tDetails mustn't be null / empty
    //tDetails must have the same tipe iuran
    //mapAccGLs must contains all records of MapAccountGL table
    public GLTransRecordSet(long type_no, TransactionDetail.Tipe tipe, Level.Level1 lv1, TransactionDetail.PaymentMethod pm, List<TransactionDetail> tDetails, List<MapAccountGL> mapAccGLs) throws SQLException, KasirException{
        System.out.println("size: "+(tDetails.size()));
        System.out.println(tDetails.contains(null));
        TransactionDetail tDetailSample = tDetails.get(0);
        
        MapAccountGL magl = null;
        for(MapAccountGL maglTmp : mapAccGLs){
            if(maglTmp.iuranName.equalsIgnoreCase(tipe.toString()) && maglTmp.level1 == lv1)
                magl = maglTmp;
        }
    
        String iuranGL = magl.accountGLIuran;
        String kasBankGL = pm == TransactionDetail.PaymentMethod.TRANSFER? magl.accountGLBank : magl.accountGLKas;
        double totalAmount = 0;
        
        tDetailsRec = new ArrayList<>();
        for(TransactionDetail tDetail : tDetails){
            if(tDetail.level1 == lv1 && tDetail.paymentMethod == pm){
                tDetailsRec.add(new GLTransRecord(type_no, iuranGL, tDetail));
                totalAmount += tDetail.amount;
                tDetail.settled = true;
            }            
        }
        
        kasBankRec = new GLTransRecord(type_no, tDetailSample.lastUpdateDate, kasBankGL, null, totalAmount);
    }
    
    //ret null if tDetails is null / empty
    //ret null if tDetails doesn't have any tDetail with level1 'lv1' & paymentMethod 'pm'
    //elts of tDetails must have tipe 'tipe'
    //mapAccGLs must contains all records of MapAccountGL table
    public static GLTransRecordSet create(long type_no, TransactionDetail.Tipe tipe, Level.Level1 lv1, TransactionDetail.PaymentMethod pm, List<TransactionDetail> tDetails, List<MapAccountGL> mapAccGLs) throws SQLException, KasirException{
        if(tDetails != null && !tDetails.isEmpty()){
            GLTransRecordSet gltrs = new GLTransRecordSet(type_no, tipe, lv1, pm, tDetails, mapAccGLs);
//            if(gltrs.tDetailsRec.isEmpty() && gltrs.kasBankRec == null)
            if(gltrs.tDetailsRec.isEmpty())
                return null;
            else
                return gltrs;
        }else
            return null;
    }
    
    public String asInsertClause() throws KasirException{
        String tmp = "";
        for(GLTransRecord gltr : tDetailsRec)
            tmp += gltr.asInsertClause() + ", ";
        
        return tmp + kasBankRec.asInsertClause();
    }
}

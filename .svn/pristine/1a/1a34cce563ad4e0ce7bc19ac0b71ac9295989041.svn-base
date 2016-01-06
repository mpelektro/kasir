/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kasir;

import java.sql.*;
import pelajar.*;
import iuran.*;
import sak.*;
import java.util.*;
import gui.*;
import javax.swing.JFrame;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Master
 */
public class Kasir {

    /**
     * @param args the command line arguments
     */
    public static void testLevel(){
//        Level lv = new Level("");
//        System.out.println(lv.toDBString());
    }
    
    public static void testProfilSelect() throws SQLException, KasirException{
        
    }
    
    public static void testIuranSelect() throws SQLException, KasirException{
        IPP ipp = Control.selectIuran(Iuran.Tipe.IPP, 1);
        System.out.println(ipp.entries.get(0).amount);
        //IPSP ipsp = Control.selectIuran(Iuran.Tipe.IPSP, 1);
        //System.out.println(ipsp.amount);
        List<IPP> ipps = Control.selectIurans(Iuran.Tipe.IPP, "id", 3);
    }
    public static void testIuranInsert() throws SQLException, KasirException{
//        Clerk claudia = Control.login("cynthia", "bella");
//        
//        
//        IPSP ipsp = new IPSP("1", new Level("smp-1-a"), 300, "nunggak mulu nih orang");
//        System.out.println(Control.insertIuran(Iuran.Tipe.IPSP, ipsp));
        
        
        
        ArrayList<Entry> ippEntries = new ArrayList<>();
        ippEntries.add(new Entry(1, 100));
        ippEntries.add(new Entry(2, 125));
        ippEntries.add(new Entry(3, 150));
        IPP ipp = new IPP("1", new Level("sma-10-ipa3"), ippEntries);
        
        System.out.println(Control.insertIuran(Iuran.Tipe.IPP, ipp));
        
        
        /* iks
        Seragam iks = new Seragam("1", new Level("SMA-1-2-3"), "beli seragam", 300000, null);
        System.out.println(Control.insertIuran(Iuran.Tipe.Seragam, iks));
        */
    }
    
    public static void testIuranUpdate() throws SQLException, KasirException{
        IPP ipp = Control.selectIuran(Iuran.Tipe.IPP, 5);
        ipp.entries.get(0).transactDetailIDs = new HashSet(Arrays.asList(3,2));
        Control.updateIuran(Iuran.Tipe.IPP, ipp);
    }
    
    public static void testTDetailInsert() throws SQLException, KasirException{
//        Clerk claudia = Control.login("cynthia", "bella");
        /*
        IPSP ipsp = Control.selectIuran(Iuran.Tipe.IPSP, IPSP.noIndukColName, false, "1");
        
        UUID uuidIPSP = UUID.randomUUID();
        IPSPTransactionDetail ipspTDetail = new IPSPTransactionDetail(uuidIPSP, ipsp.id, claudia.id, 0, ipsp.noInduk, Level.Level1.SMA, ipsp.amount, TransactionDetail.PaymentMethod.CASH, "bangke");
        
        UUID uuidTSum = UUID.randomUUID();
        TransactionSummary tSum = new TransactionSummary(uuidTSum, ipsp.noInduk, claudia.id, ipsp.amount, null);
        
        Control.insertTSummary(tSum);
        tSum = Control.selectTSummary(TransactionSummary.uuidColName, true, uuidTSum.toString());
        
        ipspTDetail.transactSummaryID = tSum.id;
        Control.insertTDetail(TransactionDetail.Tipe.IPSPTransaction, ipspTDetail);
        ipspTDetail = Control.selectTDetail(TransactionDetail.Tipe.IPSPTransaction, TransactionDetail.uuidColName, true, uuidIPSP.toString());
        
        ipsp.transactDetailIDs.add(ipspTDetail.id);
        Control.updateIuran(Iuran.Tipe.IPSP, ipsp);
        */
        
//        IPSPTransactionDetail ipspTDetail = new IPSPTransactionDetail(UUID.randomUUID(), 99, 99, 99, "99", Level.Level1.SMA, 99, TransactionDetail.PaymentMethod.TRANSFER, null);
//        Control.insertTDetail(TransactionDetail.Tipe.IPSPTransaction, ipspTDetail);
    }    
    public static void testTDetailSelect() throws SQLException, KasirException{
//        TransactionDetail tDetail = Control.selectTDetail(TransactionDetail.Tipe.IPSPTransaction, 106);
//        System.out.println(tDetail);
    }
    
    public static void testIuranFilterSelect() throws SQLException, KasirException{
//        Set<Filter<IKS>> filters = new HashSet<>();
//        filters.add(new Seragam("1", null, null, 0, null));
//        
//        Control.exactFilterSelectIurans(Iuran.Tipe.Seragam, filters);
    }
    
    public static void testMapAccGLSelect() throws SQLException, KasirException{
        ArrayList<MapAccountGL> magls = Control.selectMapAccGLs(MapAccountGL.iuranNameColName, false, "ipp");
        System.out.println(magls.size());
    }
    
    public static void testGLTransRecordBuild() throws KasirException{
        GLTransRecord gltr = new GLTransRecord(99, new Kalender(), "800-01", "note", 300);
        System.out.println(gltr.asInsertClause());
    }
    
    public static void testGenKeys() throws SQLException, KasirException{
        DBSR.initStatement();
        UUID uuid = UUID.randomUUID();
        Timestamp tstamp = new Timestamp(System.currentTimeMillis());
        String sql = "insert into IPSBTransaction (UUID,IDIuran,IDClerk,IDTransactionSummary,NomorInduk,Level1,Amount,PaymentMethod,CreateDate,LastUpdateDate) values ('" + uuid.toString() + "'," + "100," + "100," + "100," + "'100'," + "'SMA'," + "100," + "'TRANSFER'," + "'" + tstamp.toString() + "','" + tstamp.toString()+ "')";
        System.out.println(sql);
        int affectedCount = DBSR.stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        
        if(affectedCount == 0){
            System.out.println("nothing");
        }else{
            System.out.println(affectedCount);
            System.out.println(DBSR.stmt.getUpdateCount());
            ResultSet rs = DBSR.stmt.getGeneratedKeys();
            rs.next();
            System.out.println(rs.getInt(1));
        }
        DBSR.stmt.close();
    }
    //========================
    public static void main(String[] args) throws SQLException, KasirException{
        //testGenKeys();
        //testIuranSelect();
        //testIuranInsert();
        //testIuranUpdate();
        //testTDetailInsert();
        //testTDetailSelect();
        //testMapAccGLSelect();
        //testGLTransRecordBuild();
        
        JFrame loginFrame = new LoginFrame();
        loginFrame.pack();
        loginFrame.setVisible(true);
        
    }
}
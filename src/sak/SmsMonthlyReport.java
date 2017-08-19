/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sak;

import gui.AppFrame;
import iuran.IPP;
import iuran.Iuran;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.swing.table.TableModel;
import kasir.Clerk;
import kasir.Control;
import org.openide.util.Exceptions;
import pelajar.Biodata;
import pelajar.Level;
import pelajar.Profil;

/**
 *
 * @author Mpe
 */
public class SmsMonthlyReport {
    Kalender kal;
    private ArrayList<Object> profils;
    private AppFrame af;
    
    public SmsMonthlyReport() throws SQLException, KasirException{
        final long start = System.nanoTime();


        kal = new Kalender();
        kal.getMonth();
//        System.out.println(kal.getTime());
//        System.out.println(kal.getMonth());
        Biodata biodata = new Biodata();
        Clerk clerk = new Clerk();
        af = new AppFrame(clerk);
        
            Profil profil = new Profil();
            profil.currentLevel = new Level();
            Level level = new Level();
            //level.tahun = kal.get(Calendar.YEAR);
            level.tahun = 2017;
            profil.currentLevel.tahun = level.tahun;
            //profil.biodata = biodata.isEmpty()?null:biodata;
            profil.biodata = new Biodata();
            profil.biodata.telpon1 = "08";
            String columnNames[] = {"Nomor Induk", "Nama Siswa", "Sekolah", "Kelas"};
       Set<Profil> setProfil = new HashSet<>();
       setProfil.add(profil);
       Map<String, Profil> searchResultMap = Control.filterSelectProfils(setProfil);
       Object[][] data = new Object[searchResultMap.size()][5];
       int i = 0;
       int j = 0;
       profils = new ArrayList<>();       
       if(searchResultMap.size() > 0){
        for(Map.Entry<String, Profil> entry: searchResultMap.entrySet()){
            data[i][0]= entry.getKey();
            data[i][1]= entry.getValue().biodata.telpon1;
            data[i][2]= entry.getValue().currentLevel.level1;
            data[i][3]= entry.getValue().currentLevel.level2.toString().concat(" - "+entry.getValue().currentLevel.level3.toString());
            profils.add(entry.getValue());
            af.buildTunggakanProfilTableModel(entry.getValue());
            if(af.tunggakanBerjalan > 0f || af.totalTunggakanPasca > 0f){
                System.out.println(j+" "+data[i][1]+" "+data[i][2]+" "+data[i][3]+" "+entry.getValue().currentLevel.tahun+" "+String.valueOf(af.tunggakanBerjalan)+" "+String.valueOf(af.totalTunggakanPasca)+" Total Tunggakan "+String.valueOf(af.totalTunggakanPasca+af.tunggakanBerjalan));
                j++;
//                if(j%2==1){
//                    System.out.println("wait 1 sec...");
//                   
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException ex) {
//                        Exceptions.printStackTrace(ex);
//                    }
//                }
            }
            i++;
        }
        System.out.println("Total Record Siswa Aktif : "+i);
        System.out.println("Total Record SMS Tagihan : "+j);
       }
        try {
            this.finalize();
        } catch (Throwable ex) {
            Exceptions.printStackTrace(ex);
        }
        final long end = System.nanoTime();

        System.out.println("Took: " + ((end - start) / 1000000000) + "s");
        System.exit(0);
            
    }
    public static void main(String[] args){
        try {
            new SmsMonthlyReport();
//        System.out.println("SMS");
        
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        } catch (KasirException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}

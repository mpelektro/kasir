/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sak;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
    public SmsMonthlyReport() throws SQLException, KasirException{
        kal = new Kalender();
        kal.getMonth();
//        System.out.println(kal.getTime());
//        System.out.println(kal.getMonth());
        Biodata biodata = new Biodata();
        
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
       profils = new ArrayList<>();       
       if(searchResultMap.size() > 0){
        for(Map.Entry<String, Profil> entry: searchResultMap.entrySet()){
            data[i][0]= entry.getKey();
            data[i][1]= entry.getValue().biodata.telpon1;
            data[i][2]= entry.getValue().currentLevel.level1;
            data[i][3]= entry.getValue().currentLevel.level2.toString().concat(" - "+entry.getValue().currentLevel.level3.toString());
            profils.add(entry.getValue());
            
            System.out.println(i+" "+data[i][1]);
            i++;
        }
        System.out.println(i);
       }
            
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

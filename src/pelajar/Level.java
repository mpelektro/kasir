package pelajar;

import java.sql.*;
import java.util.LinkedList;
import org.apache.commons.lang3.StringUtils;

public class Level{
    public enum Level1 {SMP, SMA, SMK}
    public enum Level2 {
        TUJUH("7"), DELAPAN("8"), SEMBILAN("9"), SEPULUH("10"), SEBELAS("11"), DUABELAS("12");
        
        public String printout;
        private Level2(String po){
            printout = po;
        }
        public String toString(){
            return printout;
        }
    }
    public enum Level3 {
        SATU("1"), DUA("2"), TIGA("3"), EMPAT("4"), LIMA("5"), ENAM("6"), TUJUH("7"), DELAPAN("8"), SEMBILAN("9"), SEPULUH("10"), SEBELAS("11"), DUABELAS("12"), IPA1("IPA1"), IPA2("IPA2"), IPA3("IPA3"), IPA4("IPA4"), IPA5("IPA5"), IPS1("IPS1"), IPS2("IPS2"), IPS3("IPS3"), IPS4("IPS4"), IPS5("IPS5"), TKJ1("TKJ1"), TKJ2("TKJ2"), PTV1("PTV1"), PTV2("PTV2"), MM1("MM1"),UNCLASSIFIED("UNCLASSIFIED");
        
        public String printout;
        private Level3(String po){
            printout = po;
        }
        public String toString(){
            return printout;
        }
    }
    
    public Level1 level1;
    public Level2 level2;
    public Level3 level3;
    public int tahun;
    
    /* ret null if resulting Level.isEmpty = true */
    public static Level create(String lvStr){
        Level lv = new Level();
        return lv.fromDBString(lvStr)? lv : null;
    }

    public Level(Level1 lv1, Level2 lv2, Level3 lv3, int thn) {
        level1 = lv1; level2 = lv2; level3 = lv3; tahun = thn;
    }
    public Level() {}
    public Level(Level lv) {
        this(lv.level1, lv.level2, lv.level3, lv.tahun);
    }
    public Level(String lv){
        fromDBString(lv);
    }

    public boolean equals(Level lv) {
        return lv == null ? false : level1 == lv.level1 && level2 == lv.level2 && level3 == lv.level3 && tahun == lv.tahun;
    }
    public String toString() {
        String level1Str = level1 == null ? "<LV1>" : level1.toString();        
        String level2Str = level2 == null ? "<LV2>" : level2.printout;
        String level3Str = level3 == null ? "<LV3>" : level3.printout;
        return level1Str + "-" + level2Str + "-" + level3Str + "-" + String.valueOf(tahun);
    }
    public boolean isEmpty(){
        return level1 == null && level2 == null && level3 == null && tahun <= 0;
    }
    
    
    /* ret false if levels = null & if in invalid format */
    public boolean fromDBString(String levels[]) {
        if (levels == null || levels.length == 0)
            return false;
        
        level1 = null;  level2 = null;  level3 = null; tahun = 0;     //reset
        
        for(int i = 0; i < levels.length; ++i){
            if(i == 0){
                for (Level1 tmp : Level1.values()){
                    if(tmp.toString().equalsIgnoreCase(levels[0])){
                        level1 = tmp;
                        break;
                    }
                }
            }else if(i == 1){
                for(Level2 tmp : Level2.values()){
                    if(tmp.printout.equalsIgnoreCase(levels[1]) || tmp.toString().equalsIgnoreCase(levels[1])){
                        level2 = tmp;
                        break;
                    }
                }
            }else if(i == 2){
                for (Level3 tmp : Level3.values()) {
                    if (tmp.printout.equalsIgnoreCase(levels[2]) || tmp.toString().equalsIgnoreCase(levels[2])) {
                        level3 = tmp;
                        break;
                    }
                }
            }else if(i == 3){
                try{
                    tahun = Integer.parseInt(levels[3]);
                }catch(NumberFormatException e){}
            }else
                break;
        }     
        
        return !isEmpty();
    }
    
    /* ret false levels = null & if in invalid format */
    public boolean fromDBString(String levels) {
        return fromDBString(StringUtils.split(levels, "-"));
    }
    
    /* ret null if all members are null & tahun = 0
     * ret string in template: "<LV1>-<LV2>-<LV3>-tahun", any part whose corresponding member isn't null is substituted with the member's val
     */
    public String toDBString() {
        String level1Str = level1 == null? "<LV1>" : level1.toString();

        String level2Str = level2 == null? "<LV2>" : level2.printout;
        
        String level3Str = level3 == null ? "<LV3>" : level3.printout;

        return level1Str + "-" + level2Str + "-" + level3Str + "-" + String.valueOf(tahun);
    }

    
    /* colName may not be null
     * never ret null, only empty level
     */
    public Level dynFromResultSet(ResultSet rs, String colName, boolean onCallingObj) throws SQLException{
        assert colName != null : "dynFromResultSet(ResultSet, String colName = null, boolean)";
        
        Level lv = onCallingObj? this : new Level();
        lv.fromDBString(rs.getString(colName));
        return this;
    }
    
    /* colName may not be null
     * never ret null, only empty level
     */
    public static Level fromResultSet(ResultSet rs, String colName) throws SQLException {
        assert colName != null : "fromResultSet(ResultSet, null)";
        
        return new Level().dynFromResultSet(rs, colName, true);
    }
    
    /* colName may not be null
     * never ret false, only throws exception
     */
    public boolean flushResultSet(ResultSet rs, String colName) throws SQLException {
        assert colName != null : "updateResultSet(ResultSet, String colName = null)";
        
        rs.updateString(colName, toDBString());
        return true;
    }
    
    //format: lv1-lv2-lv3-tahun
    //never ret null / empty
    public String asWhereClause(){
        LinkedList<String> whereClause = new LinkedList<>();
        
        whereClause.add(level1 == null? "%" : level1.toString());
        whereClause.add(level2 == null? "%" : level2.printout);
        whereClause.add(level3 == null? "%" : level3.printout);
        whereClause.add(tahun < 1? "%" : String.valueOf(tahun));
        
        return StringUtils.join(whereClause, "-");
    }
}
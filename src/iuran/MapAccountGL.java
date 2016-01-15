/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import kasir.DBSR;
import kasir.Filter;
import net.sf.jasperreports.engine.util.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import pelajar.Level;
import sak.KasirException;
import sak.KasirObject;

/**
 *
 * @author kedra
 */
public class MapAccountGL extends KasirObject<MapAccountGL,MapAccountGL,Integer>{
    public static final String tableName = "MapAccountGL";
    public static final String idColName = "ID", iuranNameColName = "IuranName", level1ColName = "Level1", accountGLIuranColName = "AccountGLIuran";
    public static final String iuranLongNameColName = "IuranLongName", accountGLKasColName = "AccountGLKas", accountGLBankColName = "AccountGLBank";
    public static final String accountGLIDDColName = "AccountGLIDD", accountGLBeasiswaColName = "AccountGLBeasiswa", accountGLBeasiswaCostColName = "AccountGLBeasiswaCost";
    public int id;
    public String iuranName;
    public Level.Level1 level1;
    public String accountGLIuran;
    public String iuranLongName;
    public String accountGLKas;
    public String accountGLBank;
    public String accountGLIDD;
    public String accountGLBeasiswa;
    public String accountGLBeasiswaCost;
    //for filter & insertion
    public MapAccountGL(String iuranName, Level.Level1 lv1, String accGLIuran, String iuranLongName, String accGLKas, String accGLBank, String accGLIDD, String accGLBeasiswa, String accGLBeasiswaCost){
        this.iuranName = iuranName;
        level1 = lv1;
        accountGLIuran = accGLIuran;
        this.iuranLongName = iuranLongName;
        accountGLKas = accGLKas;
        accountGLBank = accGLBank;
        accountGLIDD = accGLIDD;
        accountGLBeasiswa = accGLBeasiswa;
        accountGLBeasiswaCost = accGLBeasiswaCost;
    }
    
    //for from db
    public MapAccountGL(){}
    
    public MapAccountGL(MapAccountGL magl){
        this(magl.iuranName, magl.level1, magl.accountGLIuran, magl.iuranLongName, magl.accountGLKas, magl.accountGLBank, magl.accountGLIDD, magl.accountGLBeasiswa, magl.accountGLBeasiswaCost);
        id = magl.id;
    }
    
    public static String toStringHeader(){
        return idColName + "|" + iuranNameColName + "|" + level1ColName + "|" + accountGLIuranColName + "|" + iuranLongNameColName + "|" + accountGLKasColName + "|" + accountGLBankColName  + "|" + accountGLIDDColName  + "|" + accountGLBeasiswaColName  + "|" + accountGLBeasiswaCostColName ;
    }
    public String toString(){
        return id + "|" + iuranName + "|" + level1 + "|" + accountGLIuran + "|" + iuranLongName + "|" + accountGLKas + "|" + accountGLBank + "|" + accountGLIDD + "|" + accountGLBeasiswa + "|" + accountGLBeasiswaCost;
    }
    public boolean equals(MapAccountGL magl){
        if(magl != null){
            if(id == magl.id){
                if(ObjectUtils.equals(iuranName, magl.iuranName)){
                    if(ObjectUtils.equals(level1, magl.level1)){
                        if(ObjectUtils.equals(accountGLIuran, magl.accountGLIuran)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    
    //=====================
    public boolean isInsertDBValid(){
        if(iuranName != null && !iuranName.isEmpty()){
            if(level1 != null){
                if(accountGLIuran != null && !accountGLIuran.isEmpty()){
                    if(accountGLKas != null && !accountGLKas.isEmpty()){
                        if(accountGLBank != null && !accountGLBank.isEmpty()){
                            if(accountGLIDD != null && !accountGLIDD.isEmpty()){
                                if(accountGLBeasiswa != null && !accountGLBeasiswa.isEmpty()){
                                    if(accountGLBeasiswaCost !=null && !accountGLBeasiswaCost.isEmpty())
                                        return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean isDBValid(){
        return id > 0 && isInsertDBValid();
    }
    
    public Integer getKey(){
        return id;
    }
    
    public MapAccountGL dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        MapAccountGL magl = onCallingObj? this : new MapAccountGL();
        
        magl.id = rs.getInt(idColName);
        magl.iuranName = rs.getString(iuranNameColName);
        
        String lv1 = rs.getString(level1ColName);
        for(Level.Level1 tmp : Level.Level1.values()){
            if(tmp.toString().equalsIgnoreCase(lv1))
                level1 = tmp;
        }
        
        accountGLIuran = rs.getString(accountGLIuranColName);
        iuranLongName = rs.getString(iuranLongNameColName);
        accountGLKas = rs.getString(accountGLKasColName);
        accountGLBank = rs.getString(accountGLBankColName);
        accountGLIDD = rs.getString(accountGLIDDColName);
        accountGLBeasiswa = rs.getString(accountGLBeasiswaColName);
        accountGLBeasiswaCost = rs.getString(accountGLBeasiswaCostColName);
        if(magl.isDBValid())
            return magl;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, tableName);
    }
    
    public static MapAccountGL fromResultSet(ResultSet rs) throws SQLException, KasirException{
        return new MapAccountGL().dynFromResultSet(rs, true);
    }
    
    public static MapAccountGL fromResultSet(ResultSet rs, String colName, Number val) throws SQLException, KasirException{
        assert colName != null && !colName.isEmpty();
        
        if(DBSR.searchRow(rs, colName, val) > 0)
            return fromResultSet(rs);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, val);
    }
    public static MapAccountGL fromResultSet(ResultSet rs, String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        assert colName != null && !colName.isEmpty();
        
        if(DBSR.searchRow(rs, colName, caseSensitive, val) > 0)
            return fromResultSet(rs);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, val);
    }
    
    public boolean flushResultSet(ResultSet rs) throws SQLException{
        rs.updateString(iuranNameColName, iuranName);
        rs.updateString(level1ColName, level1.toString());
        rs.updateString(accountGLIuranColName, accountGLIuran);
        rs.updateString(iuranLongNameColName, iuranLongName);
        rs.updateString(accountGLKasColName, accountGLKas);
        rs.updateString(accountGLBankColName, accountGLBank);
        rs.updateString(accountGLIDDColName, accountGLIDD);
        rs.updateString(accountGLBeasiswaColName, accountGLBeasiswa);
        rs.updateString(accountGLBeasiswaCostColName, accountGLBeasiswaCost);
        return true;
    }
    
    public boolean insertResultSet(ResultSet rs) throws SQLException, KasirException{
        if(isInsertDBValid())
            return flushResultSet(rs);
        else
            throw new KasirException(KasirException.Tipe.DB_INVALID, this);
    }
    
    public boolean updateResultSet(ResultSet rs) throws SQLException, KasirException{
        if(!isDBValid())
            throw new KasirException(KasirException.Tipe.DB_INVALID, this);
        
        if(DBSR.searchRow(rs, idColName, id) > 0)
            return flushResultSet(rs);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, this);
    }
    
    
    public String asWhereClause(){
        LinkedList<String> whereClause = new LinkedList<>();
        
        if(iuranName != null && !iuranName.isEmpty())
            whereClause.add(iuranNameColName + " LIKE '%" + iuranName + "%'");
        if(level1 != null)
            whereClause.add(level1ColName + " = " + level1);
        if(accountGLIuran != null && !accountGLIuran.isEmpty())
            whereClause.add(accountGLIuranColName + " LIKE '%" + accountGLIuran + "%'");
        if(iuranLongName != null && !iuranLongName.isEmpty())
            whereClause.add(iuranLongNameColName + " LIKE '%" + iuranLongName + "%'");
        if(accountGLKas != null && !accountGLKas.isEmpty())
            whereClause.add(accountGLKasColName + " LIKE '%" + accountGLKas + "%'");
        if(accountGLBank != null && !accountGLBank.isEmpty())
            whereClause.add(accountGLBankColName + " LIKE '%" + accountGLBank + "%'");
        if(accountGLIDD != null && !accountGLIDD.isEmpty())
            whereClause.add(accountGLIDDColName + " LIKE '%" + accountGLIDD + "%'");
        if(accountGLBeasiswa != null && !accountGLBeasiswa.isEmpty())
            whereClause.add(accountGLBeasiswaColName + " LIKE '%" + accountGLBeasiswa + "%'");
        if(accountGLBeasiswaCost != null && !accountGLBeasiswaCost.isEmpty())
            whereClause.add(accountGLBeasiswaCostColName + " LIKE '%" + accountGLBeasiswaCost + "%'");
        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    public String asWhereClauseExact(){
        LinkedList<String> whereClause = new LinkedList<>();
        
        if(iuranName != null && !iuranName.isEmpty())
            whereClause.add(iuranNameColName + " = '" + iuranName + "'");
        if(level1 != null)
            whereClause.add(level1ColName + " = " + level1);
        if(accountGLIuran != null && !accountGLIuran.isEmpty())
            whereClause.add(accountGLIuranColName + " = '" + accountGLIuran + "'");
        if(iuranLongName != null && !iuranLongName.isEmpty())
            whereClause.add(iuranLongNameColName + " = '" + iuranLongName + "'");
        if(accountGLKas != null && !accountGLKas.isEmpty())
            whereClause.add(accountGLKasColName + " = '" + accountGLKas + "'");
        if(accountGLBank != null && !accountGLBank.isEmpty())
            whereClause.add(accountGLBankColName + " = '" + accountGLBank + "'");
        
        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    
    
    //===============================
    public static MapAccountGL select(String colName, Number val) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByNumber(tableName, colName, 1, val);
        return MapAccountGL.fromResultSet(rs, colName, val);
    }
    public static MapAccountGL select(String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByString(tableName, colName, 1, val);
        return MapAccountGL.fromResultSet(rs, colName, caseSensitive, val);
    }
    
    public static ArrayList<MapAccountGL> selectS(String colName, Number... vals) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByNumber(tableName, colName, -1, vals);
        
        ArrayList<MapAccountGL> magls = new ArrayList(DBSR.rowCountRS(rs));
        rs.beforeFirst();
        while(rs.next())
            magls.add(fromResultSet(rs));
        
        return magls;
    }
    public static ArrayList<MapAccountGL> selectS(String colName, boolean caseSensitive, String... vals) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByString(tableName, colName, -1, vals);
        
        ArrayList<MapAccountGL> magls = new ArrayList(DBSR.rowCountRS(rs));
        rs.beforeFirst();
        while(rs.next())
            magls.add(fromResultSet(rs));
        
        return magls;
    }
    
    public static ArrayList<MapAccountGL> filterSelectS(Set<? extends Filter<MapAccountGL>> filters) throws SQLException, KasirException{
        ResultSet rs;
        if(filters == null)
            rs = DBSR.takeResultSetByFilterColl(tableName, null, -1, null);
        else{
            //remove null filters / filters with empty asWhereClause, so BAD_RECORD is only detected if filters = null
            //arg filters isn't modified
            Set<Filter<MapAccountGL>> validFilters = new HashSet<>();
            for(Filter<MapAccountGL> filter : filters){
                if(filter != null && !"".equals(filter.asWhereClause()))
                    validFilters.add(filter);
            }
            rs = DBSR.takeResultSetByFilterColl(tableName, null, -1, validFilters);
        }

        ArrayList<MapAccountGL> magls = new ArrayList<>(DBSR.rowCountRS(rs));
        rs.beforeFirst();
        while(rs.next())
            magls.add(fromResultSet(rs));            

        return magls;
    }
    
    public boolean insert() throws SQLException, KasirException{
        return DBSR.insertKasirO(tableName, idColName, this);
    }
    
    public boolean update() throws SQLException, KasirException{
        return DBSR.updateKasirO(tableName, idColName, this);
    }
    
    public static boolean delete(long id) throws SQLException, KasirException{
        return DBSR.deleteKasirO(tableName, idColName, id);
    }
    public static int deleteS(Set<Long> ids) throws SQLException{
        return DBSR.deleteKasirOsByNumber(tableName, idColName, ids);
    }
}

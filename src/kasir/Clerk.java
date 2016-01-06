package kasir;

import java.util.*;
import java.sql.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import sak.*;

public class Clerk extends KasirObject<Clerk,Clerk,String>{
    public static final String tableName = "Clerk";
    public static final String idColName = "ID", nipColName = "NIP", namaColName = "Nama";
    public static final String jabatanColName = "Jabatan", userColName = "Username", passColName = "Password";
    public static final String defColNamePrefix = tableName + ".";
    
    public static Clerk current = null;
    
    public int id;  //db-primary-key do not edit even on runtime
    public String nip;  //not null
    public String nama;  //not null
    public String jabatan;
    public String username;  //not null
    public String pass;  //not null
    
    
    //for filter
    public Clerk(String nip, String n, String j, String u, String p){
        this.nip = nip;  nama = n;  jabatan = j;  username = u;  pass = p;
    }
    
    //for insertion
    public Clerk(String nip, String n, String u, String p){
        this(nip, n, null, u, p);
    }
    
    //for from db
    public Clerk(){}
    
    public Clerk(Clerk clerk){
        this(clerk.nip, clerk.nama, clerk.jabatan, clerk.username, clerk.pass);
        id = clerk.id;
    }
    
    public static String toStringHeader(){
        return idColName + "|" + nipColName + "|" + namaColName + "|" + jabatanColName + "|" + userColName + "|" + passColName;
    }
    public String toString(){
        return id + "|" + nip + "|" + nama + "|" + jabatan + "|" + username + "|" + pass;
    }
    public boolean equals(Clerk clerk){
        return clerk == null? false : id == clerk.id && nip.equalsIgnoreCase(clerk.nip) && nama.equalsIgnoreCase(clerk.nama) && jabatan.equalsIgnoreCase(clerk.jabatan) && username.equalsIgnoreCase(clerk.username) && pass.equals(clerk.pass);
    }
    
    
    //==============================
    /* true if all required fields (db-not-null fields), except primary key, are valid */
    public boolean isInsertDBValid(){
        return nip != null && !nip.isEmpty() && nama != null && !nama.isEmpty() && username != null && !username.isEmpty() && pass != null && !pass.isEmpty();
    }
    /* true if all required fields (db-not-null fields) are valid */
    public boolean isDBValid(){
        return id > 0 && isInsertDBValid();
    }
    
    public String getKey(){
        return username;
    }
    
    /* rs.isBeforeFirst must be false
     * throws KasirException(BAD_RECORD, Clerk.tableName) if the resulting clerk.isDBValid = false
     * never ret null
     */
    public Clerk dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst() : "rs.isBeforeFirst = true";
        
        Clerk clerk = onCallingObj? this : new Clerk();
        
        clerk.id = rs.getInt(idColName);
        clerk.nip = rs.getString(nipColName);
        clerk.nama = rs.getString(namaColName);
        clerk.jabatan = rs.getString(jabatanColName);
        clerk.username = rs.getString(userColName);
        clerk.pass = rs.getString(passColName);
        
        if(clerk.isDBValid())
            return clerk;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, tableName);
    }
    
    /* never ret null */
    public static Clerk fromResultSet(ResultSet rs) throws SQLException, KasirException{
        return new Clerk().dynFromResultSet(rs, true);
    }
    
    public static Clerk fromResultSet(ResultSet rs, String colName, Number val) throws SQLException, KasirException{
        assert colName != null && !colName.isEmpty();
        
        if(DBSR.searchRow(rs, colName, val) > 0)
            return fromResultSet(rs);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, val);
    }
    public static Clerk fromResultSet(ResultSet rs, String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        assert colName != null && !colName.isEmpty();
        
        if(DBSR.searchRow(rs, colName, caseSensitive, val) > 0)
            return fromResultSet(rs);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, val);
    }
    
    /* id must be > 0
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, id) if no matching row found
     */
    public static Clerk fromResultSet(ResultSet rs, int id) throws SQLException, KasirException{
        assert id > 0 : "fromResultSet(ResultSet, int id < 1)";
        
        return fromResultSet(rs, idColName, id);
    }
    
    /* username may not be null / empty
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, username) if no matching row found
     */
    public static Clerk fromResultSet(ResultSet rs, String username) throws SQLException, KasirException{
        assert username != null && !username.isEmpty() : "filter = null / empty";
        
        return fromResultSet(rs, userColName, false, username);
    }
    
    /* caller of this method must ensure that isDBValid = true & RS cursor points to the intended row
     * never ret false
     */
    public boolean flushResultSet(ResultSet rs, boolean flushUsername) throws SQLException{      
        rs.updateString(nipColName, nip);
        rs.updateString(namaColName, nama);
        rs.updateString(jabatanColName, jabatan);
        if(flushUsername)
            rs.updateString(userColName, username);
        rs.updateString(passColName, DigestUtils.sha1Hex(pass).substring(0,32));
        return true;
    }
    
    /* caller of this method must ensure that RS cursor is at the insert row & that no existing row has the same username & call rs.insertRow() when all done
     * throws KasirException(DB_INVALID, this) if isInsertDBValid = false
     * never ret false
     */
    public boolean insertResultSet(ResultSet rs) throws SQLException, KasirException{        
        if(isInsertDBValid())
            return flushResultSet(rs, true);
        else
            throw new KasirException(KasirException.Tipe.DB_INVALID, this);
    }
    
    /* updates without updating username
     * if username is changed prior to calling this method, the new username is still isn't stored
     * throws KasirException(DB_INVALID, this) if isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, this) if no row matches
     * never ret false
     */
    public boolean updateResultSet(ResultSet rs) throws SQLException, KasirException{
        if(!isDBValid())
            throw new KasirException(KasirException.Tipe.DB_INVALID, this);
        
        if(DBSR.searchRow(rs, idColName, id) > 0)
            return flushResultSet(rs, false);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, this);
    }
    
    /* updates including username. if oldUsername.equalsIgnoreCase(username) = true, then it's the same as updateResultSet(rs)
     * oldUsername may not be null
     * throws KasirException(DB_INVALID, this) if isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, this/oldUsername) if no row matches
     * throws KasirException(DUPLICATE_PRIMARY_KEY, this) if the new username has already existed in db
     * never ret false
     */
    public boolean updateResultSetUsername(ResultSet rs, String oldUsername) throws SQLException, KasirException{
        assert oldUsername != null : "oldUsername = null";
        
        if(!isDBValid())
            throw new KasirException(KasirException.Tipe.DB_INVALID, this);
        
        if(username.equalsIgnoreCase(oldUsername)){
            if(DBSR.searchRow(rs, userColName, false, username) > 0)
                return flushResultSet(rs, false);
            else
                throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, this);
        }else{
            if(DBSR.searchRow(rs, userColName, false, username) > 0)
                throw new KasirException(KasirException.Tipe.DUPLICATE_PRIMARY_KEY, this);
            
            if(DBSR.searchRow(rs, userColName, false, oldUsername) > 0)
                return flushResultSet(rs, true);
            else
                throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, oldUsername);
        }
    }
    
    //ret empty if all vars are empty
    public String asWhereClause(){
        LinkedList<String> whereClause = new LinkedList<>();
        if (nip != null && !nip.isEmpty())
            whereClause.add(nipColName + " LIKE '%" + nip + "%'");
        if (nama != null && !nama.isEmpty())
            whereClause.add(namaColName + " LIKE '%" + nama + "%'");
        if (jabatan != null && !jabatan.isEmpty())
            whereClause.add(jabatanColName + " LIKE '%" + jabatan + "%'");
        if (username != null && !username.isEmpty())
            whereClause.add(userColName + " LIKE '%" + username + "%'");

        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    public String asWhereClauseExact(){
        LinkedList<String> whereClause = new LinkedList<>();
        if (nip != null && !nip.isEmpty())
            whereClause.add(nipColName + " = '" + nip + "'");
        if (nama != null && !nama.isEmpty())
            whereClause.add(namaColName + " = '" + nama + "'");
        if (jabatan != null && !jabatan.isEmpty())
            whereClause.add(jabatanColName + " = '" + jabatan + "'");
        if (username != null && !username.isEmpty())
            whereClause.add(userColName + " = '" + username + "'");

        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    
    
    //===================================CLERK SELECT
    public static Clerk select(String colName, Number val) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByNumber(tableName, colName, 1, val);
        return Clerk.fromResultSet(rs, colName, val);
    }
    public static Clerk select(String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByString(tableName, colName, 1, val);
        return Clerk.fromResultSet(rs, colName, caseSensitive, val);
    }
    
    /* id must be > 0
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, id) if no row matches
     */
    public static Clerk select(int id) throws SQLException, KasirException{
        assert id > 0 : "id < 1";

        return select(idColName, id);
    }    

    /* username may not be null / empty
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, username) if no row matches
     */
    public static Clerk select(String username) throws SQLException, KasirException{
        assert username != null && !username.isEmpty() : "username = null / empty";

        return select(userColName, false, username);
    }    
    
    /* elts of ids < 1 are ignored
    * ret all Clerk if ids = null
    * Map.get(ids[i]) = null if no row has ids[i]
    * Map.size() <= ids.length, because duplicate elts of ids are treated as 1 map entry
    * throws KasirException(BAD_RECORD, Clerk.tableName)
    * ret empty Map if (ids = null & table is empty) / (all elts of ids aren't valid (null / empty)) / ids.isEmpty = true
    * ret Map<id,Clerk>. never null
    */
    public static Map<Integer,Clerk> selectS(Set<Integer> ids) throws SQLException, KasirException{
        ResultSet rs;
        Map<Integer,Clerk> idClerk;
        if(ids == null){
            rs = DBSR.takeResultSetByNumberColl(tableName, idColName, -1, null);

            idClerk = new HashMap<>(DBSR.rowCountRS(rs));                
            rs.beforeFirst();
            while(rs.next()){
                Clerk c = Clerk.fromResultSet(rs);
                idClerk.put(c.id, c);
            }
        }else{
            Set<Integer> validId = new HashSet<>();
            for(Integer id : ids){
                if(id > 0)
                    validId.add(id);
            }
            rs = DBSR.takeResultSetByNumberColl(tableName, idColName, -1, validId);

            idClerk = new HashMap<>(validId.size());                
            for(Integer id : validId){
                try{
                    idClerk.put(id, Clerk.fromResultSet(rs, id));
                }catch(KasirException e){
                    idClerk.put(id, null);
                }
            }
        }

        return idClerk;
    }

    public static Clerk filterSelect(Filter<Clerk> filter) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByFilter(tableName, null, 1, filter);
        if(rs.next())
            return fromResultSet(rs);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, filter);
    }
    /* filters is ArrayList<Clerk>
     * null elts of filters are ignored
     * ret all Clerk if filters == null
     * throws KasirException(BAD_RECORD, Clerk.tableName)
     * ret empty Map if (filters = null & table is empty) / all elts of filters = null / filters.isEmpty = true
     * ret Map<id,clerk>. never null
     */
    public static Map<Integer,Clerk> filterSelectS(Set<? extends Filter<Clerk>> filters) throws SQLException, KasirException{
        ResultSet rs;
        if(filters == null)
            rs = DBSR.takeResultSetByFilterColl(tableName, null, -1, null);
        else{
            //remove null filters / filters with empty asWhereClause, so BAD_RECORD is only detected if filters = null
            //arg filters isn't modified
            Set<Filter<Clerk>> validFilters = new HashSet<>();
            for(Filter<Clerk> filter : filters){
                if(filter != null && !"".equals(filter.asWhereClause()))
                    validFilters.add(filter);
            }
            rs = DBSR.takeResultSetByFilterColl(tableName, null, -1, validFilters);
        }                        

        Map<Integer,Clerk> idClerk = new HashMap<>(DBSR.rowCountRS(rs));
        while(rs.next()){
            Clerk c = Clerk.fromResultSet(rs);
            idClerk.put(c.id, c);
        }

        return idClerk;
    }


    public boolean insert() throws SQLException, KasirException{
        return DBSR.insertKasirO(tableName, userColName, this);
    }
    public static int insertS(ArrayList<Clerk> clerks) throws SQLException, KasirException{
        return DBSR.insertKasirOs(tableName, userColName, clerks, true);
    }
    
    //===================================CLERK UPDATE
    /* flush current states (including username) of clerk into db
     * oldUsername & clerk may not be null. oldUsername is the previous val of clerk.username
     * throws KasirException(DB_INVALID, clerk) if clerk.isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, oldUsername) if usernameOld record = not in db
     * throws KasirException(DUPLICATE_PRIMARY_KEY, clerk) if clerk.username has already existed in db
     * never ret false
     */
    public boolean updateUsername(String oldUsername) throws SQLException, KasirException{
        assert oldUsername != null : "oldNoInduk / profil = null";

        //profil.username is also retrieved to check for username duplication
        ResultSet rs = DBSR.takeResultSetByString(tableName, userColName, 1, oldUsername, username);

        if(updateResultSetUsername(rs, oldUsername)){
            rs.updateRow();
            return true;
        }else
            return false;
    }
    public boolean update() throws SQLException, KasirException{
        return DBSR.updateKasirO(tableName, idColName, this);
    }
    public static int updateS(ArrayList<Clerk> clerks) throws SQLException, KasirException{
        return DBSR.updateKasirOs(tableName, userColName, clerks);
    }

    
    public boolean delete() throws SQLException, KasirException{
        return DBSR.deleteKasirO(tableName, idColName, id);
    }

    /* id may be < 0, which in this case del the first found bad record
     * throws KasirException(ROW_NOT_FOUND, id)
     */
    public static boolean delete(int id) throws SQLException, KasirException{
        return DBSR.deleteKasirO(tableName, idColName, id);
    }
    public static boolean delete(String username) throws SQLException, KasirException{
        return DBSR.deleteKasirO(tableName, userColName, false, username);
    }

    /* del all rows having any elt of ids on colName, including < 0 (ie. del all bad records)
     * del all rows if ids = null
     */
    public static int deleteS(Set<Integer> ids) throws SQLException{
        return DBSR.deleteKasirOsByNumber(tableName, idColName, ids);
    }

    /* clerks may not be null
     * this is equal to deleteS(user, pass, Set) where Set contains ids of all clerks, including bad id
     */
    public static int deleteS(ArrayList<Clerk> clerks) throws SQLException{
        Set<Integer> ids = new HashSet<>(clerks.size());
        for(Clerk c : clerks)
            ids.add(c.id);
        return deleteS(ids);
    }
}
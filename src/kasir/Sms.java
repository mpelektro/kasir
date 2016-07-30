package kasir;

import java.util.*;
import java.sql.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import sak.*;

public class Sms extends KasirObject<Sms,Sms,Long>{
    public static final String tableName = "Sms";
    public static final String idColName = "ID", nomorIndukColName = "NomorInduk", teleponColName = "Telepon";
    public static final String messageColName = "Message", flagRequestedColName = "FlagRequested", requesterColName = "Requester";
    public static final String createdTimeColName = "CreatedTime", updatedTimeColName = "UpdatedTime";
    public static final String defColNamePrefix = tableName + ".";
    
    public static Sms current = null;
    
    public long id;  //db-primary-key do not edit even on runtime
    public String nomorInduk;  //not null
    public String telepon;  //not null
    public String message;
    public Boolean flagRequested;  //not null
    public String requester;  //not null
    public Kalender createdTime;
    public Kalender updatedTime;
    
    
    //for filter
    public Sms(String ni, String t, String m, Boolean fr, String r, Kalender ct, Kalender ut){
        this.nomorInduk = ni;  telepon = t;  message = m;  flagRequested = fr;  requester = r; createdTime = ct; updatedTime =ut; 
    }
    
    //for insertion
    public Sms(String ni, String t, String m){
        this(ni, t, m, false, null, null, null);
    }
    
    //for from db
    public Sms(){}
    
    public Sms(Sms sms){
        this(sms.nomorInduk, sms.telepon, sms.message, sms.flagRequested, sms.requester, sms.createdTime, sms.updatedTime);
        id = sms.id;
    }
    
    public static String toStringHeader(){
        return idColName + "|" + nomorIndukColName + "|" + teleponColName + "|" + messageColName + "|" + flagRequestedColName + "|" + requesterColName + "|" + createdTimeColName + "|" + updatedTimeColName;
    }
    public String toString(){
        return id + "|" + nomorInduk + "|" + telepon + "|" + message + "|" + flagRequested + "|" + requester + "|" + createdTime + "|" + updatedTime;
    }
    public boolean equals(Sms sms){
        return sms == null? false : id == sms.id && nomorInduk.equalsIgnoreCase(sms.nomorInduk) && telepon.equalsIgnoreCase(sms.telepon) && message.equalsIgnoreCase(sms.message) && flagRequested.equals(sms.flagRequested) && requester.equalsIgnoreCase(sms.requester) && createdTime.equals(sms.createdTime) && updatedTime.equals(sms.updatedTime);
    }
    
    
    //==============================
    /* true if all required fields (db-not-null fields), except primary key, are valid */
    public boolean isInsertDBValid(){
        return nomorInduk != null && !nomorInduk.isEmpty() && telepon != null && !telepon.isEmpty() && (telepon.startsWith("+") || telepon.startsWith("0")) && message != null && !message.isEmpty();
    }
    /* true if all required fields (db-not-null fields) are valid */
    public boolean isDBValid(){
        return id > 0 && isInsertDBValid();
    }
    
//    public String getKey(){
//        return flagRequested;
//    }
    
    /* rs.isBeforeFirst must be false
     * throws KasirException(BAD_RECORD, Clerk.tableName) if the resulting sms.isDBValid = false
     * never ret null
     */
    public Sms dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException{
        assert !rs.isBeforeFirst() : "rs.isBeforeFirst = true";
        
        Sms sms = onCallingObj? this : new Sms();
        
        sms.id = rs.getLong(idColName);
        sms.nomorInduk = rs.getString(nomorIndukColName);
        sms.telepon = rs.getString(teleponColName);
        sms.message = rs.getString(messageColName);
        sms.flagRequested = rs.getBoolean(flagRequestedColName);
        sms.requester = rs.getString(requesterColName);
        sms.createdTime = Kalender.fromResultSet(rs, createdTimeColName);
        sms.updatedTime = Kalender.fromResultSet(rs, updatedTimeColName);
        if(sms.isDBValid())
            return sms;
        else
            throw new KasirException(KasirException.Tipe.BAD_RECORD, tableName);
    }
    
    /* never ret null */
    public static Sms fromResultSet(ResultSet rs) throws SQLException, KasirException{
        return new Sms().dynFromResultSet(rs, true);
    }
    
    public static Sms fromResultSet(ResultSet rs, String colName, Number val) throws SQLException, KasirException{
        assert colName != null && !colName.isEmpty();
        
        if(DBSR.searchRow(rs, colName, val) > 0)
            return fromResultSet(rs);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, val);
    }
    public static Sms fromResultSet(ResultSet rs, String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
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
    public static Sms fromResultSet(ResultSet rs, long id) throws SQLException, KasirException{
        assert id > 0 : "fromResultSet(ResultSet, int id < 1)";
        
        return fromResultSet(rs, idColName, id);
    }
    
    /* flagRequested may not be null / empty
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, flagRequested) if no matching row found
     */
    
    
    /* caller of this method must ensure that isDBValid = true & RS cursor points to the intended row
     * never ret false
     */
    public boolean flushResultSet(ResultSet rs, boolean flushUsername) throws SQLException{      
        rs.updateString(nomorIndukColName, nomorInduk);
        rs.updateString(teleponColName, telepon);
        rs.updateString(messageColName, message);
        if(flushUsername)
            rs.updateBoolean(flagRequestedColName, flagRequested);
        rs.updateString(requesterColName, requester);
        return true;
    }
    
    /* caller of this method must ensure that RS cursor is at the insert row & that no existing row has the same flagRequested & call rs.insertRow() when all done
     * throws KasirException(DB_INVALID, this) if isInsertDBValid = false
     * never ret false
     */
    public boolean insertResultSet(ResultSet rs) throws SQLException, KasirException{        
        if(isInsertDBValid())
            return flushResultSet(rs, true);
        else
            throw new KasirException(KasirException.Tipe.DB_INVALID, this);
    }
    
    /* updates without updating flagRequested
     * if flagRequested is changed prior to calling this method, the new flagRequested is still isn't stored
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
    
    /* updates including flagRequested. if oldUsername.equalsIgnoreCase(flagRequested) = true, then it's the same as updateResultSet(rs)
     * oldUsername may not be null
     * throws KasirException(DB_INVALID, this) if isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, this/oldUsername) if no row matches
     * throws KasirException(DUPLICATE_PRIMARY_KEY, this) if the new flagRequested has already existed in db
     * never ret false
     */
//    public boolean updateResultSetUsername(ResultSet rs, String oldUsername) throws SQLException, KasirException{
//        assert oldUsername != null : "oldUsername = null";
//        
//        if(!isDBValid())
//            throw new KasirException(KasirException.Tipe.DB_INVALID, this);
//        
//        if(flagRequested.equalsIgnoreCase(oldUsername)){
//            if(DBSR.searchRow(rs, userColName, false, flagRequested) > 0)
//                return flushResultSet(rs, false);
//            else
//                throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, this);
//        }else{
//            if(DBSR.searchRow(rs, userColName, false, flagRequested) > 0)
//                throw new KasirException(KasirException.Tipe.DUPLICATE_PRIMARY_KEY, this);
//            
//            if(DBSR.searchRow(rs, userColName, false, oldUsername) > 0)
//                return flushResultSet(rs, true);
//            else
//                throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, oldUsername);
//        }
//    }
    
    //ret empty if all vars are empty
    public String asWhereClause(){
        LinkedList<String> whereClause = new LinkedList<>();
        if (nomorInduk != null && !nomorInduk.isEmpty())
            whereClause.add(nomorIndukColName + " LIKE '%" + nomorInduk + "%'");
        if (telepon != null && !telepon.isEmpty())
            whereClause.add(teleponColName + " LIKE '%" + telepon + "%'");
        if (message != null && !message.isEmpty())
            whereClause.add(messageColName + " LIKE '%" + message + "%'");
        if (flagRequested != null )
            whereClause.add(flagRequestedColName + " LIKE '%" + flagRequested + "%'");

        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    public String asWhereClauseExact(){
        LinkedList<String> whereClause = new LinkedList<>();
        if (nomorInduk != null && !nomorInduk.isEmpty())
            whereClause.add(nomorIndukColName + " = '" + nomorInduk + "'");
        if (telepon != null && !telepon.isEmpty())
            whereClause.add(teleponColName + " = '" + telepon + "'");
        if (message != null && !message.isEmpty())
            whereClause.add(messageColName + " = '" + message + "'");
        if (flagRequested != null )
            whereClause.add(flagRequestedColName + " = '" + flagRequested + "'");

        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    
    
    //===================================CLERK SELECT
    public static Sms select(String colName, Number val) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByNumber(tableName, colName, 1, val);
        return Sms.fromResultSet(rs, colName, val);
    }
    public static Sms select(String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByString(tableName, colName, 1, val);
        return Sms.fromResultSet(rs, colName, caseSensitive, val);
    }
    
    /* id must be > 0
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, id) if no row matches
     */
    public static Sms select(long id) throws SQLException, KasirException{
        assert id > 0 : "id < 1";

        return select(idColName, id);
    }    

    /* flagRequested may not be null / empty
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, flagRequested) if no row matches
     */
   
    
    /* elts of ids < 1 are ignored
    * ret all Clerk if ids = null
    * Map.get(ids[i]) = null if no row has ids[i]
    * Map.size() <= ids.length, because duplicate elts of ids are treated as 1 map entry
    * throws KasirException(BAD_RECORD, Clerk.tableName)
    * ret empty Map if (ids = null & table is empty) / (all elts of ids aren't valid (null / empty)) / ids.isEmpty = true
    * ret Map<id,Clerk>. never null
    */
    public static Map<Long,Sms> selectS(Set<Long> ids) throws SQLException, KasirException{
        ResultSet rs;
        Map<Long,Sms> idClerk;
        if(ids == null){
            rs = DBSR.takeResultSetByNumberColl(tableName, idColName, -1, null);

            idClerk = new HashMap<>(DBSR.rowCountRS(rs));                
            rs.beforeFirst();
            while(rs.next()){
                Sms c = Sms.fromResultSet(rs);
                idClerk.put(c.id, c);
            }
        }else{
            Set<Long> validId = new HashSet<>();
            for(Long id : ids){
                if(id > 0)
                    validId.add(id);
            }
            rs = DBSR.takeResultSetByNumberColl(tableName, idColName, -1, validId);

            idClerk = new HashMap<>(validId.size());                
            for(Long id : validId){
                try{
                    idClerk.put(id, Sms.fromResultSet(rs, id));
                }catch(KasirException e){
                    idClerk.put(id, null);
                }
            }
        }

        return idClerk;
    }

    public static Sms filterSelect(Filter<Sms> filter) throws SQLException, KasirException{
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
     * ret Map<id,sms>. never null
     */
    public static Map<Long,Sms> filterSelectS(Set<? extends Filter<Sms>> filters) throws SQLException, KasirException{
        ResultSet rs;
        if(filters == null)
            rs = DBSR.takeResultSetByFilterColl(tableName, null, -1, null);
        else{
            //remove null filters / filters with empty asWhereClause, so BAD_RECORD is only detected if filters = null
            //arg filters isn't modified
            Set<Filter<Sms>> validFilters = new HashSet<>();
            for(Filter<Sms> filter : filters){
                if(filter != null && !"".equals(filter.asWhereClause()))
                    validFilters.add(filter);
            }
            rs = DBSR.takeResultSetByFilterColl(tableName, null, -1, validFilters);
        }                        

        Map<Long,Sms> idClerk = new HashMap<>(DBSR.rowCountRS(rs));
        while(rs.next()){
            Sms c = Sms.fromResultSet(rs);
            idClerk.put(c.id, c);
        }

        return idClerk;
    }


    public boolean insert() throws SQLException, KasirException{
        return DBSR.insertKasirO(tableName, idColName, this);
    }
    public static int insertS(ArrayList<Sms> smss) throws SQLException, KasirException{
        return DBSR.insertKasirOs(tableName, idColName, smss, true);
    }
    
    //===================================CLERK UPDATE
    /* flush current states (including flagRequested) of sms into db
     * oldUsername & sms may not be null. oldUsername is the previous val of sms.flagRequested
     * throws KasirException(DB_INVALID, sms) if sms.isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, oldUsername) if flagRequestedOld record = not in db
     * throws KasirException(DUPLICATE_PRIMARY_KEY, sms) if sms.flagRequested has already existed in db
     * never ret false
     */
   
    public boolean update() throws SQLException, KasirException{
        return DBSR.updateKasirO(tableName, idColName, this);
    }
    public static int updateS(ArrayList<Sms> smss) throws SQLException, KasirException{
        return DBSR.updateKasirOs(tableName, idColName, smss);
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
    public static boolean delete(String flagRequested) throws SQLException, KasirException{
        return DBSR.deleteKasirO(tableName, idColName, false, flagRequested);
    }

    /* del all rows having any elt of ids on colName, including < 0 (ie. del all bad records)
     * del all rows if ids = null
     */
    public static int deleteS(Set<Long> ids) throws SQLException{
        return DBSR.deleteKasirOsByNumber(tableName, idColName, ids);
    }

    /* smss may not be null
     * this is equal to deleteS(user, requester, Set) where Set contains ids of all smss, including bad id
     */
    public static int deleteS(ArrayList<Sms> smss) throws SQLException{
        Set<Long> ids = new HashSet<>(smss.size());
        for(Sms c : smss)
            ids.add(c.id);
        return deleteS(ids);
    }

    @Override
    public Long getKey() {
        return id;
    }
}
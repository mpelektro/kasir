/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kasir;

import java.sql.*;
import java.util.*;
import org.apache.commons.codec.digest.DigestUtils;
import pelajar.*;
import sak.*;
import iuran.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author kedra
 */
public class Control {
    public enum DBType{INT, BIGINT, FLOAT, VARCHAR, DATE, TIMESTAMP, ENUM}
    
    /* insert 'clerk' directly into db using predefined db-account & immediately login using 'clerk'
     * ret 'clerk' with valid 'id'
     * 'clerk' may not be null
     * ret null if clerk.isDBValid = false
     * throws KasirException(DUPLICATE_USERNAME, clerk) if a clerk with the same username has already existed
     * ACHTUNG!!! username is not case sensitive
     */
    public static Clerk insertInitialClerk(Clerk clerk) throws SQLException, KasirException{
        assert clerk != null : "clerk = null";
        
        if(insertClerk(clerk)){
            Clerk c = selectClerk(clerk.username);  //retrieve to get 'id'
            c.pass = clerk.pass;  //set c.pass as the decoded version
            return c;
        }            
        return null;
    }
    
    
    /* username & pass may not be null
     * ret null if isDBValid = false
     * throws KasirException(LOGIN_ERROR, Pair(username,pass)) for failed auth
     * ACHTUNG!!! username is not case sensitive
     */
    public static Clerk login(String user, String pass) throws SQLException, KasirException{
        assert user != null && pass != null : "login(String user = null, String pass = null)";
        
        //to server
        try{
            Clerk clerk = selectClerk(user);
            if(clerk == null)
                throw new KasirException(KasirException.Tipe.LOGIN_ERROR, new Pair<>(user,pass));
            else if(clerk.username.equalsIgnoreCase(user) && clerk.pass.equals(DigestUtils.sha1Hex(pass).substring(0,32))){
                //Clerk.current = new Clerk(null, null, null, "marbun", "marbun123456");
                Clerk.current = new Clerk(clerk.nip, clerk.nama, clerk.username, clerk.pass);
                Clerk.current.id = clerk.id;
                clerk.pass = pass;
                return clerk;
            }else
                throw new KasirException(KasirException.Tipe.LOGIN_ERROR, new Pair<>(user,pass));
        }catch(KasirException e){
            throw new KasirException(KasirException.Tipe.LOGIN_ERROR, new Pair<>(user,pass));
        }
    }
    
    public void logout() throws SQLException{
        DBSR.stmt.close();
        Clerk.current = null;
    }
    //===================================PROFIL SELECT
    public static Profil selectProfil(String colName, Number val) throws SQLException, KasirException{
        return Profil.select(colName, val);
    }
    public static Profil selectProfil(String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        return Profil.select(colName, caseSensitive, val);
    }
    
    /* noInduk may not be null / empty
     * never ret null
     * throw KasirException(ROW_NOT_FOUND, noInduk)
     */
    public static Profil selectProfil(String noInduk) throws SQLException, KasirException{
        return Profil.select(noInduk);
    }
    
    
    /* null / empty elts of noInduks are ignored
     * ret all Profil if noInduks = null
     * Map.get(noInduks[i]) = null if no row has noInduks[i]
     * Map.size() <= noInduks.length, because duplicate elts of ids are treated as 1 map entry
     * throws KasirException(BAD_RECORD, Profil.tableName)
     * throws KasirException(ROW_NOT_FOUND, Set<Integer>, Set elts are indexes such that noInduks.get(index) is valid but not found
     * ret empty Map if (noInduks = null & table is empty) / (all elts of noInduks aren't valid (null / empty)) / noInduks.isEmpty = true
     * ret Map<noInduk,profil>. never null
     */
    public static Map<String, Profil> selectProfils(Set<String> noInduks) throws SQLException, KasirException{
        return Profil.selectS(noInduks);
    }
    
    
    public static ArrayList<Profil> selectProfils(String curLev)throws SQLException, KasirException{
        return Profil.selectS(curLev);
    } 
    /* filters is either ArrayList<Profil> or ArrayList<Biodata>
     * null elts of filters are ignored
     * ret all Profil if filters == null
     * throws KasirException(BAD_RECORD, Profil.tableName)
     * ret empty Map if (filters = null & table is empty) / all elts of filters = null / filters.isEmpty = true
     * ret Map<noInduk,profil>. never null
     */
    public static Map<String, Profil> filterSelectProfils(Set<? extends Filter<Profil>> filters) throws SQLException, KasirException{
        return Profil.filterSelectS(filters);
    }
    
    //===================================PROFIL INSERT
    /* insert current states (including noInduk) of profil into db
     * profil may not be null
     * throws KasirException(DB_INVALID, profil) if profil.isDBValid = false
     * throws KasirException(DUPLICATE_PRIMARY_KEY, profil) if profil.noInduk has already existed in db
     * never ret false
     */
    public static boolean insertProfil(Profil profil) throws SQLException, KasirException{      
        return profil.insert();
    }
    
    /* insert current states (including noInduk) of profils into db
     * 'profils' may not be null
     * null elts of 'profils' are removed, note this removal done directly on the arraylist given as 'profils'
     * throws KasirException(DB_INVALID, Set<Integer>), Set elts are indexes such that profils.get(index).isDBValid() = false
     * throws KasirException(DUPLICATE_PRIMARY_KEY_INPUT, Set<Set<Integer>>), a Set elt is a Set of indexes of profils having the same noInduk
     * throws KasirException(DUPLICATE_PRIMARY_KEY, Set<Integer>), Set elts are indexes such that profils.get(index).noInduk = already in db
     * ret num of profil successfully inserted
     * ret -1 if profils.isEmpty() = true before null elts removal
     * ret -2 if profils.isEmpty() = true after null elts removal
     */
    public static int insertProfils(ArrayList<Profil> profils) throws SQLException, KasirException{
        return Profil.insertS(profils);
    }
    
    //===================================PROFIL UPDATE
    /* flush current states (including noInduk) of profil into db
     * oldNoInduk & profil may not be null. oldNoInduk is the previous val of profil.noInduk
     * throws KasirException(DB_INVALID, profil) if profil.isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, oldNoInduk) if noIndukOld record = not in db
     * throws KasirException(DUPLICATE_PRIMARY_KEY, profil) if profil.noInduk has already existed in db
     * never ret false
     */
    public static boolean updateProfilNoInduk(String oldNoInduk, Profil profil) throws SQLException, KasirException{        
        return profil.updateNoInduk(oldNoInduk);
    }
    
    /* flush current states (except noInduk) of profil into db
     * if profil.noInduk is changed prior to calling this method then either not finding the record / updating the wrong record
     * profil may not be null
     * throws KasirException(DB_INVALID, profil) if profil.isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, profil) if profil = not in db
     * never ret false
     */
    public static boolean updateProfil(Profil profil) throws SQLException, KasirException{
        return profil.update();
    }
    
    /* flush current states (except noInduk) of profils into db
     * for each profil, if profil.noInduk is changed prior to calling this method then either not finding the record / updating the wrong record
     * 'profils' may not be null
     * null elts of 'profils' are removed, note this removal done directly on the arraylist given as 'profils'
     * throws KasirException(DB_INVALID, Set<Integer>), Set elts are indexes such that profils.get(index).isDBValid() = false
     * throws KasirException(ROW_NOT_FOUND, Set<Integer>), Set elts are indexes such that profils.get(index) = not in db
     * ret num of profil successfully flushed
     * ret -1 if profils.isEmpty() = true before null elts removal
     * ret -2 if profils.isEmpty() = true after null elts removal
     */
    public static int updateProfils(ArrayList<Profil> profils) throws SQLException, KasirException{
        return Profil.updateS(profils);
    }
    
    //===================================PROFIL DELETE
    /* noInduk may be null / "", which in this case del the first found bad record
     * throws KasirException(ROW_NOT_FOUND, noInduk)
     */
    public static boolean deleteProfil(String noInduk) throws SQLException, KasirException{
        return Profil.delete(noInduk);
    }
    
    /* del all rows having any elt of noInduks on colName, including null/empty (ie. del all bad records)
     * del all rows if noInduks = null
     */
    public static int deleteProfils(Set<String> noInduks) throws SQLException{
        return Profil.deleteS(noInduks);
    }
    
    /* profils may not be null
     * this is equal to deleteProfils(user, pass, Set) where Set contains noInduks of all profils, including bad noInduk
     */
    public static int deleteProfils(ArrayList<Profil> profils) throws SQLException{
        return Profil.deleteS(profils);
    }
    
    
    
    //===================================CLERK SELECT
    public static Clerk selectClerk(String colName, Number val) throws SQLException, KasirException{
        return Clerk.select(colName, val);
    }
    public static Clerk selectClerk(String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        return Clerk.select(colName, caseSensitive, val);
    }
    
    /* id must be > 0
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, id) if no row matches
     */
    public static Clerk selectClerk(int id) throws SQLException, KasirException{
        return Clerk.select(id);
    }
    

    /* username may not be null / empty
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, username) if no row matches
     */
    public static Clerk selectClerk(String username) throws SQLException, KasirException{
        return Clerk.select(username);
    }
    
    
    /* elts of ids < 1 are ignored
    * ret all Clerk if ids = null
    * Map.get(ids[i]) = null if no row has ids[i]
    * Map.size() <= ids.length, because duplicate elts of ids are treated as 1 map entry
    * throws KasirException(BAD_RECORD, Clerk.tableName)
    * ret empty Map if (ids = null & table is empty) / (all elts of ids aren't valid (null / empty)) / ids.isEmpty = true
    * ret Map<id,Clerk>. never null
    */
    public static Map<Integer,Clerk> selectClerks(Set<Integer> ids) throws SQLException, KasirException{
        return Clerk.selectS(ids);
    }

    /* filters is ArrayList<Clerk>
     * null elts of filters are ignored
     * ret all Clerk if filters == null
     * throws KasirException(BAD_RECORD, Clerk.tableName)
     * ret empty Map if (filters = null & table is empty) / all elts of filters = null / filters.isEmpty = true
     * ret Map<id,clerk>. never null
     */
    public static Map<Integer,Clerk> filterSelectClerks(Set<? extends Filter<Clerk>> filters) throws SQLException, KasirException{
        return Clerk.filterSelectS(filters);
    }
    
    
    public static boolean insertClerk(Clerk clerk) throws SQLException, KasirException{
        return clerk.insert();
    }
    public static int insertClerks(ArrayList<Clerk> clerks) throws SQLException, KasirException{
        return Clerk.insertS(clerks);
    }
    
    //===================================CLERK UPDATE
    /* flush current states (including username) of clerk into db
     * oldUsername & clerk may not be null. oldUsername is the previous val of clerk.username
     * throws KasirException(DB_INVALID, clerk) if clerk.isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, oldUsername) if usernameOld record = not in db
     * throws KasirException(DUPLICATE_PRIMARY_KEY, clerk) if clerk.username has already existed in db
     * never ret false
     */
    public static boolean updateClerkUsername(String oldUsername, Clerk clerk) throws SQLException, KasirException{
        return clerk.updateUsername(oldUsername);
    }
    public static boolean updateClerk(Clerk clerk) throws SQLException, KasirException{
        return clerk.update();
    }
    public static int updateClerks(ArrayList<Clerk> clerks) throws SQLException, KasirException{
        return Clerk.updateS(clerks);
    }

    
    /* id may be < 0, which in this case del the first found bad record
     * throws KasirException(ROW_NOT_FOUND, id)
     */
    public static boolean deleteClerk(int id) throws SQLException, KasirException{
        return Clerk.delete(id);
    }
    public static boolean deleteClerk(String username) throws SQLException, KasirException{
        return Clerk.delete(username);
    }

    /* del all rows having any elt of ids on colName, including < 0 (ie. del all bad records)
     * del all rows if ids = null
     */
    public static int deleteClerks(Set<Integer> ids) throws SQLException{
        return Clerk.deleteS(ids);
    }

    /* clerks may not be null
     * this is equal to deleteS(user, pass, Set) where Set contains ids of all clerks, including bad id
     */
    public static int deleteClerks(ArrayList<Clerk> clerks) throws SQLException{
        return Clerk.deleteS(clerks);
    }
    
    //=====================================SMS
    //===================================SMS SELECT
    public static Sms selectSms(String colName, Number val) throws SQLException, KasirException{
        return Sms.select(colName, val);
    }
    public static Sms selectSms(String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        return Sms.select(colName, caseSensitive, val);
    }
    
    /* id must be > 0
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, id) if no row matches
     */
    public static Sms selectSms(int id) throws SQLException, KasirException{
        return Sms.select(id);
    }
    

    /* username may not be null / empty
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, username) if no row matches
     */
    public static Sms selectSms(Long username) throws SQLException, KasirException{
        return Sms.select(username);
    }
    
    
    /* elts of ids < 1 are ignored
    * ret all Sms if ids = null
    * Map.get(ids[i]) = null if no row has ids[i]
    * Map.size() <= ids.length, because duplicate elts of ids are treated as 1 map entry
    * throws KasirException(BAD_RECORD, Sms.tableName)
    * ret empty Map if (ids = null & table is empty) / (all elts of ids aren't valid (null / empty)) / ids.isEmpty = true
    * ret Map<id,Sms>. never null
    */
    public static Map<Long,Sms> selectSmss(Set<Long> ids) throws SQLException, KasirException{
        return Sms.selectS(ids);
    }

    /* filters is ArrayList<Sms>
     * null elts of filters are ignored
     * ret all Sms if filters == null
     * throws KasirException(BAD_RECORD, Sms.tableName)
     * ret empty Map if (filters = null & table is empty) / all elts of filters = null / filters.isEmpty = true
     * ret Map<id,sms>. never null
     */
    public static Map<Long,Sms> filterSelectSmss(Set<? extends Filter<Sms>> filters) throws SQLException, KasirException{
        return Sms.filterSelectS(filters);
    }
    
    
    public static boolean insertSms(Sms sms) throws SQLException, KasirException{
        return sms.insert();
    }
    public static int insertSmss(ArrayList<Sms> smss) throws SQLException, KasirException{
        return Sms.insertS(smss);
    }
    
    //===================================SMS UPDATE
    /* flush current states (including username) of sms into db
     * oldUsername & sms may not be null. oldUsername is the previous val of sms.username
     * throws KasirException(DB_INVALID, sms) if sms.isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, oldUsername) if usernameOld record = not in db
     * throws KasirException(DUPLICATE_PRIMARY_KEY, sms) if sms.username has already existed in db
     * never ret false
     */
    
    public static boolean updateSms(Sms sms) throws SQLException, KasirException{
        return sms.update();
    }
    public static int updateSmss(ArrayList<Sms> smss) throws SQLException, KasirException{
        return Sms.updateS(smss);
    }

    
    /* id may be < 0, which in this case del the first found bad record
     * throws KasirException(ROW_NOT_FOUND, id)
     */
    public static boolean deleteSms(int id) throws SQLException, KasirException{
        return Sms.delete(id);
    }
    public static boolean deleteSms(String username) throws SQLException, KasirException{
        return Sms.delete(username);
    }

    /* del all rows having any elt of ids on colName, including < 0 (ie. del all bad records)
     * del all rows if ids = null
     */
    public static int deleteSmss(Set<Long> ids) throws SQLException{
        return Sms.deleteS(ids);
    }

    /* smss may not be null
     * this is equal to deleteS(user, pass, Set) where Set contains ids of all smss, including bad id
     */
    public static int deleteSmss(ArrayList<Sms> smss) throws SQLException{
        return Sms.deleteS(smss);
    }
    
    //======================================IURAN
    public static <T extends Iuran> T selectIuran(T.Tipe tipe, String colName, Number val) throws SQLException, KasirException{
        return Iuran.select(tipe, colName, val);
    }
    public static <T extends Iuran> T selectIuran(T.Tipe tipe, String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        return Iuran.select(tipe, colName, caseSensitive, val);
    }
    public static <T extends Iuran> T selectIuran(T.Tipe tipe, long id) throws SQLException, KasirException{
        return Iuran.select(tipe, id);
    }
    
    public static <T extends Iuran> List<T> selectIurans(T.Tipe tipe, String colName, Number... vals) throws SQLException, KasirException{
        return Iuran.selectS(tipe, colName, vals);
    }
    public static <T extends Iuran> List<T> selectIurans(T.Tipe tipe, String colName, boolean caseSensitive, String... vals) throws SQLException, KasirException{
        return Iuran.selectS(tipe, colName, caseSensitive, vals);
    }
    public static <T extends Iuran> Map<Long,T> selectIurans(T.Tipe tipe, Set<Long> ids) throws SQLException, KasirException{
        return Iuran.selectS(tipe, ids);
    }
    /*
    public static <T extends Iuran> T filterSelectIuran(T.Tipe tipe, Filter<T> filter) throws SQLException, KasirException{
        return Iuran.filterSelect(tipe, filter);
    }*/
    public static <T extends Iuran> Map<Long,T> filterSelectIurans(T.Tipe tipe, Set<? extends Filter<T>> filters) throws SQLException, KasirException{
        return Iuran.filterSelectS(tipe, filters);
    }
    public static <T extends Iuran> Map<Long,T> exactFilterSelectIurans(T.Tipe tipe, Set<? extends Filter<T>> filters) throws SQLException, KasirException{
        return Iuran.exactFilterSelectS(tipe, filters);
    }
    
    public static <T extends Iuran> boolean insertIuran(T.Tipe tipe, T iuran) throws SQLException, KasirException{
        return iuran.insert(tipe);
    }        
    public static <T extends Iuran> int insertIurans(T.Tipe tipe, ArrayList<T> iurans) throws SQLException, KasirException{
        return Iuran.insertS(tipe, iurans);
    }
    
    public static <T extends Iuran> boolean updateIuran(T.Tipe tipe, T iuran) throws SQLException, KasirException{
        return iuran.update(tipe);
    }        
    public static <T extends Iuran> int updateIurans(T.Tipe tipe, ArrayList<T> iurans) throws SQLException, KasirException{
        return Iuran.updateS(tipe, iurans);
    }
    
    public static <T extends Iuran> boolean deleteIuran(T.Tipe tipe, long id) throws SQLException, KasirException{
        return Iuran.delete(tipe, id);
    }
    public static <T extends Iuran> int deleteIurans(T.Tipe tipe, Set<Long> ids) throws SQLException{
        return Iuran.deleteS(tipe, ids);
    }
    public static <T extends Iuran> int deleteIurans(T.Tipe tipe, ArrayList<T> iurans) throws SQLException{
        return Iuran.deleteS(tipe, iurans);
    }
    
    
    //======================================TDETAIL
    public static <T extends TransactionDetail> T selectTDetail(T.Tipe tipe, String colName, Number val) throws SQLException, KasirException{
        return TransactionDetail.select(tipe, colName, val);
    }
    public static <T extends TransactionDetail> T selectTDetail(T.Tipe tipe, String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        return TransactionDetail.select(tipe, colName, caseSensitive, val);
    }
    public static <T extends TransactionDetail> T selectTDetail(T.Tipe tipe, long id) throws SQLException, KasirException{
        return TransactionDetail.select(tipe, id);
    }
    
    public static <T extends TransactionDetail> List<T> selectTDetails(T.Tipe tipe, String colName, boolean caseSentivie, String val) throws SQLException, KasirException{
        return TransactionDetail.selectS(tipe, colName, caseSentivie, val);
    }
    public static <T extends TransactionDetail> List<T> selectTDetails(T.Tipe tipe, String colName, String val) throws SQLException, KasirException{
        return TransactionDetail.selectS(tipe, colName, val);
    }
    public static <T extends TransactionDetail> List<T> selectTDetails(T.Tipe tipe, String colName, boolean val) throws SQLException, KasirException{
        return TransactionDetail.selectS(tipe, colName, val);
    }
    
    
    public static <T extends TransactionDetail> Map<Long,T> selectTDetails(T.Tipe tipe, Set<Long> ids) throws SQLException, KasirException{
        return TransactionDetail.selectS(tipe, ids);
    }
    
    public static <T extends TransactionDetail> Map<Long,T> filterSelectTDetails(T.Tipe tipe, Set<? extends Filter<T>> filters) throws SQLException, KasirException{
        return TransactionDetail.filterSelectS(tipe, filters);
    }
    
    public static <T extends TransactionDetail> boolean insertTDetail(T.Tipe tipe, T tDetail) throws SQLException, KasirException{
        return tDetail.insert(tipe);
    }        
    public static <T extends TransactionDetail> int insertTDetails(T.Tipe tipe, ArrayList<T> tDetails) throws SQLException, KasirException{
        return TransactionDetail.insertS(tipe, tDetails);
    }
    
    public static <T extends TransactionDetail> boolean updateTDetail(T.Tipe tipe, T tDetail) throws SQLException, KasirException{
        return tDetail.update(tipe);
    }        
    public static <T extends TransactionDetail> int updateTDetails(T.Tipe tipe, ArrayList<T> tDetails) throws SQLException, KasirException{
        return TransactionDetail.updateS(tipe, tDetails);
    }
    
    public static <T extends TransactionDetail> boolean updateInsertTDetail(T.Tipe tipe, T tDetail) throws SQLException, KasirException{
        return tDetail.updateInsert(tipe);
    }
    public static <T extends TransactionDetail> int updateInsertTDetails(T.Tipe tipe, ArrayList<T> tDetails) throws SQLException, KasirException{
        return TransactionDetail.updateInsertS(tipe, tDetails);
    }
    
    public static <T extends TransactionDetail> boolean deleteTDetail(T.Tipe tipe, long id) throws SQLException, KasirException{
        return TransactionDetail.delete(tipe, id);
    }
    public static <T extends TransactionDetail> int deleteTDetails(T.Tipe tipe, Set<Long> ids) throws SQLException{
        return TransactionDetail.deleteS(tipe, ids);
    }
    public static <T extends TransactionDetail> int deleteTDetails(T.Tipe tipe, ArrayList<T> tDetails) throws SQLException{
        return TransactionDetail.deleteS(tipe, tDetails);
    }
    
    
    //======================================TSUMMARY
    public static TransactionSummary selectTSummary(String colName, Number val) throws SQLException, KasirException{
        return TransactionSummary.select(colName, val);
    }
    public static TransactionSummary selectTSummary(String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        return TransactionSummary.select(colName, caseSensitive, val);
    }
    public static TransactionSummary selectTSummary(long id) throws SQLException, KasirException{
        return TransactionSummary.select(id);
    }
    
    public static Map<Long, TransactionSummary> selectTSummaries(Set<Long> ids) throws SQLException, KasirException{
        return TransactionSummary.selectS(ids);
    }
    
    public static Map<Long, TransactionSummary> filterSelectTSummaries(Set<? extends Filter<TransactionSummary>> filters) throws SQLException, KasirException{
        return TransactionSummary.filterSelectS(filters);
    }
    
    public static boolean insertTSummary(TransactionSummary tSummary) throws SQLException, KasirException{      
        return tSummary.insert();
    }
    
    public static boolean updateTSummary(TransactionSummary tSummary) throws SQLException, KasirException{
        return tSummary.update();
    }
    
    public static boolean deleteTSummary(Long id) throws SQLException, KasirException{
        return TransactionSummary.delete(id);
    }
    
    
    //===================================MapAccountGL
    public static MapAccountGL selectMapAccGL(String colName, Number val) throws SQLException, KasirException{
        return MapAccountGL.select(colName, val);
    }
    public static MapAccountGL selectMapAccGL(String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        return MapAccountGL.select(colName, caseSensitive, val);
    }
    
    public static ArrayList<MapAccountGL> selectMapAccGLs(String colName, Number... vals) throws SQLException, KasirException{
        return MapAccountGL.selectS(colName, vals);
    }
    public static ArrayList<MapAccountGL> selectMapAccGLs(String colName, boolean caseSensitive, String... vals) throws SQLException, KasirException{
        return MapAccountGL.selectS(colName, caseSensitive, vals);
    }
    
    public static ArrayList<MapAccountGL> filterSelectMapAccGLs(Set<? extends Filter<MapAccountGL>> filters) throws SQLException, KasirException{
        return MapAccountGL.filterSelectS(filters);
    }
    
    public static boolean insertMapAccGL(MapAccountGL magl) throws SQLException, KasirException{
        return magl.insert();
    }
    public static boolean updateMapAccGL(MapAccountGL magl) throws SQLException, KasirException{
        return magl.update();
    }
    
    public static boolean deleteMapAccGL(long id) throws SQLException, KasirException{
        return MapAccountGL.delete(id);
    }
    
    //===========================================transfer tDetails to gl_trans   
    public static class TDetailToGL_Trans{
        static final String akunDBurl = "jdbc:mysql://ark3.dayarka.com/rusly_accountingdb2016", akundbUsername = "marbun", akundbPass = "marbun123456";
        static Connection conn;
        static Statement stmt;
        
        static long type_no;
        static List<MapAccountGL> mapAccGLs;
        static ArrayList<ArrayList<GLTransRecordSet>> GLTRSsAL;  //all tDetails gltrs
        
        static void init() throws SQLException, KasirException{
            //init stmt
            if(stmt == null || stmt.isClosed()){
                if(conn == null || conn.isClosed())
                    conn = DriverManager.getConnection(akunDBurl, akundbUsername, akundbPass);
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            }
            
            //get type_no
            ResultSet rs = stmt.executeQuery("select max(type_no) from 0_gl_trans");
            rs.next();
            type_no = rs.getLong(1) + 1;
            //type_no = stmt.executeQuery("select max(type_no) from 0_gl_trans").getLong(1) + 1;
            
            //get mapAccGLs
            mapAccGLs = selectMapAccGLs(MapAccountGL.idColName, (Number[])null);
            
            //build gltrs
            GLTRSsAL = new ArrayList<>();
            for(TransactionDetail.Tipe tipeTDetail : TransactionDetail.Tipe.values())
                GLTRSsAL.add(buildGLTRS(tipeTDetail));  //GLTRSsAL might contain empty arraylist
        }
        
        static ArrayList<GLTransRecordSet> buildGLTRS(TransactionDetail.Tipe tipe) throws SQLException, KasirException{
            //Harus tambahin filter per kasir
            
            ArrayList<TransactionDetail> tDetails = new ArrayList<>(selectTDetails(tipe, TransactionDetail.settledColName, false));
            //filter untuk current clerk 
            ArrayList<TransactionDetail> temp = new ArrayList<>();
            for(int i = 0; i<tDetails.size(); i++){
                if(tDetails.get(i).idClerk != Clerk.current.id){
                    temp.add(tDetails.get(i));
                }
            }
            tDetails.removeAll(temp);
            ArrayList<TransactionDetail> normalTDetails = new ArrayList<>(), piutangTDetails = new ArrayList<>();
            for(TransactionDetail tDetail : tDetails){
                if(tDetail.piutang)
                    piutangTDetails.add(tDetail);
                else
                    normalTDetails.add(tDetail);
            }
            
            
            ArrayList<GLTransRecordSet> gltrsS = new ArrayList<>();
            for(Level.Level1 lv1 : Level.Level1.values()){
                for(TransactionDetail.PaymentMethod pm : TransactionDetail.PaymentMethod.values()){
                //for normal
                    GLTransRecordSet gltrs = GLTransRecordSet.create(type_no, tipe, lv1, pm, normalTDetails, mapAccGLs);
                    if(gltrs != null){
                        gltrsS.add(gltrs);
                        ++type_no;
                    }
                
                    
                    //for piutang
                    gltrs = GLTransRecordSet.create(type_no, tipe, lv1, pm, piutangTDetails, mapAccGLs);
                    //gltrs = GLTransRecordSet.create(type_no, TransactionDetail.Tipe.CicilanHutangTransaction, lv1, TransactionDetail.PaymentMethod.CASH, piutangTDetails, mapAccGLs);
                    if(gltrs != null){
                        gltrsS.add(gltrs);
                        ++type_no;
                    }
                }
            }
            
            Control.updateTDetails(tipe, tDetails);
            
            return gltrsS;
        }
        
        static void deinit() throws SQLException{
            GLTRSsAL = null;
            mapAccGLs = null;
            conn.close();
        }
        
        static String genThePuckingSQL() throws KasirException{
            String sql = "insert into 0_gl_trans (type_no, tran_date, account, memo_, amount, opposite_party) values ";
            LinkedList<String> gltrsInsertClause = new LinkedList<>();
            
            for(ArrayList<GLTransRecordSet> gltrsS : GLTRSsAL){
                for(GLTransRecordSet gltrs : gltrsS)
                    gltrsInsertClause.add(gltrs.asInsertClause());
            }
            
            if(gltrsInsertClause.isEmpty())
                return null;
            else
                return sql + StringUtils.join(gltrsInsertClause, ", ");
        }
        
        public static int kirimCoiiiiiiiiiiiiii() throws SQLException, KasirException{
            init();
            String sql = genThePuckingSQL();
            int status;
            if(sql == null || sql.isEmpty())
                return -1;
            else
                status =  stmt.executeUpdate(genThePuckingSQL());
            deinit();
            return status;
        }
    }
}

package kasir;

import java.util.*;
import java.sql.*;
import pelajar.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import sak.*;

public class DBSR{
    public static final String dbURL = "jdbc:mysql://ark3.dayarka.com/rusly_kasirdb", dbUsername = "marbun", dbPass = "marbun123456";
    //public static final String dbURL = "jdbc:mysql://ark3.dayarka.com/rusly_ppdbdb", dbUsername = "marbun", dbPass = "marbun123456";
    public static Connection conn;
    public static Statement stmt;

    static{
            try{
                    Class.forName("com.mysql.jdbc.Driver");
            }catch(ClassNotFoundException e){}
    }

    public static void initStatement() throws SQLException{
        if(stmt == null || stmt.isClosed()){
            if (conn == null || conn.isValid(0))
                //conn = DriverManager.getConnection(dbURL, "root", "admin");    //all db access auth
                conn = DriverManager.getConnection(dbURL, dbUsername, dbPass);    //all db access auth                    
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        }
    }

    //================================ETC
    /* ret the num of rows in rs
     * rs.beforeFirst() is called at entry
     * rs.isBeforeFirst() = true at exit
     */
    public static int rowCountRS(ResultSet rs) throws SQLException{
        int rowCount = 0;
        rs.beforeFirst();
        while(rs.next())
            ++rowCount;
        rs.beforeFirst();
        return rowCount;
    }


    /*==========================RESULT SET ROW SEARCHING
    /* colName may not be null
     * if data = null, it's assigned 0 because for numeric data type, sql null is returned as 0 by this jdbc driver
     * ret the absolute row num of the first matching row (int > 0), ret 0 if none matches
     * rs.beforeFirst() is called at entry
     * rs.getRow() = returned int, at exit
     */
    public static int searchRow(ResultSet rs, String colName, Number data) throws SQLException{
        assert colName != null : "colName = null";

        data = data == null? 0 : data;

        Class dataType = data.getClass();            
        rs.beforeFirst();

        if(dataType.equals(Integer.class)){            
            while(rs.next()){
                if(data.intValue() == ((Number)(rs.getObject(colName))).intValue())
                    return rs.getRow();
            }
        }else if(dataType.equals(Long.class)){
            while(rs.next()){
                if(data.longValue() == ((Number)(rs.getObject(colName))).longValue())
                    return rs.getRow();
            }
        }else if(dataType.equals(Float.class)){
            while(rs.next()){
                if(data.floatValue() == ((Number)(rs.getObject(colName))).floatValue())
                    return rs.getRow();
            }
        }else if(dataType.equals(Double.class)){
            while(rs.next()){
                if(data.doubleValue() == ((Number)(rs.getObject(colName))).floatValue())
                    return rs.getRow();
            }
        }else
            assert false;

        return 0;
    }

    /* colName may not be null
     * array[i] is the row num of the first row that matches data[i], 0 if not found
     * if data[i] = null, it's assigned 0 because for numeric data type, sql null is returned as 0 by this jdbc driver
     * ret empty array if data = null
     */
    public static int[] searchRow(ResultSet rs, String colName, Number... data) throws SQLException{
        assert colName != null : "colName = null";

        if(data != null){
            int rowNums[] = new int[data.length];
            for(int i = 0; i < data.length; i++)
                rowNums[i] = searchRow(rs, colName, data[i]);
            return rowNums;
        }

        return new int[0];
    }

    /* colName may not be null
     * if data = null, it's assigned 0 because for numeric data type, sql null is returned as 0 by this jdbc driver
     * ret row nums of all matching rows, in increasing order
     * ret empty array if none found
     */
    public static int[] searchRows(ResultSet rs, String colName, Number data) throws SQLException{
        assert colName != null : "colName = null";

        data = data == null? 0 : data;

        Class dataType = data.getClass();
        int rowNums[] = new int[0];
        rs.beforeFirst();

        if(dataType.equals(Integer.class)){
            while(rs.next()){
                if(data.intValue() == ((Number)(rs.getObject(colName))).intValue())
                    rowNums = ArrayUtils.add(rowNums, rs.getRow());
            }
        }else if(dataType.equals(Long.class)){
            while(rs.next()){
                if(data.longValue() == ((Number)(rs.getObject(colName))).longValue())
                    rowNums = ArrayUtils.add(rowNums, rs.getRow());
            }
        }else if(dataType.equals(Float.class)){
            while(rs.next()){
                if(data.floatValue() == ((Number)(rs.getObject(colName))).floatValue())
                    rowNums = ArrayUtils.add(rowNums, rs.getRow());
            }
        }

        return rowNums;
    }

    /* colName may not be null
     * if data[i] = null, it's assigned 0 because for numeric data type, sql null is returned as 0 by this jdbc driver
     * array[i][] is array of row nums of row that matches data[i], array[i].length = 0 if no matching row for data[i]
     * ret array.length = 0 if data = null
     */

    public static int[][] searchRows(ResultSet rs, String colName, Number... data) throws SQLException{
        assert colName != null : "colName = null";

        if(data != null){
            int rowNums[][] = new int[data.length][];
            for(int i = 0; i < data.length; i++)
                rowNums[i] = searchRows(rs, colName, data[i]);
        }

        return new int[0][];
    }

    /* colName may not be null
     * ret the absolute row num of the first matching row (int > 0), ret 0 if none matches
     * rs.beforeFirst() is called at entry
     * rs.getRow() = returned int, at exit
     */
    public static int searchRow(ResultSet rs, String colName, boolean caseSensitive, String data) throws SQLException{
        assert colName != null : "searchRow(ResultSet, String colName = null, boolean, String)";

        rs.beforeFirst();
        if(data == null){
            while(rs.next())
                if(rs.getString(colName) == null)
                    return rs.getRow();
        }else{
            if(caseSensitive){
                while(rs.next())
                    if(data.equals(rs.getString(colName)))
                        return rs.getRow();
            }else{                    
                while(rs.next()){
                    if(data.equalsIgnoreCase(rs.getString(colName)))
                        return rs.getRow();
                }
            }
        }
        return 0;
    }

    /* colName may not be null
     * ret row nums of all matching rows
     * ret empty array if none found
     */
    public static int[] searchRows(ResultSet rs, String colName, boolean caseSensitive, String data) throws SQLException{
        assert colName != null : "searchRows(ResultSet, String colName = null, boolean, String)";

        int rowNums[] = new int[0];
        rs.beforeFirst();
        if(data == null){
            while(rs.next()){
                if(rs.getString(colName) == null)
                    rowNums = ArrayUtils.add(rowNums, rs.getRow());
            }
        }else{
            if(caseSensitive){
                while(rs.next()){
                    if(data.equals(rs.getString(colName)))
                        rowNums = ArrayUtils.add(rowNums, rs.getRow());
                }
            }else{
                while(rs.next()){
                    if(data.equalsIgnoreCase(rs.getString(colName)))
                        rowNums = ArrayUtils.add(rowNums, rs.getRow());
                }
            }
        }

        return rowNums;
    }

    /* colName may not be null
     * array[i] is the row num of the first row that matches data[i], 0 if not found
     * ret empty array if data = null
     */
    public static int[] searchRow(ResultSet rs, String colName, boolean caseSensitive, String... data) throws SQLException{
        assert colName != null : "searchRow(ResultSet, String colName = null, boolean, String... data)";

        if(data != null){
            int rowNums[] = new int[data.length];
            for(int i = 0; i < data.length; ++i)
                rowNums[i] = searchRow(rs, colName, caseSensitive, data[i]);
            return rowNums;
        }
        return new int[0];
    }

    /* colName may not be null
     * array[i][] are the row nums of rows that match data[i], array[i].length = 0 if no matching row
     * ret array.length = 0 if data = null
     */
    public static int[][] searchRows(ResultSet rs, String colName, boolean caseSensitive, String... data) throws SQLException{
        assert colName != null : "searchRows(ResultSet, String colName = null, String...)";

        if(data != null){
            int rowNums[][] = new int[data.length][];
            for(int i = 0; i < data.length; i++)
                rowNums[i] = searchRows(rs, colName, caseSensitive, data[i]);
        }

        return new int[0][];
    }

    /* colName may not be null
     * ret int > 0 if found, which is the row num of the first matching row, otherwise 0
     */  
    public static int searchRow(ResultSet rs, String colName, Kalender kal) throws SQLException{
        assert colName != null : "searchRow(ResultSet, String colName = null, Kalender)";

        if(kal == null){
            while(rs.next()){
                if(rs.getTimestamp(colName) == null)
                    return rs.getRow();
            }
        }else{
            while(rs.next()){
                if(kal.equals(rs.getTimestamp(colName)))
                    return rs.getRow();
            }
        }
        return 0;
    }

    /* colName may not be null
     * array[i] is the row num of the first row that matches data[i], 0 if not found
     * ret empty array if data = null
     */
    public static int[] searchRow(ResultSet rs, String colName, Kalender... data) throws SQLException{
        assert colName != null : "searchRow(ResultSet, String colName = null, boolean, String... data)";

        int rowNums[] = new int[0];
        if(data != null){
            for(Kalender dat : data)
                rowNums = ArrayUtils.add(rowNums, searchRow(rs, colName, dat));
        }
        return rowNums;
    }

    public static int[] searchRows(ResultSet rs, String colName, Kalender data) throws SQLException{
        return null;
    }

    public static int[][] searchRows(ResultSet rs, String colName, Kalender... data) throws SQLException{
        return null;
    }

    /* colName may not be null
     * ret int > 0 if found, which is the row num of the first matching row, otherwise 0
     */
    public static int searchRow(ResultSet rs, String colName, Level level) throws SQLException{
        assert colName != null : "searchRow(ResultSet, String colName = null, Level)";

        rs.beforeFirst();
        if(level == null){
            while(rs.next()){
                if(Level.fromResultSet(rs, colName) == null)
                    return rs.getRow();
            }
        }else{
            while(rs.next()){
                if(level.equals(Level.fromResultSet(rs, colName)))
                    return rs.getRow();
            }
        }
        return 0;
    }

    /* filter may not be null
     * ret int > 0 if found, which is the row num of the first matching row, otherwise 0
     */
    /*
    public static int searchRow(ResultSet rs, Filter filter) throws SQLException{
        assert filter != null : "searchRow(ResultSet, Filter filter = null)";

        rs.beforeFirst();
        while(rs.next()){
            if(filter.equals(filter.dynFromResultSet(rs, false)))
                return rs.getRow();
        }
        return 0;
    }
    */
    /* filter may not be null
     * ret row nums of all matching rows
     * ret empty array if none found
     */
    /*
    public static int[] searchRows(ResultSet rs, Filter filter) throws SQLException{
        assert filter != null : "searchRows(ResultSet, Filter filter = null)";

        int rowNums[] = new int[0];
        while(rs.next()){
            if(filter.equals(filter.dynFromResultSet(rs, false)))
                rowNums = ArrayUtils.add(rowNums, rs.getRow());
        }
        return rowNums;
    }
    */


    //====================================GENERIC TAKERESULTSET
    /* tableName & colName may not be null
     * rowNum is the num of rows first found (from top). rowNum < 0 = all valid rows
     * ret empty RS if not one row matches / keys.length == 0
     * any row matches if keys = null / (Number[])null
     * ret RS containing all rows if keys = null / (Number[])null & rowNum = -1
     * the resulting rows are sorted by 'colName'
     */
    public static ResultSet takeResultSetByNumber(String tableName, String colName, int rowNum, Number... keys) throws SQLException{
       // assert tableName != null && colName != null : "tableName / colName = null";   

        String whereClause = "";
        if(keys != null){
            if(keys.length == 0){   //cheat on creating empty rs
                if(tableName.equals(Profil.tableName))
                    whereClause = " where " + Profil.noIndukColName + " = '~!@#$%^&*'";
                else
                    whereClause = " where id = '~!@#$%^&*'";
            }else{
                LinkedList<String> whereClauseParts = new LinkedList<>();
                for(Number key : keys){
                    if(key == null)
                        whereClauseParts.add(colName + " = ''");
                    else
                        whereClauseParts.add(colName + " = " + key);
                }

                if(!whereClauseParts.isEmpty())
                    whereClause = " WHERE " + StringUtils.join(whereClauseParts, " OR ");
            }
        }

        String limitClause = rowNum < 1? "" : " LIMIT " + rowNum;

        String sql = "SELECT * FROM " + tableName + whereClause + " ORDER BY " + colName + limitClause;
        System.out.println(sql);
        initStatement();
        return stmt.executeQuery(sql);
    }
    public static ResultSet takeResultSetByNumberColl(String tableName, String colName, int rowNum, Collection<? extends Number> keys) throws SQLException{
        return takeResultSetByNumber(tableName, colName, rowNum, keys == null? null : keys.toArray(new Number[0]));
    }

    /* tableName & colName may not be null
     * rowNum is the num of rows first found (from top). rowNum < 0 = all valid rows
     * ret empty RS if no row matches / keys.length = 0
     * any row matches if keys = null / (String[])null
     * ret RS containing all rows if keys = null / (String[])null & rowNum = -1
     * the resulting rows are sorted by 'colName'
     */        
    public static ResultSet takeResultSetByString(String tableName, String colName, int rowNum, String... keys) throws SQLException{
        assert tableName != null && colName != null : "tableName / colName = null";

        String whereClause = "";
        if(keys != null){
            if(keys.length == 0){   //cheat on creating empty rs

                if(tableName.equals(Profil.tableName))
                    whereClause = " where " + Profil.noIndukColName + " = '~!@#$%^&*'";
                else
                    whereClause = " where id = '~!@#$%^&*'";
            }else{
                LinkedList<String> whereClauseParts = new LinkedList<>();
                for(String key : keys){
                    if(key == null)
                        whereClauseParts.add(colName + " is null");
                    else
                        whereClauseParts.add(colName + " = '" + key + "'");
                }
                if(!whereClauseParts.isEmpty())
                    whereClause = " WHERE " + StringUtils.join(whereClauseParts, " OR ");
            }
        }

        String limitClause = rowNum < 1? "" : " LIMIT " + rowNum;

        String sql = "SELECT * FROM " + tableName + whereClause + " ORDER BY " + colName + limitClause;
        System.out.println(sql);
        initStatement();
        return stmt.executeQuery(sql);
    }
    public static ResultSet takeResultSetByStringColl(String tableName, String colName, int rowNum, Collection<String> keys) throws SQLException{
        return takeResultSetByString(tableName, colName, rowNum, keys == null? null : keys.toArray(new String[0]));
    }
    
     public static ResultSet takeResultSetByStringCollMpe(String tableName, String colName, int rowNum, String keys) throws SQLException{
        return takeResultSetByString(tableName, colName, rowNum, keys == null? null : keys);
    }

    /* tableName may not be null
     * null elts of filters / filters whose asWhereClause = "", aren't included
     * ret empty RS if not one row matches / filters.length == 0
     * any row matches if filters = null / (Filter[])null
     * ret RS containing all rows if filters = null / (Filter[])null & rowNum = -1
     * orderBy is array of col names, which specify the returned rows order. if null no specific order guaranteed
     * null elts of orderBy are ignored
     */
    public static ResultSet takeResultSetByFilter(String tableName, String[] orderBy, int rowNum, Filter... filters) throws SQLException{
        assert tableName != null : "tableName = null";

        String whereClause = "";            
        if(filters != null){
            if(filters.length == 0){   //cheat on creating empty rs
                if(tableName.equals(Profil.tableName))
                    whereClause = " where " + Profil.noIndukColName + " = '~!@#$%^&*'";
                else
                    whereClause = " where id = '~!@#$%^&*'";
            }else{
                LinkedList<String> whereClauseParts = new LinkedList<>();
                for(Filter filter : filters){
                    if(filter != null){
                        String wc = filter.asWhereClause();
                        if(wc != null && !wc.equals(""))
                            whereClauseParts.add(wc);
                    }
                }
                if(!whereClauseParts.isEmpty())
                    whereClause = " WHERE " + StringUtils.join(whereClauseParts, " OR ");
            }
        }

        String limitClause = rowNum < 1? "" : " LIMIT " + rowNum;

        String orderClause = StringUtils.join(orderBy, ", ");
        orderClause = orderClause == null? "" : " ORDER BY " + orderClause + limitClause;


        String sql = "SELECT * FROM " + tableName + whereClause + orderClause;
        
        System.out.println(sql);
        initStatement();
        return stmt.executeQuery(sql);
    }
    public static ResultSet takeResultSetByFilterColl(String tableName, String[] orderBy, int rowNum, Collection<? extends Filter<?>> filters) throws SQLException{
        return takeResultSetByFilter(tableName, orderBy, rowNum, filters == null? null : filters.toArray(new Filter[0]));
    }
    public static ResultSet takeResultSetByFilterExact(String tableName, String[] orderBy, int rowNum, Filter... filters) throws SQLException{
        assert tableName != null : "tableName = null";

        String whereClause = "";            
        if(filters != null){
            if(filters.length == 0){   //cheat on creating empty rs
                if(tableName.equals(Profil.tableName))
                    whereClause = " where " + Profil.noIndukColName + " = '~!@#$%^&*'";
                else
                    whereClause = " where id = '~!@#$%^&*'";
            }else{
                LinkedList<String> whereClauseParts = new LinkedList<>();
                for(Filter filter : filters){
                    if(filter != null){
                        String wc = filter.asWhereClauseExact();
                        if(wc != null && !wc.equals(""))
                            whereClauseParts.add(wc);
                    }
                }
                if(!whereClauseParts.isEmpty())
                    whereClause = " WHERE " + StringUtils.join(whereClauseParts, " OR ");
            }
        }

        String limitClause = rowNum < 1? "" : " LIMIT " + rowNum;

        String orderClause = StringUtils.join(orderBy, ", ");
        orderClause = orderClause == null? "" : " ORDER BY " + orderClause + limitClause;


        String sql = "SELECT * FROM " + tableName + whereClause + orderClause;
        System.out.println(sql);
        initStatement();
        return stmt.executeQuery(sql);
    }
    public static ResultSet takeResultSetByFilterCollExact(String tableName, String[] orderBy, int rowNum, Collection<? extends Filter<?>> filters) throws SQLException{
        return takeResultSetByFilterExact(tableName, orderBy, rowNum, filters == null? null : filters.toArray(new Filter[0]));
    }

    public static ResultSet takeResultSetAll(String tableName, String[] orderBy, int rowNum) throws SQLException{
        String orderClause = StringUtils.join(orderBy, ", ");
        orderClause = orderClause == null? "" : " ORDER BY " + orderClause;

        String sql = "SELECT * FROM " + tableName + orderClause;
        initStatement();
        return stmt.executeQuery(sql);
    }

    public static ResultSet takeResultSet(String tableName, String colName, int rowNum, Object key) throws SQLException{
        if(key == null)
            return takeResultSetByString(tableName, colName, rowNum, (String)null);
        else{
            Class klass = key.getClass();
            if(String.class.equals(klass))
                return takeResultSetByString(tableName, colName, rowNum, (String)key);
            else if(Number.class.isAssignableFrom(klass))
                return takeResultSetByNumber(tableName, colName, rowNum, (Number)key);
            else{
                assert false;
                return null;
            }
        }                
    }


    //====================================GENERIC INSERT, UPDATE, DELETE
    /* insert current states (including key) of kasirO into db
     * kasirO may not be null
     * throws KasirException(DB_INVALID, kasirO) if kasirO.isInsertDBValid = false
     * throws KasirException(DUPLICATE_PRIMARY_KEY, kasirO) if kasirO.key has already existed in db
     * never ret false
     */
    public static <T extends KasirObject> boolean insertKasirO(String tableName, String keyColName, T kasirO) throws SQLException, KasirException{
        assert kasirO != null;

        ResultSet rs = takeResultSet(tableName, keyColName, 1, kasirO.getKey());
        if(rowCountRS(rs) == 0){
            rs.moveToInsertRow();
            if(kasirO.insertResultSet(rs)){
                rs.insertRow();
                return true;
            }else
                return false;
        }else
            throw new KasirException(KasirException.Tipe.DUPLICATE_PRIMARY_KEY, kasirO);
    }

    /* insert current state (including key) of profils into db
     * kasirOs may not be null
     * null elts of kasirOs are removed. note this removal is done directly on the obj given as kasirOs
     * throws KasirException(DB_INVALID, Set<Integer>), Set elts are indexes such that kasirOs.get(index).isInsertDBValid = false
     * throws KasirException(DUPLICATE_PRIMARY_KEY_INPUT, Set<Set<Integer>>), a Set elt is a Set of indexes of kasirOs having the same key
     * throws KasirException(DUPLICATE_PRIMARY_KEY, Set<Integer>), Set elts are indexes such that kasirOs.get(index).key = already in db
     * ret num of kasirO successfully inserted
     * ret -1 if kasirOs.isEmpty = true before null elts removal
     * ret -2 if kasirOs.isEmpty = true after null elts removal
     */
    public static <T extends KasirObject> int insertKasirOs(String tableName, String keyColName, ArrayList<T> kasirOs, boolean keyModifiable) throws SQLException, KasirException{
        assert kasirOs != null;

        if(kasirOs.isEmpty())
            return -1;

        kasirOs.removeAll(Collections.singleton(null));

        if(kasirOs.isEmpty())
            return -2;


        //check entity.isInsertDBValid
        Set<Integer> invalidKasirOIdx = new HashSet<>();
        for(int i = 0; i < kasirOs.size(); ++i){
            if(!kasirOs.get(i).isInsertDBValid())
                invalidKasirOIdx.add(i);
        }

        if(!invalidKasirOIdx.isEmpty())
            throw new KasirException(KasirException.Tipe.DB_INVALID, invalidKasirOIdx);


        //check if keys of all key-modifiable kasirOs are unique
        if(keyModifiable){
            Set<Set<Integer>> duplicateKeySet = new HashSet<>();
            Set<Integer> checkedIdx = new HashSet<>();

            for(int i = 0; i < kasirOs.size(); ++i){
                Set<Integer> duplicateKeyIdx = new HashSet<>();

                if(!checkedIdx.contains(i)){
                    for(int j = i; j < kasirOs.size(); ++j){
                        if(i != j){
                            if(kasirOs.get(i).getKey().equals(kasirOs.get(j).getKey())){
                                duplicateKeyIdx.add(i);
                                duplicateKeyIdx.add(j);
                                checkedIdx.add(j);
                            }
                        }
                    }
                }
                checkedIdx.add(i);

                if(!duplicateKeyIdx.isEmpty())
                    duplicateKeySet.add(duplicateKeyIdx);
            }

            if(!duplicateKeySet.isEmpty())
                throw new KasirException(KasirException.Tipe.DUPLICATE_PRIMARY_KEY_INPUT, duplicateKeySet);
        }


        //take RS
        ResultSet rs = null;            
        Class keyType = ((T)kasirOs.toArray()[0]).getKey().getClass(); //safe, because here kasirOs can't be null / empty & the entity.isDBValid = true
        int[] rowNums = null;

        if(String.class.equals(keyType)){
            String[] keys = new String[kasirOs.size()];
            for(int i = 0; i < keys.length; ++i)
                keys[i] = (String) kasirOs.get(i).getKey();

            rs = takeResultSetByString(tableName, keyColName, -1, keys);

            rowNums = searchRow(rs, keyColName, false, keys);
        }else if(Number.class.isAssignableFrom(keyType)){
            Number[] keys = new Number[kasirOs.size()];
            for(int i = 0; i < keys.length; ++i)
                keys[i] = (Number) kasirOs.get(i).getKey();

            rs= takeResultSetByNumber(tableName, keyColName, -1, keys);

            rowNums = searchRow(rs, keyColName, keys);
        }else
            assert false;


        //check kasirOs key duplicate
        if(keyModifiable){
            Set<Integer> existedProfil = new HashSet<>();

            for(int i = 0; i < rowNums.length; ++i){
                if(rowNums[i] > 0)
                    existedProfil.add(i);
            }

            if(!existedProfil.isEmpty())
                throw new KasirException(KasirException.Tipe.DUPLICATE_PRIMARY_KEY, existedProfil);
        }


        //insert kasirOs
        int insertSuccess = 0;
        rs.moveToInsertRow();
        for(int i = 0; i < kasirOs.size(); ++i){
            if(kasirOs.get(i).insertResultSet(rs)){
                rs.insertRow();
                ++insertSuccess;
            }
        }
        return insertSuccess;
    }


    /* flush current states (except key) of kasirO into db
     * if kasirO.key is changed prior to calling this method then either not finding the record / updating the wrong record
     * kasirO may not be null
     * throws KasirException(DB_INVALID, kasirO)
     * throws KasirException(ROW_NOT_FOUND, kasirO)
     * never ret false
     */
    public static <T extends KasirObject> boolean updateKasirO(String tableName, String keyColName, T kasirO) throws SQLException, KasirException{
        assert kasirO != null;

        ResultSet rs = takeResultSet(tableName, keyColName, 1, kasirO.getKey());

        if(kasirO.updateResultSet(rs)){
            rs.updateRow();
            return true;
        }else
            return false;
    }

    /* flush current states (except noInduk) of kasirOs into db
     * for each profil, if profil.noInduk is changed prior to calling this method then either not finding the record / updating the wrong record
     * 'kasirOs' may not be null
     * null elts of 'kasirOs' are removed, note this removal done directly on the arraylist given as 'kasirOs'
     * throws KasirException(DB_INVALID, Set<Integer>), Set elts are indexes such that kasirOs.get(index).isDBValid() = false
     * throws KasirException(ROW_NOT_FOUND, Set<Integer>), Set elts are indexes such that kasirOs.get(index) = not in db
     * ret num of profil successfully flushed
     * ret -1 if kasirOs.isEmpty() = true before null elts removal
     * ret -2 if kasirOs.isEmpty() = true after null elts removal
     */
    public static <T extends KasirObject> int updateKasirOs(String tableName, String keyColName, ArrayList<T> kasirOs) throws SQLException, KasirException{
        assert kasirOs != null : "profils = null";

        if(kasirOs.isEmpty())
            return -1;

        kasirOs.removeAll(Collections.singleton(null));

        if(kasirOs.isEmpty())
            return -2;


        //check kasirO.isDBValid
        Set<Integer> invalidKasirOIdx = new HashSet<>();
        for(int i = 0; i < kasirOs.size(); ++i){
            if(!kasirOs.get(i).isDBValid())
                invalidKasirOIdx.add(i);
        }

        if(!invalidKasirOIdx.isEmpty())
            throw new KasirException(KasirException.Tipe.DB_INVALID, invalidKasirOIdx);


        //take RS
        ResultSet rs = null;            
        Class keyType = ((T)kasirOs.toArray()[0]).getKey().getClass(); //safe, because here kasirOs can't be null / empty & the entity.isDBValid = true
        int[] rowNums = null;

        if(String.class.equals(keyType)){
            String[] keys = new String[kasirOs.size()];
            for(int i = 0; i < keys.length; ++i)
                keys[i] = (String) kasirOs.get(i).getKey();

            rs = takeResultSetByString(tableName, keyColName, -1, keys);

            rowNums = searchRow(rs, keyColName, false, keys);
        }else if(Number.class.isAssignableFrom(keyType)){
            Number[] keys = new Number[kasirOs.size()];
            for(int i = 0; i < keys.length; ++i)
                keys[i] = (Number) kasirOs.get(i).getKey();

            rs= takeResultSetByNumber(tableName, keyColName, -1, keys);

            rowNums = searchRow(rs, keyColName, keys);
        }else
            assert false;


        //check kasirOs existence
        Set<Integer> notExistKasirO = new HashSet<>();
        for(int i = 0; i < rowNums.length; ++i){
            if(rowNums[i] < 1)
                notExistKasirO.add(i);
        }

        if(!notExistKasirO.isEmpty())
            throw new KasirException(KasirException.Tipe.DUPLICATE_PRIMARY_KEY, notExistKasirO);



        //flush kasirOs
        int updateSuccess = 0;
        for(int i = 0; i < kasirOs.size(); ++i){
            if(kasirOs.get(i).updateResultSet(rs)){
                rs.updateRow();
                ++updateSuccess;
            }
        }
        return updateSuccess;
    }

    public static <T extends KasirObject> boolean updateInsertKasirOKeyUnmodifiable(String tableName, String keyColName, T kasirO) throws SQLException, KasirException{
        assert kasirO != null;

        if(kasirO.isInsertDBValid()){
            try{
                updateKasirO(tableName, keyColName, kasirO);
            }catch(KasirException e){
                insertKasirO(tableName, keyColName, kasirO);
            }
        }else
            throw new KasirException(KasirException.Tipe.DB_INVALID, kasirO);
        return true;
    }
    public static <T extends KasirObject> int updateInsertKasirOsKeyUnmodifiable(String tableName, String keyColName, ArrayList<T> kasirOs) throws SQLException, KasirException{
        assert kasirOs != null : "profils = null";

        if(kasirOs.isEmpty())
            return -1;

        kasirOs.removeAll(Collections.singleton(null));

        if(kasirOs.isEmpty())
            return -2;


        //separate kasirOs to be updated & ones to be inserted based on the fact that isDBValid is a superset of isInsertDBValid
        ArrayList<T> insertedKasirOs = new ArrayList<>();
        ArrayList<T> updatedKasirOs = new ArrayList<>();
        for(int i = 0; i < kasirOs.size(); ++i){
            if(kasirOs.get(i).isDBValid())
                updatedKasirOs.add(kasirOs.get(i));
            else
                insertedKasirOs.add(kasirOs.get(i));  //note, this can contain kasirOs that is also inInsertDBValid = false (ie. not valid due to other reasons than not having valid key
        }


        //insert & update
        int insertSuccess = insertKasirOs(tableName, keyColName, insertedKasirOs, false);
        int updateSuccess = updateKasirOs(tableName, keyColName, updatedKasirOs);


        return insertSuccess + updateSuccess;
    }


    /* del the first row that matches data by colName. intended to del row by unique column
     * throws KasirException(ROW_NOT_FOUND, data)
     * never ret false
     */
    public static boolean deleteKasirO(String tableName, String colName, Number data) throws SQLException, KasirException{
        ResultSet rs = takeResultSetByNumber(tableName, colName, 1, data == null? (Number)null : data);
        if(rs.next()){
            rs.deleteRow();
            return true;
        }else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, data);
    }
    public static boolean deleteKasirO(String tableName, String colName, boolean caseSensitive, String data) throws SQLException, KasirException{
        ResultSet rs = takeResultSetByString(tableName, colName, -1, data == null? (String)null : data);

        int rowNum = searchRow(rs, colName, caseSensitive, data);
        if(rowNum > 0){
            rs.deleteRow();
            return true;
        }else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, data);
    }

    /* del all rows that matches any elt of data on colName
     * dell all rows if data = null. del all rows having null/empty on colName if data contains null/""
     */
    public static int deleteKasirOsByString(String tableName, String colName, Set<String> data) throws SQLException{
        ResultSet rs = takeResultSetByStringColl(tableName, colName, -1, data);

        int delSuccess = 0;
        while(rs.next()){
            rs.deleteRow();
            ++delSuccess;
        }
        return delSuccess;
    }
    public static int deleteKasirOsByNumber(String tableName, String colName, Set<? extends Number> data) throws SQLException{
        ResultSet rs = takeResultSetByNumberColl(tableName, colName, -1, data);

        int delSuccess = 0;
        while(rs.next()){
            rs.deleteRow();
            ++delSuccess;
        }
        return delSuccess;
    }


    //========================================PROFIL SELECT, UPDATE, INSERT, DELETE 
    /* noInduksOldProfil may not be null
     * key part of noInduksOldProfil is the old noInduk which must be given regardless of whether the corresponding profil.noInduk is changed / not
     * if the key part (old noInduk) is null, that Map.Entry is ignored
     * ret num of successful update
     * throws KasirException(ROW_NOT_FOUND, noIndukOld) if a record with oldUsername isn't found
     * throws KasirException(DUPLICATE_PRIMARY_KEY, profil) if profil.key has already existed in db
     */
    public static int updateProfils(String clerk, String pass, HashMap<String,Profil> noInduksOldProfil) throws SQLException, KasirException{
        assert noInduksOldProfil != null : "updateProfil(String, String, Map<String,Profil> noIndukProfil = null)";

        Set<String> noInduksOldSet = noInduksOldProfil.keySet();
        noInduksOldSet.removeAll(Collections.singleton(null));
        String noInduksOld[] = noInduksOldSet.toArray(new String[0]);

        Profil profils[] = new Profil[noInduksOld.length];
        for(int i = 0; i < noInduksOld.length; i++)
            profils[i] = noInduksOldProfil.get(noInduksOld[i]);

        String noInduks[] = new String[profils.length];
        for(int i = 0; i < profils.length; i++)
            noInduks[i] = profils[i].noInduk;


        ResultSet rs = takeResultSetByString(Profil.tableName, Profil.noIndukColName, -1, ArrayUtils.addAll(noInduksOld, noInduks));

        int rowNumsOld[] = searchRow(rs, Profil.noIndukColName, false, noInduksOld);
        for(int i = 0; i < rowNumsOld.length; i++){
            if(rowNumsOld[i] == 0)
                throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, noInduksOld[i]);
        }

        int rowNums[] = searchRow(rs, Profil.noIndukColName, false, noInduks);
        for(int i = 0; i < rowNums.length; i++){
            if(rowNums[i] != 0 && !noInduksOld[i].equals(noInduks[i]))
                throw new KasirException(KasirException.Tipe.DUPLICATE_PRIMARY_KEY, profils[i]);
        }

        int updateSuccess = 0;
        for(String noIndukOld : noInduksOld){
            if(noInduksOldProfil.get(noIndukOld).updateResultSetNoInduk(rs, noIndukOld)){
                updateSuccess++;
                rs.updateRow();
            }                
        }
        return updateSuccess;
    }


    //=================================CLERK SELECT, INSERT, UPDATE, DELETE     
    /* oldUsernamesClerk may not be null
     * key part of oldUsernamesClerk is the old username which must be given regardless of whether the corresponding clerk.username is changed / not
     * if the key part (old username) is null, that Map.Entry is ignored
     * ret num of successful update
     * throws KasirException(ROW_NOT_FOUND, oldUsername) if a record with oldUsername isn't found
     * throws KasirException(DUPLICATE_PRIMARY_KEY, profil) if profil.key has already existed in db
     */
    public static int updateClerks(String user, String pass, LinkedHashMap<String,Clerk> oldUsernamesClerk) throws SQLException, KasirException{
        assert oldUsernamesClerk != null : "updateProfil(String, String, Map<String,Profil> noIndukProfil = null)";

        Set<String> oldUsernamesSet = oldUsernamesClerk.keySet();
        oldUsernamesSet.removeAll(Collections.singleton(null));
        String oldUsernames[] = oldUsernamesSet.toArray(new String[0]);

        Clerk clerks[] = new Clerk[oldUsernames.length];
        for(int i = 0; i < oldUsernames.length; i++)
            clerks[i] = oldUsernamesClerk.get(oldUsernames[i]);

        String usernames[] = new String[clerks.length];
        for(int i = 0; i < clerks.length; i++)
            usernames[i] = clerks[i].username;


        ResultSet rs = takeResultSetByString(Clerk.tableName, Clerk.userColName, -1, ArrayUtils.addAll(oldUsernames, usernames));

        int rowNumsOld[] = searchRow(rs, Clerk.userColName, false, oldUsernames);
        for(int i = 0; i < rowNumsOld.length; i++){
            if(rowNumsOld[i] == 0)
                throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, oldUsernames[i]);
        }

        int rowNums[] = searchRow(rs, Clerk.userColName, false, usernames);
        for(int i = 0; i < rowNums.length; i++){
            if(rowNums[i] != 0 && !oldUsernames[i].equals(usernames[i]))
                throw new KasirException(KasirException.Tipe.DUPLICATE_PRIMARY_KEY, clerks[i]);
        }

        int updateSuccess = 0;
        for(String oldUsername : oldUsernames){
            if(oldUsernamesClerk.get(oldUsername).updateResultSetUsername(rs, oldUsername)){
                updateSuccess++;
                rs.updateRow();
            }                
        }
        return updateSuccess;
    }
}
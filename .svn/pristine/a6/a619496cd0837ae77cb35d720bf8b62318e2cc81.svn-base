/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.util.*;
import java.sql.*;
import pelajar.*;
import kasir.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import sak.KasirException;
import sak.KasirObject;

/**
 *
 * @author kedra
 */
public abstract class Iuran<IuranType extends Iuran> extends KasirObject<IuranType,IuranType,Long>{
    public static final String idColName = "ID", noIndukColName = "NomorInduk", chargedLevelColName = "ChargedLevel";
    
    /* once
    //ipsp = i pengembangan sarana pendidikan
    //ips = i pendaftaran siswa
    //iua = i ujian akhir
    //ipsb = i pembinaan siswa baru (MOS)
    */
    /* periodic
    //ipp = spp
    //ius = i ujian semester
    //iuap = i ujian akhir periodic
    //osis = i pembinaan siswa
    //iks = i keterampilan siswa (komputer)
    //pvt = i praktikum sinema
    */
    /* regular
    //seragam = i kelengkapan siswa attribute
    //attribute
    //pasb = i kelengkapan siswa non-attribute
    //buku = ya buat bayar buku dudul
    //sumbangan
    //ill = i lain2
    //tabungan
    //idd = i dibayar dimuka
    //beasiswa = i beasiswa ada duitnya
    //beasiswacost = i beasiswa gak ada duitnya
    */
    public enum Tipe{IPSP, IPS, IUA, IPSB, IPP, IUS, IUAP, OSIS, IKS, PVT, Seragam, Attribute, PASB, Buku, Sumbangan, ILL, Tabungan, IDD, Beasiswa, BeasiswaCost, CicilanHutang}
    
    public long id; //primary-key
    public String noInduk; //not-null
    public Level chargedLevel; //not-null
    
    //create filter & for insertion
    protected Iuran(String noInduk, Level cLevel){
        this.noInduk = noInduk;
        chargedLevel = cLevel;
    }
    
    //create from db
    protected Iuran(){}
    
    protected Iuran(IuranType iuran){
        this(iuran.noInduk, iuran.chargedLevel);
        id = iuran.id;
    }
    
    
    public abstract Tipe getTipe();
    public abstract TransactionDetail.Tipe getTipeTDetail();
    
    public static String toStringHeader(){
        return "Tipe|" + idColName + "|" + noIndukColName + "|" + chargedLevelColName;
    }
    public String toString(){
        return getTipe() + "|" + id + "|" + noInduk + "|" + chargedLevel;
    }    
    public boolean equals(IuranType iuran){
        if(iuran != null)
            return getTipe().equals(iuran.getTipe()) && id == iuran.id && ObjectUtils.equals(noInduk, iuran.noInduk) && ObjectUtils.equals(chargedLevel, iuran.chargedLevel);
        return false;
    }
    
    //==============================================================
    /* true if all required fields (db-not-null fields), except auto-inc field, are valid */
    public boolean isInsertDBValid(){
        return noInduk != null && !noInduk.isEmpty();
    }
    /* true if all required fields (db-not-null fields) are valid */
    public boolean isDBValid(){
        return id > 0 && isInsertDBValid();
    }
    
    public Long getKey(){
        return id;
    }
    
    
    public abstract IuranType dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException;
    
    protected IuranType dynFromResultSet(ResultSet rs) throws SQLException{
        id = rs.getLong(idColName);
        noInduk = rs.getString(noIndukColName);
        chargedLevel = Level.fromResultSet(rs, chargedLevelColName);
        
        return (IuranType) this;
    }
    
    /* tipe may not be null
     * never ret null
     */
    public static <T extends Iuran> T fromResultSet(ResultSet rs, Tipe tipe) throws SQLException, KasirException{
        assert tipe != null : "fromResultSet(ResultSet, Tipe tipe = null)";
        
        switch(tipe){
            case IPSP:
                return (T) new IPSP().dynFromResultSet(rs, true);
            case IPS:
                return (T) new IPS().dynFromResultSet(rs, true);
            case IUA:
                return (T) new IUA().dynFromResultSet(rs, true);
            case IPSB:
                return (T) new IPSB().dynFromResultSet(rs, true);
            case IPP:
                return (T) new IPP().dynFromResultSet(rs, true);
            case IUS:
                return (T) new IUS().dynFromResultSet(rs, true);
            case IUAP:
                return (T) new IUAP().dynFromResultSet(rs, true);
            case OSIS:
                return (T) new OSIS().dynFromResultSet(rs, true);
            case IKS:
                return (T) new IKS().dynFromResultSet(rs, true);
            case PVT:
                return (T) new PVT().dynFromResultSet(rs, true);
            case Seragam:
                return (T) new Seragam().dynFromResultSet(rs, true);
            case Attribute:
                return (T) new Attribute().dynFromResultSet(rs, true);
            case PASB:
                return (T) new PASB().dynFromResultSet(rs, true);
            case Buku:
                return (T) new Buku().dynFromResultSet(rs, true);
            case Sumbangan:
                return (T) new Sumbangan().dynFromResultSet(rs, true);
            case ILL:
                return (T) new ILL().dynFromResultSet(rs, true);
            case Tabungan:
                return (T) new Tabungan().dynFromResultSet(rs, true);
            case IDD:
                return (T) new IDD().dynFromResultSet(rs, true);
            case Beasiswa:
                return (T) new Beasiswa().dynFromResultSet(rs, true);
            case BeasiswaCost:
                return (T) new BeasiswaCost().dynFromResultSet(rs, true);
            case CicilanHutang:
                return (T) new CicilanHutang().dynFromResultSet(rs, true);
            default:
                assert false : "the impossible";
                return null; //even more impossible
        }
    }
    
    /* ret the first matching row
     * val may be anything, including null
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, iuranID) if no matching row found
     */
    public static <T extends Iuran> T fromResultSet(ResultSet rs, Tipe tipe, String colName, Number val) throws SQLException, KasirException{
        assert tipe != null && colName != null && !colName.isEmpty();
        
        if(DBSR.searchRow(rs, colName, val) > 0)
            return fromResultSet(rs, tipe);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, val);
    }
    
    /* ret the first matching row
     * val may be anything, including null
     * never ret null
     * throws KasirException(ROW_NOT_FOUND, iuranID) if no matching row found
     */
    public static <T extends Iuran> T fromResultSet(ResultSet rs, Tipe tipe, String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        assert tipe != null && colName != null && !colName.isEmpty();
        
        if(DBSR.searchRow(rs, colName, caseSensitive, val) > 0)
            return fromResultSet(rs, tipe);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, val);
    }
    
    public boolean flushResultSet(ResultSet rs) throws SQLException{
        rs.updateString(noIndukColName, noInduk);
        if(chargedLevel != null)
            chargedLevel.flushResultSet(rs, chargedLevelColName);
        return true;
    }
    
    /* caller of this method must ensure that RS cursor is at the insert row & call rs.insertRow() when all done
     * throws KasirException(DB_INVALID, this) if isInsertDBValid = false
     * never ret false
     * 
     */
    public boolean insertResultSet(ResultSet rs) throws SQLException, KasirException{
        if(isInsertDBValid())
            return flushResultSet(rs);
        else
            throw new KasirException(KasirException.Tipe.DB_INVALID, this);
    }
    
    /* throws KasirException(DB_INVALID, this) if isDBValid = false
     * throws KasirException(ROW_NOT_FOUND, this) if no row matches
     * never ret false
     */
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
        
        if (noInduk != null && !noInduk.isEmpty())
            whereClause.add(noIndukColName + " LIKE '%" + noInduk + "%'");
        
        if (chargedLevel != null)
                whereClause.add(chargedLevelColName + " LIKE '%" + chargedLevel.asWhereClause() + "%'");
        
        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    public String asWhereClauseExact(){
        LinkedList<String> whereClause = new LinkedList<>();
        
        if (noInduk != null && !noInduk.isEmpty())
            whereClause.add(noIndukColName + " = '" + noInduk + "'");
        
        if (chargedLevel != null)
                whereClause.add(chargedLevelColName + " LIKE '%" + chargedLevel.asWhereClause() + "%'");
        
        return whereClause.isEmpty()? "" : StringUtils.join(whereClause, " AND ");
    }
    
    //=========================================
    //public abstract void retrieveTransactionDetail() throws SQLException, KasirException;
    //public abstract void storeTransactionDetail() throws SQLException, KasirException;
    
    
    //=========================================
    public static <T extends Iuran> T select(T.Tipe tipe, String colName, Number val) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByNumber(tipe.toString(), colName, 1, val);
        return T.fromResultSet(rs, tipe, colName, val);
    }
    public static <T extends Iuran> T select(T.Tipe tipe, String colName, boolean caseSensitive, String val) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByString(tipe.toString(), colName, 1, val);
        return T.fromResultSet(rs, tipe, colName, caseSensitive, val);
    }
    public static <T extends Iuran> T select(T.Tipe tipe, long id) throws SQLException, KasirException{
        assert id > 0 : "id < 1";

        return select(tipe, idColName, id);
    }
    
    public static <T extends Iuran> List<T> selectS(T.Tipe tipe, String colName, Number... vals) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByNumber(tipe.toString(), colName, -1, vals);
        List<T> iurans = new ArrayList<>();
        
        while(rs.next())
            iurans.add((T) T.fromResultSet(rs, tipe));
        
        if(iurans.isEmpty())
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, vals);
        
        return iurans;
    }
    public static <T extends Iuran> List<T> selectS(T.Tipe tipe, String colName, boolean caseSensitive, String... vals) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByString(tipe.toString(), colName, -1, vals);
        List<T> iurans = new ArrayList<>();
        
        for(String val : vals)
            iurans.add((T) T.fromResultSet(rs, tipe, colName, caseSensitive, val));
        
        if(iurans.isEmpty())
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, vals);
        
        return iurans;
    }
    public static <T extends Iuran> Map<Long,T> selectS(T.Tipe tipe, Set<Long> ids) throws SQLException, KasirException{
        ResultSet rs;
        Map<Long,T> idIuran;
        if(ids == null){
            rs = DBSR.takeResultSetByNumberColl(tipe.toString(), idColName, -1, null);

            idIuran = new HashMap<>(DBSR.rowCountRS(rs));
            rs.beforeFirst();
            while(rs.next()){
                T t = T.fromResultSet(rs, tipe);
                idIuran.put(t.id, t);
            }
        }else{
            Set<Long> validId = new HashSet<>();
            for(Long id : ids){
                if(id > 0)
                    validId.add(id);
            }
            rs = DBSR.takeResultSetByNumberColl(tipe.toString(), idColName, -1, validId);

            idIuran = new HashMap<>(validId.size());
            for(Long id : validId){
                try{
                    idIuran.put(id, (T) T.fromResultSet(rs, tipe, idColName, id));
                }catch(KasirException e){
                    idIuran.put(id, null);
                }
            }
        }

        return idIuran;
    }
    
    public static <T extends Iuran> T filterSelect(Tipe tipe, Filter<T> filter) throws SQLException, KasirException{
        ResultSet rs = DBSR.takeResultSetByFilter(tipe.toString(), null, 1, filter);
        if(rs.next())
            return fromResultSet(rs, tipe);
        else
            throw new KasirException(KasirException.Tipe.ROW_NOT_FOUND, filter);
    }
    public static <T extends Iuran> Map<Long,T> filterSelectS(T.Tipe tipe, Set<? extends Filter<T>> filters) throws SQLException, KasirException{
        ResultSet rs;
        if(filters == null)
            rs = DBSR.takeResultSetByFilterColl(tipe.toString(), null, -1, null);
        else{
            //remove null filters / filters with empty asWhereClause, so BAD_RECORD is only detected if filters = null
            //arg filters isn't modified
            Set<Filter<T>> validFilters = new HashSet<>();
            for(Filter<T> filter : filters){
                if(filter != null && !"".equals(filter.asWhereClause()))
                    validFilters.add((Filter<T>) filter);
            }
            rs = DBSR.takeResultSetByFilterColl(tipe.toString(), null, -1, validFilters);
        }                        

        Map<Long,T> idIuran = new HashMap<>(DBSR.rowCountRS(rs));
        while(rs.next()){
            T t = T.fromResultSet(rs, tipe);
            idIuran.put(t.id, t);
        }

        return idIuran;
    }
    public static <T extends Iuran> Map<Long,T> exactFilterSelectS(T.Tipe tipe, Set<? extends Filter<T>> filters) throws SQLException, KasirException{
        ResultSet rs;
        if(filters == null)
            rs = DBSR.takeResultSetByFilterCollExact(tipe.toString(), null, -1, null);
        else{
            //remove null filters / filters with empty asWhereClause, so BAD_RECORD is only detected if filters = null
            //arg filters isn't modified
            Set<Filter<T>> validFilters = new HashSet<>();
            for(Filter<T> filter : filters){
                if(filter != null && !"".equals(filter.asWhereClauseExact()))
                    validFilters.add((Filter<T>) filter);
            }
            rs = DBSR.takeResultSetByFilterCollExact(tipe.toString(), null, -1, validFilters);
        }

        Map<Long,T> idIuran = new HashMap<>(DBSR.rowCountRS(rs));
        while(rs.next()){
            T t = T.fromResultSet(rs, tipe);
            idIuran.put(t.id, t);
        }

        return idIuran;
    }
    
    public <T extends Iuran> boolean insert(T.Tipe tipe) throws SQLException, KasirException{
        return DBSR.insertKasirO(tipe.toString(), idColName, this);
    }        
    public static <T extends Iuran> int insertS(T.Tipe tipe, ArrayList<T> iurans) throws SQLException, KasirException{
        return DBSR.insertKasirOs(tipe.toString(), idColName, iurans, false);
    }
    
    public <T extends Iuran> boolean update(T.Tipe tipe) throws SQLException, KasirException{
        return DBSR.updateKasirO(tipe.toString(), idColName, this);
    }        
    public static <T extends Iuran> int updateS(T.Tipe tipe, ArrayList<T> iurans) throws SQLException, KasirException{
        return DBSR.updateKasirOs(tipe.toString(), idColName, iurans);
    }
    
    public static <T extends Iuran> boolean delete(T.Tipe tipe, long id) throws SQLException, KasirException{
        return DBSR.deleteKasirO(tipe.toString(), idColName, id);
    }
    public static <T extends Iuran> int deleteS(T.Tipe tipe, Set<Long> ids) throws SQLException{
        return DBSR.deleteKasirOsByNumber(tipe.toString(), idColName, ids);
    }
    public static <T extends Iuran> int deleteS(T.Tipe tipe, ArrayList<T> iurans) throws SQLException{
        Set<Long> ids = new HashSet<>(iurans.size());
        for(T iuran : iurans)
            ids.add(iuran.id);
        return deleteS(tipe, ids);
    }
}
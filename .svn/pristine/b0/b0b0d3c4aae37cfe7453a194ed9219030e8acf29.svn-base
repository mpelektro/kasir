/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sak;

import java.sql.ResultSet;
import java.sql.SQLException;
import kasir.Filter;

/**
 *
 * @author kedra
 */
public abstract class KasirObject<ObjectType, FilterType, KeyType> implements Filter<FilterType>{
    //onCallingObject == true -> a new Filter obj built from rs current row returned, otherwise the calling Filter obj is modified & returned
    public abstract ObjectType dynFromResultSet(ResultSet rs, boolean onCallingObj) throws SQLException, KasirException;
    public abstract boolean insertResultSet(ResultSet rs) throws SQLException, KasirException;
    public abstract boolean updateResultSet(ResultSet rs) throws SQLException, KasirException;
    public abstract KeyType getKey();
    public abstract boolean isInsertDBValid();
    public abstract boolean isDBValid();
    //public abstract T3[] createKeyArray(int size);
}

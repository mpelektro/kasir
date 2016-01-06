/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sak;

import java.sql.*;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
/**
 *
 * @author kedra
 */
public class Kalender extends GregorianCalendar{
    public enum Format{DATE, TIMESTAMP}
    
    /* use constructor instead of this to create filter
     * throws KasirException(KALENDER_INPUT_ERROR) for invalid input
     */
    public static Kalender create(int y, int m, int d, int h, int min, int s) throws KasirException{
        if(y < 1970 || y > 2038 || m < 1 || m > 12 || d < 1 || d > 31 || h < 0 || h > 23 || min < 0 || min > 59 || s < 0 || s > 59)
            throw new KasirException(KasirException.Tipe.KALENDER_INPUT_ERROR, null);
        return new Kalender(y, m, d, h, min, s);
    }
    public static Kalender create(int y, int m, int d) throws KasirException{
        return create(y, m, d, 0, 0, 0);
    }
    
    //give -1 as part that isn't needed
    public Kalender(int y, int m, int d, int h, int min, int s){        
	super(y,m-1,d,h,min,s);
    }
    public Kalender(int y, int m, int d){
        super(y,m-1,d,-1,-1,-1);
    }
    public Kalender(){}
    public Kalender(long millisec){
        setTimeInMillis(millisec);
    }
    public Kalender(java.util.Date datetime){
        assert datetime != null : "Kalender(null)";
        
        setTimeInMillis(datetime.getTime());
    }    
    public Kalender(Kalender kalender){
        assert kalender != null : "Kalender(null)";
        
        setTimeInMillis(kalender.getTimeInMillis());
    }
	
    public int getMonth(){
	return get(Calendar.MONTH)+1;
    }
    
    public String toString(){
        String str =  String.valueOf(get(Calendar.YEAR)) + "-" + String.valueOf(getMonth()) + "-" + String.valueOf(get(Calendar.DATE));
        str += " " + String.valueOf(get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(get(Calendar.MINUTE)) + ":" + String.valueOf(get(Calendar.SECOND));
        return str;
    }
    public boolean equals(Kalender kal){
        return kal == null? false : getTimeInMillis() == kal.getTimeInMillis();
    }
    /* note, comparing Kalender with Date/Timestamp must use Kalender.equals */
    public boolean equals(java.util.Date datetime){
        return datetime == null? false : getTimeInMillis() == datetime.getTime();
    }
    
    public java.sql.Date toDate(){
	return new java.sql.Date(getTimeInMillis());
    }
    public java.sql.Timestamp toTimestamp(){
        java.sql.Timestamp tstamp = new java.sql.Timestamp(getTimeInMillis());
        tstamp.setNanos(0);
	return tstamp;
    }
    
    
    //db io
    /* ret null for null cell */
    public Kalender dynFromResultSet(ResultSet rs, String colName, boolean onCallingObject) throws SQLException{
        assert colName != null : "dynFromResultSet(*, null, *)";
        
        Kalender kal = onCallingObject? this : new Kalender();
        
        java.sql.Timestamp tstamp = rs.getTimestamp(colName);
        if(tstamp == null)
            return null;
        
        kal.setTimeInMillis(tstamp.getTime());
        return kal;
    }
    
    /* ret null for null cell */
    public static Kalender fromResultSet(ResultSet rs, String colName) throws SQLException{
        assert colName != null : "fromResultSet(*, null)";
        
        return new Kalender().dynFromResultSet(rs, colName, true);
    }
    
    /* never ret false, only throws exception */
    public boolean flushResultSet(ResultSet rs, String colName) throws SQLException{
        assert colName != null : "updateResultSet(*, null)";
        
        rs.updateTimestamp(colName, toTimestamp());
        return true;
    }
    
    //format: Y*-MM-DD HH:MM:SS
    //give -1 for part to be excluded, eg. new Kalender(-1,-1,-1,11,-1,1) -> filter hour 11 sec 01
    //never ret null / empty
    public String asWhereClause(boolean dateOnly){
        LinkedList<String> dateWC = new LinkedList<>();
        int tmp = internalGet(Calendar.YEAR);
        dateWC.add(tmp < 0? "%" : String.valueOf(tmp));
        
        tmp = internalGet(Calendar.MONTH);
        dateWC.add(tmp < 0? "%" : ((tmp < 9? "%" : "") + String.valueOf(tmp+1)));
        
        tmp = internalGet(Calendar.DATE);
        dateWC.add(tmp < 0? "%" : ((tmp < 10? "%" : "") + String.valueOf(tmp)));
        
        if(dateOnly)
            return StringUtils.join(dateWC, "-");
        else{
            LinkedList<String> timeWC = new LinkedList<>();
            tmp = internalGet(Calendar.HOUR);
            timeWC.add(tmp < 0? "%" : ((tmp < 10? "0" : "") + String.valueOf(tmp)));

            tmp = internalGet(Calendar.MINUTE);
            timeWC.add(tmp < 0? "%" : ((tmp < 10? "0" : "") + String.valueOf(tmp)));

            tmp = internalGet(Calendar.SECOND);
            timeWC.add(tmp < 0? "%" : ((tmp < 10? "0" : "") + String.valueOf(tmp)));

            return StringUtils.join(dateWC, "-") + " " + StringUtils.join(timeWC, ":");
        }       
    }
}

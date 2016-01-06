/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sak;

/**
 *
 * @author kedra
 */
public class KasirException extends Exception{
    public enum Tipe {LOGIN_ERROR, DUPLICATE_PRIMARY_KEY, DUPLICATE_PRIMARY_KEY_INPUT, KALENDER_INPUT_ERROR, ROW_NOT_FOUND, DUPLICATE_USERNAME, DUPLICATE_USERNAME_INPUT, DB_INVALID, BAD_RECORD, BAD_PRIMARY_KEY}
    
    public Tipe tipe;
    public Object data;
    
    public KasirException(){}
    public KasirException(String msg){
        super(msg);
    }
    public KasirException(Throwable cause){
        super(cause);
    }
    public KasirException(String msg, Throwable cause){
        super(msg, cause);
    }
    
    public KasirException(Tipe tipe){
        this.tipe = tipe;
    }
    public KasirException(Tipe tipe, Object data){
        this.tipe = tipe;   this.data = data;
    }
    public KasirException(Tipe tipe, Object data, String msg){
        super(msg);
        this.tipe = tipe;   this.data = data;
    }
    
    public String toString(){
        return tipe + ": " + data;
    }
}
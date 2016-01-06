/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iuran;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author kedra
 */
public class Entry{
    public int period;
    public float amount;
    public float debt;
    public Set<Long> transactDetailIDs;
    
    //for filter & from db
    public Entry(int p, float a, float d, Set<Long> ids){
        period = p;
        amount = a;
        debt = d;
        transactDetailIDs = ids;
    }
    
    //for insertion
    public Entry(int p, float a){
        this(p, a, 0, new HashSet<Long>());
    }
}

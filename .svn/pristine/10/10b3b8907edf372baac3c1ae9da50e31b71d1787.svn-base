/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sak;

import net.sf.jasperreports.engine.util.ObjectUtils;

/**
 *
 * @author kedra
 */
public class Triple<L,M,R>{
    L left;
    M middle;
    R right;
    
    public Triple(L l, M m, R r){
        left = l;  middle = m;  right = r;
    }
    public Triple(Triple<L,M,R> t){
        left = t.left;  middle = t.middle;  right = t.right;
    }
    public boolean equals(Triple<L,M,R> triple){
        return triple == null? false : ObjectUtils.equals(left, triple.left) && ObjectUtils.equals(middle, triple.middle) && ObjectUtils.equals(right, triple.right);
    }
    
    public String toString(){
        return "(left) " + left + " -- " + "(middle) " + middle + " -- " + "(right) " + right;
    }
}

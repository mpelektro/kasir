/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sak;

/**
 *
 * @author kedra
 */
public class Pair<F,S>{
    public F left;
    public S right;
    
    public Pair(F f, S s){
        left = f;  right = s;
    }
    public Pair(Pair<F,S> p){
        left = p.left;  right = p.right;
    }
    public boolean equals(Pair<F,S> pair){
        return pair == null? false : left.equals(pair.left) && right.equals(pair.right);
    }
    
    public String toString(){
        return "(left) " + left + " -- " + "(right) " + right;
    }
}

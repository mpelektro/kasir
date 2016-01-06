/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sak;

import java.util.*;
import org.apache.commons.lang3.ArrayUtils;
/**
 *
 * @author kedra
 */
public class ETC {
    /* ret null only if array = null
     * ret the same array if array.length = 0
     * ret a new array for all other cases
     */
    public static <T> T[] removeElt(T array[], T... elements){
        if(array == null)
            return null;
        else if (array.length == 0)
            return array;
        else{
            HashSet<Integer> idx = new HashSet<>();
            int fromIndex = 0;
            if(elements == null){
                for(int i = 0; i<array.length; i++){
                    if(array[i] == null)
                        idx.add(i);
                }
            }else{
                HashSet<T> elts = new HashSet<>();
                for(T element : elements)
                    elts.add(element);
                
                for(T elt : elts){
                    if(elt == null){
                        for(int i = 0; i < array.length; i++){
                            if(array[i] == null)
                                idx.add(i);
                        }
                    }else{
                        for(int i = 0; i < array.length; i++){
                            if(elt.equals(array[i]))
                                idx.add(i);
                        }
                    }
                }
            }
            return ArrayUtils.removeAll(array, ArrayUtils.toPrimitive(idx.toArray(new Integer[0])));
        }
    }
    
    
}

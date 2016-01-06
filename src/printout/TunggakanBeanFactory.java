/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import pelajar.Profil;

/**
 *
 * @author Master
 */
public class TunggakanBeanFactory implements Serializable{
    private static Collection data = new ArrayList();
    
    public void addTunggakanBean(TunggakanBean tunggakanBean){
        data.add(tunggakanBean);
    }
    
    public static void clearTunggakanBean(){
       data.clear();
    }
    
    public static Collection getBeanCollection(){
        return data;  
    }
}

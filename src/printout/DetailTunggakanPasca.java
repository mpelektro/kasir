/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printout;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import kasir.DBSR;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import org.ini4j.Ini;
import org.openide.util.Exceptions;

/**
 *
 * @author Master
 */
public class DetailTunggakanPasca {
    

    public static Connection establishConnection(){
    Connection connection = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String oracleURL="";
            try{
                Ini ppdbIni = new Ini(new File("lib/ini/ppdb.ini"));
                if(ppdbIni.get("program", "name", String.class).equals("ppdb")){
                     oracleURL = DBSR.dbURLppdb;
                }else if(ppdbIni.get("program", "name", String.class).equals("du")){
                     oracleURL = DBSR.dbURLdu;
                }else{
                    oracleURL = DBSR.dbURL;
                }
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
                    oracleURL = null;
            }
            connection = DriverManager.getConnection(oracleURL,"marbun","kenapa123456");
            connection.setAutoCommit(false);
        }
        catch(SQLException exception)
        {
            exception.printStackTrace();
        }catch (ClassNotFoundException ex) {
            Exceptions.printStackTrace(ex);
        }
        return connection;

    }
}

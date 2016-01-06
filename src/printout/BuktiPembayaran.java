/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printout;
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
import org.openide.util.Exceptions;

/**
 *
 * @author Master
 */
public class BuktiPembayaran {
    

    public static Connection establishConnection(){
    Connection connection = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String oracleURL = DBSR.dbURL;
            connection = DriverManager.getConnection(oracleURL,"marbun","marbun123456");
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

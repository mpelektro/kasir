/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sak;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
/**
 *
 * @author Mpe
 */
public class SmsMaskingSender {
    private String phone;
    private String data;
    public SmsMaskingSender(String p, String m){
        
        try {  
            this.data = URLEncoder.encode("user", "UTF-8") + "=" +
                    URLEncoder.encode("mpelektro_api", "UTF-8");
            this.data += "&" + URLEncoder.encode("password", "UTF-8") + "=" +
                    URLEncoder.encode("aPAfG4z", "UTF-8");
            this.data += "&" + URLEncoder.encode("SMSText", "UTF-8") + "=" +
                    URLEncoder.encode(m, "UTF-8");
            this.data += "&" + URLEncoder.encode("GSM", "UTF-8") + "=" +
                    URLEncoder.encode(p, "UTF-8");

            this.phone = p;
            sendSms();
        } catch (Exception e) {
            System.out.println(e.toString());
        }  
		
	  
    }
    
    private Transformer sendSms() throws Exception{        
        if(this.phone.startsWith("62")){
            // Send data
                URL url = new URL("http://api.nusasms.com/api/v3/sendsms/plain");
                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(this.data);
                wr.flush();
                // Get the response
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(conn.getInputStream());
                TransformerFactory factory1 = TransformerFactory.newInstance();
                Transformer xform = factory1.newTransformer();
                xform.transform(new DOMSource(doc), new StreamResult(System.out));
                return xform;
        }else{
            Transformer xform = null;
            return xform;
        }                        
    }
}

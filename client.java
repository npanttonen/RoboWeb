package app;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.Enumeration;

//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.client.Invocation.Builder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;

import lejos.hardware.Button;
import lejos.hardware.Sound;
public class client implements Runnable{
	DataExchange DEobj;
	
	public client(DataExchange DE) {
		DEobj = DE;
	
	}


	@Override
	public void run() {
		while(true) {
			
		//Input
		URL url = null;
		HttpURLConnection conn = null;
		InputStreamReader isr = null;
		BufferedReader br=null;
		//Output
		URL url2 = null;
		HttpURLConnection conn2 = null;
			
		String s=null;
		try { // Input try catch
//			url = new URL("https://ev3test-380115.appspot.com/rest/ev3service/sayhello");
//			url = new URL("http://192.168.0.102:8080/rest/ev3service/sayhello");
//			url = new URL("http://192.168.1.64:8080/rest/laptopservive/servicename");
			url = new URL("http://192.168.0.103:8080/rest/services/speed");
//			url = new URL("http://192.168.0.101");
			conn = (HttpURLConnection)url.openConnection();
//			System.out.println(conn.toString()); //Tulostaa vain URLin
//			if (conn==null) {
//	  			System.out.println("No connection!!!");
//			}
			InputStream is=null;
			try {
				is=conn.getInputStream();
			}
			catch (Exception e) {
	  			System.out.println("Exception conn.getInputSteam()");
	  			e.printStackTrace();
	            System.out.println("Cannot get InputStream!");
			}
			isr = new InputStreamReader(is);
    		br=new BufferedReader(isr);
			while ((s=br.readLine())!=null){
				System.out.println(s);
				DEobj.setSpeed(Integer.parseInt(s));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
          System.out.println("Some problem!");
		}// Input try catch - END
		
		try {// Output try catch
			url = new URL("http://192.168.0.118:8080/rest/services/jotain/7");
			conn2 = (HttpURLConnection)url2.openConnection();
			if (conn2==null) {
	  			System.out.println("No connection!!!");
			}
			InputStream is=null;
			try {
				is=conn2.getInputStream();
			}
			catch (Exception e) {
	  			System.out.println("Exception conn.getInputSteam()");
	  			e.printStackTrace();
	            System.out.println("Cannot get InputStream!");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
          System.out.println("Some problem!");
		}// Output try catch - END
		}
	}

}
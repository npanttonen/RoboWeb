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
//      System.out.println("Read some text from URL\n");
//      System.out.println("Press any key to start");

      URL url = null;
		HttpURLConnection conn = null;
		InputStreamReader isr = null;
		BufferedReader br=null;

		String s=null;
		try {
//			url = new URL("https://ev3test-380115.appspot.com/rest/ev3service/sayhello");
//			url = new URL("http://192.168.0.102:8080/rest/ev3service/sayhello");
//			url = new URL("http://192.168.1.64:8080/rest/laptopservive/servicename");
//			url = new URL("http://192.168.0.103:8080/rest/services/speed");
			url = new URL("http://192.168.0.105:8080/rest/services/speed");
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
		}
		}
	}

}
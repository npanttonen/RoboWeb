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
		int IP = 103; // http://192.168.0.{IP} -- Computer IP address
		
			// #### INPUTS ###	
		//Speed
		URL url = null;
		HttpURLConnection conn = null;
		InputStreamReader isr = null;
		BufferedReader br=null;
		//DesInVal
		URL url2 = null;
		HttpURLConnection conn2 = null;
		InputStreamReader isr2 = null;
		BufferedReader br2=null;
		//Turn
		URL url3 = null;
		HttpURLConnection conn3 = null;
		InputStreamReader isr3 = null;
		BufferedReader br3=null;
		//Output
		URL url4 = null;
		HttpURLConnection conn4 = null;

		// #### INPUTS ###
		String s=null;
		try { // Speed
			url = new URL("http://192.168.0."+IP+":8080/rest/services/speed");
			conn = (HttpURLConnection)url.openConnection();

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
		}// Speed - END
		
		try { // DesInVal
			url2 = new URL("http://192.168.0."+IP+":8080/rest/services/DesInVal");
			conn2 = (HttpURLConnection)url2.openConnection();

			InputStream is=null;
			try {
				is=conn2.getInputStream();
			}
			catch (Exception e) {
	  			System.out.println("Exception conn.getInputSteam()");
	  			e.printStackTrace();
	            System.out.println("Cannot get InputStream!");
			}
			isr2 = new InputStreamReader(is);
    		br2=new BufferedReader(isr2);
			while ((s=br2.readLine())!=null){
				System.out.println(s);
				DEobj.setSpeed(Integer.parseInt(s));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
          System.out.println("Some problem!");
		}// DesInVal - END
		
		try { // Turn
			url3 = new URL("http://192.168.0."+IP+":8080/rest/services/Turn");
			conn3 = (HttpURLConnection)url3.openConnection();

			InputStream is=null;
			try {
				is=conn3.getInputStream();
			}
			catch (Exception e) {
	  			System.out.println("Exception conn.getInputSteam()");
	  			e.printStackTrace();
	            System.out.println("Cannot get InputStream!");
			}
			isr3 = new InputStreamReader(is);
    		br3=new BufferedReader(isr3);
			while ((s=br3.readLine())!=null){
				System.out.println(s);
				DEobj.setSpeed(Integer.parseInt(s));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
          System.out.println("Some problem!");
		}// Turn - END
		
		// ### OUTPUT ###
		try {
			url4 = new URL("http://192.168.0."+IP+":8080/rest/services/output/"+DEobj.getOD()+"/"+DEobj.getTime());
			conn4 = (HttpURLConnection)url4.openConnection();
			if (conn4==null) {
	  			System.out.println("No connection!!!");
			}
			InputStream is=null;
			try {
				is=conn4.getInputStream();
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
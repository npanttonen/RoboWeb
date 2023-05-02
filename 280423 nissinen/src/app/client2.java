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
public class client2 implements Runnable{
	DataExchange DEobj;
	
	public client2(DataExchange DE) {
		DEobj = DE;
	
	}

	//kutsutaan arvoja metodilla, parametrilla service-osoitteen loppuosa
	@Override
	public void run() {
	System.out.println("Client2");
	while(true) {
		//nopeus
	String nopeus = value("speed");
	DEobj.setSpeed(Integer.parseInt(nopeus));
	//desinval
	String desin = value("desinval");
	//muutetaan string int arvoksi, jos arvo alle 15 asetetaan 15
	int isv = Integer.parseInt(desin);
	if(isv < 15) {
		isv = 15;
	}
	DEobj.setIntensityValue(isv);
	System.out.println(isv);
	}
}


	private String value(String urlend) {
		String palautus = "0";
		URL url = null;
		HttpURLConnection conn = null;
		InputStreamReader isr = null;
		BufferedReader br=null;

		String s=null;
		try {
			String osoite = "http://192.168.0.101:8080/rest/services/" + urlend;
			url = new URL(osoite);
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
				palautus = s;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
          System.out.println("Some problem!");
          palautus = "0";
		}
		return palautus;
	}//metodin loppu vakanen
}
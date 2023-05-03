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
		//kutsutaan ja asetetaan nopeus
	String nopeus = value("speed");
	DEobj.setSpeed(Integer.parseInt(nopeus));
	//kutsutaan ja asetetaan desinval
	String desin = value("desinval");
	//muutetaan string int arvoksi, jos arvo alle 15 asetetaan 15
	int isv = Integer.parseInt(desin);
	if(isv < 15) {
		isv = 15;
	}
	DEobj.setIntensityValue(isv);
	System.out.println(isv);
	//hae turn arvo
	String turnwheel = value("turn");
	DEobj.setSpeed(Integer.parseInt(turnwheel));
	//kutsutaan lahetysmetodi. parametrina lahetettava arvo
	lahetys("object/", DEobj.getOD());
	
	} //silmukan vakanen
}


	private String value(String urlend) {
		String palautus = "0";
		URL url = null;
		HttpURLConnection conn = null;
		InputStreamReader isr = null;
		BufferedReader br=null;

		String s=null;
		try {
			String osoite = "http://192.168.0.118:8080/rest/services/" + urlend;
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
		//palautetaan arvo string
		return palautus;
	}//metodin loppu vakanen
	
	private void lahetys(String sendend,int sendvalue) {
		URL url2 = null;
		HttpURLConnection conn2 = null;
		//tehdaan osoitteen loppupaate parametreilla
		String urlend = sendend + sendvalue +"/"+ DEobj.getTime();
		try {
			url2 = new URL("http://192.168.0.118:8080/rest/services/" + urlend);
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
		}
	} //metodin loppu vakanen
}
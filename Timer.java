package app;

import lejos.utility.Delay;

public class Timer implements Runnable{
	DataExchange DEobj;
	public Timer(DataExchange DE) {
		DEobj = DE;
	}
	@Override
	public void run() {
		// Set the starting time to zero
        
        while (true) {

            // Wait for one second
            Delay.msDelay(1000);

            // Increment the time by one second
            DEobj.setTime(DEobj.getTime()+1);
        }
    }
}
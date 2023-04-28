package app;

import lejos.hardware.Button;

public class RunClass {
	private static DataExchange DE;
	private static LightSensor LS;
	private static ObjectDodge OD;
	private static Motors MO;
	private static client2 CL;
	
	
	public static void main(String[] args) {
		DE = new DataExchange();
		System.out.println("Press to go!");
		while(true) {
        	if(Button.getButtons()!=0) {
                break;
            }
        }
		CL = new client2(DE);
		LS = new LightSensor(DE);
		OD = new ObjectDodge(DE);
		MO = new Motors(DE);
		
		
		Thread clientThread = new Thread(CL);
		Thread LineThread = new Thread(LS);
		Thread ObjectThread = new Thread(OD);
		Thread MotorThread = new Thread(MO);
		
		clientThread.start();
		MotorThread.start();
		LineThread.start();
		//ObjectThread.start();
		
		
		while(true) {
        	if(Button.getButtons()!=0) {
        		System.exit(0);
                break;
            }
        }
	}

}

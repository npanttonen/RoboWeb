package app;

public class DataExchange {

    private int OD = 1;  // if 1, follow line
    private int intensityValue = 0;
    private int speed = 0;
    private int turn = 200;
    private int DesInVal = 26;
    private int time = 0;


    public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getDesInVal() {
		return DesInVal;
	}
	public void setDesInVal(int desInVal) {
		DesInVal = desInVal;
	}
	public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public DataExchange() {

    }
    public void setOD(int oD) {
        OD = oD;
    }
    public int getOD() {
        return OD;
    }

    public int getIntensityValue() {
        return intensityValue;
    }
    public void setIntensityValue(int intensityValue) {
        this.intensityValue = intensityValue;
    }
    public int getTurn() {
        if (turn<140) {
            turn=140;
        }
        if (turn>200) {
            turn=200;
        }
        return turn;
    }
    public void setTurn(int turn) {
        this.turn = turn;
    }



}
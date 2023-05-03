package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Speed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int speed;
    private float DesInVal;
    private int OD;
    private int turn;
    private int Time;
  
	public Speed() {
        super();
    }
	public Speed(int id, int speed, float desInVal, int turn, int OD, int time) {
		super();
		this.id = id;
		this.speed = speed;
		this.DesInVal = desInVal;
		this.turn = turn;
		this.OD = OD;
		this.Time = time;
	}

    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public float getDesInVal() {
        return DesInVal;
    }
    public void setDesInVal(float DesInVal) {
        this.DesInVal = DesInVal;
    }
    public int getId() {
        return id;
    }
    public int getOD() {
  		return OD;
  	}
  	public void setOD(int OD) {
  		this.OD = OD;
  	}
    public void setId(int id) {
        this.id = id;
    }

	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public int getTime() {
		return Time;
	}
	public void setTime(int time) {
		Time = time;
	}
	@Override
	public String toString() {
		return "Speed [id=" + id + ", speed=" + speed + ", DesInVal=" + DesInVal + ", turn=" + turn + "]";
	}


	
	
}

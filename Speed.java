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

  
	public Speed() {
        super();
    }
	public Speed(int id, int speed, float desInVal, int turn, int OD) {
		super();
		this.id = id;
		this.speed = speed;
		this.DesInVal = desInVal;
		this.turn = turn;
		this.OD = OD;
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
    public int getOD1() {
  		return OD;
  	}
  	public void setOp1(int OD) {
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


	@Override
	public String toString() {
		return "Speed [id=" + id + ", speed=" + speed + ", DesInVal=" + DesInVal + ", turn=" + turn + "]";
	}


	
	
}

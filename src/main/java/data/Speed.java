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
	
	public Speed() {
		super();
	}
	public Speed(float DesInVal) {
		super();
		this.DesInVal = DesInVal;

	}
	public Speed(int speed, float DesInVal) {
		super();
		this.speed = speed;
		this.DesInVal = DesInVal;
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
	

	public String toString() {
		return speed+": "+DesInVal;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Speed(int id, int speed, float desInVal) {
		super();
		this.id = id;
		this.speed = speed;
		this.DesInVal = desInVal;
	}
	
}
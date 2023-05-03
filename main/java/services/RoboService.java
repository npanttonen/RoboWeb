package services;

import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.persistence.Entity;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Speed;



@Path("/services")
public class RoboService {

	//hae nopeus mysql-palvelimelta

	@GET
	@Path("/speed")
	@Produces(MediaType.APPLICATION_JSON)
	public int Speed() {


		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ev3");

		EntityManager em = emf.createEntityManager();

		// find the Speed object with id=1
		Speed speed = em.find(Speed.class, 1);

		int speedint = speed.getSpeed();
		// close the EntityManager and EntityManagerFactory
		em.close();
		emf.close();
					return speedint;
		}

	//hae desinval
	@GET
	@Path("/desinval")
	@Produces(MediaType.APPLICATION_JSON)
	public float DesInVal() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ev3");
		EntityManager em = emf.createEntityManager();

		// find the desinval object with id=1
		data.Speed d = em.find(Speed.class, 1);

		float desifloat = (float) d.getDesInVal();
		// close the EntityManager and EntityManagerFactory
		em.close();
		emf.close();
					return desifloat;
		}
	@GET
	@Path("/turn")
	@Produces(MediaType.APPLICATION_JSON)
	public int Turn() {


		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ev3");

		EntityManager em = emf.createEntityManager();

		// find the Speed object with id=1
		Speed speed = em.find(Speed.class, 1);

		int turn = speed.getTurn();
		// close the EntityManager and EntityManagerFactory
		em.close();
		emf.close();
					return turn;
		}

//Reading all the rows from table Speed.
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Speed> readAllSpeed() {
	//Create an EntityManagerFactory with the settings from persistence.xml file
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("ev3");
		//And then EntityManager, which can manage the entities.
		EntityManager em=emf.createEntityManager();
		
		//Read all the rows from table Speed. Here the Speed must start with capital, 
		//because class's name starts. This returns a List of Speed objects.
		List<Speed> list=em.createQuery("select a from Speed a").getResultList();
		return list;
	}

	// Getting the values from EV3 and putting them into database
	@GET
	@Path("/output/{par1}/{par2}")
	@Produces(MediaType.TEXT_PLAIN)
	public int postSpeedByParams1(@PathParam("par1") int p1, @PathParam("par2") int p2) {
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("ev3");
		    EntityManager em = emf.createEntityManager();
		    em.getTransaction().begin();

		    // get the existing Speed object with id 1, or create a new one if it doesn't exist
		    Speed ODObj = em.find(Speed.class, 1);
		    if (ODObj == null) {
		    	ODObj = new Speed();
		    	ODObj.setId(1);
		    }

		    // update the values of the Speed object with the new values
		    ODObj.setOD(p1);
		    ODObj.setTime(p2);

		    // persist the updated Speed object
		    em.persist(ODObj);
		    em.getTransaction().commit();
		    return p1;
	}

//lähetä nopeus mysql-palvelimelle	
//This method uses FormParams, but does the same as previous	
	@POST
	@Path("/addspeed")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postSpeedByParams(@FormParam("speed") int speed, @FormParam("DesInVal") float DesInVal, @FormParam("turn") int turn) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ev3");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    
	    Speed speedObj = em.find(Speed.class, 1);
	    if (speedObj == null) {
	    	speedObj = new Speed();
	    	speedObj.setId(1);
	    }

	    // update the values of the Speed object with the new values
	    
	    speedObj.setSpeed(speed);
	    speedObj.setDesInVal(DesInVal);
	    speedObj.setTurn(turn);

	    // persist the updated Speed object
	    em.persist(speedObj);
	    em.getTransaction().commit();
	}
	// getting time from the database
	@GET
	@Path("/time")
	@Produces(MediaType.TEXT_HTML)
	public String time() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ev3");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    
	    Speed speedObj = em.find(Speed.class, 1);

	    em.close();
	    emf.close();

	    // update the values of the Speed object with the new values
	    return ""+speedObj.getTime();
	     
	}
	// getting state from the database
	@GET
	@Path("/state")
	@Produces(MediaType.TEXT_HTML)
	public String State() {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ev3");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    
	    Speed speedObj = em.find(Speed.class, 1);

	    em.close();
	    emf.close();
	    
	    // gives a statement for the state the robot is in
	    if (speedObj.getOD()==0) {
	    	return "Program starting...";
	    }
	    if (speedObj.getOD()==1) {
	    	return "Following line";
	    }
	    if (speedObj.getOD()==2) {
	    	return "Evading obstacle";
	    }else { return "track has been completed";}
	     
	}
}

package rest;

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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Speed;

@Path("/hunterservice")
public class Hunterservice {
//Reading all the rows from table Speed.
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Speed> readAllSpeed() {
	//Create an EntityManagerFactory with the settings from persistence.xml file
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("mysql");
		//And then EntityManager, which can manage the entities.
		EntityManager em=emf.createEntityManager();
		
		//Read all the rows from table Speed. Here the Speed must start with capital, 
		//because class's name starts. This returns a List of Speed objects.
		List<Speed> list=em.createQuery("select a from Speed a").getResultList();
		return list;
	}
	
//This method uses FormParams, but does the same as previous	
	@POST
	@Path("/addspeed")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void postSpeedByParams(@FormParam("speed") int speed, @FormParam("DesInVal") float DesInVal, @FormParam("turn") int turn) {
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("EV3");
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    
	    // get the existing Speed object with id 1, or create a new one if it doesn't exist
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
	    //return speedObj;
	}
	@GET
	@Path("/speed")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public int Speed() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EV3");
		EntityManager em = emf.createEntityManager();

		// find the Speed object with id=1
		Speed speed = em.find(Speed.class, 1);

		int speedint = speed.getSpeed();
		// close the EntityManager and EntityManagerFactory
		em.close();
		emf.close();
					return speedint;
		}
}

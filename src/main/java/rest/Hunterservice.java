package rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
	public Speed postSpeedByParams(@FormParam("speed") int speed, @FormParam("DesInVal") float DesInVal) {
		Speed Speed=new Speed(speed, DesInVal);
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("hunterapp");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(Speed);
		em.getTransaction().commit();
		return Speed;
	}
}

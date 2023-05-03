package conn;

import java.sql.ResultSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Speed;



@Path("/services")
public class test1 {
	@GET
	@Path("/test1")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello1() {
		return "Hi, There";
	}
	
	@GET
	@Path("/speed")
	@Produces(MediaType.APPLICATION_JSON)
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
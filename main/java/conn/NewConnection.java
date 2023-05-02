package conn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Dog;

public class NewConnection {

	@POST
	@Path("/adddog")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Dog> addDog(@FormParam("breed") String breed, @FormParam("weight") String weight) {
		ArrayList<Dog> list=new ArrayList<>();
		weight=weight.replace(",", ".");//If user uses comma 
		Connection conn=null;
		try{
			conn=Connections.getConnection();
		}
		catch(Exception e) {
			Dog d=new Dog(0, "Adding dogs failed", 0); //For debugging if connection fails
			list.add(d);
			return list;
		}
		//Using normal Prepared statement to add the values into the database
		try {
			PreparedStatement pstmt=conn.prepareStatement("insert into dog(breed, weight) values(?,?)");
			pstmt.setString(1, breed);
			pstmt.setString(2, weight);
			pstmt.executeUpdate();
			
			//Using common statement while reading, because there are no variables in the sql statement
			java.sql.Statement stmt=conn.createStatement();
			ResultSet RS=stmt.executeQuery("select * from dog");
			while (RS.next()) {
				Dog dog=new Dog();
				dog.setId(RS.getInt("id"));
				dog.setBreed(RS.getString("breed"));
				dog.setWeight(RS.getFloat("weight"));
				list.add(dog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Before the function ends, the connection should be closed
		//This closing just returns the connection to the connection pool
		finally {
			try {
				if (conn!=null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}

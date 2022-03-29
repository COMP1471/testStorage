package testStorage.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import testStorage.Controller.DatabaseConnection;

public abstract class Person 
{
private String personfName;
private String personlName;

private String personEmail;
private String personPhoneNumber;

	public Person(String personfName, String personlName, String personEmail, String personPhoneNumber)
	{
		this.personfName = personfName;
		this.personlName = personlName;
	    this.personEmail = personEmail;
		this.personPhoneNumber = personPhoneNumber;
	}
	
	public String getPersonfName() {
		return personfName;
	}
	public void setPersonfName(String personfName) {
		this.personfName = personfName;
	}
	public String getPersonlName() {
		return personlName;
	}
	public void setPersonlName(String personlName) {
		this.personlName = personlName;
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	public String getPersonPhoneNumber() {
		return personPhoneNumber;
	}
	public void setPersonPhoneNumber(String personPhoneNumber) {
		this.personPhoneNumber = personPhoneNumber;
	}

	public int addInsertDetails() {
	     int Id = 0;
	     StringBuilder insertPerson = new StringBuilder("insert into person")
	        		.append(" (FName,LName,Email,PhoneNumber) ")
	        		.append(" values (?,?,?,?) ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
	   	 try(PreparedStatement preparedStatement = connection.prepareStatement(insertPerson.toString(),Statement.RETURN_GENERATED_KEYS)) {
 	    	preparedStatement.setString(1,getPersonfName());
 	    	preparedStatement.setString(2,getPersonlName());
 	    	preparedStatement.setString(3,getPersonEmail());
 	    	preparedStatement.setString(4,getPersonPhoneNumber());
 	    	int affectedRow = preparedStatement.executeUpdate();
 	    	if(affectedRow == 0) {
 	    		return Id;
 	    	}
 	    	ResultSet rs = preparedStatement.getGeneratedKeys();
 	    	rs.next();
 	    	Id = rs.getInt(1);
	   	 } catch (SQLException e) {
	   		 e.printStackTrace();
	   	 }
		return Id;
	}
	
}

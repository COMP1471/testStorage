package testStorage.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import testStorage.Controller.DatabaseConnection;

public class Manager extends Employee
{

	public Manager(String personfName, String personlName, String personEmail, String personPhoneNumber, int employeeID, int branchID) 
	{
		super(personfName, personlName, personEmail, personPhoneNumber, employeeID, branchID);
		// TODO Auto-generated constructor stub
		
	}
	
	public Boolean deliveryRequestFor(Crate crate,Date onDate) {
		Date currentTime = new Date(System.currentTimeMillis());
		Order order = new Order(crate.getCrateID(), this.getBranchID(), false, onDate,currentTime , OrderType.delivery); 
		return placeThe(order);
	}
	
}

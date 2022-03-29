package testStorage.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import testStorage.Controller.DatabaseConnection;

public class Employee extends Person 
{
private int employeeID;
private int branchID;
	 
	public Employee(String personfName, String personlName, String personEmail, String personPhoneNumber, int employeeID, int branchID) 
	{
		super(personfName, personlName, personEmail, personPhoneNumber);
		this.employeeID = employeeID;
		this.branchID = branchID;
		
	}
	
	public Employee(String personfName, String personlName, String personEmail, String personPhoneNumber, int branchID) 
	{
		super(personfName, personlName, personEmail, personPhoneNumber);
		this.branchID = branchID;
		
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranch(int branchID) {
		this.branchID = branchID;
	}
	
	public void sendCrates(ArrayList<Crate> crateSendList)
	{
		
	}
	
	public Branch getBranch() {
	    	Branch branch = null;
	  		StringBuilder sql = new StringBuilder("SELECT ")
	  				.append("ID,")
	  				.append("Postcode,")
	  				.append("Address,")
	  				.append("ClientID")
	  				.append(" from Branch ")
					.append(" where ID = ? ");
			Connection connection = DatabaseConnection.getInstance().sqlConnection();
		    try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){    
		       preparedStatement.setInt(1, this.getBranchID());
		       ResultSet rs = preparedStatement.executeQuery();
		       while(rs.next()) {
		    	  branch = new Branch(
		    			  rs.getInt("ID"),
		    			  rs.getInt("ClientID"),
		    			  rs.getString("Address"),
		    			  rs.getString("Postcode"));
		       }
	       } catch (SQLException e) {
		    	  e.printStackTrace();
		    }
		    return branch;
	}
	
	public Boolean collectRequestFor(Crate crate,Date onDate) {
		Date currentTime = new Date(System.currentTimeMillis());
		Order order = new Order(crate.getCrateID(), this.getBranchID(), false, onDate,currentTime , OrderType.collection); 
		return placeThe(order);
	}
	
	
	protected Boolean placeThe(Order order) {
		StringBuilder sql = new StringBuilder("INSERT INTO OrderPlaced ")
				.append(" (CreatedBy, Type,CreatedOn, expectedDate, isCompleted,ForCrate) ")
				.append("VALUES (?,?,?,?,?,?);");
		Connection connection = DatabaseConnection.getInstance().sqlConnection();
	    try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){  
	    	preparedStatement.setInt(1,order.getBranchID());
	    	preparedStatement.setString(2, order.getType().toString());
	    	preparedStatement.setDate(3, (Date) order.getCreatedDate());
	    	preparedStatement.setDate(4, (Date) order.getExpectedDate());
	    	preparedStatement.setBoolean(5, order.getIsCompleted());
	    	preparedStatement.setInt(6,order.getCrateID());
	    	return preparedStatement.executeUpdate() == 0 ? false : true;
	    } catch (SQLException e) {
	    	  e.printStackTrace();
	    }
		return false;
	}
	
	
	public ArrayList<Crate> acceptDelivery(Delivery delivery)
	{
		ArrayList<Crate> requestedCrateList = delivery.getRequestCrateList();
		// check if these are mine and return the validated list
		
		ArrayList<Crate> validateCrateList = new ArrayList<Crate>();
		
		if (requestedCrateList.size() < 1)
		{
			
			delivery.setCompleted(false);
			return null;
		}
		else
		{
			for (int i = 0; i < requestedCrateList.size(); i ++)
			{
				Crate requestedCrate = requestedCrateList.get(i);
				
				//if (requestedCrate.getClientID() == branch.getClientID())
				//{
				//validateCrateList.add(requestedCrate);
				//}
			}
			if (validateCrateList.size() > 0)
			{
				delivery.setCompleted(true);
				return validateCrateList;
			}
			else
			{
				delivery.setCompleted(false);
				return null;
			}
		}
	}
}

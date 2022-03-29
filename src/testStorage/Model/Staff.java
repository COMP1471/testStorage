package testStorage.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import testStorage.Controller.DatabaseConnection;

public class Staff extends Person  {
	
private int staffID; 
private int officeID;

	public Staff(String personfName, String personlName, String personEmail, String personPhoneNumber, 
			     int staffID,int officeID)
	{
		super(personfName, personlName, personEmail, personPhoneNumber);
		this.setStaffID(staffID);
		this.setOfficeID(officeID);
	}
	
	public Staff(String personfName, String personlName, String personEmail, String personPhoneNumber,int officeID)
{
	super(personfName, personlName, personEmail, personPhoneNumber);
	this.setOfficeID(officeID);
}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	@Override
	public String toString() {
		return this .getPersonfName()+ " " + this.getPersonlName();
	}

	public int getOfficeID() {
		return officeID;
	}

	public void setOfficeID(int officeID) {
		this.officeID = officeID;
	}
	
	public ArrayList<WareHouse> getWareHouseList() {
		 ArrayList<WareHouse> warehouseList = new ArrayList<WareHouse>(); 
		 StringBuilder sql = new StringBuilder("SELECT ")
	        		.append("ID,")
	        		.append("Address,")
					.append(" Postcode ")
					.append(" from Office ")
				    .append(" where IsWarehouse = 1 ;");
			 Connection connection = DatabaseConnection.getInstance().sqlConnection();
	    	 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
	    	    	ResultSet rs =  preparedStatement.executeQuery();
	    	    	while(rs.next()) {
	    	    	WareHouse  warehouse = new WareHouse(
	    	    			  rs.getInt("ID"),
	    	    			  rs.getString("Address"),
	    	    			  rs.getString("Postcode")
	    	    			  );
	    	    	 warehouseList.add(warehouse);
	    	    	}   
	    	  } catch (SQLException e) {
		    	  e.printStackTrace();
		    }
			return warehouseList;
	}
	
	public ArrayList<Client> getAllClient() {
		ArrayList<Client> clients = new ArrayList<Client>();
		StringBuilder sql = new StringBuilder("Select ")
				.append("ID,")
				.append("Name,")
				.append("AddedBy ")
				.append(" from Client ; ");
	   Connection connection = DatabaseConnection.getInstance().sqlConnection();
	   try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){ 
		   ResultSet rs = preparedStatement.executeQuery();
		   while(rs.next()) {
			   Client client = new Client(
					   rs.getInt("ID"),
					   rs.getString("Name")
					   );
			   clients.add(client);
		   }
	   } catch (SQLException e) {
	    	  e.printStackTrace();
	    }
       return clients;
	}
}

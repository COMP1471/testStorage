package testStorage.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import testStorage.Controller.DatabaseConnection;

public class Client {
	
private int clientID;

private String clientName;


	public Client( String clientName)
	{
		this.clientName = clientName;
	}


	public Client(int clientID, String clientName)
	{
		this.clientID = clientID;
		this.clientName = clientName;
	}
	
	
	public int getClientID() 
	{
		return clientID;
	}
	
	public void setClientID(int clientID) 
	{
		this.clientID = clientID;
	}
	
	public String getClientName() 
	{
		return clientName;
	}
	
	public void setClientName(String clientName)
	{
		this.clientName = clientName;
	}
	
	public ArrayList<Bill> getBilings() {
		ArrayList<Bill> bills = new ArrayList<Bill>(); 
		StringBuilder sql = new StringBuilder("SELECT ")
				.append("Billing.ID,")
  				.append("FromDate,")
  				.append("ToDate,")
  				.append("Paid,")
  				.append("ForClient,")
  				.append("Total,")
				.append("BillingBy")
				.append(" from Billing ")
				.append(" where ForClient = ? ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
		       preparedStatement.setInt(1, getClientID());
		       ResultSet rs = preparedStatement.executeQuery();
		       while(rs.next()) {
		    	   Bill bill = new Bill(
		    			   rs.getInt(1),
		    			   rs.getInt("ForClient"),
		    			   rs.getDate("ToDate"),
		    			   rs.getDate("FromDate"),
		    			   rs.getDouble("Total"),
		    			   rs.getBoolean("Paid"),
		    			   rs.getInt("BillingBy")
		    			   );
		    	   bills.add(bill);
		       } 
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
		return bills;
	}
	
	public ArrayList<Crate> getCrates() {
	    ArrayList<Crate> crates = null;
	    StringBuilder sql = new StringBuilder("SELECT")
	    		.append(" Crate.ID,")
	    		.append("Type,")
	    		.append("IsFull,")
	    		.append("CreatedOnDate,")
	    		.append("ShelfID,")
	    		.append("ClientID,")
	    		.append("OfficeID,")
	    		.append("Status,")
	    		.append("Size")
	    		.append(" FROM Crate")
	    		.append(" join Warehouse")
				.append(" ON Warehouse.CrateID=Crate.ID")
				.append(" where Crate.ClientID= ? ;");
	    Connection connection = DatabaseConnection.getInstance().sqlConnection();
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
		    preparedStatement.setInt(1, getClientID());
		    ResultSet rs = preparedStatement.executeQuery();
		    crates = new ArrayList<Crate>();
	        while (rs.next()) {
	        	System.out.print(rs);
	        	CrateStatus status = CrateStatus.valueOf(rs.getString("Status"));
	        	CrateSize size = CrateSize.valueOf(rs.getString("Size"));
	        	CrateContentType type = CrateContentType.valueOf(rs.getString("Type"));
	        	System.out.println("crate"+rs.getInt(1));
	        	Crate crate = new Crate(
	        			rs.getInt(1),
	        			rs.getInt("ShelfID"),
	        			rs.getInt("ClientID"),
	        			rs.getInt("OfficeID"),
	        			rs.getDate("CreatedOnDate"),
	        			status,
	        			size,
	        			type,
	        			rs.getBoolean("IsFull"));
	        	crates.add(crate);
			  } 	
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } 
		return crates;	
	}
	
	public ArrayList<Branch> getListOfBranch(){
		ArrayList<Branch> branchList = new ArrayList<Branch>();
  		StringBuilder sql = new StringBuilder("SELECT ")
  				.append("ID,")
  				.append("Postcode,")
  				.append("Address,")
  				.append("ClientID")
  				.append(" from Branch ")
				.append(" where ClientID = ? ");
		Connection connection = DatabaseConnection.getInstance().sqlConnection();
	    try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){    
	       preparedStatement.setInt(1, getClientID());
	       ResultSet rs = preparedStatement.executeQuery();
	       while(rs.next()) {
	    	 Branch branch = new Branch(
	    			  rs.getInt("ID"),
	    			  rs.getInt("ClientID"),
	    			  rs.getString("Address"),
	    			  rs.getString("Postcode"));
	       
	       branchList.add(branch);
	     }
       } catch (SQLException e) {
	    	  e.printStackTrace();
	    }
	    return branchList;
	}
 	
	public ArrayList<Order> getOrdersInProgress() {
		ArrayList<Branch> branchList = getListOfBranch();
		ArrayList<Order> orders = new ArrayList<Order>();
		for(Branch branch : branchList) {
			orders.addAll(branch.getOrdersInProgress());
		}
		return orders;
	}
 	
    public  ArrayList<Order> getOrders() {
		ArrayList<Branch> branchList = getListOfBranch();
		ArrayList<Order> orders = new ArrayList<Order>();
		for(Branch branch : branchList) {
			orders.addAll(branch.getOrders());
		}
		return orders;
	}

}

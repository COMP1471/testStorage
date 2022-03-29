package testStorage.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import testStorage.Controller.DatabaseConnection;

public class Branch extends Observer {

private int branchID;

private int clientID;

private String branchAddress;

private String branchPostcode;

public Branch( int clientID, String branchAddress, String branchPostcode) 
{
	this.setClientID(clientID);
	this.branchAddress = branchAddress;
	this.branchPostcode = branchPostcode;
}

	public Branch(int branchID, int clientID, String branchAddress, String branchPostcode) 
	{
		this.branchID = branchID;
		this.setClientID(clientID);
		this.branchAddress = branchAddress;
		this.branchPostcode = branchPostcode;
	}


	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public String getBranchPostcode() {
		return branchPostcode;
	}

	public void setBranchPostcode(String branchPostcode) {
		this.branchPostcode = branchPostcode;
	}
	    
    public Client getClient() {
 	Client client = null;
		StringBuilder sql = new StringBuilder("SELECT ")
				.append(" ID,")
				.append("Name")
				.append(" from Client ")
				.append(" where ID = ? ");
		Connection connection = DatabaseConnection.getInstance().sqlConnection();
	    try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
	    	preparedStatement.setInt(1,getClientID());
	    	ResultSet rs =  preparedStatement.executeQuery();
	    	while(rs.next()) {
	    	     client = new Client(
	    	    		  rs.getInt("ID")
	    	    		 ,rs.getString("Name"));
	    	}
	    } catch (SQLException e) {
	    	  e.printStackTrace();
	    }
  	  return client;
    }
    
    public ArrayList<Order> getOrders() {
    	ArrayList<Order> orders = new ArrayList<Order>();
        StringBuilder sql = new StringBuilder("SELECT ")
        		.append("ID,")
        		.append("Type,")
				.append(" 	CreatedBy ,")
				.append(" ManagedBy , ")
				.append(" CreatedOn , ")
				.append(" expectedDate, ")
				.append("isCompleted, ")
				.append(" ForCrate ")
				.append(" from OrderPlaced ")
				.append(" where CreatedBy = ? ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
    	 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
    			preparedStatement.setInt(1,getBranchID());
    	    	ResultSet rs =  preparedStatement.executeQuery();
    	    	while(rs.next()) {
    	    	  Order order = new Order(
    	    			   rs.getInt("ID"),
    	    			   rs.getInt("ForCrate"),
    	    			   this.getBranchID(),
    	    			   rs.getBoolean("isCompleted"),
    	    			   rs.getDate("expectedDate"),
    	    			   rs.getDate("CreatedOn"),
    	    			   OrderType.valueOf(rs.getString("Type")));
    	    	  if (rs.getInt("ManagedBy") != 0) {
    	    		  int staffID = rs.getInt("ManagedBy");
    	    		  order.setStaffID(staffID);
    	    	  }
    	    	  orders.add(order);
    	    	}
    	    	     
    	  } catch (SQLException e) {
	    	  e.printStackTrace();
	    }
		return orders;
    }
    
    public ArrayList<Order> getOrdersInProgress() {
    	ArrayList<Order> orders = new ArrayList<Order>();
        StringBuilder sql = new StringBuilder("SELECT ")
        		.append("ID,")
        		.append("Type,")
				.append(" 	CreatedBy ,")
				.append(" ManagedBy , ")
				.append(" CreatedOn , ")
				.append(" expectedDate, ")
				.append("isCompleted, ")
				.append(" ForCrate ")
				.append(" from OrderPlaced ")
				.append(" where CreatedBy = ? And isCompleted = 0 ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
    	 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
    			preparedStatement.setInt(1,getBranchID());
    	    	ResultSet rs =  preparedStatement.executeQuery();
    	    	while(rs.next()) {
    	    	  Order order = new Order(
    	    			   rs.getInt("ID"),
    	    			   rs.getInt("ForCrate"),
    	    			   this.getBranchID(),
    	    			   rs.getBoolean("isCompleted"),
    	    			   rs.getDate("expectedDate"),
    	    			   rs.getDate("CreatedOn"),
    	    			   OrderType.valueOf(rs.getString("Type")));
    	    	  if (rs.getInt("ManagedBy") != 0) {
    	    		  int staffID = rs.getInt("ManagedBy");
    	    		  order.setStaffID(staffID);
    	    	  }
    	    	  orders.add(order);
    	    	}    
    	  } catch (SQLException e) {
	    	  e.printStackTrace();
	    }
		return orders;
    }
    
    public ArrayList<Employee> getAllEmployee() {
    	ArrayList<Employee> employeeList = new ArrayList<Employee>();
    	StringBuilder sql = new StringBuilder("SELECT ")
    			   .append("Employee.ID,")
    				.append("FName,")
    				.append("LName,")
    				.append("UserName,")
    				.append("Email,")
    				.append("BranchID,")
    				.append("IsManager,")
    				.append("PhoneNumber")
    				.append(" from Employee join person ")
    				.append(" ON Employee.PersonID=person.ID")
    				.append(" where BranchID = ? ;");
    	  Connection connection = DatabaseConnection.getInstance().sqlConnection();
   		try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
   	        preparedStatement.setInt(1,this.getBranchID());
   	        ResultSet rs = preparedStatement.executeQuery();
   	        while (rs.next()) {
   	      	Employee employee = new Employee(
	        			rs.getString("FName"),
	        			rs.getString("LName"),
	        			rs.getString("Email"),
	        			rs.getString("PhoneNumber"), 
	        			rs.getInt(1), 
	        			rs.getInt("BranchID")
	        			);	
   	        	employeeList.add(employee);
   	          }
   	        } catch (SQLException e) {
   	        	e.printStackTrace();
   	        }
		return employeeList;
    	
    }
 
}

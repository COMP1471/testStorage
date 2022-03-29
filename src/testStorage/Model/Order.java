package testStorage.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import testStorage.Controller.DatabaseConnection;

public class Order {
 
   private int orderID;
	
   private int crateID;	
	
   private int BranchID;
    
   private Boolean isCompleted; 
    
   private Date expectedDate;
    
   private Date createdDate;
    
   private int staffID;
    
   private OrderType type;
    
   public Order(int crateID, int placedBy, Boolean isCompleted, Date expectedDate, Date createdDate,OrderType type) {
		super();
		this.crateID = crateID;
		this.BranchID = placedBy;
		this.isCompleted = isCompleted;
		this.expectedDate = expectedDate;
		this.createdDate = createdDate;
		this.type = type;
	}
   public Order(int orderID,int crateID, int placedBy, Boolean isCompleted, Date expectedDate, Date createdDate, OrderType type) {
		super();
		this.orderID = orderID;
		this.crateID = crateID;
		this.BranchID = placedBy;
		this.isCompleted = isCompleted;
		this.expectedDate = expectedDate;
		this.createdDate = createdDate;
		this.type = type;
	}
    public Order(int orderID,int crateID, int placedBy, Boolean isCompleted, Date expectedDate, Date createdDate,int staffID, OrderType type) {
		super();
		this.orderID = orderID;
		this.crateID = crateID;
		this.BranchID = placedBy;
		this.isCompleted = isCompleted;
		this.expectedDate = expectedDate;
		this.createdDate = createdDate;
		this.staffID = staffID;
		this.type = type;
	}
    
    public int getOrderID() {
    	return orderID;
    }
    
    public void setOrderID(int orderID) {
    	this.orderID = orderID;
    }
    
    public int getCrateID() {
		return crateID;
	}

	public void setCrate(int crateID) {
		this.crateID = crateID;
	}

	public int getBranchID() {
		return BranchID;
	}

	public void setBranchID(int BranchID) {
		this.BranchID = BranchID;
	}

	public Boolean getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public Date getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Date expectedDate) {
		this.expectedDate = expectedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getStaffID() {
	   return staffID;
	}
	
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}   
	
	public Staff getManagedby() {
			   Staff staff = null;
			   StringBuilder sql = new StringBuilder("SELECT ")
					    .append("Staff.ID,")
						.append("FName,")
						.append("LName,")
						.append("OfficeID,")
						.append("Email,")
						.append("PhoneNumber,")
						.append("Department ")
						.append(" from Staff join person ")
						.append(" ON Staff.PersonID=person.ID")
						.append(" where Staff.ID = ? ;");
			    Connection connection = DatabaseConnection.getInstance().sqlConnection();
				try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
			        preparedStatement.setInt(1, getStaffID());
			        ResultSet rs = preparedStatement.executeQuery();
			        while (rs.next()) {
			        	staff = new Staff(
			        			rs.getString("FName"),
			        			rs.getString("LName"),
			        			rs.getString("Email"),
			        			rs.getString("PhoneNumber"), 
			        			rs.getInt(1), 
			        			rs.getInt("OfficeID")
			        			);
					}
			    } catch (SQLException e) {
			        e.printStackTrace();
			    } 
				return staff;
	}
	
	
	public Crate getCrate() {
	    Crate crate = null;
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
				.append(" where Crate.ID= ? ;");
	    Connection connection = DatabaseConnection.getInstance().sqlConnection();
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
		    preparedStatement.setInt(1, getCrateID());
		    ResultSet rs = preparedStatement.executeQuery();
	        while (rs.next()) {
	        	System.out.print(rs);
	        	CrateStatus status = CrateStatus.valueOf(rs.getString("Status"));
	        	CrateSize size = CrateSize.valueOf(rs.getString("Size"));
	        	CrateContentType type = CrateContentType.valueOf(rs.getString("Type"));
	        	System.out.println("crate"+rs.getInt(1));
	        	 crate = new Crate(
	        			rs.getInt(1),
	        			rs.getInt("ShelfID"),
	        			rs.getInt("ClientID"),
	        			rs.getInt("OfficeID"),
	        			rs.getDate("CreatedOnDate"),
	        			status,
	        			size,
	        			type,
	        			rs.getBoolean("IsFull"));
			  } 	
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } 
		return crate;	
	}
	
	public Branch getPlacedBy() {
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
	
}

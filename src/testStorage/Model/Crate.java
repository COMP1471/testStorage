package testStorage.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import testStorage.Controller.DatabaseConnection;

public class Crate 
{
private int crateID;
private int shelfNumber;
private int clientID;  // id of a client to whom the crate belongs to 
private int wareHouseID;

public int getWareHouseID() {
	return wareHouseID;
}

public void setWareHouseID(int wareHouseID) {
	this.wareHouseID = wareHouseID;
}

private Date createdOnDate;
private ArrayList<Observer> observers = new ArrayList<Observer>();
private CrateStatus crateStatusEnum;
private CrateSize crateSizeEnum;
private CrateContentType crateContentTypeEnum;

private boolean isFull;

	

	public Crate(int crateID, int shelfNumber, int clientID, int wareHouseID,  Date createdOnDate, CrateStatus crateStatusEnum, CrateSize crateSizeEnum, CrateContentType crateContentTypeEnum, boolean isFull) 
	{
		this.crateID = crateID;
		this.shelfNumber = shelfNumber;
		this.clientID = clientID;
		this.wareHouseID = wareHouseID;
		
		this.createdOnDate = createdOnDate;
		
		this.crateStatusEnum = crateStatusEnum;
		this.crateSizeEnum = crateSizeEnum;
		this.crateContentTypeEnum = crateContentTypeEnum;
		this.isFull = isFull;
	}

	public Crate(int shelfNumber, int clientID, int wareHouseID,  Date createdOnDate, CrateStatus crateStatusEnum, CrateSize crateSizeEnum, CrateContentType crateContentTypeEnum, boolean isFull) 
	{
		this.shelfNumber = shelfNumber;
		this.clientID = clientID;
		this.wareHouseID = wareHouseID;
		
		this.createdOnDate = createdOnDate;
		
		this.crateStatusEnum = crateStatusEnum;
		this.crateSizeEnum = crateSizeEnum;
		this.crateContentTypeEnum = crateContentTypeEnum;
		this.isFull = isFull;
	}
	
	public void removeContentByID(int contentID) 
	{
		// TODO Auto-generated method stub
		
	}

	public int getCrateID() {
		return crateID;
	}

	public void setCrateID(int crateID) {
		this.crateID = crateID;
	}

	public int getShelfNumber() {
		return shelfNumber;
	}

	public void setShelfNumber(int shelfNumber) {
		this.shelfNumber = shelfNumber;
	}
	
	public Date getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(Date createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public CrateStatus getCrateStatusEnum() {
		return crateStatusEnum;
	}

	public void setCrateStatusEnum(CrateStatus crateStatusEnum) {
		this.crateStatusEnum = crateStatusEnum;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	public CrateSize getCrateSizeEnum() {
		return crateSizeEnum;
	}

	public void setCrateSizeEnum(CrateSize crateSizeEnum) {
		this.crateSizeEnum = crateSizeEnum;
	}

	public CrateContentType getCrateContentTypeEnum() {
		return crateContentTypeEnum;
	}

	public void setCrateContentTypeEnum(CrateContentType crateContentTypeEnum) {
		this.crateContentTypeEnum = crateContentTypeEnum;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	public void attach(Observer observer) {
		observers.add(observer);
	}  
	
	public void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
   
	public Boolean isOrderInProgress() {
    	Boolean inProgress = false;
    	StringBuilder sql = new StringBuilder("SELECT *")
    			.append(" From OrderPlaced ")
    			.append(" where ForCrate = ? And isCompleted = 0 ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
		    preparedStatement.setString(1,String.valueOf(getCrateID()));
	    return preparedStatement.executeQuery().next(); 
		   } catch (SQLException e) {
			 e.printStackTrace();
		   }
    	return inProgress;
    }	
	
	public Order getLastestOrder() {
		Order order = null;
        StringBuilder sql = new StringBuilder("SELECT ")
        		.append("Type,")
				.append(" CreatedBy,")
				.append(" ManagedBy, ")
				.append(" CreatedOn, ")
				.append(" expectedDate, ")
				.append("isCompleted, ")
				.append(" ForCrate ")
				.append(" from OrderPlaced ")
				.append(" where ForCrate = ?")
				.append(" ORDER BY CreatedOn DESC LIMIT 1; ");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
    	 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
    			preparedStatement.setInt(1,getCrateID());
    	    	ResultSet rs =  preparedStatement.executeQuery();
    	    	while(rs.next()) {
    	    	   order = new Order(
    	    			   rs.getInt("ForCrate"),
    	    			   rs.getInt("CreatedBy"),
    	    			   rs.getBoolean("isCompleted"),
    	    			   rs.getDate("expectedDate"),
    	    			   rs.getDate("CreatedOn"),
    	    			   OrderType.valueOf(rs.getString("Type")));
    	    	}    
    	  } catch (SQLException e) {
	    	  e.printStackTrace();
	    }
    	return order;
	}
	
	public ArrayList<Content> getContent() {
		ArrayList<Content> contents = new ArrayList<Content>();
		StringBuilder sql = new StringBuilder("SELECT")
				.append(" ID,")
		        .append(" Name ")
		        .append(" from Content ")
		        .append(" where CrateID = ? ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
			    preparedStatement.setInt(1, this.getCrateID());
			    ResultSet rs = preparedStatement.executeQuery();
			    while (rs.next()) {
			    	Content content = new Content(
			    			  rs.getInt("ID"),
			    			  rs.getString("Name")
			    			);
			        contents.add(content);
			    }
		 } catch (SQLException e) {
			   e.printStackTrace();
		 }
		return contents;
	}
	
	
	public Boolean addContent(Content content) {
		StringBuilder sql = new StringBuilder("INSERT INTO Content")
				.append(" (Name,CrateID) ")
				.append("VALUES (?,?) ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
		 preparedStatement.setString(1, content.getContentName());
		 preparedStatement.setInt(2, getCrateID());
		 return preparedStatement.executeUpdate() == 0 ? false : true;
		} catch (SQLException e) {
				e.printStackTrace();
		}  
	 	return false;
	}
    
	public String toString()
	{
		return "Crate ID: " + crateID + " on the shelf number " + shelfNumber + " and belongs to " + clientID + " created on " + createdOnDate;  // id of a client to whom the crate belongs to 

	}

	public boolean compareTo(Crate existingCrate) 
	{
		// if contents overlap return TRUE
		return false;
	}
}

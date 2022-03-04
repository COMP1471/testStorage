package testStorage.Model;

import java.sql.*;
import java.util.Date;

public class Crate 
{
private int crateID;
private Date createdOnDate;
private CrateStatus crateStatusEnum;
private boolean isFull;

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

	  public static void main(String [] args) {
			 Connection con = DatabaseConnection.getInstance().sqlConnection();
			 String sql = "select * from person";
			 try {
				PreparedStatement  prepareStatement = con.prepareStatement(sql);
				ResultSet result = prepareStatement.executeQuery();  
				while(result.next()){  
				System.out.println("Name "+result.getString(2) +" "+ result.getString(3));  
				}  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		  }
}

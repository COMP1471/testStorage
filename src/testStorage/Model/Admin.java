package testStorage.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import testStorage.Controller.DatabaseConnection;

public class Admin extends Staff {
	
	public Admin(String personfName, String personlName, String personEmail, String personPhoneNumber, int staffID,int officeID) 
	{
		super(personfName, personlName, personEmail, personPhoneNumber, staffID,officeID);
	}
	
		
	public Boolean addWarehouse(Office office) {
		StringBuilder sql = new StringBuilder("insert into Office")
	        		.append(" (Address,Postcode,IsWarehouse) ")
	        		.append(" values (?,?,?) ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
	   	 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) { 
	   		 preparedStatement.setString(1, office.getOfficeAddress());
	   		 preparedStatement.setString(2, office.getOfficePostCode());
	   		 preparedStatement.setBoolean(3, true);
	   		int affectedRow = preparedStatement.executeUpdate();
 	    	if(affectedRow != 0) {
 	    		return true;
 	    	} 
	   	 } catch (SQLException e) {
	   		 e.printStackTrace();
	   	 }
		return false;
	}
	
	public Boolean removeWarehouse(WareHouse warehouse) {
		StringBuilder sql = new StringBuilder("Delete from Office")
        		.append(" where ID = ? ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
	   	 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) { 
	   		 preparedStatement.setInt(1, warehouse.getOfficeID());
	   		int affectedRow = preparedStatement.executeUpdate();
		    	if(affectedRow != 0) {
		    		return true;
		    	} 
	   	 } catch (SQLException e) {
	   		 e.printStackTrace();
	   	 }
		return false;
	}
		
	public Boolean addNewStaff(Staff staff,User user,String department) {
		 StringBuilder insertStaff = new StringBuilder("insert into Staff ")
	        		.append(" (Department,OfficeID,PersonID,UserName,Password) ")
	        		.append(" values (?,?,?,?,?) ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
 	    	try (PreparedStatement preparedStatement2 = connection.prepareStatement(insertStaff.toString())) {
 	    		int personID = staff.addInsertDetails();
 	    		if (personID == 0) {
 	    		   return false;	
 	    		}
 	    		preparedStatement2.setString(1, department);
 	    		preparedStatement2.setInt(2, staff.getOfficeID());;
 	    		preparedStatement2.setInt(3, personID);
 	    		preparedStatement2.setString(4, user.getUserName());
 	    		preparedStatement2.setString(5, user.getPassWord());
 	    		if(preparedStatement2.executeUpdate() != 0) {
 	    			return true;
 	    		}
	   	   } catch (SQLException e) {
	    	  e.printStackTrace();
	    }
		return false;
	}
	
}

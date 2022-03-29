package testStorage.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import testStorage.Controller.DatabaseConnection;

public class User {
    private String userName;
    private String passWord;
    
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
    public Boolean isAValidStaff() {
			StringBuilder sql = new StringBuilder("SELECT * FROM Staff ")
					.append(" where UserName = ? ")
					.append(" and Password = ? ");
			Connection connection = DatabaseConnection.getInstance().sqlConnection();
		try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
		    preparedStatement.setString(1, getUserName().replaceAll(" ", ""));
		    preparedStatement.setString(2, getPassWord().replaceAll(" ", ""));
		    return preparedStatement.executeQuery().next();    
		} catch (SQLException e) {
		    e.printStackTrace();
		}       
 	   return false;
    }
    
    public Boolean isAValidEmployee() {
 			StringBuilder sql = new StringBuilder("SELECT * FROM Employee")
 					.append(" where Username = ? ")
 					.append(" and Password = ? ");
 			Connection connection = DatabaseConnection.getInstance().sqlConnection();
 		try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
 		    preparedStatement.setString(1, getUserName().replaceAll(" ", ""));
 		    preparedStatement.setString(2, getPassWord().replaceAll(" ", ""));
 		    return preparedStatement.executeQuery().next();    
 		} catch (SQLException e) {
 		    e.printStackTrace();
 		}       
 	   return false;
    }
    
    public Employee getEmplyoee() {
 	   Employee employee = null;
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
 				.append(" where UserName = ? ")
 				.append(" and  Password = ? ;");
 	    Connection connection = DatabaseConnection.getInstance().sqlConnection();
 		try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
 	        preparedStatement.setString(1,getUserName().replaceAll(" ", ""));
 	        preparedStatement.setString(2,getPassWord().replaceAll(" ", ""));
 	        ResultSet rs = preparedStatement.executeQuery();
 	        while (rs.next()) {
 	        	if (rs.getBoolean("IsManager")) {
 	            employee = new Manager(
 	            		rs.getString("FName"),
 	        			rs.getString("LName"),
 	        			rs.getString("Email"),
 	        			rs.getString("PhoneNumber"), 
 	        			rs.getInt(1), 
 	        			rs.getInt("BranchID")
 	            		);
 	        	} else {
 	        	employee = new Employee(
 	        			rs.getString("FName"),
 	        			rs.getString("LName"),
 	        			rs.getString("Email"),
 	        			rs.getString("PhoneNumber"), 
 	        			rs.getInt(1), 
 	        			rs.getInt("BranchID")
 	        			);
 			}
 	      }
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    } 
 		return employee;
    }
    
    public Staff getStaff() {
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
					.append(" where UserName = ? ")
					.append(" and  Password = ? ;");
		    Connection connection = DatabaseConnection.getInstance().sqlConnection();
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
		        preparedStatement.setString(1,getUserName().replaceAll(" ", ""));
		        preparedStatement.setString(2,getPassWord().replaceAll(" ", ""));
		        ResultSet rs = preparedStatement.executeQuery();
		        while (rs.next()) {
		         Departments department = Departments.valueOf(rs.getString("Department"));
		         switch (department) {
		         case Admin: 
		        	 staff = new Admin(
		        				rs.getString("FName"),
			        			rs.getString("LName"),
			        			rs.getString("Email"),
			        			rs.getString("PhoneNumber"), 
			        			rs.getInt(1), 
			        			rs.getInt("OfficeID")
		        			 ); 
		        	 
		        	 break;
		         case Sales: 
		           staff = new Sales(
		        			rs.getString("FName"),
		        			rs.getString("LName"),
		        			rs.getString("Email"),
		        			rs.getString("PhoneNumber"), 
		        			rs.getInt(1), 
		        			rs.getInt("OfficeID")
		        			);
		             break;
		         case Accounting: 
		         staff = new Accounting(
		        			rs.getString("FName"),
		        			rs.getString("LName"),
		        			rs.getString("Email"),
		        			rs.getString("PhoneNumber"), 
		        			rs.getInt(1), 
		        			rs.getInt("OfficeID")
		        			);
		             break;
		             
		         case Management: 
	        	    staff = new Management(
		        			rs.getString("FName"),
		        			rs.getString("LName"),
		        			rs.getString("Email"),
		        			rs.getString("PhoneNumber"), 
		        			rs.getInt(1), 
		        			rs.getInt("OfficeID")
		        			);
		             break;
				  }
		       }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } 
			return staff;
	   }
   
}

package testStorage.Model;

import java.sql.*;

public class Crate {
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

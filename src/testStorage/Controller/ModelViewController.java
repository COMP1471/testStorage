package testStorage.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelViewController 
{
	public static void main(String[] args) throws ClassNotFoundException
	{
		 Connection con = DatabaseConnection.getInstance().sqlConnection();
		 String sql = "select * from person";
		 
		 try 
		 {
			PreparedStatement  prepareStatement = con.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			while(result.next())
			{  
				System.out.println("Name "+result.getString(2) +" "+ result.getString(3));  
			}  
		} 
		catch (SQLException e) 
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}

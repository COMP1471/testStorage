package testStorage.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import testStorage.Controller.DatabaseConnection;

public class Content 
{
	private int contentID;
	private String contentName;

	public Content(String contentName)
	{
		this.contentName = contentName;
	}

	public Content(int contentID, String contentName)
	{
		this.contentID = contentID;
		this.contentName = contentName;
	}
	
	public int getContentID() 
	{
		return contentID;
	}
	
	public void setContentID(int contentID) 
	{
		this.contentID = contentID;
	}
	
	public String getContentName() 
	{
		return contentName;
	}
	
	public void setContentName(String contentName)
	{
		this.contentName = contentName;
	}

	public Boolean remove() {
		StringBuilder sql = new StringBuilder("DELETE FROM Content ")
				.append(" where ID= ? ;");
		Connection connection = DatabaseConnection.getInstance().sqlConnection();
	    try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
	    preparedStatement.setString(1, String.valueOf(getContentID()));
	    return preparedStatement.executeUpdate() == 0 ? false : true;    
		} catch (SQLException e) {
			e.printStackTrace();
		}       
	   	return false;
	}
}

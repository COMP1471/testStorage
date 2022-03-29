package testStorage.Controller;

import java.sql.*;

public class DatabaseConnection {
      private String userName = "root";
      private String passWord = "";
      private String hostName = "localhost";
      private String databaseName = "test";
      private String portNumber = "3306";
      private Connection sqlConnection;
      private static final int maxRetry = 3;
      private static DatabaseConnection shared = null;
      
      private DatabaseConnection() {
           createSqlConnection(0);
      }
      
      public static DatabaseConnection getInstance() {
    	  if (shared == null) 
    		  shared = new DatabaseConnection();	  
    	  return shared;
      }
      
      public Connection sqlConnection() {
    	  return sqlConnection;
      }
      
      protected void finalize()  {
    	  if (sqlConnection != null) {
    		  try {
				sqlConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logs("error in closing the connection"+ e.getMessage());
			}
    	  }
      }
      
      private void createSqlConnection(int currentSession) {
    	  try {
    		  Class.forName("com.mysql.jdbc.Driver");
    		  sqlConnection = DriverManager.getConnection("jdbc:mysql://"+hostName+":"+portNumber+"/"+databaseName+"",userName,passWord);
    		   logs("connection successful");
    	    } catch(ClassNotFoundException ex1) {
    	       logs("failed to find to driver class " + ex1.getMessage());
    	       System.exit(1);
    	    } catch(SQLException ex2) {
    		   logs("Connection failed " + ex2.getMessage());
    		   if (maxRetry > currentSession) {
    		   logs("retrying to connect");
    		   createSqlConnection(currentSession++);
    	    }
         }
      }
     
      private void logs(String args) {
    	  System.out.println(args);
      }
      
}

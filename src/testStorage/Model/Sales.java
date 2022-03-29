package testStorage.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import testStorage.Controller.DatabaseConnection;

public class Sales extends Staff
{

	public Sales(String personfName, String personlName, String personEmail, String personPhoneNumber, int staffID,int officeID) 
	{
		super(personfName, personlName, personEmail, personPhoneNumber, staffID, officeID);
	}
	
    @Override
	public ArrayList<Client> getAllClient() {
		ArrayList<Client> clients = new ArrayList<Client>();
		StringBuilder sql = new StringBuilder("Select ")
				.append("ID,")
				.append("Name,")
				.append("AddedBy ")
				.append(" from Client ")
				.append(" where AddedBy = ? ;");
	   Connection connection = DatabaseConnection.getInstance().sqlConnection();
	   try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){ 
		   preparedStatement.setInt(1, this.getStaffID());
		   ResultSet rs = preparedStatement.executeQuery();
		   while(rs.next()) {
			   Client client = new Client(
					   rs.getInt("ID"),
					   rs.getString("Name")
					   );
			   clients.add(client);
		   }
	   } catch (SQLException e) {
	    	  e.printStackTrace();
	    }
       return clients;
	}
	
	public Boolean addNewClient(Client client) {
		StringBuilder sql = new StringBuilder("insert into Client ")
				.append("(Name,AddedBy)")
				.append(" Values (?,?) ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){ 
			   preparedStatement.setString(1, client.getClientName());
			   preparedStatement.setInt(2, this.getStaffID());
			   int affectedRow = preparedStatement.executeUpdate();
			   if (affectedRow != 0) {
				   return true;
			   }
		   } catch (SQLException e) {
		    	  e.printStackTrace();
		 }
		return false;
	}
	
	public Boolean addNewBranch(Branch branch) {
		StringBuilder sql = new StringBuilder("insert into Branch ")
				.append("(Address,Postcode,ClientID)")
				.append(" Values (?,?,?) ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){ 
			   preparedStatement.setString(1, branch.getBranchAddress());
			   preparedStatement.setString(2, branch.getBranchPostcode());
			   preparedStatement.setInt(3, branch.getClientID());
			   int affectedRow = preparedStatement.executeUpdate();
			   if (affectedRow != 0) {
				   return true;
			   }
		   } catch (SQLException e) {
		    	  e.printStackTrace();
		 }
		return false;
	}
	
	public Boolean addNewEmployeeFor(User user,Employee employee) {
		StringBuilder sql = new StringBuilder("insert into Employee ")
				.append("(IsManager,PersonID,BranchID,Username,Password)")
				.append(" Values (?,?,?,?,?) ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){ 
			  int personID = employee.addInsertDetails();
			  if (personID == 0 ) {
				 return false;  
			  }
			   Boolean isManager = (employee instanceof Manager) ? true : false;
			   preparedStatement.setBoolean(1, isManager );
			   preparedStatement.setInt(2, personID);
			   preparedStatement.setInt(3, employee.getBranchID());
			   preparedStatement.setString(4, user.getUserName());
			   preparedStatement.setString(5, user.getPassWord());
			   int affectedRow = preparedStatement.executeUpdate();
			   if (affectedRow != 0) {
				   return true;
			   }
		   } catch (SQLException e) {
		    	  e.printStackTrace();
		 }
		return false;
	}
	
	public Boolean addNewCrate(Crate crate) {
		StringBuilder sql = new StringBuilder("insert into Crate ")
				.append("(Type,ClientID,IsFull,ShelfID,CreatedOnDate,Status,Size)")
				.append(" Values (?,?,?,?,?,?,?) ;");
		StringBuilder warehouseMapper = new StringBuilder("insert into Warehouse")
				.append(" (OfficeID,CrateID) ")
				.append("Values (?,?)");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString(),Statement.RETURN_GENERATED_KEYS)){
			 preparedStatement.setString(1, crate.getCrateContentTypeEnum().toString());
			 preparedStatement.setInt(2, crate.getClientID());
			 preparedStatement.setBoolean(3, crate.isFull());
			 preparedStatement.setInt(4, crate.getShelfNumber());
			 preparedStatement.setDate(5, (Date) crate.getCreatedOnDate());
			 preparedStatement.setString(6, crate.getCrateStatusEnum().toString());
			 preparedStatement.setString(7, crate.getCrateSizeEnum().toString());
				int affectedRow = preparedStatement.executeUpdate();
	 	    	if(affectedRow == 0) {
	 	    		return false;
	 	    	}
	 	    	ResultSet rs = preparedStatement.getGeneratedKeys();
	 	    	rs.next();
	 	    	try (PreparedStatement preparedStatement2 = connection.prepareStatement(warehouseMapper.toString())) {
	 	    		preparedStatement2.setInt(1, crate.getWareHouseID());
	 	    		preparedStatement2.setInt(2, rs.getInt("ID"));
	 	    		if(preparedStatement2.executeUpdate() != 0) {
	 	    			return true;
	 	    		}
	 	 	     } catch (SQLException e) {
	 		    	  e.printStackTrace();
	 		    }
		 } catch (SQLException e) {
	    	  e.printStackTrace();
	     }
		return false;
	}
	
   public Boolean removeClient(Client client) {
		StringBuilder sql = new StringBuilder(" Delete from Client ")
				.append(" WHERE ID = ? ");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		try(PreparedStatement preparedStatement  = connection.prepareStatement(sql.toString())) {
			preparedStatement.setInt(1, client.getClientID());
			int rowAffected = preparedStatement.executeUpdate();
			if (rowAffected != 0 ) {
				return true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
   }
   
   public Boolean removeBranch(Branch branch) {
 		StringBuilder sql = new StringBuilder(" Delete from Branch ")
 				.append(" WHERE ID = ? ");
 		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
 		try(PreparedStatement preparedStatement  = connection.prepareStatement(sql.toString())) {
 			preparedStatement.setInt(1, branch.getBranchID());
 			int rowAffected = preparedStatement.executeUpdate();
 			if (rowAffected != 0 ) {
 				return true;
 			} 
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		return false;
    }
   
   public Boolean removeEmployee(Employee employee) {
 		StringBuilder sql = new StringBuilder(" Delete from Employee ")
 				.append(" WHERE ID = ? ");
 		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
 		try(PreparedStatement preparedStatement  = connection.prepareStatement(sql.toString())) {
 			preparedStatement.setInt(1, employee.getEmployeeID());
 			int rowAffected = preparedStatement.executeUpdate();
 			if (rowAffected != 0 ) {
 				return true;
 			} 
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		return false;
    }
   
   public Boolean removeCrate(Crate crate) {
 		StringBuilder sql = new StringBuilder(" Delete from Crate ")
 				.append(" WHERE ID = ? ");
 		StringBuilder warehouseMapper = new StringBuilder(" Delete from WareHouse ")
 				.append(" WHERE CrateID = ? ");
 		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
 		try(PreparedStatement preparedStatement  = connection.prepareStatement(sql.toString())) {
 			preparedStatement.setInt(1, crate.getCrateID());
 			int rowAffected = preparedStatement.executeUpdate();
 			if (rowAffected == 0 ) {
 				return false;
 			} 
 			try(PreparedStatement preparedStatement2  = connection.prepareStatement(warehouseMapper.toString())) {
 	 			preparedStatement2.setInt(1, crate.getCrateID());
 	 			if (preparedStatement2.executeUpdate() != 0 ) {
 	 				return true;
 	 			} 
 	 		} catch (SQLException e) {
 	 			e.printStackTrace();
 	 		}
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
 		return false;
    }
}

package testStorage.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import testStorage.Controller.DatabaseConnection;

public class Management  extends Staff
{
	public Management(String personfName, String personlName, String personEmail, 
			String personPhoneNumber, int staffID,int officeID) {
		super(personfName, personlName, personEmail, personPhoneNumber, staffID, officeID);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Order> getPendingOrders() {
		ArrayList<Order> orders = new ArrayList<Order>();
		StringBuilder sql = new StringBuilder(" SELECT ID,")
				.append("CreatedBy,")
				.append("expectedDate,")
				.append("Type,")
				.append("ForCrate,")
				.append("CreatedBy,")
				.append("CreatedOn,")
				.append("isCompleted ")
				.append("FROM OrderPlaced ") 
				.append(" WHERE ManagedBy IS NULL AND ") 
				.append(" OrderPlaced.ForCrate =(SELECT Warehouse.CrateID ")
				.append(" FROM Warehouse WHERE Warehouse.OfficeID = ? );");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		try(PreparedStatement preparedStatement  = connection.prepareStatement(sql.toString())) {
			preparedStatement.setInt(1, this.getOfficeID());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Order order = new Order(
						rs.getInt("ID"),
						rs.getInt("ForCrate"),
						rs.getInt("CreatedBy"),
						rs.getBoolean("isCompleted"),
						rs.getDate("expectedDate"),
						rs.getDate("CreatedOn"),
						OrderType.valueOf(rs.getString("Type"))
						);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	
	public  Boolean setOrderStatusTo(CrateStatus status,Order order) {
		StringBuilder sql = new StringBuilder(" UPDATE  Crate ")
				.append(" SET Status = ? ")
				.append(" WHERE Id = ? ");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		try(PreparedStatement preparedStatement  = connection.prepareStatement(sql.toString())) {
			preparedStatement.setString(1, status.toString());
			preparedStatement.setInt(2, order.getCrateID());
			int rowAffected = preparedStatement.executeUpdate();
			if (rowAffected != 0 ) {
				return true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public  Boolean setOrderCompleted(Order order) {
		StringBuilder sql = new StringBuilder(" UPDATE OrderPlaced ")
				.append(" SET isCompleted = ? ")
				.append(" WHERE Id = ? ");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		try(PreparedStatement preparedStatement  = connection.prepareStatement(sql.toString())) {
			preparedStatement.setBoolean(1, true);
			preparedStatement.setInt(2, order.getOrderID());
			int rowAffected = preparedStatement.executeUpdate();
			if (rowAffected != 0 ) {
				return true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public  Boolean rejectOrder(Order order) {
		StringBuilder sql = new StringBuilder(" Delete from OrderPlaced ")
				.append(" WHERE Id = ? ");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		try(PreparedStatement preparedStatement  = connection.prepareStatement(sql.toString())) {
			preparedStatement.setInt(2, order.getOrderID());
			int rowAffected = preparedStatement.executeUpdate();
			if (rowAffected != 0 ) {
				return true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public  Boolean setManageOrderFor(Order order) {
		StringBuilder sql = new StringBuilder(" UPDATE OrderPlaced ")
				.append(" SET ManagedBy = ? ")
				.append(" WHERE Id = ? ");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		try(PreparedStatement preparedStatement  = connection.prepareStatement(sql.toString())) {
			preparedStatement.setInt(1, getStaffID());
			preparedStatement.setInt(2, order.getOrderID());
			int rowAffected = preparedStatement.executeUpdate();
			if (rowAffected != 0 ) {
				return true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Order> getOrdersManageBy() {
		ArrayList<Order> orders = new ArrayList<Order>();
		StringBuilder sql = new StringBuilder(" SELECT ID,")
				.append("CreatedBy,")
				.append("expectedDate,")
				.append("ManagedBy,")
				.append("Type,")
				.append("ForCrate,")
				.append("CreatedBy,")
				.append("CreatedOn,")
				.append("isCompleted ")
				.append("FROM OrderPlaced ") 
				.append(" WHERE ManagedBy = ? ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		try(PreparedStatement preparedStatement  = connection.prepareStatement(sql.toString())) {
			preparedStatement.setInt(1, this.getStaffID());
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Order order = new Order(
						rs.getInt("ID"),
						rs.getInt("ForCrate"),
						rs.getInt("CreatedBy"),
						rs.getBoolean("isCompleted"),
						rs.getDate("expectedDate"),
						rs.getDate("CreatedOn"),
						rs.getInt("ManagedBy"),
						OrderType.valueOf(rs.getString("Type"))
						);
				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
}
package testStorage.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import testStorage.Controller.DatabaseConnection;

public class Accounting extends Staff
{
	public Accounting(String personfName, String personlName, String personEmail, String personPhoneNumber, int staffID, int officeInt) 
	{
		super(personfName, personlName, personEmail, personPhoneNumber, staffID, officeInt);
	}

	public Boolean generateBill(Bill bill)
	{
		StringBuilder sql = new StringBuilder("insert into Billing ")
				.append("(FromDate,ToDate,Total,ForClient,Paid,BillingBy)")
				.append(" Values (?,?,?,?,?,?) ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){ 
			   preparedStatement.setDate(1, (Date) bill.getFromDate());
			   preparedStatement.setDate(2, (Date) bill.getToDate());
			   preparedStatement.setFloat(3, (float) bill.getAmountBilled());
			   preparedStatement.setInt(4, bill.getClientID());
			   preparedStatement.setBoolean(5, bill.isPaid());
			   preparedStatement.setInt(6, getStaffID());
			   int affectedRow = preparedStatement.executeUpdate();
			   if (affectedRow != 0) {
				   return true;
			   }
		   } catch (SQLException e) {
		    	  e.printStackTrace();
		 }
		return false;
	}
	
	public Boolean setBillStateToPaid(Bill bill)
	{
		StringBuilder sql = new StringBuilder("update Billing ")
				.append(" Set Paid = ?")
				.append(" where ID = ;");
		 Connection connection = DatabaseConnection.getInstance().sqlConnection();
		 try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){ 
			   preparedStatement.setBoolean(1, true);
			   preparedStatement.setInt(2, bill.getBillID());
			   int affectedRow = preparedStatement.executeUpdate();
			   if (affectedRow != 0) {
				   return true;
			   }
		   } catch (SQLException e) {
		    	  e.printStackTrace();
		 }
		return false;
	}
}

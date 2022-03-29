package testStorage.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import testStorage.Controller.DatabaseConnection;

public class Bill {
	
private int billID;

private int clientID;

private Date toDate;

private Date fromDate;

private double amountBilled;

private boolean paid;

private int staffID;

public Bill(int billID, int clientID, Date toDate, Date fromDate, double amountBilled, boolean paid,int staffID) {
	this.billID = billID;
	this.clientID = clientID;
	this.toDate = toDate;
	this.fromDate = fromDate;
	this.amountBilled = amountBilled;
	this.paid = paid;
	this.staffID = staffID;
}

public int getBillID() {
	return billID;
}

public int getClientID() {
	return clientID;
}

public Date getToDate() {
	return toDate;
}

public Date getFromDate() {
	return fromDate;
}

public double getAmountBilled() {
	return amountBilled;
}

public boolean isPaid() {
	return paid;
}

	public Staff getCreatedBy() {
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
					.append(" where Staff.ID = ? ;");
		    Connection connection = DatabaseConnection.getInstance().sqlConnection();
			try(PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())){
		        preparedStatement.setInt(1, getStaffID());
		        ResultSet rs = preparedStatement.executeQuery();
		        while (rs.next()) { 
		        	  staff  = new  Staff(rs.getString("FName"),
		        			rs.getString("LName"),
		        			rs.getString("Email"),
		        			rs.getString("PhoneNumber"), 
		        			rs.getInt(1), 
		        			rs.getInt("OfficeID"));
			        }
			    } catch (SQLException e) {
			    	e.printStackTrace();
			 }
		return staff;
	}


	private int getStaffID() {
		return staffID;
	}

}

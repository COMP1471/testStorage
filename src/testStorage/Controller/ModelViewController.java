package testStorage.Controller;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import testStorage.Model.Accounting;
import testStorage.Model.Branch;
import testStorage.Model.Client;
import testStorage.Model.Crate;
import testStorage.Model.CrateContentType;
import testStorage.Model.CrateFactory;
import testStorage.Model.CrateSize;
import testStorage.Model.CrateStatus;
import testStorage.Model.Employee;
import testStorage.Model.Management;
import testStorage.Model.Manager;
import testStorage.Model.Office;
import testStorage.Model.Sales;
import testStorage.Model.Staff;
import testStorage.Model.WareHouse;
import testStorage.View.LoginFrame;

public class ModelViewController 
{
	public static void main(String[] args) throws ClassNotFoundException
	{
		// Establish connenction with the database
		 Connection con = DatabaseConnection.getInstance().sqlConnection();
		 
		// on initialise
		// 1) Load all clients with their associated branches and create their instances 
		ArrayList<Client> clientList = loadAllClients(con);	
		
		// 2) Load all Packford offices with their associated staff and create their instances 
		ArrayList<Office> office = loadAllOffices(con);	
	  }
	
	private static ArrayList<Office> loadAllOffices(Connection con) 
	{
		String sql = "select * from office";
		ArrayList<Office> officeList = new ArrayList<Office>();
		
		try 
		{
			PreparedStatement  prepareStatement = con.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			while(result.next())
			{  
				int officeID = Integer.valueOf(result.getString(1));
				String officeAddress = result.getString(2);
				String officePostCode = result.getString(3);
				int isWareHouse = Integer.valueOf(result.getString(4));
				
				if (isWareHouse != 1)
				{
					Office office = new Office(officeID, officeAddress, officePostCode, getStaffForOfficeID(officeID, con));
					officeList.add(office);
				}
				else
				{
					Office office = new WareHouse(officeID, officeAddress, officePostCode, getStaffForOfficeID(officeID, con), getCratesForOfficeID(officeID, con));
					officeList.add(office);
				}
			}  
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return officeList;
	}

	private static ArrayList<Crate> getCratesForOfficeID(int officeID, Connection con)
	{
		String sql = "select * from warehouse where OfficeID = " + officeID;
		ArrayList<Crate> crateList = new ArrayList<Crate>();
		
		CrateFactory crateFactory = new CrateFactory();
		
		try 
		{
			PreparedStatement  prepareStatement = con.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			while(result.next())
			{
				int crateID = Integer.valueOf(result.getString(3));
				
				Crate crate = crateFactory.retrieveCrateByID(crateID, officeID, con);
				crateList.add(crate);
			}  
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		 
		return crateList;
	}

	private static ArrayList<Staff> getStaffForOfficeID(int officeID, Connection con) 
	{
		String sql = "select * from staff where OfficeID	 = " + officeID;
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		
		try 
		{
			PreparedStatement  prepareStatement = con.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			while(result.next())
			{
				int staffID = Integer.valueOf(result.getString(1));
				
				String fName = result.getString(2);
				String lName = result.getString(3);
				String departmentName = result.getString(4);
				String userName = result.getString(5);
				String password = result.getString(6);
				String phoneNumber = result.getString(7);
				String email = result.getString(8);
				
				switch (departmentName)
				{
					case "Sales":
					{
						Staff salesStaff = new Sales(fName, lName, email, phoneNumber, staffID, userName);
						staffList.add(salesStaff);
					}
					case "Accounting":
					{
						Staff accountingStaff = new Accounting(fName, lName, email, phoneNumber, staffID, userName);
						staffList.add(accountingStaff);
					}
					case "Management":
					{
						Staff mgmtStaff = new Management(fName, lName, email, phoneNumber, staffID, userName);
						staffList.add(mgmtStaff);
					}
				}
			}  
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		 
		return staffList;
	}

	private static boolean insertNewStaff()
	{
		String sql = "INSERT INTO `staff` (`ID`, `fName`, `lName`, `Department`, `OfficeID`, `UserName`, `Password`, `Phone`, `Email`) VALUES ('3', 'Ludovic', 'Malone', 'Accounting', '1', 'ludoMad', '787898', '+44 020 789 2345', 'ludovicMalone@Packford.co.uk'), ('4', 'Joseph', 'Azan', 'Management', '1', 'jozan1234', '0987654321', '+44 020 789 3254', 'josephazan@Packford.co.uk');";
		return false;
	}
	
	private static ArrayList<Client> loadAllClients(Connection con)
	{
		String sql = "select * from client";
		ArrayList<Client> clientList = new ArrayList<Client>();
		
		try 
		{
			PreparedStatement  prepareStatement = con.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			while(result.next())
			{  
				int clientID = Integer.valueOf(result.getString(1));
				String clientName = result.getString(2);
				
				Client client = new Client(clientID, clientName, getBranchesForClientID(clientID, con));
				clientList.add(client);
			}  
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return clientList;
	}	
	/*
	 * 		 String sql = "select * from client";
		 
		 try 
		 {
			PreparedStatement  prepareStatement = con.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			while(result.next())
			{  
				System.out.println("Name "+result.getString(1) +" "+ result.getString(2));  
			}  
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	 * 
	 */

	private static ArrayList<Branch> getBranchesForClientID(int clientID, Connection con) 
	{
		String sql = "select * from branch where ClientID = " + clientID;
		ArrayList<Branch> branchList = new ArrayList<Branch>();
		
		try 
		{
			PreparedStatement  prepareStatement = con.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			while(result.next())
			{
				int branchID = Integer.valueOf(result.getString(1));
				String branchAddresss = result.getString(2);
				String branchPostCode = result.getString(3);
				
				Branch branch = new Branch(branchID, clientID, getEmployeesForBranchID(branchID, clientID, con), branchAddresss, branchPostCode);
				branchList.add(branch);
			}  
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		 
		return branchList;
	}

	private static ArrayList<Employee> getEmployeesForBranchID(int branchID, int clientID, Connection con) 
	{
		String sql = "select * from employee where BranchID	 = " + branchID;
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		try 
		{
			PreparedStatement  prepareStatement = con.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			while(result.next())
			{
				int employeeID = Integer.valueOf(result.getString(1));
				
				String fName = result.getString(2);
				String lName = result.getString(3);
				String email = result.getString(4);
				String userName = result.getString(5);
				String phoneNumber = result.getString(6);
				int isManager = Integer.valueOf(result.getString(7));
				
				if (isManager != 1)
				{
					Employee employee = new Employee(fName, lName, email, phoneNumber, employeeID, userName, branchID);
					employeeList.add(employee);
				}
				else
				{
					Employee manager = new Manager(fName, lName, email, phoneNumber, employeeID, userName, branchID);
					employeeList.add(manager);
				}
			}  
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		 
		return employeeList;
	}

}

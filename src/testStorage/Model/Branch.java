package testStorage.Model;

import java.util.ArrayList;

public class Branch extends Observer
{
private int branchID;
private int clientID;

private ArrayList<Employee> branchEmployeeList;
private String branchAddress;
	
	public Branch(int branchID, int clientID, String branchAddress) 
	{
		this.branchID = branchID;
		this.setClientID(clientID);
		
		branchEmployeeList = new ArrayList<Employee>();
		this.branchAddress = branchAddress;
	}

	public Branch(int branchID, int clientID, ArrayList<Employee> branchEmployeeList, String branchAddress) 
	{
		this.branchID = branchID;
		this.setClientID(clientID);
		
		this.branchEmployeeList = branchEmployeeList;
		this.branchAddress = branchAddress;
	}

	public int getBranchID() {
		return branchID;
	}

	public void setBranchID(int branchID) {
		this.branchID = branchID;
	}

	public ArrayList<Employee> getBranchEmployeeList() {
		return branchEmployeeList;
	}

	public void setBranchEmployeeList(ArrayList<Employee> branchEmployeeList) {
		this.branchEmployeeList = branchEmployeeList;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

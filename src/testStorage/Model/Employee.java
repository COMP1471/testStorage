package testStorage.Model;

import java.util.ArrayList;

public class Employee extends Person 
{
private int employeeID;
private String employeeUserName;
private Branch branch;
	 
	public Employee(String personfName, String personlName, String personEmail, String personPhoneNumber, int employeeID, String employeeUserName, Branch branch) 
	{
		super(personfName, personlName, personEmail, personPhoneNumber);
		
		this.employeeID = employeeID;
		this.employeeUserName = employeeUserName;
		this.branch = branch;
		
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeUserName() {
		return employeeUserName;
	}

	public void setEmployeeUserName(String employeeUserName) {
		this.employeeUserName = employeeUserName;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	public void sendCrates(ArrayList<Crate> crateSendList)
	{
		
	}
	
}

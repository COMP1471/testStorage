package testStorage.Model;

import java.util.ArrayList;

public class Employee extends Person 
{
private int employeeID;
private String employeeUserName;
private int branchID;
	 
	public Employee(String personfName, String personlName, String personEmail, String personPhoneNumber, int employeeID, String employeeUserName, int branchID) 
	{
		super(personfName, personlName, personEmail, personPhoneNumber);
		
		this.employeeID = employeeID;
		this.employeeUserName = employeeUserName;
		this.branchID = branchID;
		
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

	public int getBranch() {
		return branchID;
	}

	public void setBranch(int branchID) {
		this.branchID = branchID;
	}
	
	public void sendCrates(ArrayList<Crate> crateSendList)
	{
		
	}
	public ArrayList<Crate> acceptDelivery(Delivery delivery)
	{
		ArrayList<Crate> requestedCrateList = delivery.getRequestCrateList();
		// check if these are mine and return the validated list
		
		ArrayList<Crate> validateCrateList = new ArrayList<Crate>();
		
		if (requestedCrateList.size() < 1)
		{
			
			delivery.setCompleted(false);
			return null;
		}
		else
		{
			for (int i = 0; i < requestedCrateList.size(); i ++)
			{
				Crate requestedCrate = requestedCrateList.get(i);
				
				//if (requestedCrate.getClientID() == branch.getClientID())
				//{
				//validateCrateList.add(requestedCrate);
				//}
			}
			if (validateCrateList.size() > 0)
			{
				delivery.setCompleted(true);
				return validateCrateList;
			}
			else
			{
				delivery.setCompleted(false);
				return null;
			}
		}
	}
}

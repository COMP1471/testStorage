package testStorage.Model;

public class Staff extends Person  
{
private int staffID; 
private String staffUserName;

	public Staff(String personfName, String personlName, String personEmail, String personPhoneNumber, int staffID, String staffUserName)
	{
		super(personfName, personlName, personEmail, personPhoneNumber);
		
		this.setStaffID(staffID);
		this.setStaffUserName(staffUserName);
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public String getStaffUserName() {
		return staffUserName;
	}

	public void setStaffUserName(String staffUserName) 
	{
		this.staffUserName = staffUserName;
	}

}

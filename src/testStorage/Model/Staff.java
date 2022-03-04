package testStorage.Model;

public class Staff extends Person  
{
private int staffID; 
private String staffUserName;
private String department;

	public Staff(String personfName, String personlName, String personEmail, String personPhoneNumber)
	{
		super(personfName, personlName, personEmail, personPhoneNumber);
		
		this.staffID = staffID;
		this.staffUserName = staffUserName;
		this.department = department;
	}

}

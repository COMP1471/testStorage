package testStorage.Model;

import java.util.ArrayList;

public class Office 
{
private int officeID;
private String officeAddress;
private String officePostCode;
private ArrayList<Staff> staffList;

	public Office(int officeID, String officeAddress, String officePostCode, ArrayList<Staff> staffList)
	{
		this.officeID = officeID;
		this.officeAddress = officeAddress;
		this.setOfficePostCode(officePostCode);
		this.staffList = staffList;
	}

	public Office(int officeID, String officeAddress, String officePostCode)
	{
		this.officeID = officeID;
		this.officeAddress = officeAddress;
		this.setOfficePostCode(officePostCode);
		staffList = new ArrayList<Staff>();
	}
	
	public int getOfficeID() 
	{
		return officeID;
	}
	public void setOfficeID(int officeID) {
		this.officeID = officeID;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public ArrayList<Staff> getStaffList() {
		return staffList;
	}
	public void setStaffList(ArrayList<Staff> staffList) {
		this.staffList = staffList;
	}

	public String getOfficePostCode() {
		return officePostCode;
	}

	public void setOfficePostCode(String officePostCode) {
		this.officePostCode = officePostCode;
	}

}

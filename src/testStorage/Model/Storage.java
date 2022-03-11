package testStorage.Model;

import java.util.ArrayList;
import java.util.Date;

public class Storage 
{
private Date startDate;
private Date endDate;
private ArrayList<Crate> storedCrateList;
int lengthOfStorage;

	public Storage(Date startDate, Date endDate, ArrayList<Crate> storedCrateList, int lengthOfStorage) 
	{
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setStoredCrateList(storedCrateList);
		this.lengthOfStorage = lengthOfStorage;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ArrayList<Crate> getStoredCrateList() {
		return storedCrateList;
	}

	public void setStoredCrateList(ArrayList<Crate> storedCrateList) {
		this.storedCrateList = storedCrateList;
	}


}

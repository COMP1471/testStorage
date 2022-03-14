package testStorage.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Storage 
{
private LocalDate startDate;
private LocalDate endDate;
private ArrayList<Crate> storedCrateList;
int lengthOfStorage;

	public Storage(LocalDate startDate, ArrayList<Crate> storedCrateList, int lengthOfStorage) 
	{
		this.setStartDate(startDate);
		endDate = startDate.plusDays(lengthOfStorage);
		
		this.setStoredCrateList(storedCrateList);
		this.lengthOfStorage = lengthOfStorage;
	}

	public ArrayList<Crate> getStoredCrateList() 
	{
		return storedCrateList;
	}

	public void setStoredCrateList(ArrayList<Crate> storedCrateList) 
	{
		this.storedCrateList = storedCrateList;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public boolean compareTo(Storage existingStorage)
	{
		// if start/end dates, crates and contents as well as length are same
		return false;
	}


}

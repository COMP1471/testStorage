package testStorage.Model;

import java.util.ArrayList;

public class WareHouse extends Office 
{
private ArrayList<Crate> crateList;

	public WareHouse(int officeID, String officeAddress)
	{
		super(officeID, officeAddress);
		crateList = new ArrayList<Crate>();
	}

	public ArrayList<Crate> getCrateList() 
	{
		return crateList;
	}
	
	public void setCrateList(ArrayList<Crate> crateList) 
	{
		this.crateList = crateList;
	}
	
	public void addrates(ArrayList<Crate> newCrateList)
	{
		for(int i = 0; i < newCrateList.size(); i++)
		{
			Crate crateToAdd = newCrateList.get(i);
			crateToAdd.setCrateStatusEnum(CrateStatus.inStorage);
			crateList.add(crateToAdd);
		}
	}

}

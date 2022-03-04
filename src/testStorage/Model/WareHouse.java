package testStorage.Model;

import java.util.ArrayList;

public class WareHouse extends Office 
{
private ArrayList<Crate> crateList;

	public WareHouse()
	{
		
	}
	
	public ArrayList<Crate> getCrateList() 
	{
		return crateList;
	}
	
	public void setCrateList(ArrayList<Crate> crateList) 
	{
		this.crateList = crateList;
	}

}

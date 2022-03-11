package testStorage.Model;

import java.util.ArrayList;

public class WareHouse extends Office 
{
private ArrayList<Crate> crateList;
private boolean [][] shelfs = new boolean[6][6];

	public WareHouse(int officeID, String officeAddress)
	{
		super(officeID, officeAddress);
		crateList = new ArrayList<Crate>();
		setSelfLoactions(crateList);
	}

	public ArrayList<Crate> getCrateList() 
	{
		return crateList;
	}
	
	public void setCrateList(ArrayList<Crate> crateList) 
	{
		this.crateList = crateList;
		setSelfLoactions(crateList);
	}

	private void setSelfLoactions(ArrayList<Crate> crateList) {
		for(int i = 0 ; i < crateList.size(); i++) {
			Crate crate = this.crateList.get(i);
			int shelfNumber = crate.getShelfNumber();
			int x = shelfNumber / 10 ;
			int y = shelfNumber % 10 ;
			if ((x <  6 && x >= 0) && (y < 6 && y >= 0)) {
			this.shelfs[x][y] = true;
		   }	
		}
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
		
	public int getEmptyShelf()
	{  
		for( int i = 0 ; i < shelfs.length ; i++ ) {
			for (int j = 0 ; j < shelfs[i].length; j++ ) {
				if (!shelfs[i][j]) 
				{
					int shelfID = i * 10 + j;
					shelfs[i][j] = true;
					return shelfID;
				}
			}
		}
		return -1;
	}


}

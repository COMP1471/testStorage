package testStorage.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class StorageAllocator 
{
private ArrayList<Storage> storageList;
private ArrayList<WareHouse> warehouseList;
	
	public StorageAllocator()
	{
		// connect to DB and initialise all of the existing warehouse/storage instances
		storageList = new ArrayList<Storage>();
		warehouseList = new ArrayList<WareHouse>();
	}
	
	public void sendToStorage(Storage firstStorage, Employee employee) 
	{
		//check if item is already scheduled for storage and if the dates don't overlap		
		boolean sameCratesToStore = false;
		sameCratesToStore = checkStorageDatesOverlap(firstStorage);
		
		if (!sameCratesToStore)
		{
			ArrayList<Crate> toStoreCrateList = firstStorage.getStoredCrateList();
			WareHouse warehouse = findTheBestWarehouse(employee);
			warehouse.addCrates(toStoreCrateList);
			storageList.add(firstStorage);
		}
		else
		{
			System.out.println("Some of the crates are already scheduled for storage");
		}
	}

	public void addNewWareHouse(WareHouse wareHouse)
	{
		warehouseList.add(wareHouse);
	}
	
	private boolean checkStorageDatesOverlap(Storage firstStorage) 
	{
		LocalDate startDate = firstStorage.getStartDate();
		LocalDate endDate = firstStorage.getEndDate();
		
		for (int i =0; i < storageList.size(); i++)
		{
			Storage existingStorage = storageList.get(i);
			
			//check if we are not double entering the same storage request
			if(!firstStorage.compareTo(existingStorage))
			{
				LocalDate existingStartDate = existingStorage.getStartDate();
				LocalDate existingEndDate = existingStorage.getEndDate();
				
				if ((startDate.isAfter(existingStartDate))||((endDate.isBefore(existingEndDate))))
				{
					// the storage request overlaps in time with the existing storage request
					System.out.println("OVERLAP ON DATES");
				}
				else
				{
					System.out.println("NO OVERLAP ON DATES");
					
					ArrayList<Crate> toStoreCrateList = firstStorage.getStoredCrateList();
					for (int j = 0; j < toStoreCrateList.size(); j++)
					{
						Crate toStoreCrate = toStoreCrateList.get(j);
						
						ArrayList<Crate> existingCrateList = existingStorage.getStoredCrateList();
						
						for (int n = 0; n < existingCrateList.size(); n++)
						{
							Crate existingCrate = existingCrateList.get(n);
							
							if (toStoreCrate.compareTo(existingCrate))
							{
								
							}
						}
					}
				}
			}
		}
		
		return false;
	}

	public void getFromStorage(Storage firstStorage, Employee employee) 
	{
		ArrayList<Crate> storedCrateList = firstStorage.getStoredCrateList();
		boolean sameOwner = true;
		sameOwner = compareOwnership(storedCrateList, employee);	
		
		if (sameOwner)
		{
			for (int i = 0; i < storedCrateList.size(); i++)
			{
				Crate crate = storedCrateList.get(i);
				crate.getWareHouseID(); // this is where we get an instance of a warehouse where the crate is meant to be
				
				
			}
		}
		else
		{
			System.out.println("You do not have right to get this crate. Please contact your supervisor");
		}
	}

	private boolean compareOwnership(ArrayList<Crate> storedCrateList, Employee employee) 
	{
		for (int i = 0; i < storedCrateList.size(); i++)
		{
			Crate crate = storedCrateList.get(i);
			int crateOwnerID = crate.getClientID();
			int crateRequesterID = employee.getEmployeeID();
			
			// this is too basic; need to check manager as well 
			// + if pass object can return the crates which are not yours to help guide user
			if (crateOwnerID != crateRequesterID)
			{
				return false;
			}
		}
		return true;
	}
	
	private WareHouse findTheBestWarehouse(Employee employee) 
	{
		// return warehouse with the most empty shelfs
		for (int i = 0; i < warehouseList.size(); i++)
		{
			WareHouse wareHouse = warehouseList.get(i);
			wareHouse.getEmptyShelf();
			return wareHouse;
			
		}
		return null;
	}

}

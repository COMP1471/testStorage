package testStorage.Model;

import java.util.ArrayList;
import java.util.Date;

public class CrateFactory 
{
int lastCreatedCrateID;

	public CrateFactory()
	{
		lastCreatedCrateID = 0;
	}
	
	public Crate getCrate(CrateSize crateSize, CrateContentType crateContentType, WareHouse wareHouse, int clientID)
	{
		Crate crate = new Crate(lastCreatedCrateID, wareHouse.getEmptyShelf(), clientID,  wareHouse.getOfficeID(), new Date(), CrateStatus.withClient, crateSize, crateContentType, false);
		lastCreatedCrateID++;
		return crate;		
	}
}

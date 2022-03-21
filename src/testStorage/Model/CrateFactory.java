package testStorage.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
		Crate crate = new Crate(lastCreatedCrateID, wareHouse.getEmptyShelf(), clientID,  wareHouse.getOfficeID(), LocalDate.now(), CrateStatus.withClient, crateSize, crateContentType, false);
		lastCreatedCrateID++;
		return crate;		
	}
	
	public Crate retrieveCrateByID(int crateID, int officeID, Connection con) 
	{
		String sql = "select * from crate where ID	 = " + crateID;
		
		try 
		{
			PreparedStatement  prepareStatement = con.prepareStatement(sql);
			ResultSet result = prepareStatement.executeQuery();
			
			while(result.next())
			{  			
				CrateContentType crateContentTypeEnum;
				
				switch (result.getString(2))
				{
					case "PaperContent":
						crateContentTypeEnum = CrateContentType.paperContent;
					case "electronicMediaContent":
						crateContentTypeEnum = CrateContentType.electronicMediaContent;
					case "miscellaneousContent":
						crateContentTypeEnum = CrateContentType.miscellaneousContent;
					default:
						crateContentTypeEnum = CrateContentType.unknownContent;
				}
				
				int clientID = Integer.valueOf(result.getString(3));
				boolean isFull = Integer.valueOf(result.getString(4)) != 0;
				int shelfID = Integer.valueOf(result.getString(5));
				
				LocalDate createdOnDate = LocalDate.parse(result.getString(6).split(" ")[0]);
				
				CrateSize crateSizeEnum;
				
				switch (result.getString(7))
				{
					case "mediumSize":
						crateSizeEnum = CrateSize.mediumSize;
					case "largeSize":
						crateSizeEnum = CrateSize.largeSize;
					case "smallSize":
						crateSizeEnum = CrateSize.smallSize;
					default:
						crateSizeEnum = CrateSize.largeSize;
				}
				
				Crate crate = new Crate(crateID, shelfID, clientID, officeID, createdOnDate, CrateStatus.beingDelivered, crateSizeEnum, crateContentTypeEnum, isFull);
				return crate;
			}  
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
}

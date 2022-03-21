package testStorage.Model;

import java.util.ArrayList;
import java.util.Date;

public class DeliveryScheduler 
{
	public Delivery requestDelivery(Employee requestEmployee, ArrayList<Crate> requestCrateList, Date deliveryDate, String deliveryAddress) 
	{
		ArrayList<Crate> availableCrates = new ArrayList<Crate>();
		
		if (requestEmployee instanceof Manager)
		{
			for (int i = 0; i < requestCrateList.size(); i++)
			{
				Crate requestCrate = requestCrateList.get(i);
				
				if (requestCrate.getCrateStatusEnum() == CrateStatus.inStorage)
				{
					availableCrates.add(requestCrate);
				}
				else
				{
					System.out.println("Crate " + requestCrate.toString() + " is already on client site or scheduled for the delivery");
					System.out.println("Only the crates in warehouse will be added to delivery");
				}
				requestCrate.notifyAllObservers();
			}
		}
		
		Delivery delivery = new Delivery(availableCrates, deliveryDate, deliveryAddress);
		return delivery;
	}
	
		// 1. Check if have permission
		// 2. Check if crates are in warehouse 
		// 3. Confirm delivery
		// *4. Notify crate owner if does not belong to the requesting branch
	
	}

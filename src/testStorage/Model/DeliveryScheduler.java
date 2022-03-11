package testStorage.Model;

import java.util.Date;

public class DeliveryScheduler 
{
	public void requestDelivery(Employee requestEmployee, Crate requestCrate, Date deliveryDate, String deliveryAddress) 
	{
		if (requestEmployee instanceof Manager)
		{
			if (requestCrate.getCrateStatusEnum() == CrateStatus.inStorage)
			{
				Delivery delivery = new Delivery(requestCrate, deliveryDate, deliveryAddress);
			}
		}
		else
		{
			
		}
		requestCrate.notifyAllObservers();
	}
	
		// 1. Check if have permission
		// 2. Check if crates are in warehouse 
		// 3. Confirm delivery
		// *4. Notify crate owner if does not belong to the requesting branch
	
	}

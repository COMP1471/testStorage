package testStorage.Model;

import java.util.ArrayList;
import java.util.Date;

public class SubscriptionStorage extends Storage 
{
private int frequency;

	public SubscriptionStorage(Date startDate, Date endDate, ArrayList<Crate> storedCrateList, int lengthOfStorage, int frequency) 
	{
		super(startDate, endDate, storedCrateList, lengthOfStorage);
		this.frequency = frequency;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}





}

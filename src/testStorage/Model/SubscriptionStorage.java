package testStorage.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class SubscriptionStorage extends Storage 
{
private int frequency;

	public SubscriptionStorage(LocalDate startDate, ArrayList<Crate> storedCrateList, int lengthOfStorage, int frequency) 
	{
		super(startDate, storedCrateList, lengthOfStorage);
		this.frequency = frequency;
	}

	public int getFrequency() 
	{
		return frequency;
	}

	public void setFrequency(int frequency) 
	{
		this.frequency = frequency;
	}





}

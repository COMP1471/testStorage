package testStorage.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Accounting extends Staff
{
	public Accounting(String personfName, String personlName, String personEmail, String personPhoneNumber, int staffID, String staffUserName) 
	{
		super(personfName, personlName, personEmail, personPhoneNumber, staffID, staffUserName);
	}

	public void generateBill(Client client, LocalDate billingStartDate, LocalDate billingEndDate)
	{
		
	}
}

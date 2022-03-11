package testStorage.Model;

import java.util.ArrayList;

public class Sales extends Staff
{
private ArrayList<Client> clientList;

	public Sales(String personfName, String personlName, String personEmail, String personPhoneNumber, int staffID, String staffUserName) 
	{
		super(personfName, personlName, personEmail, personPhoneNumber, staffID, staffUserName);
		clientList = new ArrayList<Client>();
	}
	public ArrayList<Client> getClientList()
	{
		return clientList;
	}

	public void setClientList(ArrayList<Client> clientList)
	{
		this.clientList = clientList;
	}

	public void addClient(Client client)
	{
		clientList.add(client);
	}
}

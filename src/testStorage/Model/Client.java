package testStorage.Model;

import java.util.ArrayList;

public class Client 
{
private int clientID;

private String clientName;
private ArrayList<Branch> clientBranchList;

	public Client(int clientID, String clientName, ArrayList<Branch> clientBranchList)
	{
		this.clientID = clientID;
		this.clientName = clientName;
		this.clientBranchList = clientBranchList;
	}
	
	public int getClientID() {
		return clientID;
	}
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public ArrayList<Branch> getClientBranchList() {
		return clientBranchList;
	}
	public void setClientBranchList(ArrayList<Branch> clientBranchList) {
		this.clientBranchList = clientBranchList;
	}
}

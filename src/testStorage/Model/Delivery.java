package testStorage.Model;

import java.util.ArrayList;
import java.util.Date;

public class Delivery 
{
private ArrayList<Crate> requestCrateList;
private Date deliveryDate;
private String deliveryAddress;
private boolean completed;

	public Delivery(ArrayList<Crate> requestCrateList, Date deliveryDate, String deliveryAddress) 
	{
		this.setRequestCrateList(requestCrateList);
		this.setDeliveryDate(deliveryDate);
		this.setDeliveryAddress(deliveryAddress);
		setCompleted(false);
	}

	public ArrayList<Crate> getRequestCrateList() {
		return requestCrateList;
	}

	public void setRequestCrateList(ArrayList<Crate> requestCrateList) {
		this.requestCrateList = requestCrateList;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}

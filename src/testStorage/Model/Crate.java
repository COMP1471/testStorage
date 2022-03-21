package testStorage.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Crate 
{
private int crateID;
private int shelfNumber;
private int clientID;  // id of a client to whom the crate belongs to 
private int wareHouseID;

private LocalDate createdOnDate;
private ArrayList<Observer> observers = new ArrayList<Observer>();
private CrateStatus crateStatusEnum;
private CrateSize crateSizeEnum;
private CrateContentType crateContentTypeEnum;

private boolean isFull;
private ArrayList<Content> contentList;

	

	public Crate(int crateID, int shelfNumber, int clientID, int wareHouseID,  LocalDate createdOnDate, CrateStatus crateStatusEnum, CrateSize crateSizeEnum, CrateContentType crateContentTypeEnum, boolean isFull) 
	{
		this.crateID = crateID;
		this.shelfNumber = shelfNumber;
		this.clientID = clientID;
		this.wareHouseID = wareHouseID;
		
		this.createdOnDate = createdOnDate;
		
		this.crateStatusEnum = crateStatusEnum;
		this.crateSizeEnum = crateSizeEnum;
		this.crateContentTypeEnum = crateContentTypeEnum;
		this.isFull = isFull;
		contentList = new  ArrayList<Content>();
	}

	public void removeContentByID(int contentID) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public int getWareHouseID() {
		return wareHouseID;
	}

	public void setWareHouseID(int wareHouseID) {
		this.wareHouseID = wareHouseID;
	}
	public int getCrateID() {
		return crateID;
	}

	public void setCrateID(int crateID) {
		this.crateID = crateID;
	}

	public int getShelfNumber() {
		return shelfNumber;
	}

	public void setShelfNumber(int shelfNumber) {
		this.shelfNumber = shelfNumber;
	}
	
	public LocalDate getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(LocalDate createdOnDate) {
		this.createdOnDate = createdOnDate;
	}

	public CrateStatus getCrateStatusEnum() {
		return crateStatusEnum;
	}

	public void setCrateStatusEnum(CrateStatus crateStatusEnum) {
		this.crateStatusEnum = crateStatusEnum;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

	public CrateSize getCrateSizeEnum() {
		return crateSizeEnum;
	}

	public void setCrateSizeEnum(CrateSize crateSizeEnum) {
		this.crateSizeEnum = crateSizeEnum;
	}

	public CrateContentType getCrateContentTypeEnum() {
		return crateContentTypeEnum;
	}

	public void setCrateContentTypeEnum(CrateContentType crateContentTypeEnum) {
		this.crateContentTypeEnum = crateContentTypeEnum;
	}

	public ArrayList<Content> getContentList() {
		return contentList;
	}

	public void setContentList(ArrayList<Content> contentList) {
		this.contentList = contentList;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	public void attach(Observer observer) {
		observers.add(observer);
	}  
	
	public void notifyAllObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
	
	public String toString()
	{
		return "Crate ID: " + crateID + " on the shelf number " + shelfNumber + " and belongs to " + clientID + " created on " + createdOnDate;  // id of a client to whom the crate belongs to 

		//private CrateStatus crateStatusEnum;
		//private CrateSize crateSizeEnum;
		//private CrateContentType crateContentTypeEnum;

		//private boolean isFull;
		//private ArrayList<Content> contentList;
	}

	public boolean compareTo(Crate existingCrate) 
	{
		// if contents overlap return TRUE
		return false;
	}
}

package testStorage.Model;

import java.util.ArrayList;
import java.util.Date;

public class Crate 
{
private int crateID;
private int shelfNumber;

private Date createdOnDate;

private CrateStatus crateStatusEnum;
private CrateSize crateSizeEnum;
private CrateContentType crateContentTypeEnum;

private boolean isFull;
private ArrayList<Content> contentList;

	

	public Crate(int crateID, int shelfNumber, Date createdOnDate, CrateStatus crateStatusEnum, CrateSize crateSizeEnum, CrateContentType crateContentTypeEnum, boolean isFull, ArrayList<Content> contentList) 
	{
		this.crateID = crateID;
		this.shelfNumber = shelfNumber;
		this.createdOnDate = createdOnDate;
		this.crateStatusEnum = crateStatusEnum;
		this.crateSizeEnum = crateSizeEnum;
		this.crateContentTypeEnum = crateContentTypeEnum;
		this.isFull = isFull;
		this.contentList = contentList;
	}

	public void removeContentByID(int contentID) 
	{
		// TODO Auto-generated method stub
		
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
	
	public Date getCreatedOnDate() {
		return createdOnDate;
	}

	public void setCreatedOnDate(Date createdOnDate) {
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
}

package testStorage.Model;

public class Content 
{
private int contentID;
private String contentName;
private Crate inCrate;

	public Content(int contentID, String contentName, Crate inCrate)
	{
		this.contentID = contentID;
		this.contentName = contentName;
		this.setInCrate(inCrate);
	}
	public int getContentID() 
	{
		return contentID;
	}
	public void setContentID(int contentID) 
	{
		this.contentID = contentID;
	}
	public String getContentName() 
	{
		return contentName;
	}
	public void setContentName(String contentName)
	{
		this.contentName = contentName;
	}
	public Crate getInCrate() 
	{
		return inCrate;
	}
	public void setInCrate(Crate inCrate)
	{
		this.inCrate = inCrate;
	}
	
	public void switchCrates(Crate newInCrate)
	{
		inCrate.removeContentByID(contentID);
		this.inCrate = newInCrate;
	}
}

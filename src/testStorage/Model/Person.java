package testStorage.Model;

public abstract class Person 
{
private String personfName;
private String personlName;

private String personEmail;
private String personPhoneNumber;

	public Person(String personfName, String personlName, String personEmail, String personPhoneNumber)
	{
		this.personfName = personfName;
		this.personlName = personlName;
		
		this.personPhoneNumber = personPhoneNumber;
		this.personPhoneNumber = personPhoneNumber;
	}
	
	public String getPersonfName() {
		return personfName;
	}
	public void setPersonfName(String personfName) {
		this.personfName = personfName;
	}
	public String getPersonlName() {
		return personlName;
	}
	public void setPersonlName(String personlName) {
		this.personlName = personlName;
	}
	public String getPersonEmail() {
		return personEmail;
	}
	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}
	public String getPersonPhoneNumber() {
		return personPhoneNumber;
	}
	public void setPersonPhoneNumber(String personPhoneNumber) {
		this.personPhoneNumber = personPhoneNumber;
	}

}

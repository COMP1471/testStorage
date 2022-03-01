package testStorage.Model;

public class EmployeeBranch
{
private String branchAddress;
private int BranchID;
private String branchPostCode;
private String branchName;

	public EmployeeBranch()
	{
		
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public int getBranchID() {
		return BranchID;
	}

	public void setBranchID(int branchID) {
		BranchID = branchID;
	}

	public String getBranchPostCode() {
		return branchPostCode;
	}

	public void setBranchPostCode(String branchPostCode) {
		this.branchPostCode = branchPostCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
}

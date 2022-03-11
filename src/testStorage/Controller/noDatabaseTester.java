package testStorage.Controller;

import java.util.*;

import testStorage.Model.Branch;
import testStorage.Model.Client;
import testStorage.Model.Content;
import testStorage.Model.Crate;
import testStorage.Model.CrateContentType;
import testStorage.Model.CrateSize;
import testStorage.Model.CrateStatus;
import testStorage.Model.Employee;
import testStorage.Model.Manager;
import testStorage.Model.Office;
import testStorage.Model.Sales;
import testStorage.Model.Staff;
import testStorage.Model.WareHouse;

public class noDatabaseTester
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Employee employee1 = new Employee("Benjamin", "Rudolph", "BenR@Mail.ru", "+44 7340 50 24 76", 0, "ben69", null);
		Employee employee2 = new Employee("Jakie", "Chairs", "jakiec@gmail.com", "+44 0234 22 33 55", 1, "jakieCHRIS", null);
		
		Employee manager1 = new Manager("Nauman", "Pascale", "NaumanP@Mail.ru", "07695502476", 1, "newman", null);
		
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		
		ArrayList<Branch> branchList = new ArrayList<Branch>();
		Branch firstCLientBranch = new Branch(0, 0, employeeList, "34 stewart road");
		
		employeeList.add(manager1);
		employeeList.add(employee1);
		employeeList.add(employee2);
		System.out.println("size of the branch " + employeeList.size());
		
		branchList.add(firstCLientBranch);
		
		Client firstClient = new Client(0, "Law Firm", branchList);
		
		Staff sales1 = new Sales("James", "Navades", "jm@packford.co.uk", "020 324 5665", 0, "jamesNV");
		((Sales)sales1).addClient(firstClient);
		
		ArrayList<Content> contentList = new ArrayList<Content>();
		Crate dummyCrate = new Crate(0, 0, 0, new Date(), CrateStatus.withClient, CrateSize.mediumSize, CrateContentType.electronicMediaContent, false, contentList);
				
		for (int i = 0; i < 20; i++)
		{
			Content content = new Content(i, "TV", dummyCrate);
			contentList.add(content);
		}
		
		dummyCrate.setContentList(contentList);
		dummyCrate.setFull(true);
		
		System.out.println(dummyCrate.toString());
		
		Office warehouse = new WareHouse(0, "gaylord prospect");
		
	}

}

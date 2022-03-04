package testStorage.Controller;

import java.util.*;

import testStorage.Model.Content;
import testStorage.Model.Crate;
import testStorage.Model.CrateContentType;
import testStorage.Model.CrateSize;
import testStorage.Model.CrateStatus;
import testStorage.Model.Employee;
import testStorage.Model.Manager;

public class noDatabaseTester {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Employee employee1 = new Employee("Benjamin", "Rudolph", "BenR@Mail.ru", "+44 7340 50 24 76", 0, "ben69");
		System.out.println(employee1.getPersonPhoneNumber());
		
		Manager manager1 = new Manager("Nauman", "Pascale", "NaumanP@Mail.ru", "07695502476", 1, "ben69");
		System.out.println(manager1.getPersonPhoneNumber());
		
		Crate dummyCrate = new Crate(0, 0, new Date(), CrateStatus.inStorage, CrateSize.mediumSize, CrateContentType.electronicMediaContent, false, null);
		
		
		ArrayList<Content> contentList = new ArrayList<Content>();
				
		for (int i = 0; i < 20; i++)
		{
			Content content = new Content(i, "TV", dummyCrate);
			contentList.add(content);
		}
		
		dummyCrate.setContentList(contentList);
		
		System.out.println(dummyCrate.getContentList().get(6).getContentName());
		
	}

}

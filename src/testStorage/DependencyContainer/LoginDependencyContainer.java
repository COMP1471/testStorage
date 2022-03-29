package testStorage.DependencyContainer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import testStorage.Model.Employee;
import testStorage.Model.Staff;
import testStorage.View.Login.LoginViewController;

public class LoginDependencyContainer {
	
	private ClientDashBoardDependencyContainer clientContainer;
	private StaffDashBoardDependencyContainer staffContainer;
	
	public LoginDependencyContainer() {
		clientContainer = new ClientDashBoardDependencyContainer();
		staffContainer = new StaffDashBoardDependencyContainer();
		
	}
	
	public Scene loadView() throws IOException {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/Login/Login.fxml"));
	    Parent parent = (Parent) loader.load();
		LoginViewController controller  = loader.getController();
		controller.setContainer(this);
	    Scene scene = new Scene(parent);
		return  scene;
	}
	
	public Scene showClientDashBoad(Employee employee) throws IOException {
		return clientContainer.loadView(employee);
	}
	
	public Scene showStaffDashBoad(Staff staff) throws IOException {
		return staffContainer.loadView(staff);
	}
	
}

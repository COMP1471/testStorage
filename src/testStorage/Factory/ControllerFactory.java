package testStorage.Factory;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import testStorage.Controller.LoginViewController;
import testStorage.Model.Employee;
import testStorage.Model.Staff;

public class ControllerFactory {
	
	private ClientDashBoardFactory clientContainer;
	private StaffDashBoardFactory staffContainer;
	
	public ControllerFactory() {
		clientContainer = new ClientDashBoardFactory();
		staffContainer = new StaffDashBoardFactory();
		
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

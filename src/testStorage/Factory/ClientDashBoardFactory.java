package testStorage.Factory;

import java.io.IOException;
import java.sql.Date;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import testStorage.Controller.ClientDashBoardViewController;
import testStorage.Controller.ContentUpdateController;
import testStorage.Controller.CrateDetailController;
import testStorage.Model.Crate;
import testStorage.Model.Employee;

public class ClientDashBoardFactory {
	
	private Employee employee;
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Scene loadView(Employee employee) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ClientDashboard.fxml"));
		Parent parent = (Parent) loader.load();
		ClientDashBoardViewController controller = loader.getController();
		System.out.print(controller);
		controller.setEmployee(employee);
		setEmployee(employee);
		controller.setClientDashBoardContainer(this);
		controller.getClientData();
	    Scene scene = new Scene(parent);
		return  scene;
	}

	public Scene showCrateContentUpdate(Crate crate,Date onDate) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CrateContentUpdate.fxml"));
		Parent parent = (Parent) loader.load();
	    ContentUpdateController controller = loader.getController();
		System.out.print(controller);
	    controller.setEmployee(employee);
	    controller.setOnDate(onDate);
	    controller.setCrate(crate);
	    Scene scene = new Scene(parent);
		return  scene;
	}
	
	
	public Scene showCrateDetail(Crate crate) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CrateDetail.fxml"));
		Parent parent = (Parent) loader.load();
		CrateDetailController controller = loader.getController();
		System.out.print(controller);
		controller.setCrate(crate);
		controller.setEmployee(employee);
		controller.typeOfRequestCheck();
		controller.setClientDashBoardContainer(this);
	    Scene scene = new Scene(parent);
		return  scene;
	}

}

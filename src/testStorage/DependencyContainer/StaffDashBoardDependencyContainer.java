package testStorage.DependencyContainer;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import testStorage.Model.Admin;
import testStorage.Model.Management;
import testStorage.Model.Staff;
import testStorage.View.AdminController;
import testStorage.View.OrderManagementController;
import testStorage.View.ClientDashboard.ClientDashBoardViewController;
import testStorage.View.StaffDashboard.StaffDashBaordViewController;

public class StaffDashBoardDependencyContainer {
	
	public Scene loadView(Staff staff) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/StaffDashboard/StaffDashboard.fxml"));
		Parent parent = (Parent) loader.load();
		StaffDashBaordViewController controller  = loader.getController();
		System.out.print(controller);
		controller.setStaffContainer(this);
		controller.setStaff(staff);
	    Scene scene = new Scene(parent);
		return  scene;
	}
	
	public Scene showAddStaffController(Admin admin) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/StaffForm.fxml"));
		Parent parent = (Parent) loader.load();
		AdminController controller  = loader.getController();
		System.out.print(controller);
		controller.setAdmin(admin);
		controller.populateWarehouseList();
	    Scene scene = new Scene(parent);
		return  scene;
	}
	
	public Scene showPendingOrders(Management management) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/PendingOrders.fxml"));
		Parent parent = (Parent) loader.load();
		OrderManagementController controller  = loader.getController();
		System.out.print(controller);
		controller.setOrdermanager(management);
		controller.populatePendingOrder();
	    Scene scene = new Scene(parent);
		return  scene;
	}
	
	public Scene showManageOrders(Management management) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ManageOrders.fxml"));
		Parent parent = (Parent) loader.load();
		OrderManagementController controller  = loader.getController();
		System.out.print(controller);
		controller.setOrdermanager(management);
		controller.populateOrderInProgress();
	    Scene scene = new Scene(parent);
		return  scene;
	}
	
	public Scene showManagmentWarehouse(Admin admin) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/WarehouseManagement.fxml"));
		Parent parent = (Parent) loader.load();
		AdminController controller  = loader.getController();
		System.out.print(controller);
		controller.setAdmin(admin);
		controller.setTableContent();
	    Scene scene = new Scene(parent);
		return  scene;
	}
	
}

package testStorage.Factory;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import testStorage.Controller.AccountingController;
import testStorage.Controller.AdminController;
import testStorage.Controller.ClientDashBoardViewController;
import testStorage.Controller.OrderManagementController;
import testStorage.Controller.SalesManagementController;
import testStorage.Controller.StaffDashBaordViewController;
import testStorage.Model.Accounting;
import testStorage.Model.Admin;
import testStorage.Model.Management;
import testStorage.Model.Sales;
import testStorage.Model.Staff;

public class StaffDashBoardFactory {
	
	public Scene loadView(Staff staff) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/StaffDashboard.fxml"));
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
	
	public Scene showClientManagement(Sales sales) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/AddClient.fxml"));
		Parent parent = (Parent) loader.load();
		SalesManagementController controller  = loader.getController();
		System.out.print(controller);
		controller.setSalesStaff(sales);
		controller.setupForManageingClient();
	    Scene scene = new Scene(parent);
		return  scene;
	}
	
	public Scene showBranchManagement(Sales sales) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/BranchForm.fxml"));
		Parent parent = (Parent) loader.load();
		SalesManagementController controller  = loader.getController();
		System.out.print(controller);
		controller.setSalesStaff(sales);
		controller.setupForManageingBranch();
	    Scene scene = new Scene(parent);
		return  scene;
	}
	
	
	public Scene showEmployeeManagement(Sales sales) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/EmployeeForm.fxml"));
		Parent parent = (Parent) loader.load();
		SalesManagementController controller  = loader.getController();
		System.out.print(controller);
		controller.setSalesStaff(sales);
		controller.setupForManageingClient();
	    Scene scene = new Scene(parent);
		return  scene;
	}
	
	public Scene showCrateManagement(Sales sales) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CrateForm.fxml"));
		Parent parent = (Parent) loader.load();
		SalesManagementController controller  = loader.getController();
		System.out.print(controller);
		controller.setSalesStaff(sales);
		controller.setupForManageingBranch();
	    Scene scene = new Scene(parent);
		return  scene;
	}
	
	
	public Scene showBillingGenration(Accounting accounts) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/GentrateBilling.fxml"));
		Parent parent = (Parent) loader.load();
		AccountingController controller  = loader.getController();
		System.out.print(controller);
		controller.setAccounts(accounts);
		controller.setUpClientTable());
	    Scene scene = new Scene(parent);
		return  scene;
	}
	

}

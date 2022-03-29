package testStorage.View.StaffDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import testStorage.DependencyContainer.StaffDashBoardDependencyContainer;
import testStorage.Model.Accounting;
import testStorage.Model.Admin;
import testStorage.Model.Management;
import testStorage.Model.Sales;
import testStorage.Model.Staff;
import testStorage.View.NavigationManager;

public class StaffDashBaordViewController implements Initializable { 

     @FXML
     private GridPane adminPane;
     
     @FXML 
     private GridPane salesPane;
     
     @FXML
     private GridPane accountsPane;
     
     @FXML
     private Label greetingLabel;
     
     @FXML 
     private GridPane managementPane;
     
     private Staff staff;
     
     private StaffDashBoardDependencyContainer staffContainer;

	@Override
	 public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	  }
	
	
	 private void loginInAsAdmin() {
		 adminPane.setVisible(true);
		 salesPane.setVisible(false);
		 accountsPane.setVisible(false);
		 managementPane.setVisible(false);
	 }
	
	 private void loginInAsSales() {	
		 salesPane.setVisible(true);
		 managementPane.setVisible(false);
		 adminPane.setVisible(false);
		 accountsPane.setVisible(false);
	 }
	
	 private void loginInAsManagement() {
		 managementPane.setVisible(true);
		 accountsPane.setVisible(false);
		 salesPane.setVisible(false);
		 adminPane.setVisible(false);
	 }
	 
	 private void loginInAsAccounts() {
		 accountsPane.setVisible(true);
		 adminPane.setVisible(false);
		 managementPane.setVisible(false);
		 salesPane.setVisible(false);
	 }
	
	 @FXML 
	 private void handleLogout(ActionEvent event) {
		 NavigationManager.getInstance().popScene();
	 }
	
	 @FXML 
	 private void handleAddStaff(ActionEvent event) throws IOException {
		 Admin admin = (Admin) staff; 
		 Scene scene =  staffContainer.showAddStaffController(admin);
		 NavigationManager.getInstance().pushNewScene(scene);
	 }
	
	 @FXML 
	 private void handleManageWarehouse(ActionEvent event) throws IOException {
		 Admin admin = (Admin) staff; 
		 Scene scene =  staffContainer.showManagmentWarehouse(admin);
		 NavigationManager.getInstance().pushNewScene(scene);
	 }
	 
	 @FXML 
	 private void handleManageOrders(ActionEvent event) throws IOException {
		 Management management = (Management) staff; 
		 Scene scene =  staffContainer.showManageOrders(management);
		 NavigationManager.getInstance().pushNewScene(scene);
	 }
	 
	 @FXML 
	 private void handlePendingOrders(ActionEvent event) throws IOException {
		 Management management = (Management) staff;  
		 Scene scene =  staffContainer.showPendingOrders(management);
		 NavigationManager.getInstance().pushNewScene(scene);
	 }
	
	 @FXML 
	 private void handleAddClient(ActionEvent event) {	 
		 
	 }
	 
	 @FXML 
	  private void handleManagerBranch(ActionEvent event) {
		 
	 }

	  public void setStaffContainer(StaffDashBoardDependencyContainer staffContainer) {
			this.staffContainer = staffContainer;
	 }
	 
	  public void setStaff(Staff staff) {
		this.staff = staff;
		setWelcomeLabel();
		if (staff instanceof Accounting) {
			 loginInAsAccounts();
		} else if (staff instanceof Sales) {
			 loginInAsSales();
		} else if (staff instanceof Admin) {
			 loginInAsAdmin();
		} else {
			 loginInAsManagement();
		}
	  }
	  
	  private void setWelcomeLabel() {
		  greetingLabel.setText("Hi , "+ staff.toString());
	  }
	  
	  
}
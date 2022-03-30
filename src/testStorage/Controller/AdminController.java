package testStorage.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import testStorage.Model.Admin;
import testStorage.Model.Content;
import testStorage.Model.Departments;
import testStorage.Model.Office;
import testStorage.Model.Staff;
import testStorage.Model.User;
import testStorage.Model.WareHouse;
import testStorage.View.NavigationManager;

public class AdminController implements Initializable {
	
	@FXML
	private TextField fName;
	
	@FXML
	private TextField lName;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField userName;
	
	@FXML
	private TextField phoneNumber;
	
	@FXML
	private TextField password;
	
	@FXML
	private Button addStaffButton;
	
	@FXML
	private Button addManagementButton;
	
	@FXML
	private TextField warehouseAddress;
	
	@FXML
	private TextField warehousePostcode;
	
	@FXML
	private TableView<WareHouse> warehouseTable;
	
	@FXML 
	private TableColumn<WareHouse,String> addressColumn;
	
	@FXML 
	private TableColumn<WareHouse, Integer> iDColumn;
	
	@FXML
	private ComboBox<Departments> departments;
	
	@FXML
	private ComboBox<WareHouse> wareHouse;
	
	private Admin admin;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	@FXML
	public void handleStaffAdding(ActionEvent event) {
		Departments selectedDepartment = departments.getValue(); 
		Boolean success = admin.addNewStaff(getStaff(), getUser(), selectedDepartment.toString());
	  if (success) {
		addStaffButton.setText("Added");
		addStaffButton.setDisable(true);
		addStaffButton.setStyle("-fx-text-fill: white; -fx-background-color: #008000; -fx-background-radius: 0;");
	  }
   }
	
	public void setTableContent() {
		MenuItem menuitem = new MenuItem("Remove");
		menuitem.setOnAction((ActionEvent event) -> {
		    System.out.println("Menu item 1");
		    WareHouse warehouse = warehouseTable.getSelectionModel().getSelectedItem();
		    admin.removeWarehouse(warehouse);
		    updateWarehouseTable();
		});

		ContextMenu menu = new ContextMenu();
		menu.getItems().add(menuitem);
		warehouseTable.setContextMenu(menu);
		updateWarehouseTable();
	}
	
	private void updateWarehouseTable() {
		ObservableList<WareHouse> warehouseList = FXCollections.observableArrayList(); 
		ArrayList<WareHouse> warehouseArray = admin.getWareHouseList();
		addressColumn.setCellValueFactory(new PropertyValueFactory<WareHouse, String>("officeAddress"));
		iDColumn.setCellValueFactory(new PropertyValueFactory<WareHouse,Integer>("officeID"));
		warehouseList.addAll(warehouseArray);
		warehouseTable.setItems(warehouseList);
	}
	
	@FXML
	public void handleAddWarehouse(ActionEvent event) {
		String address = warehouseAddress.getText();
		String postcode = warehousePostcode.getText();
		Office office = new Office(address,postcode);
		if(admin.addWarehouse(office)) {
			updateWarehouseTable();
		}
	}
	
	@FXML
	public void handleBackAction(ActionEvent event) {
		NavigationManager.getInstance().popScene();
	}
	
	private User getUser() {
		User user = new User();
		user.setUserName(userName.getText());
		user.setPassWord(password.getText());
		return user; 
	}

	private Staff getStaff() {
	  WareHouse warehouse = wareHouse.getValue();
	  Staff staff = new Staff(
			   fName.getText(),
			   lName.getText(),
			   email.getText(),
			   phoneNumber.getText(),
			   warehouse.getOfficeID());
	  return staff;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void populateWarehouseList() {
		addWarehouseList();
		addDepartmentList();
	}
	
	private void addWarehouseList() {
		ObservableList<WareHouse> warehouseList = FXCollections.observableArrayList(); 
		ArrayList<WareHouse> warehouseArray = admin.getWareHouseList();
		warehouseList.addAll(warehouseArray);
		wareHouse.setItems(warehouseList);
	}
	
	private void addDepartmentList() {
		departments.setItems(FXCollections.observableArrayList(Departments.values()));
	}
	

}

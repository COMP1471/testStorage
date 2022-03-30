package testStorage.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.Date;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import testStorage.Model.Branch;
import testStorage.Model.Client;
import testStorage.Model.Crate;
import testStorage.Model.CrateContentType;
import testStorage.Model.CrateSize;
import testStorage.Model.CrateStatus;
import testStorage.Model.Employee;
import testStorage.Model.Order;
import testStorage.Model.Sales;
import testStorage.Model.User;
import testStorage.Model.WareHouse;
import testStorage.View.NavigationManager;

public class SalesManagementController  implements Initializable {
	
	@FXML 
	private TextField employeeFName; 
	
	@FXML
	private TextField employeeUserName;
	
	@FXML
	private TextField employeePassword;
	
	@FXML
	private TextField employeeEmail;
	
	@FXML
	private TextField employeePhoneNumber;
	
	@FXML
	private TextField employeeLName;	
	
	@FXML
	private TextField branchAddress;
	
	@FXML 
	private TextField branchPostcode;
	
	@FXML 
	private TextField clientName;
	
	@FXML
	private TextField crateShelfNumber;
	
	
	@FXML
	private ComboBox<Client> clientSelection;
	
	@FXML
	private ComboBox<Branch> branchSelection;
	
	@FXML
	private ComboBox<CrateSize> crateSizeSelection;
	
	@FXML
	private ComboBox<CrateContentType> crateContentSelection;
	
	@FXML
	private ComboBox<WareHouse> wareHouseSelection;
	
	@FXML
	private TableView<Client> clientTable;
	
	@FXML
	private TableColumn<Client , String> clientNameCol;
	
	@FXML
	private TableColumn<Client, Integer> clientIDCol;
	
	@FXML
	private TableView<Branch> branchTable;
	
	@FXML
	private TableColumn<Branch , Integer> branchIDCol;
	
	@FXML
	private TableColumn<Branch, String> branchAddressCol;
	
	@FXML
	private TableView<Employee> employeeTable;
	
	@FXML
	private TableColumn<Employee , Integer> employeeIDCol;
	
	@FXML
	private TableColumn<Employee , String>  employeeNameCol;
	
	@FXML
	private TableView<Crate> crateTable;
	
	@FXML
	private TableColumn<Crate , Integer> crateIDCol;
	
	@FXML
	private TableColumn<Crate, CrateContentType>  crateContentCol;
	
	private Sales salesStaff;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setSalesStaff(Sales sales) {
		this.salesStaff = sales;
	}
	// Client Section 
	public void setupForManageingClient() {
		clientIDCol.setCellValueFactory(new PropertyValueFactory<Client, Integer>("clientID"));
		clientNameCol.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
		ContextMenu context = new ContextMenu();
		MenuItem menu = new MenuItem("remove");
		menu.setOnAction((ActionEvent event) -> {
			Client client = clientTable.getSelectionModel().getSelectedItem();
			salesStaff.removeClient(client);
			populateClientTable();
		});
		context.getItems().add(menu);
		clientTable.setContextMenu(context);
		populateClientTable();
	}
	
	private void populateClientTable() {
		ObservableList<Client> clientlist = FXCollections.observableArrayList(); 
		ArrayList<Client> clients = salesStaff.getAllClient();
		clientlist.addAll(clients);
		clientTable.setItems(clientlist);
	}
	
	
	@FXML
	public void handleAddNewClient(ActionEvent event) { 
		String name = clientName.getText();
		Client client = new Client(name);
		salesStaff.addNewClient(client);
		populateClientTable();
	}
	
	// Branch Section 
	public void setupForManageingBranch() {
		branchIDCol.setCellValueFactory(new PropertyValueFactory<Branch, Integer>("BranchID"));
		branchAddressCol.setCellValueFactory(new PropertyValueFactory<Branch, String>("branchAddress"));
		setupClientComboBox();
		ContextMenu context = new ContextMenu();
		MenuItem menu = new MenuItem("remove");
		menu.setOnAction((ActionEvent event) -> {
			Branch branch = branchTable.getSelectionModel().getSelectedItem();
			salesStaff.removeBranch(branch);
			populateBranchTable();
		});
		context.getItems().add(menu);
		clientTable.setContextMenu(context);
	}
 	
	private void setupClientComboBox() {
		ArrayList<Client> clients = salesStaff.getAllClient();
		ObservableList<Client> clientlist = FXCollections.observableArrayList(); 
		clientlist.addAll(clients);
		clientSelection.setItems(clientlist);
	}
	
	private void populateBranchTable() {
	  Client client = clientSelection.getValue();
		if (client != null) {
		ObservableList<Branch> branchlist = FXCollections.observableArrayList(); 
		ArrayList<Branch> branchs = client.getListOfBranch();
		branchlist.addAll(branchs);
		branchTable.setItems(branchlist);
	  }
	}
	
	@FXML
	public void handleClientSelection(ActionEvent event) { 
		  populateBranchTable();
	}
	
	
	@FXML
	public void handleAddNewBranch(ActionEvent event) { 
		Client client = clientSelection.getValue();
		if (client != null) {
		String address = branchAddress.getText();
		String postCode = branchPostcode.getText();
		Branch branch = new Branch(client.getClientID(),address,postCode);
		salesStaff.addNewBranch(branch);
		populateBranchTable();
	 }
	}
	
	// Employee Section
	public void setupForManageingEmployee() {
		employeeIDCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("employeeID"));
		employeeNameCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Employee, String> arg) {
				Employee employee = arg.getValue();
                String name = employee.getPersonfName() + " " + employee.getPersonlName();
				return new SimpleStringProperty(name);
			}
			
		});
		setupClientComboBox();
		ContextMenu context = new ContextMenu();
		MenuItem menu = new MenuItem("remove");
		menu.setOnAction((ActionEvent event) -> {
			Employee employee = employeeTable.getSelectionModel().getSelectedItem();
			salesStaff.removeEmployee(employee);
			populateEmployeeTable();
		});
		context.getItems().add(menu);
		employeeTable.setContextMenu(context);
	}
	
	private void setupBranchComboBox() {
		  Client client = clientSelection.getValue();
	    if (client != null) {
	    ObservableList<Branch> branchlist = FXCollections.observableArrayList(); 
		ArrayList<Branch> branchs = client.getListOfBranch();
		branchlist.addAll(branchs);
		branchSelection.setItems(branchlist);
 	}
  }
	
  private void populateEmployeeTable() {
		  Branch branch = branchSelection.getValue();
			if (branch != null) {
			ObservableList<Employee> employeelist = FXCollections.observableArrayList(); 
			ArrayList<Employee> branchs = branch.getAllEmployee();
			employeelist.addAll(branchs);
			employeeTable.setItems(employeelist);
		  }
  }

  @FXML
  public void handleClientSelectionForEmployee(ActionEvent event) { 
	  setupBranchComboBox();
  }

  @FXML
  public void handleBranchSelectionForEmployee(ActionEvent event) { 
	  populateEmployeeTable();
  }
  
  @FXML
  public void handleAddNewEmployee(ActionEvent event) { 
	    Branch branch = branchSelection.getValue();
	    User user = new User();
	    user.setUserName(employeeUserName.getText());
	    user.setPassWord(employeePassword.getText());
		if (branch != null) {
		 String fname = employeeFName.getText();
		 String lname = employeeLName.getText();
         String email = employeeEmail.getText();
	     String phoneNumber = employeePhoneNumber.getText();
		 Employee employee = new Employee(fname,lname,email,phoneNumber,branch.getBranchID()); 
	     salesStaff.addNewEmployeeFor(user,employee);  
	     populateEmployeeTable();
	  }
  }

  // Crate section
  public void setupForManageingCrate() {
		crateIDCol.setCellValueFactory(new PropertyValueFactory<Crate, Integer>("CrateID"));
		crateContentCol.setCellValueFactory(new PropertyValueFactory<Crate, CrateContentType>("crateContentTypeEnum"));
		setupClientComboBox();
		ContextMenu context = new ContextMenu();
		MenuItem menu = new MenuItem("remove");
		menu.setOnAction((ActionEvent event) -> {
			Crate crate = crateTable.getSelectionModel().getSelectedItem();
			salesStaff.removeCrate(crate);
			
		});
		context.getItems().add(menu);
		setupWarehouseComboBox();
		crateTable.setContextMenu(context);
		crateSizeSelection.setItems(FXCollections.observableArrayList( CrateSize.values()));
		crateContentSelection.setItems(FXCollections.observableArrayList(CrateContentType.values()));
	}
  
  private void setupWarehouseComboBox() {
		ArrayList<WareHouse> warehouse = salesStaff.getWareHouseList();
		ObservableList<WareHouse> warehouselist = FXCollections.observableArrayList(); 
		warehouselist.addAll(warehouse);
		wareHouseSelection.setItems(warehouselist);
  }
  
   @FXML
   public void handleClientSelectionForCrate(ActionEvent event) { 
	   populateCrateTable();
   }
  
   private void populateCrateTable() {
	  Client client = clientSelection.getValue();
		if (client != null) {
		ObservableList<Crate> cartelist = FXCollections.observableArrayList(); 
		ArrayList<Crate> crates = client.getCrates(); 
		cartelist.addAll(crates);
		crateTable.setItems(cartelist);
	  }
   }
   
   @FXML 
   public void handleAddNewCrate(ActionEvent event) {  
	   Client client = clientSelection.getValue();
	   WareHouse warehouse = wareHouseSelection.getValue();
		if (client != null && warehouse != null) {
			String selfNumber = crateShelfNumber.getText(); 
			CrateSize size = crateSizeSelection.getValue();
			CrateContentType type = crateContentSelection.getValue();
			long millsec = System.currentTimeMillis();
			Date createdOn = new Date(millsec);
			Crate crate = new Crate(Integer.parseInt(selfNumber),client.getClientID(),warehouse.getOfficeID(),
					createdOn,CrateStatus.inStorage,size,type,false);
			salesStaff.addNewCrate(crate);
		}
   }
   
   @FXML 
   public void handleBackAction(ActionEvent event) { 
	   NavigationManager.getInstance().popScene();
   }
  
}

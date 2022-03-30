package testStorage.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import testStorage.Factory.ClientDashBoardFactory;
import testStorage.Model.Bill;
import testStorage.Model.Client;
import testStorage.Model.Crate;
import testStorage.Model.Employee;
import testStorage.Model.Order;
import testStorage.Model.OrderType;
import testStorage.Model.Staff;
import testStorage.View.NavigationManager;

public class ClientDashBoardViewController implements Initializable {
   
	@FXML 
	private ListView<Crate> listView;
	
	@FXML 
	private Label clientName;
	
	@FXML 
	private Label totalCrate;
	
	@FXML
	private Button ongoingOrders;
	
	@FXML 
	private Button billingButton;
	
	@FXML
    private Button crateButton;
	
	@FXML
    private Button orderButton;
	
	@FXML
    private AnchorPane billingPane;
	
	@FXML 
	private AnchorPane onGoingOrdersPane;
	
	@FXML 
	private AnchorPane ordersPane;
	
	@FXML 
	private TableView<Bill> billingTable; 
	
	@FXML
	private TableColumn<Bill , Boolean> dueBilling;
	
	@FXML
	private TableColumn<Bill, Double> amountBilling;
	
	@FXML
	private TableColumn<Bill, Integer> billingID;
	
	@FXML
	private TableColumn<Bill, Date> toDateBilling;
	
	@FXML
	private TableColumn<Bill, Date> fromDateBilling;
	
	@FXML
	private TableColumn<Bill, Staff> createdBy;
	
	@FXML 
	private TableView<Order> onGoingOrderTable; 
	
	@FXML
	private TableColumn<Order , Integer> onGoingOrderID;
	
	@FXML
	private TableColumn<Order, Date> onGoingExpectedDate;
	
	@FXML
	private TableColumn<Order, OrderType> onGoingType;
	
	@FXML
	private TableColumn<Order, Integer> onGoingCrateID;
	
	@FXML
	private TableColumn<Order, String> onGoingCrateStatus;
	
	@FXML
	private TableColumn<Order, String> onGoingPlacedBy;
	
	@FXML
	private TableColumn<Order, String> onGoingManagedBy;
	
	
	@FXML 
	private TableView<Order> orderTable; 
	
	@FXML
	private TableColumn<Order , Integer> orderID;
	
	@FXML
	private TableColumn<Order, Date> expectedDate;
	
	@FXML
	private TableColumn<Order, OrderType> type;
	
	@FXML
	private TableColumn<Order, Integer> crateID;
	
	@FXML
	private TableColumn<Order, Date> placedDate;
	
	@FXML
	private TableColumn<Order, String> placedBy;
	
	@FXML
	private TableColumn<Order, String> managedBy;
	
	@FXML
	private AnchorPane cratePane;
	
	private ClientDashBoardFactory clientDashBoardContainer; 
	
	public void setClientDashBoardContainer(ClientDashBoardFactory clientDashBoardContainer) {
		this.clientDashBoardContainer = clientDashBoardContainer;
	}

	private String activeButton = "-fx-text-fill: #f0a500;-fx-background-color: #37352e; -fx-background-radius: 0;";
	
	private String unactiveButton = "-fx-text-fill: #FFFFFF;-fx-background-color: #1B1A17; -fx-background-radius: 0;";
	
	private ObservableList<Crate> crateObservableList;
	
	private Employee employee; 
	
	private Client client;
    
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	public void getClientData() {
		client = employee.getBranch().getClient();
		setClientName(client);
		fetchClientCrates();
	}
	
	private void fetchClientBills() {
		 ObservableList<Bill> billingList = FXCollections.observableArrayList();
		 ArrayList<Bill> bills = client.getBilings();
		 billingList.addAll(bills);
		 dueBilling.setCellValueFactory(new PropertyValueFactory<Bill, Boolean>("paid"));
	     amountBilling.setCellValueFactory(new PropertyValueFactory<Bill, Double>("amountBilled"));
	     toDateBilling.setCellValueFactory(new PropertyValueFactory<Bill, Date>("toDate"));
	     fromDateBilling.setCellValueFactory(new PropertyValueFactory<Bill, Date>("fromDate"));
	     billingID.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("billID"));
	     createdBy.setCellValueFactory(new PropertyValueFactory<Bill, Staff>("createdBy"));
	     billingTable.setItems(billingList);
		 
	}
	
	private void fetchClientOnGoingOrders() {
		 ObservableList<Order> onGoingOrderList = FXCollections.observableArrayList();
		 ArrayList<Order> orders = client.getOrdersInProgress();
		 onGoingOrderList.addAll(orders);
		 onGoingOrderID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderID"));
		 onGoingExpectedDate.setCellValueFactory(new PropertyValueFactory<Order, Date>("expectedDate"));
		 onGoingCrateID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("crateID"));
		 onGoingType.setCellValueFactory(new PropertyValueFactory<Order, OrderType>("type"));
		 onGoingCrateStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
			    @Override
			    public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> order) {
			    	String status = order.getValue().getCrate().getCrateStatusEnum().toString();
			    	return new SimpleStringProperty(status);
			    }
	     });
		 onGoingPlacedBy.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
			    @Override
			    public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> order) {
			    	String address = order.getValue().getPlacedBy().getBranchAddress();
			    	return new SimpleStringProperty(address);
			    }
	     });
		 onGoingManagedBy.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
			    @Override
			    public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> order) {
			    	String name = order.getValue().getManagedby() == null ? "Not Yet Assigned" : order.getValue().getManagedby().toString();
			    	return new SimpleStringProperty(name);
			    }
	     });
	     onGoingOrderTable.setItems(onGoingOrderList);
	}
	
	private void fetchClientOrders() {
		 ObservableList<Order> orderList = FXCollections.observableArrayList();
		 ArrayList<Order> orders = client.getOrders();
		 orderList.addAll(orders);
		 orderID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderID"));
		 expectedDate.setCellValueFactory(new PropertyValueFactory<Order, Date>("expectedDate"));
		 crateID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("crateID"));
		 type.setCellValueFactory(new PropertyValueFactory<Order, OrderType>("type"));
		 placedDate.setCellValueFactory(new PropertyValueFactory<Order, Date>("createdDate"));
		 placedBy.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
			    @Override
			    public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> order) {
			    	String address = order.getValue().getPlacedBy().getBranchAddress();
			    	return new SimpleStringProperty(address);
			    }
	     });
		 managedBy.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {
			    @Override
			    public ObservableValue<String> call(TableColumn.CellDataFeatures<Order, String> order) {
			    	String name = order.getValue().getManagedby() == null ? "Not Yet Assigned" : order.getValue().getManagedby().toString();
			    	return new SimpleStringProperty(name);
			    }
	     });
	     orderTable.setItems(orderList);
	}
	
	private void fetchClientCrates() {
		crateObservableList =  FXCollections.observableArrayList();
	    ArrayList<Crate> crates = client.getCrates();
	    this.crateObservableList.addAll(crates);
	    setTotalNumberOfCrate(crates.size());
	    listView.setCellFactory(carteListView -> new CrateListViewCell(clientDashBoardContainer));
		listView.setItems(crateObservableList);
	}
	
	public void setClientName(Client client) {
	     this.clientName.setText(client.getClientName());
	}
	
	public void setTotalNumberOfCrate(int total) {
	     this.totalCrate.setText("Total Crate : "+ total);
	}
	
	
	@FXML 
	private void handleLogOutAsClient(ActionEvent event) { 
		NavigationManager.getInstance().popScene();
	}
	
	@FXML 
	private void handleTabSwitchToBilling(ActionEvent event) { 
		billingButton.setStyle(activeButton);
		crateButton.setStyle(unactiveButton);
		ongoingOrders.setStyle(unactiveButton);
		orderButton.setStyle(unactiveButton);
		billingPane.setVisible(true);
		cratePane.setVisible(false);
		ordersPane.setVisible(false);
		onGoingOrdersPane.setVisible(false);
		fetchClientBills();
	}
	
	@FXML 
	private void handleTabSwitchToOrders(ActionEvent event) { 
		crateButton.setStyle(unactiveButton);
		billingButton.setStyle(unactiveButton);
		ongoingOrders.setStyle(unactiveButton);
		orderButton.setStyle(activeButton);
		ordersPane.setVisible(true);
		cratePane.setVisible(false);
		onGoingOrdersPane.setVisible(false);
		billingPane.setVisible(false);
		fetchClientOrders();
	}
	
	@FXML 
	private void handleTabSwitchToCrate(ActionEvent event) { 
		crateButton.setStyle(activeButton);
		billingButton.setStyle(unactiveButton);
		ongoingOrders.setStyle(unactiveButton);
		orderButton.setStyle(unactiveButton);
		cratePane.setVisible(true);
		onGoingOrdersPane.setVisible(false);
		ordersPane.setVisible(false);
		billingPane.setVisible(false);
		fetchClientCrates();
	}
	
	@FXML 
	private void handleTabSwitchToOnGoingOrders(ActionEvent event) { 
		ongoingOrders.setStyle(activeButton);
		crateButton.setStyle(unactiveButton);
		billingButton.setStyle(unactiveButton);
		orderButton.setStyle(unactiveButton);
		onGoingOrdersPane.setVisible(true);
		cratePane.setVisible(false);
		ordersPane.setVisible(false);
		billingPane.setVisible(false);
		fetchClientOnGoingOrders();
	}
	

}

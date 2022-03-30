package testStorage.Controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import testStorage.Model.Branch;
import testStorage.Model.Crate;
import testStorage.Model.CrateStatus;
import testStorage.Model.Management;
import testStorage.Model.Order;
import testStorage.Model.OrderType;
import testStorage.View.NavigationManager;

public class OrderManagementController implements Initializable {
	
	@FXML
	private TableView<Order> orderTable;
	
	@FXML 
	private TableColumn<Order , Integer> orderID;
	
	@FXML 
	private TableColumn<Order , OrderType> orderType;
	
	@FXML 
	private TableColumn<Order , Integer> orderCrateID;
	
	@FXML 
	private TableColumn<Order , Date> orderExpected;
	
	@FXML 
	private TableColumn<Order , String> orderAddress;
	
	@FXML 
	private TableColumn<Order , String> orderCrateStatus;
	
	@FXML 
	private ComboBox<CrateStatus> orderStatus;

	private Management orderManager;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		orderID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("orderID"));
		orderType.setCellValueFactory(new PropertyValueFactory<Order, OrderType>("type"));
		orderCrateID.setCellValueFactory(new PropertyValueFactory<Order, Integer>("crateID"));
		orderExpected.setCellValueFactory(new PropertyValueFactory<Order, Date>("expectedDate"));
		orderAddress.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Order, String> arg) {
                Branch branch = arg.getValue().getPlacedBy();
				return new SimpleStringProperty(branch.getBranchAddress());
			}
			
		});
	}
	
	public void setOrdermanager(Management orderManager) {
		this.orderManager = orderManager;
	}
	
	
	public void populateOrderInProgress() {
		orderCrateStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Order, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Order, String> arg) {
                Crate crate = arg.getValue().getCrate();
				return new SimpleStringProperty(crate.getCrateStatusEnum().toString());
			}
			
		});
		ContextMenu context = new ContextMenu();
		MenuItem menu = new MenuItem("Completed");
		menu.setOnAction((ActionEvent event) -> {
			Order order = orderTable.getSelectionModel().getSelectedItem();
			orderManager.setOrderCompleted(order);
			orderInProgressTable();
		});
		context.getItems().add(menu);
		orderTable.setContextMenu(context);
		orderStatus.setItems(FXCollections.observableArrayList(CrateStatus.values()));
		orderInProgressTable();
	}

	private void orderInProgressTable() {
		ObservableList<Order> orders = FXCollections.observableArrayList(); 
		ArrayList<Order> orderlist = orderManager.getOrdersManageBy();
		orders.addAll(orderlist);
		orderTable.setItems(orders);
	}
	
	
	public void populatePendingOrder() {
		ContextMenu context = new ContextMenu();
		MenuItem menu = new MenuItem("Reject");
		menu.setOnAction((ActionEvent event) -> {
			Order order = orderTable.getSelectionModel().getSelectedItem();
			orderManager.rejectOrder(order);
			orderPendingTable();
		});
		context.getItems().add(menu);
		orderTable.setContextMenu(context);
		orderPendingTable();
	}

	private void orderPendingTable() {
		ObservableList<Order> orders = FXCollections.observableArrayList(); 
		ArrayList<Order> orderlist = orderManager.getPendingOrders();
		orders.addAll(orderlist);
		orderTable.setItems(orders);
	}
	
	@FXML
	public void handleUpdateingOrderStatus(ActionEvent event) {
		Order order = orderTable.getSelectionModel().getSelectedItem();
		CrateStatus status = orderStatus.getValue();
          if(order != null) {
        	  orderManager.setOrderStatusTo(status, order);
        	  orderInProgressTable();
          } 
	}
	
	@FXML
	public void handleAssignedOrder(ActionEvent event) {
		Order order = orderTable.getSelectionModel().getSelectedItem();
		System.out.print(order.getOrderID());
		if(order != null) {
			orderManager.setManageOrderFor(order);
			orderPendingTable();
		}
	}
	
	@FXML
	public void handleBackAction(ActionEvent event) {
		NavigationManager.getInstance().popScene();
	}
	
}

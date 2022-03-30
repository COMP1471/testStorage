package testStorage.Controller;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import testStorage.Model.Accounting;
import testStorage.Model.Bill;
import testStorage.Model.Client;
import testStorage.Model.Order;
import testStorage.View.NavigationManager;

public class AccountingController implements Initializable {
	
	
	@FXML 
	private TextField amount;
	
	@FXML 
	private DatePicker fromDate;
	
	@FXML 
	private DatePicker toDate;
	
	@FXML
	private TableView<Client> clientTable;
	
	@FXML
	private ComboBox<Client> clientSelection;
	
	@FXML 
	private TableColumn<Client , Integer> clientID;
	
	@FXML 
	private TableColumn<Client , String>  clientName;
	
	@FXML
	private TableView<Bill> billingTable;
	
	@FXML 
	private TableColumn<Bill , Integer> billingID;
	
	@FXML 
	private TableColumn<Bill, Double> billingAmount;
	
	@FXML 
	private TableColumn<Bill, Boolean> billingIsPaid;
		
	private Accounting accounts;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void setUpClientTable() {
		ObservableList<Client> clientList = FXCollections.observableArrayList(); 
		ArrayList<Client> clients = accounts.getAllClient();
		clientID.setCellValueFactory(new PropertyValueFactory<Client, Integer>("clientID"));
	    clientName.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
		clientList.addAll(clients);
		clientTable.setItems(clientList);
	}
	
	@FXML
	public void handleGrenrateBill(ActionEvent event) { 
		Client client = clientTable.getSelectionModel().getSelectedItem();
		if(client != null) {
			Double enterdAmount = Double.parseDouble(amount.getText());
			LocalDate fromDatePicker = fromDate.getValue();
			Instant fromInstant = fromDatePicker.atStartOfDay(ZoneId.systemDefault()).toInstant();	
			long fromtimeInMillis = fromInstant.toEpochMilli();
			Date from = new Date(fromtimeInMillis);
			LocalDate toDatePicker = toDate.getValue();
			Instant instant = toDatePicker.atStartOfDay(ZoneId.systemDefault()).toInstant();	
			long toTimeInMillis = instant.toEpochMilli();
			Date to = new Date(toTimeInMillis);
			Bill billing = new Bill(
					client.getClientID(),
					to,
					from,
				    enterdAmount,
				    false);
		 	accounts.generateBill(billing);	
		}
	}
	
	public Accounting getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounting accounts) {
		this.accounts = accounts;
	}
	
	//ChangingBillSTATUS
	
	public void setUpBillTable() {
	    billingID.setCellValueFactory(new PropertyValueFactory<Bill, Integer>("billID"));
	    billingAmount.setCellValueFactory(new PropertyValueFactory<Bill, Double>("amountBilled"));
	    billingIsPaid.setCellValueFactory(new PropertyValueFactory<Bill, Boolean>("paid"));
		ObservableList<Client> clientList = FXCollections.observableArrayList(); 
		ArrayList<Client> clients = accounts.getAllClient();
		clientList.addAll(clients);
		clientSelection.setItems(clientList);
	}
	
	@FXML
	public void handleClientSelection(ActionEvent event) { 
		Client client = clientSelection.getValue();
		ObservableList<Bill> billingList = FXCollections.observableArrayList(); 
		ArrayList<Bill> bills = client.getBilings();
		billingList.addAll(bills);
		billingTable.setItems(billingList);
	}
	
	@FXML
	public void handlePaidBill(ActionEvent event) { 
		Bill bill = billingTable.getSelectionModel().getSelectedItem();
		if (bill != null) {
		accounts.setBillStateToPaid(bill);
	  }
    }
	
	
	@FXML 
	public void handleBackAction(ActionEvent event) {
		NavigationManager.getInstance().popScene();
	}
}

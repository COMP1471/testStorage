package testStorage.View.CrateDetail;

import java.io.IOException;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import testStorage.DependencyContainer.ClientDashBoardDependencyContainer;
import testStorage.Model.Branch;
import testStorage.Model.Content;
import testStorage.Model.Crate;
import testStorage.Model.CrateStatus;
import testStorage.Model.Employee;
import testStorage.Model.Manager;
import testStorage.Model.Order;
import testStorage.View.NavigationManager;

public class CrateDetailController implements Initializable  {

	private Crate crate;

	private Employee employee;

	@FXML
	private Label crateID;
	
	@FXML
	private Label status;
	
	@FXML
	private Label wareHouse;
	
	@FXML
	private Label contentType;
	
	@FXML
	private Label size;
	
	@FXML
	private Button requestCrate;
	
	@FXML
	private Button collectCrate;
	
	@FXML
	private DatePicker expectedDate;
	
	@FXML 
	private DatePicker collectionDate;
	
	@FXML 
	private TableView<Content> contentTable; 
	
	@FXML 
	private VBox requestedDetail;
	
	@FXML 
	private Label requestedBy; 
	
	@FXML 
	private TableColumn<Content, String> contentName;
	
	@FXML 
	private TableColumn<Content, Integer> contentID;
	
	private ClientDashBoardDependencyContainer clientDashBoardContainer; 
	
	public void setClientDashBoardContainer(ClientDashBoardDependencyContainer clientDashBoardContainer) {
		this.clientDashBoardContainer = clientDashBoardContainer;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	private void fetchContentOfCrate() {
	   ObservableList<Content> contentList = FXCollections.observableArrayList();
	   System.out.print(crate.getCrateID());
	   ArrayList<Content> contents = crate.getContent();
	   contentID.setCellValueFactory(new PropertyValueFactory<Content, Integer>("contentID"));
	   contentName.setCellValueFactory(new PropertyValueFactory<Content, String>("contentName"));
	   contentList.addAll(contents);
	   contentTable.setItems(contentList);
	}
	
	public void setCrate(Crate crate) {
		this.crate = crate;
		crateID.setText("Crate ID : " + crate.getClientID());
		status.setText(crate.getCrateStatusEnum().toString());
		wareHouse.setText(" Warehouse : " + crate.getWareHouseID());
		contentType.setText(" Content Type : " + crate.getCrateContentTypeEnum().toString());
		size.setText(crate.getCrateSizeEnum().toString());
		fetchContentOfCrate();
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public void typeOfRequestCheck() {
		    Order currentOrder = crate.getLastestOrder();
		    System.out.print(crate.getCrateStatusEnum().toString());
	      	switch (crate.getCrateStatusEnum()) {
	        	case inStorage,withClient :
	        	    if (crate.getCrateStatusEnum() == CrateStatus.inStorage) {
	        	               if ((currentOrder.getIsCompleted())&&(employee instanceof Manager)) {
	        	            	   setCrateRequestMode();
	        	               } else {
	        	            	   setInTrasitMode(currentOrder);
	        	               }
	        	    } else {
	        	    	if ((currentOrder.getBranchID() == employee.getBranchID())&&(currentOrder.getIsCompleted())) {
	        	    		 setCrateCollectMode();
	        	    	} else {
	        	    		 setInTrasitMode(currentOrder);
	        	    	}
	        	    }
	        	    break;
	        	case beingDelivered,waitingToBeDelivered:
	        		setInTrasitMode(currentOrder);
	        		break;
	      	}
	}

	
	private void setCrateRequestMode() {
	      requestCrate.setVisible(true);
	      expectedDate.setVisible(true);
	      collectionDate.setVisible(false);
	      collectCrate.setVisible(false);
	      requestedDetail.setVisible(false);
	}
	
	private void setCrateCollectMode() {
		   collectionDate.setVisible(true);
		   collectCrate.setVisible(true);
		   requestCrate.setVisible(false);
		   expectedDate.setVisible(false);
		   requestedDetail.setVisible(false);
	}
	
	private void setInTrasitMode(Order order) {
		requestedDetail.setVisible(true);
		requestCrate.setVisible(false);
		expectedDate.setVisible(false);
		collectionDate.setVisible(false);
	    collectCrate.setVisible(false);
	    Branch branch = order.getPlacedBy();
	    requestedBy.setText( branch.getBranchAddress() + " \n " + branch.getBranchPostcode());
	}
 	
	@FXML 
	private void handleBackAction(ActionEvent event) { 
		NavigationManager.getInstance().popScene();
	}
	
	@FXML 
	private void handleRequestCrate(ActionEvent event) { 
		Manager manager = (Manager) employee;
		LocalDate localDate =  expectedDate.getValue();
		Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();	
		long timeInMillis = instant.toEpochMilli();
		Boolean requestSent = manager.deliveryRequestFor(crate,new Date(timeInMillis));
		if (requestSent) {
			requestCrate.setDisable(true);
			requestCrate.setText("Request Sent");
			requestCrate.setStyle("-fx-text-fill: white; -fx-background-color: #008000; -fx-background-radius: 0;");
		}
	}
	
	@FXML 
	private void handleCollectCrate(ActionEvent event) throws IOException { 
		LocalDate localDate =  collectionDate.getValue();
		Instant instant = localDate.atStartOfDay(ZoneId.systemDefault()).toInstant();	
		long timeInMillis = instant.toEpochMilli();
		Scene contentUpdate = this.clientDashBoardContainer.showCrateContentUpdate(crate, new Date(timeInMillis));
		NavigationManager.getInstance().pushNewScene(contentUpdate);
	}
}

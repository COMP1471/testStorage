package testStorage.Controller;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import testStorage.Model.Content;
import testStorage.Model.Crate;
import testStorage.Model.Employee;
import testStorage.View.NavigationManager;

public class ContentUpdateController implements Initializable  { 

	@FXML
	private TableView<Content> contentTable;
	
	@FXML 
	private TableColumn<Content, String> contentName;
	
	@FXML 
	private TableColumn<Content, Integer> contentID;
	
	@FXML
	private TextField FileName;
	
	private Date onDate;
	
	private Crate crate;
	
	private Employee employee;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
		MenuItem menuitem = new MenuItem("Delete");
		menuitem.setOnAction((ActionEvent event) -> {
		    System.out.println("Menu item 1");
		    Content item = contentTable.getSelectionModel().getSelectedItem();
		    item.remove();
		    setTableOfContent();
		});

		ContextMenu menu = new ContextMenu();
		menu.getItems().add(menuitem);
		contentTable.setContextMenu(menu);
    }
	
	private void setTableOfContent() {
           ObservableList<Content> contentList = FXCollections.observableArrayList();	
           ArrayList<Content> contents = crate.getContent();
           contentList.addAll(contents);
    	   contentID.setCellValueFactory(new PropertyValueFactory<Content, Integer>("contentID"));
    	   contentName.setCellValueFactory(new PropertyValueFactory<Content, String>("contentName"));
           contentTable.setItems(contentList);
	}

	public void setOnDate(Date onDate) {
		this.onDate = onDate;
	}
	
	public void setCrate(Crate crate) {
		this.crate = crate;
		setTableOfContent();
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@FXML 
	private void handlePlaceCollectionRequest(ActionEvent event) { 
		Boolean done = employee.collectRequestFor(crate, onDate);
		if (done) {
			NavigationManager.getInstance().popScenes(2);
		}
	}
	

	@FXML 
	private void handleBackAction(ActionEvent event) { 
		NavigationManager.getInstance().popScene();
	}

	@FXML 
	private void handleAddFile(ActionEvent event) {
		String filename = this.FileName.getText(); 
		if (filename != null ) {
		Content content = new Content(filename);
		crate.addContent(content);
		setTableOfContent();
 	 }
  }
	
}
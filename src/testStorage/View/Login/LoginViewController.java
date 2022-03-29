package testStorage.View.Login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import testStorage.DependencyContainer.LoginDependencyContainer;
import testStorage.Model.Employee;
import testStorage.Model.Staff;
import testStorage.Model.User;
import testStorage.View.NavigationManager;

public class LoginViewController implements Initializable {

	final private String activeStyle = "-fx-text-fill: #000000; -fx-background-radius: 35; -fx-background-color: #F0A500;";
	final private String unactiveStyle = "-fx-text-fill: #FFFFFF; -fx-background-radius: 35; -fx-background-color: #1C00ff00;";

	private LoginDependencyContainer loginContainer; 
	
	@FXML
	private Button clientButton;
	
	@FXML
	private Button staffButton;
	
	@FXML
	private TextField userName;
	
	@FXML
	private TextField passWord;
	
	@FXML
	private Label errorMessage;
	
	private LoginType type = LoginType.staff;
	
	
	public enum LoginType {
		client,
		staff
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	
	
	@FXML
	private void handleLoginButtonAction(ActionEvent event) throws IOException {
		  Button button =  (Button) event.getSource();
		  User user = getUser();
		  switch(type) {
		   case client: 
			   if (user.isAValidEmployee()) {
			   loadClientDashboard(button);
			   } else {
				   errorMessage.setText("wrong username / password");
				   errorMessage.setOpacity(1); 
			   }
			   break;
		   case staff: 
			   if (user.isAValidStaff()) {
			   loadStaffDashboard(button);
			   } else {
				   errorMessage.setText("wrong username / password");
				   errorMessage.setOpacity(1);  
			   }
			   break;
		 }
	}
	
	private void loadClientDashboard(Button signUpButton) throws IOException {
		User user = getUser();
		Employee employee = user.getEmplyoee();
	    Scene scene = loginContainer.showClientDashBoad(employee);
	    NavigationManager.getInstance().pushNewScene(scene);
	}
	
	public void setContainer(LoginDependencyContainer container) {
		this.loginContainer = container;
	}
	
	private void loadStaffDashboard(Button signUpButton) throws IOException {
		User user = getUser();
		Staff staff = user.getStaff();
	    Scene scene = loginContainer.showStaffDashBoad(staff);
	    NavigationManager.getInstance().pushNewScene(scene);
	}


	private User getUser() {
		User user = new User();
		user.setUserName(userName.getText());
		user.setPassWord(passWord.getText());
		return user;
	}
	  
    @FXML 
	private void handleLoginAsClient(ActionEvent event) {
       type = LoginType.client;
       clientButton.setStyle(activeStyle);
	   staffButton.setStyle(unactiveStyle);
	 }
	  
	 @FXML
	 private void handleLoginAsStaff(ActionEvent event) {
	     type = LoginType.staff;
		 staffButton.setStyle(activeStyle);
		 clientButton.setStyle(unactiveStyle);
	 }

}

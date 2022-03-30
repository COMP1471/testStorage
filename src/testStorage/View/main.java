package testStorage.View;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import testStorage.Controller.LoginViewController;
import testStorage.Factory.ControllerFactory;

public class main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		    ControllerFactory loginContainer = new ControllerFactory(); 
		    Scene scene = loginContainer.loadView();
		    NavigationManager.getInstance().setPrimaryStage(primaryStage);
	        primaryStage.setTitle("PackFord Storage");
	        NavigationManager.getInstance().pushNewScene(scene);
	        primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}

package testStorage.View;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import testStorage.DependencyContainer.LoginDependencyContainer;
import testStorage.View.Login.LoginViewController;

public class main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		    LoginDependencyContainer loginContainer = new LoginDependencyContainer(); 
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

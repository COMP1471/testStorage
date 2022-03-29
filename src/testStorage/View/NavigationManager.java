package testStorage.View;

import java.util.Stack;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class NavigationManager {
      
	  private Stage primaryStage; 
      
	  public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	private static NavigationManager shared; 
	  
	  private Stack<Scene> screenStack;
      
	  public NavigationManager() {
           screenStack = new Stack<Scene>();
      }
      
	 static public NavigationManager getInstance() { 
		  if (shared == null) {
			  shared = new NavigationManager();
			  return shared;
		  } else {
			 return shared;
		  }
	  }
     
      public void pushNewScene(Scene scene) {
    	  screenStack.push(scene);
    	  primaryStage.setScene(scene);
      }
      
      public void popScenes(int count) {
    	  for(int i = 0 ; i < count ; i++ ) {
        	  screenStack.pop();  
    	  }
    	  Scene scene = screenStack.peek();
    	  primaryStage.setScene(scene);
      }
      
      public void popScene() {
    	  screenStack.pop();
    	  Scene scene = screenStack.peek();
    	  primaryStage.setScene(scene);
      }
}

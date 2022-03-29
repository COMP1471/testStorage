package testStorage.View.ClientCrateListing;
import testStorage.DependencyContainer.ClientDashBoardDependencyContainer;
import testStorage.Model.Crate;
import testStorage.View.NavigationManager;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
public class CrateListViewCell extends ListCell<Crate> {
	
	@FXML
	private Label crateID;
	
	@FXML
	private Label crateType;
	
	@FXML 
	private Label warehouse;
	
	@FXML
	private Label status;
	
	@FXML
	private AnchorPane anchorPane;
	
	private Crate crate;
	
	private FXMLLoader mLLoader;
	
	private ClientDashBoardDependencyContainer clientDashBoardContainer; 
	
	private String stillStyle = "-fx-text-fill: #FFFFFF; -fx-background-radius: 35; -fx-background-color: #1C00ff00;";
	private String intransitStyle = "-fx-text-fill: #000000; -fx-background-radius: 35; -fx-background-color: #F0A500;"; 
	
	
	 public CrateListViewCell(ClientDashBoardDependencyContainer clientDashBoardContainer){
	      this.clientDashBoardContainer = clientDashBoardContainer;
	 }
	
	@Override
    protected void updateItem(Crate crate, boolean empty) { 
		if(empty || crate == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("CrateItem.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            this.setCrate(crate);
            this.crateID.setText("Crate ID : "+ crate.getCrateID());
            this.crateType.setText("Content Type : "+ crate.getCrateContentTypeEnum().toString());
            this.warehouse.setText("Warehouse No : "+ crate.getWareHouseID());
            
            switch (crate.getCrateStatusEnum()) {
              case beingDelivered,waitingToBeDelivered : 
            	  status.setStyle(intransitStyle);
            	  break;
              case withClient,inStorage:
            	  status.setStyle(stillStyle);
            	  break;
            }
            status.setText(crate.getCrateStatusEnum().toString());
            setText(null);
            setGraphic(anchorPane);
        }
	}
	
	public void setCrate(Crate crate) {
		this.crate = crate;
	}
	
	@FXML 
	public void handleMouseClick(MouseEvent arg0) throws IOException {
		if (crate != null) {
			 Scene scene = clientDashBoardContainer.showCrateDetail(crate);
			 NavigationManager.getInstance().pushNewScene(scene);
		   }
	}
}

package OtrioOL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;

public class PreGameWindow {
	
	public static void displyWindow(){
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Game Preference");
		
		//Layout
		BorderPane layout = new BorderPane();
		
		window.setScene(new Scene(layout, 400, 300));
		window.show();
		
		
		
	}
	

}

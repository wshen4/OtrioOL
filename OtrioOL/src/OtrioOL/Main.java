package OtrioOL;

import javafx.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;


public class Main extends Application{
	Stage window;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		window = arg0;
		window.setTitle("Otrio Online");
		
		//Button
		Button startButton = new Button("Start New Game");
		
		//Imageview
		Image welcomeImg = new Image("file:src/OtrioOL/Otrio.jpg", true);
		ImageView imv = new ImageView();
		imv.setImage(welcomeImg);
		
		
		
		//different layers of BorderPane
		BorderPane layout = new BorderPane();
		
			BorderPane bottomLayout = new BorderPane();
			bottomLayout.setCenter(startButton);
		layout.setBottom(bottomLayout);
		layout.setCenter(imv);
		
		
		window.setScene(new Scene(layout, 700, 500));
		window.setResizable(false);
		window.show();	
		
	}
}
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;


public class Main extends Application{
	static Stage window;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	
	//function for actual gaming
	private static void startGame(){
		//setup new scene
		BorderPane gameLayout = new BorderPane();
			Button backButton = new Button("Back");
			backButton.setOnAction(e -> {
				goToWelcome();
			});
		gameLayout.setTop(backButton);
		Scene gameScene = new Scene(gameLayout, 900, 700);
		window.setScene(gameScene);
		window.show();
		
	}
	
	//Function for navigating to the welcome scene
	private static void goToWelcome(){
		window.setTitle("Otrio Online");
		//play audio
		
		
		
		//Button
		Button startButton = new Button("Start New Game");
		startButton.setOnAction(e -> {
			PreGameWindow.displyWindow();
			//new Scene for actual gaming
			startGame();
		});
		
		//Imageview
		Image welcomeImg = new Image("file:src/OtrioOL/Media/Image/Otrio.jpg", true);
		ImageView imv = new ImageView();
		imv.setImage(welcomeImg);
		
		
		//different layers of BorderPane
		BorderPane layout = new BorderPane();
		
			BorderPane bottomLayout = new BorderPane();
			bottomLayout.setCenter(startButton);
		layout.setBottom(bottomLayout);
		layout.setCenter(imv);
		
		
		window.setScene(new Scene(layout, 900, 700));
		window.setResizable(false);
		window.show();	
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		window = arg0;
		goToWelcome();
		
		
	}
	
	
}
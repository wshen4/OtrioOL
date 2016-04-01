package OtrioOL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;	

import java.io.*;

public class Main extends Application{
	static Stage window;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	
	//function for actual gaming
	private static void setting(){
		//setup new scene
		BorderPane settingLayout = new BorderPane();
		Button backButton = new Button("Back");
		Button oneVsOne = new Button("1 VS 1 Local");
		backButton.setOnAction(e -> {
			goToWelcome();
		});
		oneVsOne.setOnAction(e -> {
			LocalPeopleGames();
		});
		settingLayout.setCenter(oneVsOne);
		settingLayout.setTop(backButton);
		settingLayout.setPadding(new Insets(50,50,50,50));
		
		Scene gameScene = new Scene(settingLayout, 900, 700);
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
			//new Scene for actual gaming
			setting();
		});
		
		//Imageview
		Image welcomeImg = new Image("file:OtrioOL/src/OtrioOL/Media/Image/Otrio.jpg",true);
		ImageView imv = new ImageView();
		imv.setImage(welcomeImg);
		imv.setFitHeight(600);
		imv.setFitWidth(900);
		
		//change to gridpane
		BorderPane layout = new BorderPane();
		layout.setPadding(new Insets(0,0,50,0));
		
			BorderPane bottomLayout = new BorderPane();
			bottomLayout.setCenter(startButton);
		
		
		layout.setCenter(imv);
		layout.setBottom(bottomLayout);
		
		
		window.setScene(new Scene(layout, 900, 700));
		window.setResizable(false);
		window.show();	
	}
	
	//Human 1 v 1 game locally
	private static void LocalPeopleGames(){
		//
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		window = arg0;
		goToWelcome();
				
	}
	
	
}
package OtrioOL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;	

import java.io.*;
import java.util.ArrayList;

public class Main extends Application{
	static Stage window;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	
	//Human 1 v 1 game locally
	private static void LocalPeopleGames(){
		
		//order of move
		boolean playerOneMove = true;
		
		
		//Refresh game objects
		Chessboard board = new Chessboard();
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		
		
		
		//gaming layout
		BorderPane gameLayout =  new BorderPane();
		
		//back button
		Button backButton = new Button("Quit Game");
		backButton.setOnAction(e -> {
			goToWelcome();
		});
		backButton.setEffect(new DropShadow());
		
		//build the visual of chessboard
		HBox boardBack = new HBox(30);
		VBox col1 = new VBox(30);
		VBox col2 = new VBox(30);
		VBox col3 = new VBox(30);
		
		ArrayList<Label> chessPlace = new ArrayList<Label>();
		
		for (int i = 0; i < 9; i ++){
			chessPlace.add(new Label(Integer.toString(board.getBoard(0).get(0)) + 
				Integer.toString(board.getBoard(0).get(1)) + Integer.toString(board.getBoard(0).get(2))));
		}
		
		col1.getChildren().add(chessPlace.get(0));
		col2.getChildren().add(chessPlace.get(1));
		col3.getChildren().add(chessPlace.get(2));
		
		col1.getChildren().add(chessPlace.get(3));
		col2.getChildren().add(chessPlace.get(4));
		col3.getChildren().add(chessPlace.get(5));
		
		col1.getChildren().add(chessPlace.get(6));
		col2.getChildren().add(chessPlace.get(7));
		col3.getChildren().add(chessPlace.get(8));
		
		
		boardBack.getChildren().add(col1);
		boardBack.getChildren().add(col2);
		boardBack.getChildren().add(col3);
		boardBack.setPadding(new Insets(100, 100, 100, 100));
		
		
		//For Player 1
		Button makeMoveButton2 = new Button("Make Your Move");
		makeMoveButton2.setVisible(false);
		
		Label playerLabel1 = new Label("Player " + Integer.toString(player1.getId()));
		Label smallChessLabel1 = new Label("Small " + Integer.toString(player1.getSchess()));
		Label medChessLabel1 = new Label("Medium " + Integer.toString(player1.getMchess()));
		Label largeChessLabel1 = new Label("Large " + Integer.toString(player1.getLchess()));
		
		TextField positionText = new TextField();
		TextField chessText = new TextField();
		
		Button makeMoveButton = new Button("Make Your Move");
		makeMoveButton.setOnAction(e -> {
			try{
				int chosenPos = Integer.parseInt(positionText.getText());
				int chessType = Integer.parseInt(chessText.getText());
				
				if (chosenPos < 9 && chosenPos >= 0 && chessType < 3 && chessType >= 0
						&& board.putChess(player1, chosenPos, chessType)){
					board.putChess(player1, chosenPos, chessType);
					smallChessLabel1.setText("Small " + Integer.toString(player1.getSchess()));
					medChessLabel1.setText("Medium " + Integer.toString(player1.getMchess()));
					largeChessLabel1.setText("Large " + Integer.toString(player1.getLchess()));
					
					for (int i = 0; i < 9; i++)
						chessPlace.get(i).setText(Integer.toString(board.getBoard(i).get(0)) + 
							Integer.toString(board.getBoard(i).get(1)) + Integer.toString(board.getBoard(i).get(2)));
					makeMoveButton.setVisible(false);
					makeMoveButton2.setVisible(true);
				}
			}catch(NumberFormatException nfe){
				//System.err.println("Wrong input type");
			}
			
		});
		
		//Layout for player 1
		VBox player1Layout = new VBox(10);
		player1Layout.setPadding(new Insets(100, 0, 0, 20));
		player1Layout.getChildren().add(playerLabel1);
		player1Layout.getChildren().add(smallChessLabel1);
		player1Layout.getChildren().add(medChessLabel1);
		player1Layout.getChildren().add(largeChessLabel1);
		player1Layout.getChildren().add(new Label("Input Type of Chess:"));
		player1Layout.getChildren().add(chessText);
		player1Layout.getChildren().add(new Label("Input Position:"));
		player1Layout.getChildren().add(positionText);
		player1Layout.getChildren().add(makeMoveButton);
		
		//For Player2
		Label playerLabel2 = new Label("Player " + Integer.toString(player2.getId()));
		Label smallChessLabel2 = new Label("Small " + Integer.toString(player2.getSchess()));
		Label medChessLabel2 = new Label("Medium " + Integer.toString(player2.getMchess()));
		Label largeChessLabel2 = new Label("Large " + Integer.toString(player2.getLchess()));
		
		TextField positionText2 = new TextField();
		TextField chessText2 = new TextField();
		
		
		makeMoveButton2.setOnAction(e -> {
			try{
				int chosenPos2 = Integer.parseInt(positionText2.getText());
				int chessType2 = Integer.parseInt(chessText2.getText());
				if (chosenPos2 < 9 && chosenPos2 >= 0 && chessType2 < 3 && chessType2 >= 0
						&& board.putChess(player2, chosenPos2, chessType2)){
					board.putChess(player2, chosenPos2, chessType2);
					smallChessLabel2.setText("Small " + Integer.toString(player2.getSchess()));
					medChessLabel2.setText("Medium " + Integer.toString(player2.getMchess()));
					largeChessLabel2.setText("Large " + Integer.toString(player2.getLchess()));
					for (int i = 0; i < 9; i++)
						chessPlace.get(i).setText(Integer.toString(board.getBoard(i).get(0)) + 
							Integer.toString(board.getBoard(i).get(1)) + Integer.toString(board.getBoard(i).get(2)));
					makeMoveButton2.setVisible(false);
					makeMoveButton.setVisible(true);
				}
			}catch(NumberFormatException nfe){
				//System.err.println("Wrong input type");
			}
			
		});
		
		//Layout for player 2
		VBox player2Layout = new VBox(10);
		player2Layout.setPadding(new Insets(100, 0, 0, 20));
		player2Layout.getChildren().add(playerLabel2);
		player2Layout.getChildren().add(smallChessLabel2);
		player2Layout.getChildren().add(medChessLabel2);
		player2Layout.getChildren().add(largeChessLabel2);
		player2Layout.getChildren().add(new Label("Input Type of Chess:"));
		player2Layout.getChildren().add(chessText2);
		player2Layout.getChildren().add(new Label("Input Position:"));
		player2Layout.getChildren().add(positionText2);
		player2Layout.getChildren().add(makeMoveButton2);
		
		
		//Finalize game layout
		gameLayout.setPadding(new Insets(10, 10, 10, 10));
		gameLayout.setTop(backButton);
		gameLayout.setLeft(player1Layout);
		gameLayout.setRight(player2Layout);
		gameLayout.setCenter(boardBack);
		
		
		Scene gameScene = new Scene(gameLayout, 900, 700);
		window.setScene(gameScene);
		window.show();
			
	}
	
	//function for game setting
	private static void setting(){
		//setup new scene
		BorderPane settingLayout = new BorderPane();
		//Buttons
		//back
		Button backButton = new Button("Back");
		backButton.setEffect(new DropShadow());
		backButton.setOnAction(e -> {
			goToWelcome();
		});
			
		//local game
		Button oneVsOne = new Button("Player VS Player Local");
		oneVsOne.setEffect(new DropShadow());
		oneVsOne.setOnAction(e -> {
			LocalPeopleGames();
		});
		//VBox Layout
		VBox menuLayout = new VBox(40);
		menuLayout.setPadding(new Insets(200,50,50,350));
			
		menuLayout.getChildren().add(oneVsOne);
		menuLayout.getChildren().add(backButton);
			
		settingLayout.setCenter(menuLayout);
		//settingLayout.setPadding(new Insets(50,50,50,50));
		
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
			//new Scene for game setting
			setting();
		});
		startButton.setEffect(new DropShadow());
		
		
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
	
	

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		window = arg0;
		goToWelcome();
				
	}
	
	
}
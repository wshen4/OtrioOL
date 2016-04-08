package OtrioOL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;	

import java.io.*;
import java.util.ArrayList;

public class Main extends Application{
	private static Stage window;
	private static enum AI{
		EASY, MEDIUM, HARD;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	
	//Human 1 v 1 game locally
	private static void LocalPeopleGamesDemo(){
		
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
	private static void LocalPeopleGames(String player1Name, String player2Name, Boolean player1GoesFirst){
		
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
		
		//set the gap of width and height of the board
		HBox boardBack = new HBox(20);
		VBox col1 = new VBox(0);
		VBox col2 = new VBox(0);
		VBox col3 = new VBox(0);
		
		//Circles
		ArrayList<ArrayList<Circle>> circles = new ArrayList<ArrayList<Circle>>();
		for (int i = 0; i < 9; i ++){
			ArrayList<Circle> tmp = new ArrayList<Circle>();
			tmp.add(new Circle(50, Color.GREY));
			tmp.add(new Circle(30, Color.GREY));
			tmp.add(new Circle(10, Color.GREY));
			circles.add(tmp);
		}
		
		ArrayList<StackPane> chessPlace = new ArrayList<StackPane>();
		for (int i = 0; i < 9; i++){
			StackPane tmp = new StackPane();
			tmp.getChildren().addAll(circles.get(i).get(0), new Circle(40, Color.WHITESMOKE),
					circles.get(i).get(1), new Circle(20, Color.WHITESMOKE), circles.get(i).get(2));
			chessPlace.add(tmp);
		}
		
		//Radio Buttons Group
		ToggleGroup selectPos = new ToggleGroup();
		
		ArrayList<RadioButton> posNum = new ArrayList<RadioButton>();
		for (int i = 0; i < 9; i++){
			RadioButton tmp = new RadioButton();
			tmp.setToggleGroup(selectPos);
			tmp.setUserData(i);
			posNum.add(tmp);
		}
		posNum.get(0).setSelected(true);
		
		
		//Add to frame
		col1.getChildren().add(chessPlace.get(0));
		col1.getChildren().add(posNum.get(0));
		col2.getChildren().add(chessPlace.get(1));
		col2.getChildren().add(posNum.get(1));
		col3.getChildren().add(chessPlace.get(2));
		col3.getChildren().add(posNum.get(2));
		
		col1.getChildren().add(chessPlace.get(3));
		col1.getChildren().add(posNum.get(3));
		col2.getChildren().add(chessPlace.get(4));
		col2.getChildren().add(posNum.get(4));
		col3.getChildren().add(chessPlace.get(5));
		col3.getChildren().add(posNum.get(5));
		
		col1.getChildren().add(chessPlace.get(6));
		col1.getChildren().add(posNum.get(6));
		col2.getChildren().add(chessPlace.get(7));
		col2.getChildren().add(posNum.get(7));
		col3.getChildren().add(chessPlace.get(8));
		col3.getChildren().add(posNum.get(8));
		
		boardBack.getChildren().add(col1);
		boardBack.getChildren().add(col2);
		boardBack.getChildren().add(col3);
		boardBack.setPadding(new Insets(50, 50, 50, 50));
		
		Label sayWin = new Label();
		
		
		
		//check play order
		Button makeMoveButton = new Button("Make Your Move");
		Button makeMoveButton2 = new Button("Make Your Move");
		
		makeMoveButton.setVisible(false);
		makeMoveButton2.setVisible(false);
		
		if (player1GoesFirst)
			makeMoveButton.setVisible(true);
		else
			makeMoveButton2.setVisible(true);
		
		
		//For Player 1
		Label playerLabel1 = new Label("Player " + Integer.toString(player1.getId()));
		
		//TextField chessText = new TextField();
		//Chess Type Selection Radio Button Group
		ToggleGroup player1Group = new ToggleGroup();
		RadioButton smallRB = new RadioButton("Small " + Integer.toString(player1.getSchess()));
		smallRB.setToggleGroup(player1Group);
		smallRB.setUserData(0);
		RadioButton mediumRB = new RadioButton("Medium " + Integer.toString(player1.getMchess()));
		mediumRB.setToggleGroup(player1Group);
		mediumRB.setUserData(1);
		RadioButton largeRB = new RadioButton("Large " + Integer.toString(player1.getLchess()));
		largeRB.setToggleGroup(player1Group);
		largeRB.setUserData(2);
		smallRB.setSelected(true);
		
		
		
		makeMoveButton.setOnAction(e -> {
			try{
				int chosenPos = (int) selectPos.getSelectedToggle().getUserData();
				int chessType = (int) player1Group.getSelectedToggle().getUserData();
				
				if (board.putChess(player1, chosenPos, chessType)){
					board.putChess(player1, chosenPos, chessType);
					smallRB.setText("Small " + Integer.toString(player1.getSchess()));
					mediumRB.setText("Medium " + Integer.toString(player1.getMchess()));
					largeRB.setText("Large " + Integer.toString(player1.getLchess()));
					
					for (int i = 0; i < 9; i++){
						
						for (int j = 0; j < 3; j++){
							if (board.getBoard(i).get(j) == player1.getId())
								circles.get(i).get(2 - j).setFill(Color.DEEPPINK);
						}
					}
					
					makeMoveButton.setVisible(false);
					makeMoveButton2.setVisible(true);
					
					if (board.checkWin(player1)){
						sayWin.setText(player1Name + " Win!");
						makeMoveButton.setVisible(false);
						makeMoveButton2.setVisible(false);
					}
					
					if (!player1.checkInvt(0) && !player1.checkInvt(1) && !player1.checkInvt(2)
							&& !board.checkWin(player1) && !board.checkWin(player2)){
						sayWin.setText(player1Name + " " + player2Name + " " + "have a tie!");
						makeMoveButton.setVisible(false);
						makeMoveButton2.setVisible(false);
					}
					
				}
			}catch(NumberFormatException nfe){
				//System.err.println("Wrong input type");
			}
			
		});
		
		//Layout for player 1
		Image p1Img = new Image("file:OtrioOL/src/OtrioOL/Media/Image/p1.png",true);
		ImageView p1Imv = new ImageView();
		p1Imv.setImage(p1Img);
		p1Imv.setFitWidth(45);
		p1Imv.setFitHeight(35);
		
		VBox player1Layout = new VBox(20);
		player1Layout.setPadding(new Insets(100, 0, 0, 0));
		
		player1Layout.getChildren().add(new Label(player1Name));
		player1Layout.getChildren().add(p1Imv);
		player1Layout.getChildren().add(smallRB);
		player1Layout.getChildren().add(mediumRB);
		player1Layout.getChildren().add(largeRB);
		player1Layout.getChildren().add(makeMoveButton);
		
		//For Player2
		Label playerLabel2 = new Label("Player " + Integer.toString(player2.getId()));
		
		//Chess Type Selection Radio Button Group
		ToggleGroup player2Group = new ToggleGroup();
		RadioButton smallRB2 = new RadioButton("Small " + Integer.toString(player2.getSchess()));
		smallRB2.setToggleGroup(player2Group);
		smallRB2.setUserData(0);
		RadioButton mediumRB2 = new RadioButton("Medium " + Integer.toString(player2.getMchess()));
		mediumRB2.setToggleGroup(player2Group);
		mediumRB2.setUserData(1);
		RadioButton largeRB2 = new RadioButton("Large " + Integer.toString(player2.getLchess()));
		largeRB2.setToggleGroup(player2Group);
		largeRB2.setUserData(2);
		smallRB2.setSelected(true);
		
		
		makeMoveButton2.setOnAction(e -> {
			try{
				int chosenPos2 = (int) selectPos.getSelectedToggle().getUserData();
				int chessType2 = (int) player2Group.getSelectedToggle().getUserData();
				if (board.putChess(player2, chosenPos2, chessType2)){
					board.putChess(player2, chosenPos2, chessType2);
					smallRB2.setText("Small " + Integer.toString(player2.getSchess()));
					mediumRB2.setText("Medium " + Integer.toString(player2.getMchess()));
					largeRB2.setText("Large " + Integer.toString(player2.getLchess()));
					for (int i = 0; i < 9; i++){
						for (int j = 0; j < 3; j++){
							if (board.getBoard(i).get(j) == player2.getId())
								circles.get(i).get(2 - j).setFill(Color.TURQUOISE);
						}
					}
					
					makeMoveButton2.setVisible(false);
					makeMoveButton.setVisible(true);
					
					if (board.checkWin(player2)){
						sayWin.setText(player2Name + " Win!");
						makeMoveButton.setVisible(false);
						makeMoveButton2.setVisible(false);
					}
					
					if (!player2.checkInvt(0) && !player2.checkInvt(1) && !player2.checkInvt(2)
							&& !board.checkWin(player1) && !board.checkWin(player2)){
						sayWin.setText(player1Name + " " + player2Name + " " + "have a tie!");
						makeMoveButton.setVisible(false);
						makeMoveButton2.setVisible(false);
					}
					
				}
			}catch(NumberFormatException nfe){
				//System.err.println("Wrong input type");
			}
			
		});
		
		//Layout for player 2
		Image p2Img = new Image("file:OtrioOL/src/OtrioOL/Media/Image/p2.png",true);
		ImageView p2Imv = new ImageView();
		p2Imv.setImage(p2Img);
		p2Imv.setFitWidth(45);
		p2Imv.setFitHeight(35);
		
		VBox player2Layout = new VBox(20);
		player2Layout.setPadding(new Insets(100, 100, 0, 0));
		
		player2Layout.getChildren().add(new Label(player2Name));
		player2Layout.getChildren().add(p2Imv);
		player2Layout.getChildren().add(smallRB2);
		player2Layout.getChildren().add(mediumRB2);
		player2Layout.getChildren().add(largeRB2);

		player2Layout.getChildren().add(makeMoveButton2);
		
		
		//Finalize game layout
		gameLayout.setPadding(new Insets(50, 50, 50, 100));
		gameLayout.setTop(backButton);
		gameLayout.setLeft(player1Layout);
		gameLayout.setRight(player2Layout);
		gameLayout.setCenter(boardBack);
		gameLayout.setBottom(sayWin);
		
		
		Scene gameScene = new Scene(gameLayout, 900, 700);
		window.setScene(gameScene);
		window.show();
			
	}
	
	//Local human VS computer 
	private static void LocalAIGames(String player1Name, AI difficulty, Boolean player1GoesFirst){
		//setting name for AI
		String player2Name;
		switch (difficulty){
		case EASY:
			player2Name = "Easy Computer";
			break;
		case MEDIUM:
			player2Name = "Medium Computer";
			break;
		case HARD:
			player2Name = "Hard Computer";
			break;
		default:
			player2Name = "Computer";
		}
		
		
		

		//Refresh game objects
		Chessboard board = new Chessboard();
		Player player1 = new Player(1);
		PlayerAI player2 = new PlayerAI(2);
		
		//gaming layout
		BorderPane gameLayout =  new BorderPane();
		
		//back button
		Button backButton = new Button("Quit Game");
		backButton.setOnAction(e -> {
			goToWelcome();
		});
		backButton.setEffect(new DropShadow());
		
		//build the visual of chessboard
		
		//set the gap of width and height of the board
		HBox boardBack = new HBox(20);
		VBox col1 = new VBox(0);
		VBox col2 = new VBox(0);
		VBox col3 = new VBox(0);
		
		//Circles
		ArrayList<ArrayList<Circle>> circles = new ArrayList<ArrayList<Circle>>();
		for (int i = 0; i < 9; i ++){
			ArrayList<Circle> tmp = new ArrayList<Circle>();
			tmp.add(new Circle(50, Color.GREY));
			tmp.add(new Circle(30, Color.GREY));
			tmp.add(new Circle(10, Color.GREY));
			circles.add(tmp);
		}
		
		ArrayList<StackPane> chessPlace = new ArrayList<StackPane>();
		for (int i = 0; i < 9; i++){
			StackPane tmp = new StackPane();
			tmp.getChildren().addAll(circles.get(i).get(0), new Circle(40, Color.WHITESMOKE),
					circles.get(i).get(1), new Circle(20, Color.WHITESMOKE), circles.get(i).get(2));
			chessPlace.add(tmp);
		}
		
		//Radio Buttons Group
		ToggleGroup selectPos = new ToggleGroup();
		
		ArrayList<RadioButton> posNum = new ArrayList<RadioButton>();
		for (int i = 0; i < 9; i++){
			RadioButton tmp = new RadioButton();
			tmp.setToggleGroup(selectPos);
			tmp.setUserData(i);
			posNum.add(tmp);
		}
		posNum.get(0).setSelected(true);
		
		
		//Add to frame
		col1.getChildren().add(chessPlace.get(0));
		col1.getChildren().add(posNum.get(0));
		col2.getChildren().add(chessPlace.get(1));
		col2.getChildren().add(posNum.get(1));
		col3.getChildren().add(chessPlace.get(2));
		col3.getChildren().add(posNum.get(2));
		
		col1.getChildren().add(chessPlace.get(3));
		col1.getChildren().add(posNum.get(3));
		col2.getChildren().add(chessPlace.get(4));
		col2.getChildren().add(posNum.get(4));
		col3.getChildren().add(chessPlace.get(5));
		col3.getChildren().add(posNum.get(5));
		
		col1.getChildren().add(chessPlace.get(6));
		col1.getChildren().add(posNum.get(6));
		col2.getChildren().add(chessPlace.get(7));
		col2.getChildren().add(posNum.get(7));
		col3.getChildren().add(chessPlace.get(8));
		col3.getChildren().add(posNum.get(8));
		
		boardBack.getChildren().add(col1);
		boardBack.getChildren().add(col2);
		boardBack.getChildren().add(col3);
		boardBack.setPadding(new Insets(50, 50, 50, 50));
		
		Label sayWin = new Label();
		
		
		
		//check play order
		Button makeMoveButton = new Button("Make Your Move");
		makeMoveButton.setVisible(false);
		
		//AI player inventory display
		
		Label smallAI = new Label("Small " + Integer.toString(player2.getSchess()));
		Label mediumAI = new Label("Medium " + Integer.toString(player2.getMchess()));
		Label largeAI = new Label("Large " + Integer.toString(player2.getLchess()));
		
		if (player1GoesFirst)
			makeMoveButton.setVisible(true);
		else{
			//try{
				Pair responseAI = player2.moveEasy(board, player1);
				
				int chosenPos2 = responseAI.getPutPosition();
				int chessType2 = responseAI.getChessType();
				board.putChess(player2, chosenPos2, chessType2);
				smallAI.setText("Small " + Integer.toString(player2.getSchess()));
				mediumAI.setText("Medium " + Integer.toString(player2.getMchess()));
				largeAI.setText("Large " + Integer.toString(player2.getLchess()));
				for (int i = 0; i < 9; i++){
					for (int j = 0; j < 3; j++){
						if (board.getBoard(i).get(j) == player2.getId())
							circles.get(i).get(2 - j).setFill(Color.TURQUOISE);
					}
					
					
				makeMoveButton.setVisible(true);
					
				if (board.checkWin(player2)){
					sayWin.setText(player2Name + " Win!");
					makeMoveButton.setVisible(false);
				}
					
				if (!player2.checkInvt(0) && !player2.checkInvt(1) && !player2.checkInvt(2)
						&& !board.checkWin(player1) && !board.checkWin(player2)){
					sayWin.setText(player1Name + " " + player2Name + " " + "have a tie!");
					makeMoveButton.setVisible(false);
				}
					
			}
			//}catch(NumberFormatException nfe){
				//err
			//}
			
		}
		
		
		//For Player 1
		Label playerLabel1 = new Label("Player " + Integer.toString(player1.getId()));
		
		//TextField chessText = new TextField();
		//Chess Type Selection Radio Button Group
		ToggleGroup player1Group = new ToggleGroup();
		RadioButton smallRB = new RadioButton("Small " + Integer.toString(player1.getSchess()));
		smallRB.setToggleGroup(player1Group);
		smallRB.setUserData(0);
		RadioButton mediumRB = new RadioButton("Medium " + Integer.toString(player1.getMchess()));
		mediumRB.setToggleGroup(player1Group);
		mediumRB.setUserData(1);
		RadioButton largeRB = new RadioButton("Large " + Integer.toString(player1.getLchess()));
		largeRB.setToggleGroup(player1Group);
		largeRB.setUserData(2);
		smallRB.setSelected(true);
		
		
		
		makeMoveButton.setOnAction(e -> {
			try{
				int chosenPos = (int) selectPos.getSelectedToggle().getUserData();
				int chessType = (int) player1Group.getSelectedToggle().getUserData();
				
				if (board.putChess(player1, chosenPos, chessType)){
					board.putChess(player1, chosenPos, chessType);
					smallRB.setText("Small " + Integer.toString(player1.getSchess()));
					mediumRB.setText("Medium " + Integer.toString(player1.getMchess()));
					largeRB.setText("Large " + Integer.toString(player1.getLchess()));
					
					for (int i = 0; i < 9; i++){
						
						for (int j = 0; j < 3; j++){
							if (board.getBoard(i).get(j) == player1.getId())
								circles.get(i).get(2 - j).setFill(Color.DEEPPINK);
						}
					}
					
					makeMoveButton.setVisible(false);
					
					//check win
					if (board.checkWin(player1)){
						sayWin.setText(player1Name + " Win!");
						makeMoveButton.setVisible(false);
						return;
					}
					
					//check tie
					if (!player1.checkInvt(0) && !player1.checkInvt(1) && !player1.checkInvt(2)
							&& !board.checkWin(player1) && !board.checkWin(player2)){
						sayWin.setText("We have a tie!");
						makeMoveButton.setVisible(false);
					}
					
					//AI's Move
					//try{
						Pair responseAI = player2.moveEasy(board, player1);
						
						int chosenPos2 = responseAI.getPutPosition();
						int chessType2 = responseAI.getChessType();
						board.putChess(player2, chosenPos2, chessType2);
						smallAI.setText("Small " + Integer.toString(player2.getSchess()));
						mediumAI.setText("Medium " + Integer.toString(player2.getMchess()));
						largeAI.setText("Large " + Integer.toString(player2.getLchess()));
						for (int i = 0; i < 9; i++){
							for (int j = 0; j < 3; j++){
								if (board.getBoard(i).get(j) == player2.getId())
									circles.get(i).get(2 - j).setFill(Color.TURQUOISE);
							}
							
							
						makeMoveButton.setVisible(true);
							
						if (board.checkWin(player2)){
							sayWin.setText(player2Name + " Win!");
							makeMoveButton.setVisible(false);
						}
							
						if (!player2.checkInvt(0) && !player2.checkInvt(1) && !player2.checkInvt(2)
								&& !board.checkWin(player1) && !board.checkWin(player2)){
							sayWin.setText(player1Name + " " + player2Name + " " + "have a tie!");
							makeMoveButton.setVisible(false);
						}
							
					}
					//}catch(NumberFormatException nfe){
						//err
					//}
					
					
				}
			}catch(NumberFormatException nfe){
				//System.err.println("Wrong input type");
			}
			
		});
		
		//Layout for player 1
		Image p1Img = new Image("file:OtrioOL/src/OtrioOL/Media/Image/p1.png",true);
		ImageView p1Imv = new ImageView();
		p1Imv.setImage(p1Img);
		p1Imv.setFitWidth(45);
		p1Imv.setFitHeight(35);
		
		VBox player1Layout = new VBox(20);
		player1Layout.setPadding(new Insets(100, 0, 0, 0));
		
		player1Layout.getChildren().add(new Label(player1Name));
		player1Layout.getChildren().add(p1Imv);
		player1Layout.getChildren().add(smallRB);
		player1Layout.getChildren().add(mediumRB);
		player1Layout.getChildren().add(largeRB);
		player1Layout.getChildren().add(makeMoveButton);
		
		//For Player2
		Label playerLabel2 = new Label("Player " + Integer.toString(player2.getId()));
		
		
		//Layout for player 2
		Image p2Img = new Image("file:OtrioOL/src/OtrioOL/Media/Image/p2.png",true);
		ImageView p2Imv = new ImageView();
		p2Imv.setImage(p2Img);
		p2Imv.setFitWidth(45);
		p2Imv.setFitHeight(35);
		
		VBox player2Layout = new VBox(20);
		player2Layout.setPadding(new Insets(100, 100, 0, 0));
		
		player2Layout.getChildren().add(new Label(player2Name));
		player2Layout.getChildren().add(p2Imv);
		player2Layout.getChildren().add(smallAI);
		player2Layout.getChildren().add(mediumAI);
		player2Layout.getChildren().add(largeAI);

		
		
		//Finalize game layout
		gameLayout.setPadding(new Insets(50, 50, 50, 100));
		gameLayout.setTop(backButton);
		gameLayout.setLeft(player1Layout);
		gameLayout.setRight(player2Layout);
		gameLayout.setCenter(boardBack);
		gameLayout.setBottom(sayWin);
		
		
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
		Button backButton = new Button("Back To The Welcome Page");
		backButton.setEffect(new DropShadow());
		backButton.setOnAction(e -> {
			goToWelcome();
		});
			
		//local game
		Button oneVsOne = new Button("Player VS Player In The Same Computer");
		oneVsOne.setEffect(new DropShadow());
		//AI setting
		Button aiGame = new Button("Player VS Computer");
		aiGame.setEffect(new DropShadow());
		
		//VBox Layout
		VBox menuLayout = new VBox(20);
		menuLayout.setPadding(new Insets(80,50,50,300));
		menuLayout.getChildren().addAll(aiGame, oneVsOne, backButton);
		
		//For one on one local game
		TextField namePlayer1 = new TextField("Player1");
		namePlayer1.setMaxWidth(200);
		TextField namePlayer2 = new TextField("Player2");
		namePlayer2.setMaxWidth(200);
		Button startGameButton = new Button("Start Game");
		HBox toggleBox = new HBox(20);
		ToggleGroup checkWhoFirst = new ToggleGroup();
		RadioButton p1FirstRB = new RadioButton();
		p1FirstRB.setToggleGroup(checkWhoFirst);
		p1FirstRB.setUserData(true);
		RadioButton p2FirstRB = new RadioButton();
		p2FirstRB.setToggleGroup(checkWhoFirst);
		p2FirstRB.setUserData(false);
		toggleBox.getChildren().addAll(p1FirstRB, p2FirstRB);
		p1FirstRB.setSelected(true);
		startGameButton.setOnAction(e -> {
			LocalPeopleGames(namePlayer1.getText(), namePlayer2.getText(), 
					(boolean) checkWhoFirst.getSelectedToggle().getUserData());
		});
		oneVsOne.setOnAction(e -> {
			menuLayout.getChildren().clear();
			p1FirstRB.setText("Player 1 goes first");
			p2FirstRB.setText("Player 2 goes first");
			menuLayout.getChildren().addAll(aiGame, oneVsOne, namePlayer1, namePlayer2, toggleBox, startGameButton, backButton);
			
		});
		
		//ai game
		TextField namePlayer = new TextField("Player");
		namePlayer.setMaxWidth(200);
		HBox difficultBox = new HBox(20);
		ToggleGroup diffToggle = new ToggleGroup();
		RadioButton easyRB = new RadioButton("Easy");
		easyRB.setToggleGroup(diffToggle);
		easyRB.setUserData(AI.EASY);
		RadioButton mediumRB = new RadioButton("Medium");
		mediumRB.setToggleGroup(diffToggle);
		mediumRB.setUserData(AI.MEDIUM);
		RadioButton hardRB = new RadioButton("Hard");
		hardRB.setToggleGroup(diffToggle);
		hardRB.setUserData(AI.HARD);
		difficultBox.getChildren().addAll(easyRB);
		easyRB.setSelected(true);
		Button startAI = new Button("Start Game");
		startAI.setOnAction(e -> {
			LocalAIGames(namePlayer.getText(), (AI) diffToggle.getSelectedToggle().getUserData(), 
					(boolean) checkWhoFirst.getSelectedToggle().getUserData());
		});
		
		aiGame.setOnAction(e -> {
			menuLayout.getChildren().clear();
			p1FirstRB.setText("You go first");
			p2FirstRB.setText("You go second");
			menuLayout.getChildren().addAll(aiGame, namePlayer, difficultBox, toggleBox, startAI, oneVsOne, backButton);
		});
		
		
		
		settingLayout.setCenter(menuLayout);
		//settingLayout.setPadding(new Insets(50,50,50,50));
		
		Scene gameScene = new Scene(settingLayout, 900, 700);
		window.setScene(gameScene);
		window.show();
		
	}
	
	
	//Function for navigating to the welcome scene
	private static void goToWelcome(){
		window.setTitle("Otrio");
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
	
	//Testings
	private static void checkCircle(){
		
		VBox mainLayout = new VBox(20);
		
		StackPane testLayout = new StackPane();
		
		//Circle Design
		//Big
		Circle circleL = new Circle(100, Color.GREY);
		Circle circleM = new Circle(60, Color.GREY);
		Circle circleS = new Circle(20, Color.GREY);
		
		
		testLayout.getChildren().addAll(circleL, new Circle(80, Color.WHITESMOKE), 
				circleM, new Circle(40, Color.WHITESMOKE), circleS);
		
		Button setLButton = new Button("Set Large");
		setLButton.setOnAction(e -> {
			circleL.setFill(Color.RED);
		});
		
		Button setMButton = new Button("Set Medium");
		setMButton.setOnAction(e -> {
			circleM.setFill(Color.RED);
		});
		
		Button setSButton = new Button("Set Small");
		setSButton.setOnAction(e -> {
			circleS.setFill(Color.RED);
		});
		
		mainLayout.getChildren().addAll(testLayout, setLButton, setMButton, setSButton);
				
		Scene testScene = new Scene(mainLayout, 900, 700);
		window.setScene(testScene);
		window.show();
	}
	
	
}
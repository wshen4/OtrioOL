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
import javafx.scene.media.MediaPlayerBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;	

import java.net.Socket;
import java.nio.file.Paths;
import java.io.*;
import java.util.ArrayList;

public class OLClient implements Delivery{
	private static String input;
	private static String output;
	
	private static Socket socket;
	private final static int port = 5000;
	private static String serverAddress;
	
	public static Scene startClientGame(String player1Name, String player2Name, Boolean player1GoesFirst){
		
		//Refresh game objects
		Chessboard board = new Chessboard();
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		
		
		
		//gaming layout
		BorderPane gameLayout =  new BorderPane();
		
		//back button
		Button backButton = new Button("Quit Game");
		backButton.setOnAction(e -> {
			Main.goToWelcome();
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
		
		
		return new Scene(gameLayout, 900, 700);
		
			
	}

	@Override
	public int read(Socket socket) throws IOException {
		InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String data = br.readLine();
        
        return Integer.parseInt(data);
	}

	@Override
	public void write(Socket socket) throws IOException {
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		BufferedWriter bw = new BufferedWriter(osw);   
		String data = input;
		data = data +"\n";    
		bw.write(data);
		bw.flush();
		
	}


}

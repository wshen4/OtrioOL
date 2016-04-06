package OtrioOL;

import java.lang.*;
import java.util.ArrayList;

public class Chessboard {
	private ArrayList<ArrayList<Integer>> board;
	public Chessboard(){
		board = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 9; i++){
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for (int j = 0; j < 3; j++){
				tmp.add(0);
			}
			board.add(tmp);
		}
	}

	public boolean putChess(Player player, int position, int chessType){
		if(!player.checkInvt(chessType) || (board.get(position).get(chessType) != 0)){
			return false;
		}
		board.get(position).set(chessType, player.getId());
		player.minusChess(chessType);
		return true;
		
	}
	
	public ArrayList<Integer> getBoard(int input){
		return board.get(input);
	}
	
	


	//check condition wrap up win condition
	private void checkWin(){
		//if win {
		//pop-up window says:
		System.out.println("Player " + "Wins!");
		//go back to the main window
		//else
		//continue;
			
	}
	//win condition warp up
	//win condition one: three in the roll
		//1.check if the three position in the roll
			//get sum of the all nine points in same row of column
			//player 1 qualify: %3 = 0
			//player 2 qualify: 
		//2.check if the three chess in certain pattern:small-medium-big, 
		//	b-m-s, s-s-s, m-m-m, b-b-b
	//win condition two: occupy all three in one position: big-medium-small
	
}


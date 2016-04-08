package OtrioOL;

import java.lang.*;
import java.util.ArrayList;

public class Chessboard {
	private ArrayList<ArrayList<Integer>> board;
	private ArrayList<ArrayList<Integer>> threeWay;
	public Chessboard(){
		board = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 9; i++){
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for (int j = 0; j < 3; j++){
				tmp.add(0);
			}
			board.add(tmp);
		}
		//build an new ArrayList to save three possible position in a roll 
		threeWay = new ArrayList<ArrayList<Integer>>();
		//all possible combination of three position in a roll, every three number is a set
		int a[] = {0,1,2,0,3,6,6,7,8,2,5,8,1,4,7,3,4,5,0,4,8,2,4,6};
		for (int i = 0; i < 8; i++) {
			ArrayList array1 = new ArrayList();
			for (int j = 0; j < 3; j ++) {
				array1.add(a[i * 3 + j]);
			}
			threeWay.add(array1);
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
	
	public boolean checkWin(Player player){
		//record current player
		int currentPlayer = player.getId();		
		//	(1).check if the three chess in one position
		for (int i = 0; i < 9; i++){
			if (board.get(i).get(0) == currentPlayer && board.get(i).get(1) == currentPlayer && board.get(i).get(2) == currentPlayer) {					
				return true;
			}
		}
		
		//	(2). check if three in the roll
		// create a arraylist to save the sequence if there are three position in the three
		 ArrayList<ArrayList<Integer>> threeInRoll = new ArrayList<ArrayList<Integer>>();
		 for (int i = 0; i < 8; i++) {
			int sum[] = new int[3];
			for (int j = 0; j < 3; j++) {
				sum[j] = (board.get(threeWay.get(i).get(j)).contains(currentPlayer)) ? 1 :0 ;
			}
			if (sum[0] + sum[1] + sum[2] == 3) {
				threeInRoll.add(threeWay.get(i));
				System.out.println("three in roll");
				//System.out.println(sum[0] + sum[1] + sum[2]);
				continue;
			}
		}
		if (threeInRoll.isEmpty()) {
			return false;
		}		
		// (3). check if all three chess are in right order: small-med-big; b-m-s; s-s-s; m-m-m; b-b-b
		for (int i = 0; i < threeInRoll.size(); i++){
			// s-m-b
			if (board.get(threeInRoll.get(i).get(0)).get(0) == currentPlayer && board.get(threeInRoll.get(i).get(1)).get(1) == currentPlayer && 
					board.get(threeInRoll.get(i).get(2)).get(2) == currentPlayer) {
				return true;
			}
			//b-m-s
			else if (board.get(threeInRoll.get(i).get(0)).get(2) == currentPlayer && board.get(threeInRoll.get(i).get(1)).get(1) == currentPlayer && 
					board.get(threeInRoll.get(i).get(2)).get(0) == currentPlayer) {
				return true;
			}
			//b-b-b
			else if (board.get(threeInRoll.get(i).get(0)).get(2) == currentPlayer && board.get(threeInRoll.get(i).get(1)).get(2) == currentPlayer && 
					board.get(threeInRoll.get(i).get(2)).get(2) == currentPlayer) {
				return true;
			}
			//m-m-m
			else if (board.get(threeInRoll.get(i).get(0)).get(1) == currentPlayer && board.get(threeInRoll.get(i).get(1)).get(1) == currentPlayer && 
					board.get(threeInRoll.get(i).get(2)).get(1) == currentPlayer) {
				return true;
			}
			//s-s-s
			else if (board.get(threeInRoll.get(i).get(0)).get(0) == currentPlayer && board.get(threeInRoll.get(i).get(1)).get(0) == currentPlayer && 
					board.get(threeInRoll.get(i).get(2)).get(0) == currentPlayer) {
				return true;
			}	
		}	
		return false;	
	}
}


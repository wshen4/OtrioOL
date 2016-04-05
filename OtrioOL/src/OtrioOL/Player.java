package OtrioOL;

import java.util.ArrayList;

public class Player {
	private int pId;
	private ArrayList<Integer> inventary;
	//player constructor
	public Player(int pId0){
		this.pId = pId0;
		for (int i = 0; i < 3; i++){
			inventary.add(3);
		}
	}
	//get player's ID number
	public int getId(){
		return this.pId;
	}
	//Check how many chess this player still have
	public boolean checkInvt(int chessType){
		if (inventary.get(chessType) == 0){ 
			return false;
		}
		return true;		
	}
	//minus the this after the player put it in the game
	public void minusChess(int chessType){
		inventary.set(chessType, inventary.get(chessType) - 1);
	}
}

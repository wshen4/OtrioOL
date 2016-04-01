package OtrioOL;

import java.util.ArrayList;

public class Player {
	private int pId;
	private ArrayList<Integer> inventary;
	
	public Player(int pId0){
		this.pId = pId0;
		for (int i = 0; i < 3; i++){
			inventary.add(3);
		}
	}
	
	public int getId(){
		return this.pId;
	}
	
	public boolean checkInvt(int chessType){
		if (inventary.get(chessType) == 0){ 
			return false;
		}
		return true;
		
	}
	
	public void minusChess(int chessType){
		inventary.set(chessType, inventary.get(chessType) - 1);
	}
	
}

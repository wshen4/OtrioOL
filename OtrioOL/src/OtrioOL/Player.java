package OtrioOL;

import java.util.ArrayList;

public class Player {
	private int pId;
	private ArrayList<Integer> inventary;
	
	public Player(int pId0){
		this.pId = pId0;
		this.inventary = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++){
			inventary.add(3);
		}
	}
	
	//Copy Constructor
	public Player(Player prototype){
		this.pId = prototype.pId;
		this.inventary = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++){
			inventary.add(prototype.inventary.get(i));
		}
	}
	
	public int getId(){
		return this.pId;
	}
	
	public int getSchess(){
		return inventary.get(0);
	}
	
	public int getMchess(){
		return inventary.get(1);
		
	}
	
	public int getLchess(){
		return inventary.get(2);
		
	}
	
	public boolean checkInvt(int chessType){
		if (inventary.get(chessType) <= 0){ 
			return false;
		}
		return true;
		
	}
	
	public void minusChess(int chessType){
		inventary.set(chessType, inventary.get(chessType) - 1);
	}
	
}

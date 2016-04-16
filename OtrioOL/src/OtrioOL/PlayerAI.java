package OtrioOL;

import java.util.ArrayList;
import java.util.Random;

public abstract class PlayerAI extends Player{
	protected ArrayList<ArrayList<Integer>> putable; //list all position
	protected ArrayList<Integer> allChess; //list all chessType
	protected int flag;

	public PlayerAI(int pId0) {
		super(pId0);
		putable = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 3; i++){
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for (int j = 0; j < 9; j++) {
				tmp.add(j);
			}
			putable.add(tmp);
		}
		
		allChess = new ArrayList<Integer>();
		for (int i = 0; i < 3; i++){
			allChess.add(i);
		}
		
		flag = 0;
		
		
	}
	
	//helper functions
	//check availability
	protected void checkChessAI(){
		for (int i = 0; i < 3; i++){
			if (!this.checkInvt(i)){
				allChess.remove(new Integer(i));
			}
		}
	}
	protected void checkPos(Chessboard board){
		for (int chessType = 0; chessType < 3; chessType++){
			for (int i = 0; i < putable.get(chessType).size(); i++){
				if (board.getBoard(i).get(chessType) != 0){
					putable.get(chessType).remove(new Integer(i));
				}
			}
		}
	}
	//make every move counts
	protected void aggressive(Pair res, Chessboard board, int chessType){
		
		for (int i = 0; i < putable.get(chessType).size(); i++){
			//clone board for calculation
			Chessboard fakeboard = new Chessboard(board);
			//clone self
			Player fakeAI = new Player(this);
			//fake Pair
			Pair tmp = new Pair(0, 0);
				
			fakeboard.putChess(fakeAI, putable.get(chessType).get(i), chessType);
			if (beat_human(tmp, fakeboard)){
				res.setPair(chessType, putable.get(chessType).get(i));
				return;
			}	
		}
	}
	
	//check if AI need to block human player's move
	protected void defense_human(Pair res, Chessboard board, Player human){
		for (int i = 0; i < allChess.size(); i++){
			for (int j = 0; j < putable.get(allChess.get(i)).size(); j++){
				//clone board for calculation
				Chessboard fakeboard = new Chessboard(board);
				//clone human
				Player fakehuman = new Player(human);
				
				fakeboard.putChess(fakehuman, putable.get(allChess.get(i)).get(j), allChess.get(i));
				if (fakeboard.checkWin(human)){
					res.setPair(allChess.get(i), putable.get(allChess.get(i)).get(j));
					return;
				}
				
			}
		}
	}
	
	//check if AI has chance to win
	protected boolean beat_human(Pair res, Chessboard board){
		
		for (int i = 0; i < allChess.size(); i++){
			for (int j = 0; j < putable.get(allChess.get(i)).size(); j++){
				//clone board for calculation
				Chessboard fakeboard = new Chessboard(board);
				//clone self
				Player fakeAI = new Player(this);
				
				fakeboard.putChess(fakeAI, putable.get(allChess.get(i)).get(j), allChess.get(i));
				if (fakeboard.checkWin(this)){
					res.setPair(allChess.get(i), putable.get(allChess.get(i)).get(j));
					return true;
				}
				
			}
		}
		
		return false;
		
	}
	
	//move abstract method
	public abstract Pair move(Chessboard board, Player human, boolean isFirstMove);
	
	
}


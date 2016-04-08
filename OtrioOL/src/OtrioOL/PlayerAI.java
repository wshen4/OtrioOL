package OtrioOL;

import java.util.ArrayList;
import java.util.Random;

public class PlayerAI extends Player{
	private ArrayList<ArrayList<Integer>> putable; //list all position
	private ArrayList<Integer> allChess; //list all chessType

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
		
		
	}
	
	//Easy AI
	//Only defense human players' move and
	//if there is nothing to defense, put chess randomly
	//if AI is able to win in one step, AI will take it
	//act order (1) Set random move (2) update win move if possible (3) update defense move if possible
	//Update 04/08: Random move need to be aggressive
	public Pair moveEasy(Chessboard board, Player human, boolean isFirstMove){
		
		//Pure Random Move (Less important)
		checkChessAI();
		int chessType = allChess.get(new Random().nextInt(allChess.size()));
		checkPos(board);
		int x = putable.get(chessType).size();
		int position = putable.get(chessType).get(new Random().nextInt(x));
		
		Pair res = new Pair(chessType, position);
		
		//aggressive modify
		aggressive(res, board, chessType);
		
		//defense human player's move (Second most important)
		defense_human(res, board, human);
		
		//test win move (Most important)
		beat_human(res, board);
		
		//check if this is the first move
		//get center
		if (isFirstMove){
			res.setPair(1, 4);
		}
		
		return res;
		
	}
	
	private void aggressive(Pair res, Chessboard board, int chessType){
		
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
		
	
	
	private void checkChessAI(){
		for (int i = 0; i < 3; i++){
			if (!this.checkInvt(i)){
				allChess.remove(new Integer(i));
			}
		}
	}
	
	private void checkPos(Chessboard board){
		for (int chessType = 0; chessType < 3; chessType++){
			for (int i = 0; i < putable.get(chessType).size(); i++){
				if (board.getBoard(i).get(chessType) != 0){
					putable.get(chessType).remove(new Integer(i));
				}
			}
		}
	}
	
	private void defense_human(Pair res, Chessboard board, Player human){
		
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
	
	private boolean beat_human(Pair res, Chessboard board){
		
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
	
	
	
	
}



























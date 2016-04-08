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
	public Pair moveEasy(Chessboard board, Player human){
		
		//Pure Random Move (Less important)
		checkChessAI();
		int chessType = allChess.get(new Random().nextInt(allChess.size()));
		checkPos(chessType, board);
		int position = putable.get(chessType).get(new Random().nextInt(putable.get(chessType).size()));
		Pair res = new Pair(chessType, position);
		
		//defense human player's move (Second most important)
		defense_human(res, board, human);
		
		//test win move (Most important)
		beat_human(res, board);
		
		return res;
		
	}
	
	private void checkChessAI(){
		for (int i = 0; i < allChess.size(); i++){
			if (!this.checkInvt(i)){
				allChess.remove(i);
			}
		}
	}
	
	private void checkPos(int chessType, Chessboard board){
		for (int i = 0; i < putable.get(chessType).size(); i++){
			if (board.getBoard(i).get(chessType) != 0){
				putable.get(chessType).remove(i);
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
	
	private void beat_human(Pair res, Chessboard board){
		
		for (int i = 0; i < allChess.size(); i++){
			for (int j = 0; j < putable.get(allChess.get(i)).size(); j++){
				//clone board for calculation
				Chessboard fakeboard = new Chessboard(board);
				//clone self
				Player fakeAI = new Player(this);
				
				fakeboard.putChess(fakeAI, putable.get(allChess.get(i)).get(j), allChess.get(i));
				if (fakeboard.checkWin(this)){
					res.setPair(allChess.get(i), putable.get(allChess.get(i)).get(j));
					return;
				}
				
			}
		}
		
	}
	
	
	
	
}



























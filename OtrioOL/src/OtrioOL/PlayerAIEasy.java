package OtrioOL;

import java.util.Random;

public class PlayerAIEasy extends PlayerAI{
	
	public PlayerAIEasy(int pId0){
		super(pId0);
	}

	//Easy AI
		//Only defense human players' move and
		//if there is nothing to defense, put chess randomly
		//if AI is able to win in one step, AI will take it
		//act order (1) Set random move (2) update win move if possible (3) update defense move if possible
	@Override
	public Pair move(Chessboard board, Player human, boolean isFirstMove) {
		// TODO Auto-generated method stub
		//Pure Random Move (Less important)
		checkChessAI();
		int chessType = allChess.get(new Random().nextInt(allChess.size()));
		checkPos(board);
		int x = putable.get(chessType).size();
		int position = putable.get(chessType).get(new Random().nextInt(x));
								
		Pair res = new Pair(chessType, position);
								
		//defense human player's move (Second most important)
		defense_human(res, board, human);
								
		//test win move (Most important)
		beat_human(res, board);
								
								
		return res;
	}
	

}

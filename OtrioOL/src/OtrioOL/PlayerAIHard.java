package OtrioOL;

import java.util.Random;

public class PlayerAIHard extends PlayerAI{
	
	public PlayerAIHard(int pId0){
		super(pId0);
	}

	//Hard AI
		//Only defense human players' move and
		//if there is nothing to defense, put chess randomly
		//if AI is able to win in one step, AI will take it
		//Update 04/08: Random move need to be aggressive
		//AI will try to get center
	
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
								
		//aggressive modify
		aggressive(res, board, chessType);
								
		//defense human player's move (Second most important)
		defense_human(res, board, human);
								
		//test win move (Most important)
		beat_human(res, board);
								
		//check if this is the first move
		//get center
		if (isFirstMove || board.getBoard(4).get(1) == 0){
			res.setPair(1, 4);
		}
								
		return res;
	}

}

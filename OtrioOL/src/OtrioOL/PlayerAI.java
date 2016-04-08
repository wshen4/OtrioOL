package OtrioOL;

import java.util.ArrayList;
import java.util.Random;

public class PlayerAI extends Player{

	public PlayerAI(int pId0) {
		super(pId0);
		
	}
	
	//Easy AI
	//Only defense human players' move and
	//if there is nothing to defense, put chess randomly
	public Pair moveEasy(Chessboard board, Player human, PlayerAI computer){
		//initialize random number
		int chessType = new Random().nextInt(2 - 0) + 0;
		int position = new Random().nextInt(8 - 0) + 0;
		
		//check isVaild
		while (!board.checkPut(computer, position, chessType)){
			chessType = new Random().nextInt(2 - 0) + 0;
			position = new Random().nextInt(8 - 0) + 0;
		}
		
		Pair res = new Pair(chessType, position); //for testing
		
		return res;
		
	}

}

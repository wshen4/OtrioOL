package OtrioOL;

import java.lang.*;
import java.util.ArrayList;

public class Chessboard {
	private ArrayList<ArrayList<Integer>> board;
	public Chessboard(){
		board = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 9; i++){
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for (int j = 0; j < 3; j++){
				tmp.add(0);
			}
			board.add(tmp);
		}
	}

	public boolean putChess(Player player, int position, int chessType){
		if(board.get(position).get(chessType) != 0 && player.checkInvt(chessType))
			return false;
		board.get(position).set(chessType, player.getId());
		player.minusChess(chessType);
		return true;
	}
	
}


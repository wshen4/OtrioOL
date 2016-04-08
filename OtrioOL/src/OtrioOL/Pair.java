package OtrioOL;


public class Pair {
	private int chessType;
	private int putPosition;
	
	public Pair(int c, int p){
		this.chessType = c;
		this.putPosition = p;
		
	}
	
	public int getChessType(){
		return this.chessType;
	}
	
	public int getPutPosition(){
		return this.putPosition;
	}

}

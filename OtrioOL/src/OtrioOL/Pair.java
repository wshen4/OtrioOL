package OtrioOL;


public class Pair {
	private int chessType;
	private int putPosition;
	
	public Pair(int c, int p){
		this.chessType = c;
		this.putPosition = p;
		
	}
	
	//getter
	public int getChessType(){
		return this.chessType;
	}
	
	
	public int getPutPosition(){
		return this.putPosition;
	}
	
	//setter
	public void setPair(int c, int p){
		this.chessType = c;
		this.putPosition = p;
	}
	

}

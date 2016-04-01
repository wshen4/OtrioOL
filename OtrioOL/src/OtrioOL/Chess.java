package OtrioOL;

public class Chess {
	
	//member variable
	private enum Type{
		small, medium, large
	}
	private enum Color{
		blue, red, green, purple
	}
	private Type chessType;
	private Color chessColor;
	
	//constructor
	public Chess(Type chessType0, Color chessColor0){
		this.chessType = chessType0;
		this.chessColor = chessColor0;
	}
}

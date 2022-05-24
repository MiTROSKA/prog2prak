package prog2Prak2.game;


import javafx.scene.paint.Color;

public enum EntityType {
HANDOPERATEDMASTERSQUIRREL(Color.DARKBLUE, 0, "⬛"),
MASTERSQUIRRELBOT(Color.DARKGREY, 0, "⬛"),
MASTERSQUIRREL(Color.BLACK, 0, "⬛"),
MINISQUIRREL(Color.LIGHTBLUE, 0, "⬛"),
MINISQUIRRELBOT(Color.TURQUOISE, 0, "⬛"),
GOODBEAST(Color.GREEN, 0, "⬛"),
BADBEAST(Color.DARKRED, 0, "⬛"),
GOODPLANT(Color.LIGHTGREEN, 1,"⬤"),
BADPLANT(Color.RED, 1, "⬤"),
WALL(Color.ORANGE, 0, "⬛"),
NULL(Color.WHITE, 0, "⬛");
	
	Color color;
	int isCircle;
	String oathSign;
	
	private EntityType(Color color, int isCircle, String oathSign) {
		this.color = color;
		this.isCircle = isCircle;
		this.oathSign = oathSign;
	}
	
	
	public Color getColor() {
		return this.color;
	}
	
	public int getIsCircle() {
		return this.isCircle;
	}
	
	public String getSign() {
		return this.oathSign;
	}
	
	
}

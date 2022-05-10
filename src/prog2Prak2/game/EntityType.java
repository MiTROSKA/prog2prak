package prog2Prak2.game;


import javafx.scene.paint.Color;

public enum EntityType {
HANDOPERATEDMASTERSQUIRREL(Color.DARKBLUE, 0),
MASTERSQUIRREL(Color.BLACK, 0),
MINISQUIRREL(Color.LIGHTBLUE, 0),
GOODBEAST(Color.GREEN, 0),
BADBEAST(Color.RED, 0),
GOODPLANT(Color.GREEN, 1),
BADPLANT(Color.RED, 1),
WALL(Color.ORANGE, 0),
NULL(Color.WHITE, 0);
	
	Color color;
	int isCircle;
	
	private EntityType(Color color, int isCircle) {
		this.color = color;
		this.isCircle = isCircle;
	}
	
	
	public Color getColor() {
		return this.color;
	}
	
	public int getIsCircle() {
		return this.isCircle;
	}
	
	
}

package prog2Prak2.game;

import prog2Prak2.entities.XY;

public class BoardConfig {
	public static final XY boardSize = new XY(40, 15);
	public static final int spaltenX = boardSize.getX();
	public static final int reihenY = boardSize.getY();
	public static final int wallCount = (2*spaltenX) + (2*reihenY -4);

}

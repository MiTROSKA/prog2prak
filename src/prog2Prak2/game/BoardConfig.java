package prog2Prak2.game;

import prog2Prak2.entities.XY;

public class BoardConfig {
	private XY boardSize;
	private int spaltenX;
	private int reihenY;
	private int wallCount;
	
	BoardConfig(int x, int y) {
		this.boardSize = new XY(x, y);
		this.spaltenX = x;
		this.reihenY = y;
		this.wallCount = (2*x)+(2*y-4);
		
	}
	
	public XY getBoardSize() {
		return this.boardSize;
	}
	
	
	public int getSpalten() {
		return this.spaltenX;
	}
	
	public int getReihen() {
		return this.reihenY;
	}
}

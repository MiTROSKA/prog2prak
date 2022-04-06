package prog2Prak2.game;

import prog2Prak2.entities.HandOperatedMasterSquirrel;

public class State {
	private Board board;
	//private static int highscore;
	//private int score;
	
	public State(Board board) {
		this.board = board;
	}
	
	public void update() {
		board.update();
	}
	
	public FlattenedBoard flattenBoard() {
		return board.flatten();
	} 
	
	public HandOperatedMasterSquirrel getMasterSquirrel() {
		return board.getMasterSquirrel();
	}
}

package prog2Prak2.game;

import prog2Prak2.entities.HandOperatedMasterSquirrel;
import prog2Prak2.entities.MasterSquirrel;
import prog2Prak2.entities.NotEnoughEnergyException;

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
	
	public String getEntitiesOnField() {
		return board.getEntitiesOnField();
	}
	
	public HandOperatedMasterSquirrel getMasterSquirrel() {
		return board.getMasterSquirrel();
	}
	
	public void spawnMiniSquirrel(MasterSquirrel ms, int energy) throws Exception, NotEnoughEnergyException {
		board.spawnMiniSquirrel(ms, energy);
	}
}

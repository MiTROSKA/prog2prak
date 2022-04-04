package prog2Prak2.game;

public class State {
	private Board board;
	private static int highscore;
	private int score;
	
	public State(Board board) {
		this.board = board;
	}
	
	public void update() {//?
		
	}
	
	public FlattenedBoard flattenBoard() {
		return board.flatten();
	} 
	
}

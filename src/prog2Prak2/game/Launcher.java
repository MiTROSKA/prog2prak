package prog2Prak2.game;

import prog2Prak2.entities.HandOperatedMasterSquirrel;

public class Launcher {

	public static void main(String[] args) {
		UI userInterface = new ConsoleUI();
		HandOperatedMasterSquirrel handOpMs = new HandOperatedMasterSquirrel(1,1);
		Board board = new Board(handOpMs);
		State state = new State(board);
		Game game = new GameImpl(state, userInterface);
		game.run();  
		
		
	}
}
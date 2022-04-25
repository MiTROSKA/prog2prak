package prog2Prak2.game;

import prog2Prak2.SquirrelCommandReader.Command;

public interface UI {
	public Command getCommand();
	public void render(BoardView view);
}

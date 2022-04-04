package prog2Prak2.game;

public interface UI {
	public MoveCommand getCommand();
	public void render(BoardView view);
}

package prog2Prak2.game;

public abstract class Game {
	protected State state;
	protected UI userInterface;
	
	public Game(State state, UI userInterface) {
		this.state = state;
		this.userInterface = userInterface;
	}

	public void run() {
		while (true) {
			render();
			processInput();
			update();
		}
	}
	
	public abstract void processInput();
	public abstract void render();
	
	protected void update() {
		state.update();
	}
}

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
	
	protected abstract void processInput();
	protected abstract void render();
	protected abstract void update();
		
	
}

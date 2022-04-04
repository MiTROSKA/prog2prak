package prog2Prak2.game;

public abstract class Game {
	
	public void run() {
		while (true) {
			render();
			processInput();
			update();
		}
	}
	
	public abstract void processInput();
	public abstract void render();
	public abstract void update();
}

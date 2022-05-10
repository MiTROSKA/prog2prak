package prog2Prak2.game;

public abstract class Game {
	protected State state;
	protected UI userInterface;
	protected int timer;

	public Game(State state, UI userInterface, int timer) {
		this.state = state;
		this.userInterface = userInterface;
		this.timer = timer;
	}

	public void run() {
		while (true) {

			render();
			try {
				Thread.sleep(timer);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			processInput();
			update();
		}
	}

	protected abstract void processInput();

	protected abstract void render();

	protected abstract void update();

}

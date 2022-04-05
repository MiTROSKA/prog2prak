package prog2Prak2.game;

import prog2Prak2.entities.HandOperatedMasterSquirrel;

public class GameImpl extends Game {
	
	private HandOperatedMasterSquirrel handOpMs;

	public GameImpl(State state, UI userInterface) {
		super(state, userInterface);
		handOpMs = state.getMasterSquirrel();
	}
	
	
	public void processInput() {
		handOpMs.setMoveCommand(userInterface.getCommand());
		
	}

	public void render() {
		userInterface.render(state.flattenBoard());
		
	}

}

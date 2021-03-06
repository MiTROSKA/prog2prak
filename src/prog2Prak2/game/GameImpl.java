package prog2Prak2.game;

import java.lang.reflect.InvocationTargetException;

import HandOperated.HandOperatedFactory;
import HandOperated.HandOperatedMasterSquirrel;
import prog2Prak2.SquirrelCommandReader.Command;
import prog2Prak2.SquirrelCommandReader.GameCommandType;
import prog2Prak2.entities.MiniSquirrel;
import prog2Prak2.entities.NotEnoughEnergyException;
import prog2Prak2.entities.XY;

public class GameImpl extends Game {

	private final HandOperatedMasterSquirrel handOpMs;
	private Command command;
	private boolean lose1Turn;
	private boolean pause;

	public GameImpl(State state, UI userInterface, int timer, HandOperatedFactory bcf) {
		super(state, userInterface, timer);
		handOpMs = bcf.getHOMS();
	}

	public void processInput() {
		command = userInterface.getCommand();
	}

	public void render() {
		userInterface.render(state.flattenBoard());
	}

	protected void update() {
		lose1Turn = false;
		processCommand();
		if (!lose1Turn && !pause) {
			state.update();
		}
	}

	protected void processCommand() {
		if (command == null) {
			handOpMs.setMoveCommand(new MoveCommand(new XY(0, 0)));
		} else {
			try {
				command.getCommandType().getMethod().invoke(this, command.getParams());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unused")
	private void goUp() {
		handOpMs.setMoveCommand(new MoveCommand(XY.UP));
	}

	@SuppressWarnings("unused")
	private void goDown() {
		handOpMs.setMoveCommand(new MoveCommand(XY.DOWN));
	}

	@SuppressWarnings("unused")
	private void goLeft() {
		handOpMs.setMoveCommand(new MoveCommand(XY.LEFT));
	}

	@SuppressWarnings("unused")
	private void goRight() {
		handOpMs.setMoveCommand(new MoveCommand(XY.RIGHT));
	}

	@SuppressWarnings("unused")
	private void help() {
		GameCommandType helpList = GameCommandType.HELP;
		userInterface.specifyStatusBar(helpList.toString());
		lose1Turn = true;
	}

	@SuppressWarnings("unused")
	private void exit() {
		System.out.println("Exiting...");
		System.exit(1);
	}

	@SuppressWarnings("unused")
	private void all() {
		userInterface.specifyStatusBar(state.getEntitiesOnField());
		//System.out.println("Entities currently on the field: \n" + state.getEntitiesOnField());
		lose1Turn = true;
	}

	@SuppressWarnings("unused")
	private void master_energy() {
		System.out.println("Energy of MasterSquirrel: " + handOpMs.getEnergy());
		lose1Turn = true;
	}

	@SuppressWarnings("unused")
	private void spawn_mini(int energy) throws Exception, NotEnoughEnergyException {
		handOpMs.spawnMiniSquirrel(energy);
		state.spawnMiniSquirrel(handOpMs);
		handOpMs.setMoveCommand(new MoveCommand(new XY(0, 0)));
	}
	
	@SuppressWarnings("unused")
	private void pauseGame() {
		userInterface.specifyStatusBar("Game Paused");
		pause = true;
	}
	
	@SuppressWarnings("unused")
	private void resumeGame() {
		userInterface.specifyStatusBar("Game currently playing...");
		pause = false;
	}
	
	@SuppressWarnings("unused")
	private void immortal() {
		handOpMs.updateEnergy(100000000);
	}
}

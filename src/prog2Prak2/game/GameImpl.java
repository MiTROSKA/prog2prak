package prog2Prak2.game;

import java.lang.reflect.InvocationTargetException;

import prog2Prak2.SquirrelCommandReader.Command;
import prog2Prak2.SquirrelCommandReader.GameCommandType;
import prog2Prak2.entities.HandOperatedMasterSquirrel;
import prog2Prak2.entities.NotEnoughEnergyException;
import prog2Prak2.entities.XY;

public class GameImpl extends Game {

	private final HandOperatedMasterSquirrel handOpMs;
	private Command command;
	private boolean lose1Turn;

	public GameImpl(State state, UI userInterface) {
		super(state, userInterface);
		handOpMs = state.getMasterSquirrel();
	}

	public void processInput() {
		command = userInterface.getCommand();
	}

	public void render() {
		userInterface.render(state.flattenBoard());
	}

	protected void update() {
		lose1Turn = false;
		try {
			command.getCommandType().getMethod().invoke(this, command.getParams());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (!lose1Turn) {
			state.update();
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
		System.out.println(helpList);
		lose1Turn = true;
	}

	@SuppressWarnings("unused")
	private void exit() {
		System.out.println("Exiting...");
		System.exit(1);
	}

	@SuppressWarnings("unused")
	private void all() {
		System.out.println(state.getEntitiesOnField());
		lose1Turn = true;
	}

	@SuppressWarnings("unused")
	private void master_energy() {
		System.out.println(handOpMs.getEnergy());
		lose1Turn = true;
	}

	@SuppressWarnings("unused")
	private void spawn_mini(int energy) throws NotEnoughEnergyException{
		state.spawnMiniSquirrel(handOpMs, energy);
		handOpMs.setMoveCommand(new MoveCommand(new XY(0,0)));
	}
}

package prog2Prak2.entities;

import prog2Prak2.game.MoveCommand;

public class HandOperatedMasterSquirrel extends Squirrel {
	private MoveCommand moveCommand;

	public HandOperatedMasterSquirrel(int x, int y) {
		id = idCounter++;
		energy = 500;
		position = new XY(x, y);
		dead = false;
	}

	public void updateEnergy(int deltaWert) {
		energy += deltaWert;
		if (energy <= 0) {
			dead = true;
			System.out.println("WASTED");
			// System.exit(0);
		}
	}

	public void setMoveCommand(MoveCommand moveCommand) {
		this.moveCommand = moveCommand;
	}

	public void nextStep() {
		if (stunCounter == 0) {
			XY newPos = position.move(moveCommand.direction);
			if (entityContext.moveOk(this, newPos)) {
				this.position = newPos;
			}
		} else
			stunCounter--;
	}

	public String toString() {
		return "HandOpSquirrel nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}

	public MiniSquirrel spawnMinisquirrel(int energy) {
		updateEnergy(-energy);

		return new MiniSquirrel(position.getX(), position.getY(), energy, this.id);
	}

}

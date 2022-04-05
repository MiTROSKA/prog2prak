package prog2Prak2.entities;

import prog2Prak2.game.MoveCommand;
 
public class HandOperatedMasterSquirrel extends Squirrel{
	private MoveCommand moveCommand;

	public HandOperatedMasterSquirrel(int x, int y){
		id = idCounter++;
		energy = 500;
		position = new XY(x, y);
	}

	public void updateEnergy(int deltaWert) { energy += deltaWert; }
	
	public void setMoveCommand(MoveCommand moveCommand) {
		this.moveCommand = moveCommand;
	}

	public void nextStep() {
		 if(entityContext.move(this, moveCommand.direction)) {
			 this.position = position.move(moveCommand.direction);
		 }	
	}

	public String toString() {
		return "HandOpSquirrel nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}

	public MiniSquirrel spawnMinisquirrel(int energy) {
		updateEnergy(-energy);

		return new MiniSquirrel(position.getX(), position.getY(), energy);
	}


}

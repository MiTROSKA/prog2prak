package prog2Prak2.entities;

import prog2Prak2.game.EntityType;
import prog2Prak2.game.FlattenedBoard;
import prog2Prak2.game.MoveCommand;

public class HandOperatedMasterSquirrel extends MasterSquirrel {
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
			System.out.println("Game Over");
			System.exit(0);
		}
	}

	public void setMoveCommand(MoveCommand moveCommand) {
		this.moveCommand = moveCommand;
	}

	public void nextStep() {
		if (stunCounter == 0) {
			XY newPos = position.move(moveCommand.direction);
			entityContext.move(this, newPos);
		} else stunCounter--;
	}

	public String toString() {
		return "HandOpSquirrel nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}

	public MiniSquirrel spawnMinisquirrel(int energy, FlattenedBoard flatIsJustice) throws Exception, NotEnoughEnergyException  {
		if(this.energy - energy <=0) {
			throw new NotEnoughEnergyException("Not enough energy! You only have " + this.energy + " energy!" + " but you wanted to have " + energy + " energy! ");
		}
		
		int loopCounter = 0;
		int x = 0;
		int y = 0;
		MiniSquirrel miniSquirrel;
		do {
			XY newPos = this.position.randomMove();
			x = newPos.getX();
			y = newPos.getY();
			miniSquirrel = new MiniSquirrel(x, y, energy, this.id);
			loopCounter++;
			if(loopCounter > 500) {
				throw new Exception("No free space for MiniSquirrel");
			}
			
		} while(flatIsJustice.getEntityType(x, y) != EntityType.NULL);
		
		updateEnergy(-energy);
		
		return miniSquirrel;
	}

	@Override
	public String getNameAndEnergy() {
		return "HandOperatedSquirrel: " + this.getEnergy();
	}

}

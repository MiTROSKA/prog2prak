package prog2Prak2.entities;

public abstract class Beasts extends Entity {
	protected int stepCount = 1;
	
	public void updatePosition(XY newPos) { this.position = newPos; }

	public void updateEnergy(int deltaWert) { energy += deltaWert; }
	
	public abstract XY beastMove(XY diffVector);
	
	public void nextStep() { 
		if (stepCount == 4) {
			XY wouldPos;
			Squirrel nearestSquirrel = entityContext.getNearestSquirrel(position);
			if(nearestSquirrel == null) {
				wouldPos = position.randomMove();
			} else {
				XY direction = beastMove(position.realDiffCalc(nearestSquirrel.getPos()));
				wouldPos = position.move(direction);
			}
			entityContext.move(this, wouldPos);
			
			stepCount = 1;
		} else stepCount++;
	}
		
}

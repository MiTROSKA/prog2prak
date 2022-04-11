package prog2Prak2.entities;

public class GoodBeast extends Beasts{

	public GoodBeast(int x, int y) {
		id = idCounter++;
		position = new XY(x, y);
		energy = 300;
		dead = false;
	}
	
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

			if(entityContext.moveOk(this, wouldPos)) {
				position = wouldPos;
				} 
			stepCount = 1;
			} else stepCount++;
	}

	public String toString() {
		return "GoodBeast nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}

	
	public XY beastMove(XY diffVector) {
		return diffVector.abnormalize().negate();
	}
}

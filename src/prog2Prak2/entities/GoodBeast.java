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
				wouldPos = new XY(0,0); // TODO vorerst
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
}

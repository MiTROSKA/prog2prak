package prog2Prak2.entities;

public class BadBeast extends Beasts{
	private int lp;

	public BadBeast(int x, int y) {
		id = idCounter++;
		position = new XY(x, y);
		energy = -150;
		dead = false;
		lp = 7;
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
	
	
	public int getLifePoints() {
		return this.lp;
	}
	
	public void payLifePoint() {
		lp--;
		if(lp == 0) {
			dead = true;
		}
	}

	public String toString(){
		return "Badbeast nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}


	public XY beastMove(XY diffVector) {
		return diffVector.abnormalize();
	}
}

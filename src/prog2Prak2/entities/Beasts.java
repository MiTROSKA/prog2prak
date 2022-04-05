package prog2Prak2.entities;

public abstract class Beasts extends Entity {

	public void updatePosition(XY newPos) { this.position = newPos; }

	public void updateEnergy(int deltaWert) { energy += deltaWert; }

	public void nextStep() { //?
		XY newPos = position.randomMove();
		if(entityContext.move(this, newPos)) {
			position = newPos;
		}
	
	}
}

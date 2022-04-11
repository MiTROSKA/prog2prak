package prog2Prak2.entities;

public abstract class Beasts extends Entity {
	protected int stepCount = 1;
	
	public void updatePosition(XY newPos) { this.position = newPos; }

	public void updateEnergy(int deltaWert) { energy += deltaWert; }

	public abstract void nextStep();
		
}

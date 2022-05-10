package prog2Prak2.entities;

public abstract class Squirrel extends Entity{
	public abstract String getNameAndEnergy();
	protected int stunCounter = 0;
	public void updatePosition(XY newPos) { this.position = newPos; }
	
	public void stunned() {
		this.stunCounter = 3;
	}
}

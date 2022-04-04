package prog2Prak2.entities;

public abstract class StaticObjects extends Entity{

	public void nextStep() {}

	public void updatePosition(XY newPos) { this.position = newPos; }
}

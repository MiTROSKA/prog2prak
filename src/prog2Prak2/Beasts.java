package prog2Prak2;

public abstract class Beasts extends Entity {

	public void updatePosition(XY newPos) { this.position = newPos; }

	public void updateEnergy(int deltaWert) { energy += deltaWert; }

	public void nextStep() { position = position.randomMove(); }
}

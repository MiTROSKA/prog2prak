package prog2Prak2;

public class MiniSquirrel extends Squirrel{
	public MiniSquirrel (int x, int y, int energy) {
		position = new XY(x, y);
		this.energy = energy;
	}

	public void nextStep() { position.randomMove(); }

	public void updateEnergy(int deltaWert) { energy += deltaWert; }

	public String toString() {
		return "MiniSquirrel nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}
}

package prog2Prak2.entities;

public class MiniSquirrel extends Squirrel {
	private final int parentToken;

	public MiniSquirrel(int x, int y, int energy, int Token) {
		position = new XY(x, y);
		this.energy = energy;
		this.id = idCounter++;
		this.parentToken = Token;
		dead = false;
		this.stunCounter = 1;
	}

	public void nextStep() {
		if (stunCounter == 0) {
			updateEnergy(-1);
			XY newPos = position.randomMove();
			entityContext.move(this, newPos);
		} else stunCounter--;
	}

	public int getParentToken() {
		return this.parentToken;
	}

	public void updateEnergy(int deltaWert) {
		energy += deltaWert;
		if (energy <= 0) {dead = true;}
	}

	public String toString() {
		return "MiniSquirrel nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}
}

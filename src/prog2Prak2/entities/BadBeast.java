package prog2Prak2.entities;

public class BadBeast extends Beasts {
	private int lp;

	public BadBeast(int x, int y) {
		id = idCounter++;
		position = new XY(x, y);
		energy = -150;
		dead = false;
		lp = 7;
	}

	public int getLifePoints() {
		return this.lp;
	}

	public void payLifePoint() {
		lp--;
		if (lp == 0) {
			dead = true;
		}
	}

	public String toString() {
		return "Badbeast nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}
	
	@Override
	public XY beastMove(XY diffVector) {
		return diffVector.abnormalize();
	}
}

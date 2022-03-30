package prog2Prak2;

public class GoodBeast extends Beasts{

	public GoodBeast(int x, int y) {
		id = idCounter++;
		position = new XY(x, y);
		energy = 300;
	}

	public String toString() {
		return "GoodBeast nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}
}

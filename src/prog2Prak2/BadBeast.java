package prog2Prak2;

public class BadBeast extends Beasts{

	public BadBeast(int x, int y) {
		id = idCounter++;
		position = new XY(x, y);
		energy = -150;
	}

	public String toString(){
		return "Badbeast nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}
}

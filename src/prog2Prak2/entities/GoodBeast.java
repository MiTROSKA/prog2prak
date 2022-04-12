package prog2Prak2.entities;

public class GoodBeast extends Beasts{

	public GoodBeast(int x, int y) {
		id = idCounter++;
		position = new XY(x, y);
		energy = 300;
		dead = false;
	}
	
	public String toString() {
		return "GoodBeast nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}
	
	@Override
	public XY beastMove(XY diffVector) {
		return diffVector.abnormalize().negate();
	}
}

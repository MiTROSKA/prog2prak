package prog2Prak2.entities;

public class GoodPlant extends PlantsAndWall{

	public GoodPlant(int x, int y) {
		id = idCounter++;
		energy = 150;
		position = new XY(x, y);
		dead = false;
	}

	public String toString() {
		return "GoodPlant nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}
}

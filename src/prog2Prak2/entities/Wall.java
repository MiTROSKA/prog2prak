package prog2Prak2.entities;

public class Wall extends PlantsAndWall{

	public Wall(int x, int y) {
		id = idCounter++;
		energy = -30;
		position = new XY(x, y);
		dead = false;
	}

	public String toString() {
		return "Wall nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}
}

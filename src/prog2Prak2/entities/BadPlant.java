package prog2Prak2.entities;

public class BadPlant extends PlantsAndWall{

	public BadPlant(int x, int y) {
		id = idCounter++;
		energy = -300;
		position = new XY(x,y);
	}

	public String toString() {
		return "BadPlant nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}
}

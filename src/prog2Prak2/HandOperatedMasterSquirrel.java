package prog2Prak2;

import java.util.Scanner;

public class HandOperatedMasterSquirrel extends Squirrel{

	private static Scanner sc;

	public HandOperatedMasterSquirrel(int x, int y){
		sc = new Scanner(System.in);
		id = idCounter++;
		energy = 500;
		position = new XY(x, y);
	}

	public void updateEnergy(int deltaWert) { energy += deltaWert; }

	public void nextStep() {
		char input;
		input = sc.next().charAt(0);

		switch (input) {
		case 'w':
		//position = new XY(position.getX(), position.getY() +1);
			position = position.move(XY.UP);
			break;
		case 'a':
		//position = new XY(position.getX()-1, position.getY());
			position = position.move(XY.LEFT);
			break;
		case 's':
		//position = new XY(position.getX(), position.getY() -1);
			position = position.move(XY.DOWN);
			break;
		case 'd':
		//position = new XY(position.getX() +1, position.getY());
			position = position.move(XY.RIGHT);
			break;
		default:
		}
	}

	public String toString() {
		return "HandOpSquirrel nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}

	public MiniSquirrel spawnMinisquirrel(int energy) {
		updateEnergy(-energy);

		return new MiniSquirrel(position.getX(), position.getY(), energy);
	}
}

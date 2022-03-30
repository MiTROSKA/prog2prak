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
			position = new XY(position.getX(), position.getY() +1);
			break;
		case 'a':
			position = new XY(position.getX()-1, position.getY());
			break;
		case 's':
			position = new XY(position.getX(), position.getY() -1);
			break;
		case 'd':
			position = new XY(position.getX() +1, position.getY());
			break;
		default:
		}
	}

	public String toString() {
		return "HandOperatedMasterSquirrel nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}

	public MiniSquirrel spawnMinisquirrel(int energy) {
		updateEnergy(-energy);

		return new MiniSquirrel(position.getX(), position.getY(), energy);
	}
}

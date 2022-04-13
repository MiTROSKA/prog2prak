package prog2Prak2.game;

import java.util.Scanner;

import prog2Prak2.entities.XY;

public class ConsoleUI implements UI{
	private static Scanner scanner = new Scanner(System.in);
	
	public MoveCommand getCommand() {
		String input = scanner.nextLine();
		
		switch(input) {
		case "w":
		case "W":
			return new MoveCommand(XY.UP);
		case "a":
		case "A":
			return new MoveCommand(XY.LEFT);
		case "s":
		case "S":
			return new MoveCommand(XY.DOWN);
		case "d":
		case "D":
			return new MoveCommand(XY.RIGHT);
			
		default: return new MoveCommand(new XY(0, 0));
		}
	}
	
	public void render(BoardView view) {
		XY size = view.getSize();
		String element;
		for(int y = 0; y < size.getY(); y++) {
			for(int x = 0; x < size.getX(); x++) {
				switch(view.getEntityType(x, y)) {
				case MASTERSQUIRREL: 
					element = " s"; break;
				case GOODBEAST:
					element = " G"; break;
				case BADBEAST:
					element = " B"; break;
				case GOODPLANT:
					element = " g"; break;
				case BADPLANT:
					element = " b"; break;
				case WALL:
					element = " #"; break;
				case MINISQUIRREL:
					element = " m"; break;
				default: element = " _"; break;
				}
				System.out.print(element);
			}
			System.out.println();
		}
		System.out.println("\n" + "\n");
	}
}

package prog2Prak2.game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import prog2Prak2.SquirrelCommandReader.Command;
import prog2Prak2.SquirrelCommandReader.CommandScanner;
import prog2Prak2.SquirrelCommandReader.GameCommandType;
import prog2Prak2.entities.XY;

public class ConsoleUI implements UI{
	CommandScanner commandScanner;
	GameCommandType[] commandTypes;
	
	
	public ConsoleUI() {
		commandTypes = GameCommandType.values();
		this.commandScanner = new CommandScanner(commandTypes, new BufferedReader(new InputStreamReader(System.in)));
	}
	
	public Command getCommand() {
		 try {
			 return commandScanner.next();
		 } catch(Exception e) {
			 System.out.println("Wrong Command...");
			 return new Command(GameCommandType.HELP, new Object[0]);
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

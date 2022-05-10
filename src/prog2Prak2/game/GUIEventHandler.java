package prog2Prak2.game;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import prog2Prak2.SquirrelCommandReader.Command;
import prog2Prak2.SquirrelCommandReader.GameCommandType;

public class GUIEventHandler {

	private Command command;

	public GUIEventHandler() {
		this.command = null;
	}

	public Command getCommand() {
        Command buffer = command;
        command = null;
        return buffer;
        }

	public void setCommand() {

	}

	public void getSquirrelMoveCommand(KeyEvent e) {

		switch (e.getCode()) {
		case W:
			command = new Command(GameCommandType.UP, new Object[0]);
			break;
		case A:
			command = new Command(GameCommandType.LEFT, new Object[0]);
			break;
		case S:
			command = new Command(GameCommandType.DOWN, new Object[0]);
			break;
		case D:
			command = new Command(GameCommandType.RIGHT, new Object[0]);
			break;
		default:
			command = null;
		}

	}

	public void actionEventHandler(ActionEvent e) {
		
		if (e.getSource()instanceof MenuItem menuItem) {
			switch (menuItem.textProperty().get()) {
			case "Pause":
				command = new Command(GameCommandType.PAUSE, new Object[0]);
				break;
			case "Resume":
				command = new Command(GameCommandType.RESUME, new Object[0]);
				break;
			case "All":
				command = new Command(GameCommandType.ALL, new Object[0]);
				break;
			case "Help":
				command = new Command(GameCommandType.HELP, new Object[0]);
				break;
			case "Exit":
				command = new Command(GameCommandType.EXIT, new Object[0]);
				break;
			default:
				break;

			}
		}
		else if(e.getSource() instanceof Button button) {
			
			switch(button.textProperty().get()) {
			case "Resume":
				command = new Command(GameCommandType.RESUME, new Object[0]);
				break;
			case "Pause":
				command = new Command(GameCommandType.PAUSE, new Object[0]);
				break;
				default:
					break;
			}
		}
	}
	
	public void miniSpawner(String textFieldValue) {
		int i = Integer.parseInt(textFieldValue);
		Object[] obArr = {i};
		command = new Command(GameCommandType.SPAWN_MINI, obArr);
	}
	
	public void godMode() {
		command = new Command(GameCommandType.IMMORTAL, new Object[0]);
	}
	

}

package prog2Prak2.game;

import prog2Prak2.entities.XY;

public class MoveCommand {
	public final XY direction;
	
	public MoveCommand(XY direction) {
		this.direction = direction;
	}
}

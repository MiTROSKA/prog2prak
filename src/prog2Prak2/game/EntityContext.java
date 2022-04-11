package prog2Prak2.game;

import prog2Prak2.entities.Entity;
import prog2Prak2.entities.Squirrel;
import prog2Prak2.entities.XY;

public interface EntityContext {
	public XY getSize();
	public boolean moveOk(Entity entity, XY moveDirection);
	public Squirrel getNearestSquirrel(XY startPos);
	
}

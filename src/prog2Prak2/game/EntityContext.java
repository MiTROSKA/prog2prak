package prog2Prak2.game;

import prog2Prak2.entities.Entity;
import prog2Prak2.entities.XY;

public interface EntityContext {
	public XY getSize();
	public void move(Entity entity, XY moveDirection);
	
}

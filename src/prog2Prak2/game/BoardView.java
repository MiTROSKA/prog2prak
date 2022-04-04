package prog2Prak2.game;

import prog2Prak2.entities.XY;

public interface BoardView {
	public EntityType getEntityType(int x, int y);
	public XY getSize();
	
}

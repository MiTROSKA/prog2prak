package prog2Prak2.game;

import prog2Prak2.entities.Entity;
import prog2Prak2.entities.MasterSquirrel;
import prog2Prak2.entities.MiniSquirrel;
import prog2Prak2.entities.MiniSquirrelBot;
import prog2Prak2.entities.Squirrel;
import prog2Prak2.entities.XY;

public interface EntityContext {
	public XY getSize();
	public void move(Entity entity, XY moveDirection);
	public Squirrel getNearestSquirrel(XY startPos);
	public EntityType getEntityType(int x, int y);
	public void insert(MiniSquirrel miniSquirrel);
	public boolean isRelated(Entity myself, XY itsPos);
	public void implode(MiniSquirrelBot msb, int radius);
	public MasterSquirrel getFather(MiniSquirrel ms);
	
}

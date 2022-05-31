package prog2Prak2.entities;

import prog2Prak2.game.EntityContext;

public abstract class StaticObjects extends Entity{

	public void nextStep(EntityContext entityContext) {}

	public void updatePosition(XY newPos) { this.position = newPos; }
}

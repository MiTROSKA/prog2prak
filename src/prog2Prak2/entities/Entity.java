package prog2Prak2.entities;

import prog2Prak2.game.EntityContext;

public abstract class Entity {
	protected static int idCounter = 1;
	protected boolean dead;
	protected int id;
	protected int energy;
	protected XY position;
	public abstract void updateEnergy(int deltaWert);
	public abstract void updatePosition(XY newPos);
	public abstract void nextStep(EntityContext entityContext);
	public abstract String toString();
	
	public void updateEnergy(double deltaWert) { energy += deltaWert;}

	public XY getPos() { return this.position; }

	public int getId() { return this.id; }

	public int getEnergy () { return this.energy; }

	public boolean isSameEntity(Entity entity) {
		return this.id == entity.getId();
	}
	
	public boolean isDead() {
		return this.dead;
	}
	
	public void die() {
		dead = true;
	}
}

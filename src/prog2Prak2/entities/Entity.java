package prog2Prak2.entities;

public abstract class Entity {
	protected static int idCounter = 1;
	protected int id;
	protected int energy;
	protected XY position;
	public abstract void updateEnergy(int deltaWert);
	public abstract void updatePosition(XY newPos);
	public abstract void nextStep();
	public abstract String toString();

	public XY getPos() { return this.position; }

	public int getId() { return this.id; }

	public int getEnergy () { return this.energy; }

	public boolean isSameEntity(Entity entity) {
		if (this.id == entity.getId()) {
			return true;
		}
		return false;
	}
}

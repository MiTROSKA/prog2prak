package prog2Prak2.entities;

public class MiniSquirrel extends Squirrel{
	private int parentToken;
	
	public MiniSquirrel (int x, int y, int energy, int id) {
		position = new XY(x, y);
		this.energy = energy;
		this.id = idCounter++;
		this.parentToken = id;
		dead = false;
	}

	public void nextStep() { 
		if(stunCounter == 0) {
			updateEnergy(-1);
			XY newPos = position.randomMove();
			if(entityContext.moveOk(this, newPos)) {
			 this.position = newPos;
			}
		} else stunCounter--;
		
	}

	public int getParentToken() {
		return this.parentToken;
	}
	
	public void updateEnergy(int deltaWert) { 
		energy += deltaWert; 
		if(energy <= 0) {
			dead = true;
		}
		
		
	}

	public String toString() {
		return "MiniSquirrel nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}
}

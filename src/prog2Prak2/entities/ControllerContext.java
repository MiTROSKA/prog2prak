package prog2Prak2.entities;

import prog2Prak2.game.EntityType;

public interface ControllerContext {

	public XY getViewUpperLeft();
	
	public XY getViewLowerRight();
	
	public EntityType getEntityAt(XY xy);
	
	public void move(XY direction);
	
	public void spawnMiniBot(XY direction, int energy) throws SpawnException;
	
	public int getEnergy();

	public void updateEnergy(int i);
	
	public MiniSquirrel getMini();
	
	public XY locate();
	
	public boolean isMine(XY xy);
	
	public void implode(int impactRadius);
	
	public XY directionOfMaster();
	
	public long getRemainingSteps();
}

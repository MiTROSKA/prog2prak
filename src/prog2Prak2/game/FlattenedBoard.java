package prog2Prak2.game;

import prog2Prak2.entities.*;

public class FlattenedBoard implements BoardView, EntityContext{
	private Entity [][] entityArray;
	private XY flatBoardSize;
	
	public FlattenedBoard(Entity [][] entityArray) {
		this.entityArray = entityArray;
		this.flatBoardSize = new XY(entityArray.length, entityArray[0].length);
	}
	

	public XY getSize() {
		return this.flatBoardSize;
	} 
	
	public EntityType getEntityType(int x, int y) {
		Entity entity;
		entity = entityArray[x][y];
		
		if(entity == null) {
			return EntityType.NULL;
		} else if(entity instanceof HandOperatedMasterSquirrel) {
			return EntityType.MASTERSQUIRREL;
		} else if(entity instanceof GoodBeast) {
			return EntityType.GOODBEAST;
		} else if(entity instanceof BadBeast) {
			return EntityType.BADBEAST;
		} else if(entity instanceof GoodPlant) {
			return EntityType.GOODPLANT;
		} else if(entity instanceof BadPlant) {
			return EntityType.BADPLANT;
		} else if(entity instanceof Wall) {
			return EntityType.WALL;
		}
		
		return EntityType.NULL;
	}


	
	public boolean move(Entity entity, XY moveDirection) { //?
		XY oldPos = entity.getPos();
		XY wouldPos = entity.getPos().move(moveDirection);
		if(entityArray[wouldPos.getX()][wouldPos.getY()] == null) {
			entityArray[wouldPos.getX()] [wouldPos.getY()] = entity;
			entityArray[oldPos.getX()][oldPos.getY()] = null;
			return true;
		}
		return false;
	}

}

package prog2Prak2.game;

import prog2Prak2.entities.BadBeast;
import prog2Prak2.entities.BadPlant;
import prog2Prak2.entities.Entity;
import prog2Prak2.entities.EntitySet;
import prog2Prak2.entities.GoodBeast;
import prog2Prak2.entities.GoodPlant;
import prog2Prak2.entities.HandOperatedMasterSquirrel;
import prog2Prak2.entities.Wall;
import prog2Prak2.entities.XY;
import java.util.Random;
import prog2Prak2.game.EntityContext;

public class Board {
	XY boardSize;
	EntitySet entityset;
	private HandOperatedMasterSquirrel handOpMs;
	
	public Board(HandOperatedMasterSquirrel handOpMs) {
		entityset = new EntitySet();
		this.handOpMs = handOpMs;
		entityset.addEntity(handOpMs);
		boardSize = BoardConfig.boardSize;
		
		//wand erstellen
		for(int x = 0; x < boardSize.getX(); x++) {
			entityset.addEntity(new Wall(x,0));
			entityset.addEntity(new Wall(x, boardSize.getY()-1));
		}
		
		for(int y = 1; y < boardSize.getY()-1; y++) {
			entityset.addEntity(new Wall(0, y));
			entityset.addEntity(new Wall(boardSize.getX()-1, y));
		}
		
		//spielfeld random füllen
		Random random = new Random();
		
		for(int x = 1; x < boardSize.getX() -1; x++) {
			for(int y = 2; y < boardSize.getY()-1; y++) {
				int i = random.nextInt(100);
				if(i < 70 || i >92) {
					continue;
				}
				else if(i >= 70 && i < 77) {
					entityset.addEntity(new GoodBeast(x,y));
				}
				else if(i >= 77 && i < 83) {
					entityset.addEntity(new BadBeast(x,y));
				}
				else if(i >= 83 && i < 87) {
					entityset.addEntity(new GoodPlant(x,y));
				}
				else if(i >= 87 && i < 93) {
					entityset.addEntity(new BadPlant(x,y));
				}
			}
		}
	}
	public FlattenedBoard flatten() {
		Entity [][] entityArray;
		entityArray = new Entity[boardSize.getX()][boardSize.getY()];
		Entity entity = entityset.getEntityAt(0);

		for(int i = 1; entity != null; i++) {
			entityArray[entity.getPos().getX()][entity.getPos().getY()] = entity;
			entity = entityset.getEntityAt(i);
		}
		return new FlattenedBoard(entityArray);
	}
	
	public HandOperatedMasterSquirrel getMasterSquirrel() {
		return handOpMs;
	}
	
	public void update() {
		Entity.setEntityContext(flatten());
		entityset.nextStepCaller();
	}
	
}

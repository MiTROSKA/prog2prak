package prog2Prak2.game;

import prog2Prak2.entities.BadBeast;
import prog2Prak2.entities.BadPlant;
import prog2Prak2.entities.Entity;
import prog2Prak2.entities.EntitySet;
import prog2Prak2.entities.GoodBeast;
import prog2Prak2.entities.GoodPlant;
import prog2Prak2.entities.HandOperatedMasterSquirrel;
import prog2Prak2.entities.MasterSquirrel;
import prog2Prak2.entities.NotEnoughEnergyException;
import prog2Prak2.entities.Wall;
import prog2Prak2.entities.XY;


import java.util.Random;
//import prog2Prak2.game.EntityContext;

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
		
		//spielfeld random f�llen
		Random random = new Random();
		
		for(int x = 1; x < boardSize.getX() -1; x++) {
			for(int y = 2; y < boardSize.getY()-1; y++) {
				int i = random.nextInt(100);
				if(i < 72 || i >84) {
					continue;
				}
				else if(i < 75) {
					entityset.addEntity(new GoodBeast(x,y));
				}
				else if(i < 78) {
					entityset.addEntity(new BadBeast(x,y));
				}
				else if(i < 81) {
					entityset.addEntity(new GoodPlant(x,y));
				}
				else {
					entityset.addEntity(new BadPlant(x,y));
				}
			}
		}
	}
	public FlattenedBoard flatten() {
		return new FlattenedBoard(entityset, boardSize);
	}
	
	public HandOperatedMasterSquirrel getMasterSquirrel() {
		return handOpMs;
	}
	
	public void update() {
		Entity.setEntityContext(flatten());
		entityset.nextStepCaller();
	}
	
	public String getEntitiesOnField() {
		return entityset.toString();
	}
	
	public void spawnMiniSquirrel(MasterSquirrel ms, int energy) throws Exception, NotEnoughEnergyException {
		entityset.addEntity(ms.spawnMinisquirrel(energy, XY.RIGHT, flatten()));
	}
	
	public XY getBoardSize() {
		return this.boardSize;
	}
}

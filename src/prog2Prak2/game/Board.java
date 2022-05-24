package prog2Prak2.game;

import prog2Prak2.entities.BadBeast;
import prog2Prak2.entities.BadPlant;
import prog2Prak2.entities.Entity;
import prog2Prak2.entities.EntitySet;
import prog2Prak2.entities.GoodBeast;
import prog2Prak2.entities.GoodPlant;
import prog2Prak2.entities.MasterSquirrel;
import prog2Prak2.entities.NotEnoughEnergyException;
import prog2Prak2.entities.Wall;
import prog2Prak2.entities.XY;

import java.util.Random;

import HandOperated.HandOperatedMasterSquirrel;
//import prog2Prak2.game.EntityContext;

public class Board {
	XY boardSize;
	EntitySet entityset;

	public Board(MasterSquirrel[] squirrelsToAdd) {
		entityset = new EntitySet();
		boardSize = BoardConfig.boardSize;

		for (MasterSquirrel s : squirrelsToAdd) {
			entityset.addEntity(s);
		}

		// wand erstellen
		for (int x = 0; x < boardSize.getX(); x++) {
			entityset.addEntity(new Wall(x, 0));
			entityset.addEntity(new Wall(x, boardSize.getY() - 1));
		}

		for (int y = 1; y < boardSize.getY() - 1; y++) {
			entityset.addEntity(new Wall(0, y));
			entityset.addEntity(new Wall(boardSize.getX() - 1, y));
		}

		// spielfeld random f�llen
		Random random = new Random();
		int gbCount = 0;
		int bbCount = 0;
		int gpCount = 0;
		int bpCount = 0;

		for (int x = 1; x < boardSize.getX() - 1; x++) {
			for (int y = 2; y < boardSize.getY() - 1; y++) {
				int i = random.nextInt(100);
				if (i < 72 || i > 84) {
					continue;
				} else if (i < 75) {
					if (gbCount < 10) {
						entityset.addEntity(new GoodBeast(x, y));
						gbCount++;
					}
				} else if (i < 78) {
					if (bbCount < 10) {
						entityset.addEntity(new BadBeast(x, y));
						bbCount++;
					}
				} else if (i < 81) {
					if (gpCount < 10) {
						entityset.addEntity(new GoodPlant(x, y));
						gpCount++;
					}
				} else {
					if (bpCount < 10) {
						entityset.addEntity(new BadPlant(x, y));
						bpCount++;
					}
				}

			}
		}
	}

	public FlattenedBoard flatten() {
		return new FlattenedBoard(entityset, boardSize);
	}

	/*
	 * public HandOperatedMasterSquirrel getMasterSquirrel() { return handOpMs; }
	 */

	public void update() {
		Entity.setEntityContext(flatten());
		entityset.nextStepCaller();
	}

	public String getEntitiesOnField() {
		return entityset.toString();
	}

	public void spawnMiniSquirrel(HandOperatedMasterSquirrel handOpMs) throws NotEnoughEnergyException {
		// entityset.addEntity(handOpMs.getMini());
		flatten().insert(handOpMs.getMini());

	}

	public XY getBoardSize() {
		return this.boardSize;
	}
}

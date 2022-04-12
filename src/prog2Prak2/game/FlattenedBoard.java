package prog2Prak2.game;

import prog2Prak2.entities.*;
import java.util.Random;

public class FlattenedBoard implements BoardView, EntityContext {
	private Entity[][] entityArray;
	private Random random = new Random();
	private XY flatBoardSize;
	private EntitySet entityset;
	private EntitySet squirrelList;

	public FlattenedBoard(EntitySet entityset, XY size) {
		this.entityset = entityset;
		this.squirrelList = new EntitySet();
		this.flatBoardSize = size;
		entityArray = new Entity[flatBoardSize.getX()][flatBoardSize.getY()];
		Entity entity = entityset.getEntityAt(0);
		for (int i = 1; entity != null; i++) {
			entityArray[entity.getPos().getX()][entity.getPos().getY()] = entity;
			if (entity instanceof Squirrel) {
				squirrelList.addEntity(entity);
			}
			entity = entityset.getEntityAt(i);
		}
	}

	public Squirrel getNearestSquirrel(XY searchStart) {
		Entity nearestSquirrel = null;
		XY nearPos = null;
		Entity squirrel = squirrelList.getEntityAt(0);

		for (int i = 1; squirrel != null; i++) {
			XY squirrelPos = squirrel.getPos();
			XY difference = searchStart.fakeDiffCalc(squirrelPos);
			if (difference.getX() <= 6 && difference.getY() <= 6) {
				if (nearestSquirrel == null) {
					nearestSquirrel = squirrel;
					nearPos = nearestSquirrel.getPos();
				} else {
					XY oldDiff = searchStart.fakeDiffCalc(nearPos);
					if (difference.getX() < oldDiff.getX() || difference.getY() < oldDiff.getY()) {
						nearestSquirrel = squirrel;
						nearPos = nearestSquirrel.getPos();
					}
				}
			}
			squirrel = squirrelList.getEntityAt(i);
		}
		return (Squirrel) nearestSquirrel;
	}

	public boolean moveOk(Entity entity, XY wouldPos) {
		boolean itsOk = false;

		if (wouldPos.getX() < 0 || wouldPos.getY() < 0 || wouldPos.getX() >= flatBoardSize.getX()
				|| wouldPos.getY() >= flatBoardSize.getY()) {
			return false;
		}
		Entity hindrance = entityArray[wouldPos.getX()][wouldPos.getY()];

		if (hindrance == null) {
			move(entity, wouldPos);
			return true;
		}

		if (entity instanceof Squirrel squirrel) {
			itsOk = squirrelMoveRuling(squirrel, hindrance);
		} else if (hindrance instanceof Squirrel squirrel) { // ?
			itsOk = squirrelMoveRuling(squirrel, entity);
		}

		if (hindrance.isDead()) {reMove(hindrance);}

		if (entity.isDead()) {reMove(entity);}

		if (itsOk) {move(entity, wouldPos);}

		return itsOk;
	}

	private boolean squirrelMoveRuling(Squirrel squirrel, Entity hindrance) { // ?
		if (!(hindrance instanceof Squirrel)) {
			squirrel.updateEnergy(hindrance.getEnergy());

			if (hindrance instanceof Wall) {
				squirrel.stunned();
				return false;
			} else if (!(hindrance instanceof BadBeast)) {
				hindrance.die();
				reCreate(hindrance);
				return true;
			} else {
				((BadBeast) hindrance).payLifePoint();
				if (hindrance.isDead()) {
					reCreate(hindrance);
					return true;
				}
				return false;
			}
		}

		if (squirrel instanceof MiniSquirrel miniSquirrel) {
			if (hindrance instanceof HandOperatedMasterSquirrel handOpMs) {
				if (miniSquirrel.getParentToken() == handOpMs.getId()) { // wenn mini auf gleiches Mastersquirrel
					handOpMs.updateEnergy(miniSquirrel.getEnergy());
					miniSquirrel.die();
				} else {
					miniSquirrel.die(); // wenn mini auf anderes MasterSquirrel
				}
			} else if (hindrance instanceof MiniSquirrel miniHindrance) { // mini auf anderes mini
				if (miniSquirrel.getParentToken() != miniHindrance.getParentToken()) {
					miniSquirrel.die();
				} else {
					// passiert nix mini auf gleiches mini
				}
			}
		}

		if (squirrel instanceof HandOperatedMasterSquirrel handOpMs1) { // master auf master
			if (hindrance instanceof HandOperatedMasterSquirrel) {
				return false; // passiert nix
			} else if (hindrance instanceof MiniSquirrel miniSquirrel1) { // master auf gleiches mini
				if (handOpMs1.getId() == miniSquirrel1.getParentToken()) {
					handOpMs1.updateEnergy(miniSquirrel1.getEnergy());
					miniSquirrel1.die();
				} else {
					miniSquirrel1.die(); // master auf anderes mini
				}
			}
		}
		return true;
	}

	private void move(Entity entity, XY newPos) {
		XY oldPos = entity.getPos();
		entityArray[newPos.getX()][newPos.getY()] = entity;
		entityArray[oldPos.getX()][oldPos.getY()] = null;
	}

	public void reCreate(Entity altair) {
		int x, y;
		do {
			x = random.nextInt(flatBoardSize.getX());
			y = random.nextInt(flatBoardSize.getY());
		} while (entityArray[x][y] != null);

		if (altair instanceof GoodPlant) {
			GoodPlant gp = new GoodPlant(x, y);
			entityset.addEntity(gp);
			entityArray[x][y] = gp;
		} else if (altair instanceof BadPlant) {
			BadPlant bp = new BadPlant(x, y);
			entityset.addEntity(bp);
			entityArray[x][y] = bp;
		} else if (altair instanceof BadBeast) {
			BadBeast bb = new BadBeast(x, y);
			entityset.addEntity(bb);
			entityArray[x][y] = bb;
		} else if (altair instanceof GoodBeast) {
			GoodBeast gb = new GoodBeast(x, y);
			entityset.addEntity(gb);
			entityArray[x][y] = gb;
		}
	}

	public void reMove(Entity altair) {
		entityArray[altair.getPos().getX()][altair.getPos().getY()] = null;
		entityset.removeEntity(altair);
	}

	public XY getSize() {return this.flatBoardSize;}

	public EntityType getEntityType(int x, int y) {
		Entity entity;
		entity = entityArray[x][y];

		if (entity == null) {
			return EntityType.NULL;
		} else if (entity instanceof HandOperatedMasterSquirrel) {
			return EntityType.MASTERSQUIRREL;
		} else if (entity instanceof GoodBeast) {
			return EntityType.GOODBEAST;
		} else if (entity instanceof BadBeast) {
			return EntityType.BADBEAST;
		} else if (entity instanceof GoodPlant) {
			return EntityType.GOODPLANT;
		} else if (entity instanceof BadPlant) {
			return EntityType.BADPLANT;
		} else if (entity instanceof Wall) {
			return EntityType.WALL;
		}

		return EntityType.NULL;
	}
}

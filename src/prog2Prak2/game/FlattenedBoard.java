package prog2Prak2.game;

import prog2Prak2.entities.*;

import java.lang.reflect.Constructor;
import java.util.Random;

import HandOperated.HandOperatedMasterSquirrel;

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

	public void move(Entity entity, XY wouldPos) {
		// Darf nicht außerhalb des Spielfeldes sein
		boolean isNotOutOfBounds = true;
		boolean isHindranceNotNull = true;
		int x, y;
		XY oldPos = entity.getPos();
		x = wouldPos.getX();
		y = wouldPos.getY();

		if (x < 0 || y < 0) {
			entity.updatePosition(oldPos);
			isNotOutOfBounds = false;
		}

		if (isNotOutOfBounds) {
			Entity hindrance = entityArray[x][y];

			if (hindrance == null) {
				updatePos(entity, wouldPos);
				isHindranceNotNull = false;
			}
			// erst weitermachen wenn hindrance nicht null ist
			if (isHindranceNotNull) {
				if (entity instanceof Squirrel squirrel) {
					squirrelMoveRuling(squirrel, hindrance);
				} else if (entity instanceof BadBeast badBeast) {
					beastMoveRuling(badBeast, hindrance);
				}
				if (hindrance.isDead()) {
					reMove(hindrance);
				}

				if (entity.isDead()) {
					reMove(entity);
				}
			}
		}
	}

	private void beastMoveRuling(BadBeast beast, Entity hindrance) {

		switch (getEntityType(hindrance.getPos().getX(), hindrance.getPos().getY())) {
		case HANDOPERATEDMASTERSQUIRREL:
			hindrance.updateEnergy(beast.getEnergy());
			beast.payLifePoint();
			if (beast.isDead()) {
				reCreate(beast);
			}
			if (hindrance.isDead()) {
				beast.updatePosition(hindrance.getPos());
			}
			break;
		case MASTERSQUIRREL:
			hindrance.updateEnergy(beast.getEnergy());
			beast.payLifePoint();
			if (beast.isDead()) {
				reCreate(beast);
			}
			if (hindrance.isDead()) {
				beast.updatePosition(hindrance.getPos());
			}
			break;
		case MINISQUIRREL:
			hindrance.updateEnergy(beast.getEnergy());
			beast.payLifePoint();
			if (beast.isDead()) {
				reCreate(beast);
			}
			if (hindrance.isDead()) {
				beast.updatePosition(hindrance.getPos());
			}
		default:
			break;
		}

	}

	private void squirrelMoveRuling(Squirrel squirrel, Entity hindrance) {

		boolean gehWeiter = true;
		// Squirrel auf alles was nicht Squirrel ist
		switch (getEntityType(hindrance.getPos().getX(), hindrance.getPos().getY())) {
		case WALL:
			squirrel.updateEnergy(hindrance.getEnergy());
			squirrel.stunned();
			gehWeiter = false;
			break;
		case GOODBEAST:
			squirrel.updateEnergy(hindrance.getEnergy());
			hindrance.die();
			reCreate(hindrance);
			updatePos(squirrel, hindrance.getPos());
			gehWeiter = false;
			break;
		case BADPLANT:
			squirrel.updateEnergy(hindrance.getEnergy());
			hindrance.die();
			reCreate(hindrance);
			updatePos(squirrel, hindrance.getPos());
			gehWeiter = false;
			break;
		case GOODPLANT:
			squirrel.updateEnergy(hindrance.getEnergy());
			hindrance.die();
			reCreate(hindrance);
			updatePos(squirrel, hindrance.getPos());
			gehWeiter = false;
			break;
		case BADBEAST:
			squirrel.updateEnergy(hindrance.getEnergy());
			((BadBeast) hindrance).payLifePoint();
			if (hindrance.isDead()) {
				reCreate(hindrance);
				updatePos(squirrel, hindrance.getPos());
			}
			gehWeiter = false;
			break;
		default:
			break;
		}

		if (gehWeiter) {
			// Squirrel ist Mini
			if (squirrel instanceof MiniSquirrel) {
				switch (getEntityType(hindrance.getPos().getX(), hindrance.getPos().getY())) {
				case MASTERSQUIRREL:
					if (((MiniSquirrel) squirrel).getParentToken() == hindrance.getId()) {
						hindrance.updateEnergy(squirrel.getEnergy());
						squirrel.die();
					} else {
						squirrel.die();
					}
					break;
				case MINISQUIRREL:
					if (((MiniSquirrel) squirrel).getParentToken() != ((MiniSquirrel) hindrance).getParentToken()) {
						squirrel.die();
					}
					break;
				default:
					break;
				}
			}
			// Squirrel ist Master
			if (squirrel instanceof MasterSquirrel) {
				switch (getEntityType(hindrance.getPos().getX(), hindrance.getPos().getY())) {
				case MASTERSQUIRREL:
					break;
				case MINISQUIRREL:
					if (squirrel.getId() == ((MiniSquirrel) hindrance).getParentToken()) {
						squirrel.updateEnergy(hindrance.getEnergy());
						((MiniSquirrel) hindrance).die();
						updatePos(squirrel, hindrance.getPos());
					} else {
						((MiniSquirrel) hindrance).die();
						updatePos(squirrel, hindrance.getPos());
					}
					break;
				default:
					break;
				}
			}
		}
	}

	private void updatePos(Entity entity, XY newPos) {
		XY oldPos = entity.getPos();
		entity.updatePosition(newPos);
		entityArray[newPos.getX()][newPos.getY()] = entity;
		entityArray[oldPos.getX()][oldPos.getY()] = null;
	}

	public void reCreate(Entity altair) {
		int x, y;
		do {
			x = random.nextInt(flatBoardSize.getX());
			y = random.nextInt(flatBoardSize.getY());
		} while (entityArray[x][y] != null);

		switch (getEntityType(altair.getPos().getX(), altair.getPos().getY())) {
		case GOODPLANT:
			GoodPlant gp = new GoodPlant(x, y);
			entityset.addEntity(gp);
			entityArray[x][y] = gp;
			break;
		case BADPLANT:
			BadPlant bp = new BadPlant(x, y);
			entityset.addEntity(bp);
			entityArray[x][y] = bp;
			break;
		case GOODBEAST:
			GoodBeast gb = new GoodBeast(x, y);
			entityset.addEntity(gb);
			entityArray[x][y] = gb;
			break;
		case BADBEAST:
			BadBeast bb = new BadBeast(x, y);
			entityset.addEntity(bb);
			entityArray[x][y] = bb;
			break;
		default:
			break;
		}
	}

	public void reCreator(Entity altair) {
		int x, y;
		do {
			x = random.nextInt(flatBoardSize.getX());
			y = random.nextInt(flatBoardSize.getY());
		} while (entityArray[x][y] != null);

		try {
			Class<?> entClass = altair.getClass();
			Constructor<?> entConst = entClass.getConstructor(int.class, int.class);
			Entity rePlica = (Entity) entConst.newInstance(x, y);
			entityset.addEntity(rePlica);
			entityArray[x][y] = rePlica;
		} catch (Exception e) {
			System.out.println("Failed to re:Create");
		}
	}

	public void reMove(Entity altair) {
		entityArray[altair.getPos().getX()][altair.getPos().getY()] = null;
		entityset.removeEntity(altair);
	}

	public XY getSize() {
		return this.flatBoardSize;
	}

	public String[] getSquirrelAndEnergy() {
		return squirrelList.getSquirrelAndEnergy();
	}

	public EntityType getEntityType(int x, int y) {
		Entity entity;
		entity = entityArray[x][y];

		if (entity == null) {
			return EntityType.NULL;
	//	} else if (entity instanceof HandOperatedMasterSquirrel) {
		//	return EntityType.HANDOPERATEDMASTERSQUIRREL;
		} else if (entity instanceof MasterSquirrel) {
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
		} else if (entity instanceof MiniSquirrel) {
			return EntityType.MINISQUIRREL;
		} else if (entity instanceof MiniSquirrelBot) {
			return EntityType.MINISQUIRRELBOT;
		} else if (entity instanceof MasterSquirrelBot) {
			return EntityType.MASTERSQUIRRELBOT;
		}

		return EntityType.NULL;
	}

	public Entity[] getAffectedEntities(XY startPos, int impactRadius) {
		Entity[] draggedDownIntoTheGrave;
		Entity entity;

		XY radiusVector = new XY(impactRadius, impactRadius);
		XY leftCorner = startPos.vectorDiffCalc(radiusVector);

		int startPointY = leftCorner.getY();
		int startPointX = leftCorner.getX();

		XY rightCorner = startPos.vectorAdder(radiusVector);
		int endPointY = rightCorner.getY();
		int endPointX = rightCorner.getX();

		int entityCounter = 0;

		if (endPointY > flatBoardSize.getY()) {
			endPointY = flatBoardSize.getY();
		}

		if (endPointX > flatBoardSize.getX()) {
			endPointX = flatBoardSize.getX();
		}

		for (int y = startPointY; y <= endPointY; y++) {
			for (int x = startPointX; x <= endPointX; x++) {
				if (getEntityType(x, y) != null) {
					entityCounter++;
				}
			}
		}

		draggedDownIntoTheGrave = new Entity[entityCounter];

		int arrCounter = 0;
		for (int y = startPointY; y <= endPointY; y++) {
			for (int x = startPointX; x <= endPointX; x++) {
				if (getEntityType(x, y) != null) {
					entity = entityArray[x][y];
					draggedDownIntoTheGrave[arrCounter] = entity;
					arrCounter++;
				}
			}
		}

		return draggedDownIntoTheGrave;
	}

	public boolean isInRange(MiniSquirrelBot msb, Entity entityToTestToShoukanjuu) {

		int radius = msb.getImpactRadius();
		XY distanceVector = msb.getPos().fakeDiffCalc(entityToTestToShoukanjuu.getPos());
		int distanceX = distanceVector.getX();
		int distanceY = distanceVector.getY();

		double distance;
		distance = distanceX ^ 2 + distanceY ^ 2;
		distance = Math.sqrt(distance);

		if (radius > distance) {
			return true;
		}

		return false;
	}

	public void implode(MiniSquirrelBot msb) {
		Entity[] affectedEntities = getAffectedEntities(msb.getPos(), msb.getImpactRadius());
		Entity entity;
		double impactArea = msb.getImpactRadius() * msb.getImpactRadius() * Math.PI;
		double energyLoss;
		MasterSquirrel father = entityset.lookingForAFather(msb);
		double collectedEnergy = 0;

		for (int i = 0; i < affectedEntities.length; i++) {
			entity = affectedEntities[i];
			XY distanceVector = msb.getPos().fakeDiffCalc(entity.getPos());
			int distance;
			if (distanceVector.getX() < distanceVector.getY()) {
				distance = distanceVector.getY();
			} else {
				distance = distanceVector.getX();

			}
			energyLoss = 200 * (entity.getEnergy() / impactArea) * (1 - distance / msb.getImpactRadius());

			if (isInRange(msb, entity)) {
				switch (getEntityType(entity.getPos().getX(), entity.getPos().getY())) {
				case MASTERSQUIRREL:
					if (entity.getId() != father.getId()) {
						if (entity.getEnergy() - energyLoss <= 0) {
							collectedEnergy += entity.getEnergy();
							entity.updateEnergy(-entity.getEnergy());
							entity.die();
						} else {
							entity.updateEnergy(-energyLoss);
							collectedEnergy += energyLoss;
						}
					}
					break;
				case MINISQUIRREL:
					if (((MiniSquirrel) entity).getParentToken() != father.getId()) {
						if (entity.getEnergy() - energyLoss <= 0) {
							collectedEnergy += entity.getEnergy();
							entity.updateEnergy(-entity.getEnergy());
							entity.die(); // wann remove?
						} else {
							entity.updateEnergy(-energyLoss);
							collectedEnergy += energyLoss;
						}
					}
					break;
				case GOODBEAST:
				case GOODPLANT:
					if (entity.getEnergy() - energyLoss <= 0) {
						collectedEnergy += entity.getEnergy();
						entity.updateEnergy(-entity.getEnergy());
						entity.die();
						reCreate(entity);
					} else {
						entity.updateEnergy(-energyLoss);
						collectedEnergy += energyLoss;
					}
					break;
				case BADBEAST:
					if (entity.getEnergy() + energyLoss >= 0) {
						((BadBeast) entity).payLifePoint();
						if (((BadBeast) entity).isDead()) {
							reCreate(entity);
						}
					} else {
						entity.updateEnergy(energyLoss);
					}
					break;
				case BADPLANT:
					if (entity.getEnergy() + energyLoss >= 0) {
						entity.die();
						reCreate(entity);
					} else {
						entity.updateEnergy(energyLoss);
					}
					break;
				default:
					break;

				}
			}
		}
		father.updateEnergy(collectedEnergy);
		msb.die();
		reMove(msb);

	}

	@Override
	public void insert(MiniSquirrel miniSquirrel) {
		entityset.addEntity(miniSquirrel);
		entityArray[miniSquirrel.getPos().getX()][miniSquirrel.getPos().getY()] = miniSquirrel;
		
	}

}

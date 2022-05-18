package prog2Prak2.entities;

import prog2Prak2.game.BoardConfig;
import prog2Prak2.game.EntityType;
import prog2Prak2.game.FlattenedBoard;

public class MasterSquirrelBot extends MasterSquirrel{
	
	private BotControllerFactory botControllerFactory;
	private BotController botController;
	private ControllerContext controllerContext;
	public final int impactRadius = 15;
	
	
	public MasterSquirrelBot(int x, int y, BotControllerFactory bcf) {
		id = idCounter++;
		energy = 500;
		position = new XY(x, y);
		dead = false;
		this.botControllerFactory = bcf;
		this.botController = bcf.createMasterBotController();
		
		controllerContext = new ControllerContext() {

			@Override
			public XY getViewUpperLeft() {
				XY radiusVector = new XY(impactRadius, impactRadius);
				XY leftCorner = position.vectorDiffCalc(radiusVector);
				
				return leftCorner;
				
			}

			@Override
			public XY getViewLowerRight() {
				XY radiusVector = new XY(impactRadius, impactRadius);
				XY rightCorner = position.vectorAdder(radiusVector);
				int cornerX = rightCorner.getX();
				int cornerY = rightCorner.getY();
				
				if(cornerX > BoardConfig.spaltenX) {
					cornerX = BoardConfig.spaltenX;
				}
				
				if(cornerY > BoardConfig.reihenY) {
					cornerY = BoardConfig.reihenY;
				}
			
				rightCorner = new XY(cornerX, cornerY);
				
				return rightCorner;
			}

			@Override
			public EntityType getEntityAt(XY xy) {
				XY leftCorner = getViewUpperLeft();
				
				if(xy.getX() < leftCorner.getX() || xy.getY() < leftCorner.getY()) {
					return EntityType.NULL;
				}
				
				XY rightCorner = getViewLowerRight();
				
				if(xy.getX() > rightCorner.getX() || xy.getY() > rightCorner.getY()) {
					return EntityType.NULL;
				}
				
				return entityContext.getEntityType(xy.getX(), xy.getY());
			}

			@Override
			public void move(XY direction) {
				entityContext.move(MasterSquirrelBot.this, direction);
				
			}

			@Override
			public void spawnMiniBot(XY direction, int energy) {
				
				
			}

			@Override
			public int getEnergy() {
				return energy;
			}
			
		};
	}
	
	@Override
	public MiniSquirrel spawnMinisquirrel(int energy, XY direction, FlattenedBoard flatIsJustice)
			throws Exception, NotEnoughEnergyException {
		if (this.energy - energy <= 0) {
			throw new NotEnoughEnergyException("Not enough energy! You only have " + this.energy + " energy!"
					+ " but you wanted to have " + energy + " energy! ");
		}

		MiniSquirrel miniSquirrel;
		int x = 0;
		int y = 0;

		XY newPos = this.position.move(direction);
		x = newPos.getX();
		y = newPos.getY();

		if (flatIsJustice.getEntityType(x, y) != EntityType.NULL) {
			throw new Exception("Desired directon for MiniSquirrel not free");
		}

		miniSquirrel = new MiniSquirrel(x, y, energy, this.id);

		updateEnergy(-energy);

		return miniSquirrel;
	}

	@Override
	public String getNameAndEnergy() {
		return "MasterSquirrelBot: " + this.energy;
	}

	@Override
	public void updateEnergy(int deltaWert) {
		energy = energy + deltaWert;
		if(energy <= 0) {
			dead = true;
		}
		
	}

	@Override
	public void nextStep() {
		if (stunCounter == 0) {
			botController.nextStep(controllerContext);
		} else {
			stunCounter--;
		}
	}

	@Override
	public String toString() {
		return "MasterSquirrelBot nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}

}

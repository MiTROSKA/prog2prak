package prog2Prak2.entities;

import prog2Prak2.game.BoardConfig;
import prog2Prak2.game.EntityContext;
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
		
	}
	
	class MSBotContext implements ControllerContext{
		private EntityContext entityContext;
		
		public MSBotContext(EntityContext entityContext) {
			this.entityContext = entityContext;
		}
		
		MiniSquirrel mini;
		public void updateEnergy(int deltaWert) {
			energy = energy + deltaWert;
			if(energy <= 0) {
				dead = true;
			}
			
		}
		
		public MiniSquirrel getMini() {
			return mini;
		}

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
			XY newPos = position.move(direction);
			entityContext.move(MasterSquirrelBot.this, newPos);
			
		}

		@Override
		public void spawnMiniBot(XY direction, int energy) throws SpawnException {
			XY newPos = MasterSquirrelBot.this.position.move(direction);
			int x = 0;
			int y = 0;
			x = newPos.getX();
			y = newPos.getY();

			if (entityContext.getEntityType(x, y) != EntityType.NULL) {
				throw new SpawnException("Desired directon for MiniSquirrel not free");
			}

		
			MiniSquirrel ms = spawnMinisquirrel(energy, newPos);
			mini = ms;
			
		}

		@Override
		public int getEnergy() {
			return energy;
		}

		@Override
		public XY locate() {
			return position;
		}

		@Override
		public boolean isMine(XY xy) {
			XY leftCorner = getViewUpperLeft();
			
			if(xy.getX() < leftCorner.getX() || xy.getY() < leftCorner.getY()) {
				throw new OutOfViewException("Entity out of View!");
			}
			
			XY rightCorner = getViewLowerRight();
			
			if(xy.getX() > rightCorner.getX() || xy.getY() > rightCorner.getY()) {
				throw new OutOfViewException("Entity out of View!");
			}
			
			return entityContext.isRelated(MasterSquirrelBot.this, xy);
		
		}

		@Override
		public void implode(int impactRadius) {
			//does nothing
			
		}

		@Override
		public XY directionOfMaster() {
			// nothing, i am the master
			return null;
		}

		@Override
		public long getRemainingSteps() {
			
			return 0;
		}
		
		
	}
	@Override
	public MiniSquirrel spawnMinisquirrel(int energy, XY newPos) throws SpawnException {
		if (this.energy - energy <= 0) {
			throw new SpawnException("Not enough energy! You only have " + this.energy + " energy!"
					+ " but you wanted to have " + energy + " energy! ");
		}

		MiniSquirrel miniSquirrel;
		
		miniSquirrel = new MiniSquirrel(newPos.getX(), newPos.getY(), energy, this.id);

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
	public void nextStep(EntityContext entityContext) {
		if (stunCounter == 0) {
			botController.nextStep(new MSBotContext(entityContext));
		} else {
			stunCounter--;
		}
	}

	@Override
	public String toString() {
		return "MasterSquirrelBot nr: " + id + " Position: " + position.getX() + " " + position.getY();
	}
	
	

}

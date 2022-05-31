package prog2Prak2.entities;

import prog2Prak2.game.BoardConfig;
import prog2Prak2.game.EntityContext;
import prog2Prak2.game.EntityType;

public class MiniSquirrelBot extends MiniSquirrel {

	private BotControllerFactory botControllerFactory;
	private BotController botController;
	public final int impactRadius = 10;

	public MiniSquirrelBot(int x, int y, int energy, int Token, BotControllerFactory bcf) {
		super(x, y, energy, Token);

		this.botControllerFactory = bcf;
		this.botController = bcf.createMasterBotController();
	}

	class MiniSqBotContext implements ControllerContext {
		private EntityContext entityContext;
		
		public MiniSqBotContext(EntityContext entityContext) {
			this.entityContext = entityContext;
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

			if (cornerX > BoardConfig.spaltenX) {
				cornerX = BoardConfig.spaltenX;
			}

			if (cornerY > BoardConfig.reihenY) {
				cornerY = BoardConfig.reihenY;
			}

			rightCorner = new XY(cornerX, cornerY);

			return rightCorner;
		}

		@Override
		public EntityType getEntityAt(XY xy) {
			XY leftCorner = getViewUpperLeft();

			if (xy.getX() < leftCorner.getX() || xy.getY() < leftCorner.getY()) {
				return EntityType.NULL;
			}

			XY rightCorner = getViewLowerRight();

			if (xy.getX() > rightCorner.getX() || xy.getY() > rightCorner.getY()) {
				return EntityType.NULL;
			}

			return entityContext.getEntityType(xy.getX(), xy.getY());
		}

		@Override
		public void move(XY direction) {
			entityContext.move(MiniSquirrelBot.this, direction);

		}

		@Override
		public void spawnMiniBot(XY direction, int energy) {
			// does nothing

		}

		@Override
		public int getEnergy() {
			return energy;
		}
		
		@Override
		public void updateEnergy(int deltaWert) {
			energy = energy + deltaWert;
			if(energy <= 0) {
				dead = true;
			}
		}

		@Override
		public MiniSquirrel getMini() {
			return null;
		}
		
		public void implode(int impactRadius) {
			entityContext.implode(MiniSquirrelBot.this, impactRadius);
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
			
			return entityContext.isRelated(MiniSquirrelBot.this, xy);
		}

		@Override
		public XY directionOfMaster() {// muss man out of view prüfen?
			MasterSquirrel masterSq = entityContext.getFather(MiniSquirrelBot.this);
			XY diffVector = position.realDiffCalc(masterSq.getPos());
			XY direction = diffVector.abnormalize();
			
			
			return direction;
		}

		@Override
		public long getRemainingSteps() {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	public int getImpactRadius() {
		return this.impactRadius;
	}

	public void nextStep(EntityContext entityContext) {
		if (stunCounter == 0) {
			botController.nextStep(new MiniSqBotContext(entityContext));
		} else {
			stunCounter--;
		}
	}
}

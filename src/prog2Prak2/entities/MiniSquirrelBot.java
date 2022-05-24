package prog2Prak2.entities;

import prog2Prak2.game.BoardConfig;
import prog2Prak2.game.EntityType;

public class MiniSquirrelBot extends MiniSquirrel {

	private BotControllerFactory botControllerFactory;
	private BotController botController;
	private ControllerContext controllerContext;
	public final int impactRadius = 10;

	public MiniSquirrelBot(int x, int y, int energy, int Token, BotControllerFactory bcf) {
		super(x, y, energy, Token);

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

			
			public void updateEnergy(int deltaWert) {
			
				}

			@Override
			public MiniSquirrel getMini() {
				// TODO Auto-generated method stub
				return null;
			}
				
			

		};

	}

	public int getImpactRadius() {
		return this.impactRadius;
	}

	public void nextStep() {
		if (stunCounter == 0) {
			botController.nextStep(controllerContext);
		} else {
			stunCounter--;
		}
	}
}

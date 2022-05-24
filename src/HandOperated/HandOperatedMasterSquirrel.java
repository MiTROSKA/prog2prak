package HandOperated;

import java.util.Random;

import prog2Prak2.entities.BotController;
import prog2Prak2.entities.ControllerContext;
import prog2Prak2.entities.MiniSquirrel;
import prog2Prak2.entities.NotEnoughEnergyException;
import prog2Prak2.entities.XY;
import prog2Prak2.game.MoveCommand;

public class HandOperatedMasterSquirrel implements BotController {
	private MoveCommand moveCommand;
	private ControllerContext conCon = null;
	 private static final Random random = new Random();


	@Override
	public void nextStep(ControllerContext controllerContext) {
		 conCon = controllerContext;
		if (moveCommand != null) {
			conCon.move(moveCommand.direction);
			moveCommand = new MoveCommand(new XY(0,0));
        }
       
		
	}
	
	public void setMoveCommand(MoveCommand moveCommand) {
		this.moveCommand = moveCommand;
	}
	
	public String getNameAndEnergy() {
		return "HandOperatedSquirrel: " + conCon.getEnergy();
	}

	public int getEnergy() {
		return conCon.getEnergy();
	}
	
	public boolean isDead() {
        if (conCon != null) {
            if (conCon.getEnergy() <= 0) {
                return true;
            }
        }
        return false;
    }
	
	public void spawnMiniSquirrel(int energy) throws NotEnoughEnergyException {
        int x;
        int y;
        do {
            x = random.nextInt(3) - 1;
            y = random.nextInt(3) - 1;
        } while (x == 0 || y == 0);
        XY direction = new XY(x, y);
        conCon.spawnMiniBot(direction, energy);
        
    }

	public void updateEnergy(int i) {
		conCon.updateEnergy(i);
		
	}
	public MiniSquirrel getMini() {
		return conCon.getMini();
	}

	
	
	
}

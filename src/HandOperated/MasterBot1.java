package HandOperated;

import java.util.Random;

import prog2Prak2.entities.BotController;
import prog2Prak2.entities.BotControllerFactory;
import prog2Prak2.entities.ControllerContext;
import prog2Prak2.entities.MasterSquirrelBot;
import prog2Prak2.entities.MiniSquirrel;
import prog2Prak2.entities.SpawnException;
import prog2Prak2.entities.XY;

public class MasterBot1 extends MasterSquirrelBot implements BotController {
	
	private ControllerContext conCon = null;
	private static final Random random = new Random();
	
	public MasterBot1(int x, int y, BotControllerFactory bcf) {
		super(x, y, bcf);
		
	}
	
	@Override
	public void nextStep(ControllerContext controllerContext) {
		 conCon = controllerContext;
		 XY wouldPos = position.randomMove();
		 conCon.move(wouldPos);
	}
	
	
	public String getNameAndEnergy() {
		return "MasterBot1: " + conCon.getEnergy();
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
	
	public void spawnMiniSquirrel(int energy) throws SpawnException {
        int x;
        int y;
        do {
            x = random.nextInt(3) - 1;
            y = random.nextInt(3) - 1;
        } while (x == 0 && y == 0);
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



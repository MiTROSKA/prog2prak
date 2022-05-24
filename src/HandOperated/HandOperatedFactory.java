package HandOperated;

import prog2Prak2.entities.BotController;
import prog2Prak2.entities.BotControllerFactory;
import prog2Prak2.entities.ControllerContext;

public class HandOperatedFactory implements BotControllerFactory{

	private HandOperatedMasterSquirrel hOMS = null;

    @Override
    public BotController createMasterBotController() {
        if (hOMS == null) {
            return hOMS = new HandOperatedMasterSquirrel();
        } else {
            throw new UnsupportedOperationException("Only one hOMS");
        }
    }

	

	@Override
	public BotController createMiniBotController() {
		
		return new BotController() {

			@Override
			public void nextStep(ControllerContext controllerContext) {
				
			}
			
		};
	}
	
	public HandOperatedMasterSquirrel getHOMS() {
        return hOMS;
    }

}

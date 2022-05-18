package prog2Prak2.entities;

import prog2Prak2.game.FlattenedBoard;

public abstract class MasterSquirrel extends Squirrel{

	public abstract MiniSquirrel spawnMinisquirrel(int energy, XY direction, FlattenedBoard flatIsJustice) throws Exception, NotEnoughEnergyException;
	
}



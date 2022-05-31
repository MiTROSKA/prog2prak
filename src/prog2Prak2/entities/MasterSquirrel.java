package prog2Prak2.entities;


public abstract class MasterSquirrel extends Squirrel{

	public abstract MiniSquirrel spawnMinisquirrel(int energy, XY direction) throws Exception, NotEnoughEnergyException;
	
}



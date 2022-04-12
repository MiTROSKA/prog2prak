package prog2Prak2.unitTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2Prak2.entities.*;
import prog2Prak2.game.FlattenedBoard;
import prog2Prak2.game.MoveCommand;

public class CollisionTest {
	EntitySet entityset;
	private final XY size = new XY(50, 50);
	
	@BeforeEach
	public void startPhase() {
		entityset = new EntitySet();
	}
	
	@Test
	public void squirrelWallTest() { //3 runden stun
		entityset.addEntity(new Wall(0, 0));
		HandOperatedMasterSquirrel handOpMs = new HandOperatedMasterSquirrel(0, 1);
		entityset.addEntity(handOpMs);
		XY oldPos = handOpMs.getPos();
		int oldEnergy = handOpMs.getEnergy();
		XY newPos = handOpMs.getPos().move(XY.UP);
		
		new FlattenedBoard(entityset, size).moveOk(handOpMs, newPos);
		assertEquals(oldPos, handOpMs.getPos());
		
		for(int i = 0; i < 3; i++) { 
			handOpMs.nextStep();
			assertEquals(oldPos, handOpMs.getPos());
		}
		assertEquals(oldEnergy, handOpMs.getEnergy()+30);
	}
	
	@Test
	public void squirrelGoodPlantTest() {
		HandOperatedMasterSquirrel h = new HandOperatedMasterSquirrel(0,0);
		entityset.addEntity(h);
		GoodPlant gp = new GoodPlant(0,1);
		entityset.addEntity(gp);
		XY newPos = h.getPos().move(XY.DOWN);
		int shouldEnergy = h.getEnergy() + gp.getEnergy();
		
		new FlattenedBoard(entityset, size).moveOk(h, newPos);
		assertEquals(shouldEnergy, h.getEnergy());
		universalTester(entityset, h, gp);
	}
	
	@Test
	public void squirrelBadPlantTest() {
		HandOperatedMasterSquirrel h = new HandOperatedMasterSquirrel(0,0);
		entityset.addEntity(h);
		BadPlant bp = new BadPlant(0,1);
		entityset.addEntity(bp);
		XY newPos = h.getPos().move(XY.DOWN);
		int shouldEnergy = h.getEnergy() + bp.getEnergy();
		
		new FlattenedBoard(entityset, size).moveOk(h, newPos);
		assertEquals(shouldEnergy, h.getEnergy());
		universalTester(entityset, h, bp);
	}

	@Test
	public void beastWallTest() { //passiert nix
		entityset.addEntity(new Wall(0,0));
		GoodBeast gb = new GoodBeast(0,1);
		entityset.addEntity(gb);
		XY oldPos = gb.getPos();
		XY newPos = gb.getPos().move(XY.UP);
		
		new FlattenedBoard(entityset, size).moveOk(gb, newPos);
		assertEquals(oldPos, gb.getPos());
	}
	
	@Test
	public void beastPlantTest() {
		entityset.addEntity(new GoodPlant(0,0));
		BadBeast bb = new BadBeast(0,1);
		entityset.addEntity(bb);
		XY oldPos = bb.getPos();
		XY newPos = bb.getPos().move(XY.UP);
		
		new FlattenedBoard(entityset, size).moveOk(bb, newPos);
		assertEquals(oldPos, bb.getPos());
	}
	
	@Test
	public void goodBeastSquirrelTest() {
		HandOperatedMasterSquirrel h = new HandOperatedMasterSquirrel(0,0);
		entityset.addEntity(h);
		GoodBeast gb = new GoodBeast(0,1);
		entityset.addEntity(gb);
		int shouldEnergy = h.getEnergy() + gb.getEnergy();
		XY shouldPos = new XY(0,3);
		
		Entity.setEntityContext(new FlattenedBoard(entityset, size));
		
		for(int i = 0; i < 2*4; i++) {gb.nextStep();}

		assertEquals(shouldPos.getX(), gb.getPos().getX());
		assertEquals(shouldPos.getY(), gb.getPos().getY());
		
		for(int i = 0; i < 3; i++) {
			h.setMoveCommand(new MoveCommand(XY.DOWN));
			h.nextStep();
		}

		assertTrue(gb.isDead());
		assertEquals(shouldEnergy, h.getEnergy());
		universalTester(entityset, h, gb);
	}

	@Test
	public void badBeastSquirrel() {
		HandOperatedMasterSquirrel h = new HandOperatedMasterSquirrel(0,0);
		entityset.addEntity(h);
		BadBeast bb = new BadBeast(0,3);
		entityset.addEntity(bb);
		int shouldEnergy = h.getEnergy() + bb.getEnergy();
		XY oldPos = h.getPos();
		
		Entity.setEntityContext(new FlattenedBoard(entityset, size));
		for(int i = 0; i < 2*4; i++) {bb.nextStep();}
		
		XY shouldPos = new XY(0,1);
		assertEquals(shouldPos.getX(), bb.getPos().getX());
		assertEquals(shouldPos.getY(), bb.getPos().getY());
		
		h.setMoveCommand(new MoveCommand(XY.DOWN));
		h.nextStep();

		assertEquals(6, bb.getLifePoints());
		assertEquals(shouldEnergy, h.getEnergy());
		assertFalse(bb.isDead());
		assertEquals(oldPos, h.getPos());
		
		h.updateEnergy(10000);
		
		for(int i = 0; i < 6; i++) {
			h.setMoveCommand(new MoveCommand(XY.DOWN));
			h.nextStep();
		}
		
		assertTrue(bb.isDead());
		universalTester(entityset, h, bb);

	}
	
	//Testet ob removed wurde, ob neues erstellt wurde, ob selbe position
	private void universalTester(EntitySet entityset, Squirrel squirrel, Entity entity) {
		assertFalse(entityset.isThere(entity));
		assertSame(entityset.getEntityAt(0), squirrel);
		assertNotSame(entityset.getEntityAt(1), entity);
		if(entity instanceof GoodPlant) {
        	assertTrue(entityset.getEntityAt(1) instanceof GoodPlant);
        } if(entity instanceof BadPlant) {
        	assertTrue(entityset.getEntityAt(1) instanceof BadPlant);
        } if(entity instanceof GoodBeast) {
        	assertTrue(entityset.getEntityAt(1) instanceof GoodBeast);
        } if(entity instanceof BadBeast) {
        	assertTrue(entityset.getEntityAt(1) instanceof BadBeast);
        }
		assertTrue(entityset.reallyMoved(squirrel, entityset.getEntityAt(1)));
	}
	
	@Test
	public void miniSquirrelEnergyTest() {
		MiniSquirrel m = new MiniSquirrel(0, 0, 100, 13);
		entityset.addEntity(m);
		Entity.setEntityContext(new FlattenedBoard(entityset, size));
		
		for(int i = 100; i > 0; i--)  {
			assertEquals(m.getEnergy(), i);
			m.nextStep();
		}
		assertTrue(m.isDead());
	}
	
	@Test
	public void miniSquirrelownMasterSquirrelCollTest() {
		HandOperatedMasterSquirrel h = new HandOperatedMasterSquirrel(0,0);
    	MiniSquirrel miniSquirrel = new MiniSquirrel(0, 1, 100, h.getId());
    	entityset.addEntity(miniSquirrel);
    	entityset.addEntity(h);
    	
    	Entity.setEntityContext(new FlattenedBoard(entityset, size));
    	h.setMoveCommand(new MoveCommand(XY.DOWN));
    	h.nextStep();
    	assertTrue(miniSquirrel.isDead());
    	assertFalse(entityset.isThere(miniSquirrel));
    	assertTrue(entityset.isThere(h));
		assertEquals(600, h.getEnergy());
	}
	
	@Test 
	public void miniSquirrelOtherMasterSquirrelCollTest() {
		HandOperatedMasterSquirrel h = new HandOperatedMasterSquirrel(0,0);
    	MiniSquirrel miniSquirrel = new MiniSquirrel(0, 1, 100, h.getId()+1);
    	entityset.addEntity(h);
    	entityset.addEntity(miniSquirrel);
    	XY newPos = miniSquirrel.getPos().move(XY.UP);
    	
    	new FlattenedBoard(entityset, size).moveOk(miniSquirrel, newPos);
    	assertTrue(miniSquirrel.isDead());
    	assertFalse(entityset.isThere(miniSquirrel));
    	assertTrue(entityset.isThere(h));
		assertEquals(500, h.getEnergy()); //?
	}
	
	@Test
	public void miniSquirrelOwnMiniSquirrelCollTest() {
		MiniSquirrel miniSquirrel1 = new MiniSquirrel(0,0, 200, 100);
    	MiniSquirrel miniSquirrel2 = new MiniSquirrel(0,1, 100, 100);
    	entityset.addEntity(miniSquirrel1);
    	entityset.addEntity(miniSquirrel2);
    	XY newPos = miniSquirrel2.getPos().move(XY.UP);
    	
    	new FlattenedBoard(entityset, size).moveOk(miniSquirrel2, newPos);
    	assertFalse(miniSquirrel1.isDead());
    	assertFalse(miniSquirrel2.isDead());
    	assertTrue(entityset.isThere(miniSquirrel2) && entityset.isThere(miniSquirrel2));
    	assertTrue(miniSquirrel1.getEnergy() == 200 && miniSquirrel2.getEnergy() == 100);
	}
	
	@Test
	public void miniSquirrelOtherMiniSquirrelCollTest() {
		MiniSquirrel miniSquirrel1 = new MiniSquirrel(0, 0, 200, 100);
    	MiniSquirrel miniSquirrel2 = new MiniSquirrel(0, 1, 100, 200);
    	entityset.addEntity(miniSquirrel1);
    	entityset.addEntity(miniSquirrel2);
    	XY newPos1 = miniSquirrel1.getPos().move(XY.DOWN);
    	
    	new FlattenedBoard(entityset, size).moveOk(miniSquirrel1, newPos1);
    	assertTrue(miniSquirrel1.isDead());
    	assertFalse(miniSquirrel2.isDead());
    	assertFalse(entityset.isThere(miniSquirrel1));
		assertEquals(100, miniSquirrel2.getEnergy());
    	
    	miniSquirrel1 = new MiniSquirrel(0, 0, 200, 100);
    	entityset.addEntity(miniSquirrel1);
    	XY newPos2 = miniSquirrel2.getPos().move(XY.UP);
    	
    	new FlattenedBoard(entityset, size).moveOk(miniSquirrel2, newPos2);
    	assertFalse(miniSquirrel1.isDead());
    	assertTrue(miniSquirrel2.isDead());
    	assertFalse(entityset.isThere(miniSquirrel2));
    	assertTrue(entityset.isThere(miniSquirrel1));
	}
}

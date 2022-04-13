package prog2Prak2.unitTest;

import static org.junit.jupiter.api.Assertions.*;
//import java.util.Arrays;
import java.util.Enumeration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2Prak2.entities.BadBeast;
import prog2Prak2.entities.BadPlant;
import prog2Prak2.entities.Entity;
import prog2Prak2.entities.EntitySet;
import prog2Prak2.entities.GoodBeast;
import prog2Prak2.entities.GoodPlant;
import prog2Prak2.entities.HandOperatedMasterSquirrel;
import prog2Prak2.entities.Squirrel;
import prog2Prak2.entities.Wall;
import prog2Prak2.entities.XY;
import prog2Prak2.entities.duplicateEntityException;
import prog2Prak2.entities.missingEntityException;
import prog2Prak2.game.EntityContext;

public class EntitySetTest {
	EntitySet entityset;
	GoodBeast gb;
	BadBeast bb;
	GoodPlant gp;
	BadPlant bp;
	Wall w;
	HandOperatedMasterSquirrel handOpMs;

	@BeforeEach
	public void startPhase() {
		entityset = new EntitySet();
		gb = new GoodBeast(23, 23);
		bb = new BadBeast(23, 23);
		gp = new GoodPlant(23, 23);
		bp = new BadPlant(23, 23);
		w = new Wall(23, 23);
		handOpMs = new HandOperatedMasterSquirrel(23,23);

		entityset.addEntity(gb);
		entityset.addEntity(bb);
		entityset.addEntity(gp);
		entityset.addEntity(bp);
		entityset.addEntity(w);
	}

	@Test
	public void addTest() {
		assertTrue(entityset.isThere(gb));
		assertTrue(entityset.isThere(bb));
		assertTrue(entityset.isThere(gp));
		assertTrue(entityset.isThere(bp));
		assertTrue(entityset.isThere(w));
		assertFalse(entityset.isThere(handOpMs));
	}

	@Test
	public void removeTest() { 
		entityset.removeEntity(gb);
		entityset.removeEntity(bb);
		entityset.removeEntity(gp);
		entityset.removeEntity(bp);
		entityset.removeEntity(w);

		assertFalse(entityset.isThere(gb));
		assertFalse(entityset.isThere(bb));
		assertFalse(entityset.isThere(gp));
		assertFalse(entityset.isThere(bp));
		assertFalse(entityset.isThere(w));
	}

	@Test
	public void removeOneTest() {
		entityset.removeEntity(bb);

		assertTrue(entityset.isThere(gb));
		assertFalse(entityset.isThere(bb));
		assertTrue(entityset.isThere(gp));
		assertTrue(entityset.isThere(bp));
		assertTrue(entityset.isThere(w));
	}

	@Test
	public void duplicateExcTest() {
		try {
			entityset.addEntity(bb);
			fail();
		} catch(duplicateEntityException ignored) {

		}
	}

	@Test
	public void missingEntityExcTest() {
		try {
			entityset.removeEntity(handOpMs);
			fail();
		} catch (missingEntityException ignored) {

		}
	}
	
	public static class testEntityContext implements EntityContext {
			
		public boolean moveOk(Entity entity, XY pos) {
			return true;
		}
		
		public XY getSize() {
			return null;
		}

		@Override
		public Squirrel getNearestSquirrel(XY startPos) {
			// TODO Auto-generated method stub
			return null;
		}
	}

	@Test
	public void nextStepTest() {
		
		GoodBeast gb1 = new GoodBeast(23, 23);
		BadBeast bb1 = new BadBeast(23,23);
		GoodPlant gp1 = new GoodPlant(23,23);  
		BadPlant bp1 = new BadPlant(23, 23);
		Wall w1 = new Wall(23,23);
	
		Entity.setEntityContext(new testEntityContext());
		entityset.nextStepCaller();
		
		//because of StepCounter
		for(int i = 0; i<3; i++) {
			gb.nextStep();
			bb.nextStep();
		}

		assertTrue(entityset.reallyMoved(gb, gb1));
		assertTrue(entityset.reallyMoved(bb, bb1));
		//pflanze kann sich nicht bewegen
		assertFalse(entityset.reallyMoved(gp, gp1));
		assertFalse(entityset.reallyMoved(bp, bp1));
		assertFalse(entityset.reallyMoved(w, w1));
	}
	
	@Test
	public void enumerateForwardTest() {
		Entity e0 = null;
		Entity e1 = null;
		Entity e2 = null;
		Entity e3 = null;
		Entity e4 = null;
		
		assertEquals(gb, e0 = e.nextElement());
		assertEquals(bb, e1 = e.nextElement());
		assertEquals(gp, e2 = e.nextElement());
		assertEquals(bp, e3 = e.nextElement());
		assertEquals(w, e4 = e.nextElement());
	} 
	
	@Test
	public void enumerateBackwardTest() {
		Entity e0 = null;
		Entity e1 = null;
		Entity e2 = null;
		Entity e3 = null;
		Entity e4 = null;
		
		assertEquals(gb, e4 = e.nextElement());
		assertEquals(bb, e3 = e.nextElement());
		assertEquals(gp, e2 = e.nextElement());
		assertEquals(bp, e1 = e.nextElement());
		assertEquals(w, e0 = e.nextElement());
		
	} 
	 
	@Test
	public void enumerateRandomTest() {
		Entity[]ogArray = new Entity[entityset.getListLength()];
		Entity[]randomEntArray = new Entity[entityset.getListLength()];
		Enumeration<Entity> e = entityset.enumerateRandom();
		Enumeration<Entity> e1 = entityset.enumerateForward();
	
		for(int i = 0; e.hasMoreElements(); i++) {
			randomEntArray[i] = e.nextElement();
		}
		
		for(int i = 0; e1.hasMoreElements(); i++) {
			ogArray[i] = e1.nextElement();
		}
		
		//assertNotEquals(Arrays.toString(ogArray), Arrays.toString(randomEntArray));
		assertNotEquals(ogArray,randomEntArray);
	}
}
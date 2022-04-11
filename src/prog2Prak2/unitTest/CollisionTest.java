package prog2Prak2.unitTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import prog2Prak2.entities.*;
import prog2Prak2.game.FlattenedBoard;

public class CollisionTest {
	EntitySet entityset;
	private final XY size = new XY(50, 50);
	
	
	
	@BeforeEach
	public void startPhase() {
		entityset = new EntitySet();
	}
	
	@Test
	public void wallSquirrelTest() {
		entityset.addEntity(new Wall(0, 0));
		HandOperatedMasterSquirrel handOpMs = new HandOperatedMasterSquirrel(0, 1);
		entityset.addEntity(handOpMs);
		XY oldPos = handOpMs.getPos();
		int oldEnergy = handOpMs.getEnergy();
		XY newPos = handOpMs.getPos().move(new XY(0, -1));
		
		new FlattenedBoard(entityset, size).moveOk(handOpMs, newPos);
		assertEquals(oldPos, handOpMs.getPos());
		
		for(int i = 0; i < 3; i++) { //?
			handOpMs.nextStep();
			assertEquals(oldPos, handOpMs.getPos());
		}
		assertEquals(oldEnergy, handOpMs.getEnergy()+30);
		
	}
	
	public void wallBeastTest() {
		entityset.addEntity(new Wall(0,0));
		GoodBeast gb = new GoodBeast(0,1);
		entityset.addEntity(gb);
		XY oldPos = gb.getPos();
		XY newPos = gb.getPos().move(new XY(0, -1));
		
		
		
		
		
		
	}
	

}

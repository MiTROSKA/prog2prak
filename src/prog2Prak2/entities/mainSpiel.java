package prog2Prak2.entities;

import prog2Prak2.game.EntityContext;

public class mainSpiel {

	public static void main(String[] args) {
		// Erstellung der Figuren
		Entity s = new HandOperatedMasterSquirrel(13,13);
		EntitySet entityset = new EntitySet();
		GoodBeast gb = new GoodBeast(13,13);
		BadBeast bb = new BadBeast(13,13);
		GoodPlant goodp = new GoodPlant(13,13);
		BadPlant bp = new BadPlant(13,13);

		// bewegliche objekte hinzuf�gen
		entityset.addEntity(s);
		entityset.addEntity(gb);
		entityset.addEntity(bb);

		// unbewegliche objekte hinzuf�gen
		entityset.addEntity(goodp);
		entityset.addEntity(bp);  

		// Main game loop
		System.out.println("Vor NextStep: ");
		System.out.println("List Length: " + entityset.getListLength());
		System.out.println(entityset);
		
		
		Entity.setEntityContext(new EntityContextDummy());
		//entityset.nextStepCaller();
		//entityset.removeEntity(s);
	//	Entity mini = s.spawnMinisquirrel(100);
		
		if(s instanceof MasterSquirrel) {
			System.out.println("fokfkok");
		}else {
			System.out.println("yessir");
		}
		
		
		
	//	System.out.println(entityset);
		
		
		
		

	/*
		int i;
		for (i = 0; i < 10; i++) {
			entityset.nextStepCaller();
			System.out.println("Nach NextStep: ");
			System.out.println("List Length: " + entityset.getListLength());
			System.out.println(entityset);
		} */

		/*
		 * // updateposition test System.out.println("Vor Update Position: " + '\n' + gb
		 * + '\n');
		 *
		 * gb.updatePosition(new XY(100, 100));
		 *
		 * System.out.println("Nach Update Position: " + '\n' + gb + '\n');
		 *
		 * // entfernen eines elements entityset.removeEntity(bp);
		 *
		 * // Liste nach entfernen System.out.println("Nach Remove: ");
		 * System.out.println("List Length: " + entityset.getListLength());
		 * System.out.println(entityset);
		 */
	}
}


class EntityContextDummy implements EntityContext{

	@Override
	public XY getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean moveOk(Entity entity, XY moveDirection) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Squirrel getNearestSquirrel(XY startPos) {
		// TODO Auto-generated method stub
		return null;
	}
	
}

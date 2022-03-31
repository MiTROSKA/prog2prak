package prog2Prak2;

public class mainSpiel {

	public static void main(String[] args) {
		// Erstellung der Figuren
		HandOperatedMasterSquirrel s = new HandOperatedMasterSquirrel(13,13);
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

		int i;
		for (i = 0; i < 10; i++) {
			entityset.nextStepCaller();
			System.out.println("Nach NextStep: ");
			System.out.println("List Length: " + entityset.getListLength());
			System.out.println(entityset);
		}

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

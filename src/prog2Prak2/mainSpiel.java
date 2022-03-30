package prog2Prak2;

public class mainSpiel {

	public static void main(String[] args) {
		//Erstellung der Figuren
		EntitySet entityset = new EntitySet();
		GoodPlant goodp = new GoodPlant(3,3);
		BadPlant bp = new BadPlant(3,3);
		GoodBeast gb = new GoodBeast(3,3);
		BadBeast bb = new BadBeast(3,3);

		//unbewegliche objekte hinzuf�gen
		entityset.addEntity(goodp);
		entityset.addEntity(bp);

		//bewegliche objekte hinzuf�gen
		entityset.addEntity(gb);
		entityset.addEntity(bb);

		//was grad in der liste drin ist
		System.out.println("Vor NextStep: ");
		System.out.println("List Length: " + entityset.getListLength());
		System.out.println(entityset);

		//bewegung
		entityset.nextStepCaller();

		//welche position nach bewegung ist
		System.out.println("Nach NextStep: ");
		System.out.println("List Length: " + entityset.getListLength());
		System.out.println(entityset);

		//updateposition test
		System.out.println("Vor Update Position: " + '\n' + gb + '\n');

		gb.updatePosition(new XY(100, 100));

		System.out.println("Nach Update Position: " + '\n' + gb + '\n');

		//entfernen eines elements
		entityset.removeEntity(gb);

		//Liste nach entfernen
		System.out.println("Nach Remove: ");
		System.out.println("List Length: " + entityset.getListLength());
		System.out.println(entityset);

		//Mastersquirrel erstellen
		HandOperatedMasterSquirrel s = new HandOperatedMasterSquirrel(4,4);
		System.out.println("MasterSq Energy vor Spawn: " + s.getEnergy());

		// Minisquirrel erstellen
		s.spawnMinisquirrel(300);
		System.out.println("MasterSq Energy nach Spawn: " + s.getEnergy() + '\n');

		System.out.println("Commit 2 test");

	/*	//Mastersquirrel bewegung test
		int i;
		System.out.println("Position vor Move: ");
		System.out.println(s);

		for(i = 0; i < 10; i++) {
			s.nextStep();
			System.out.println("Position nach Move: ");
			System.out.println(s);
		} */
	}
}

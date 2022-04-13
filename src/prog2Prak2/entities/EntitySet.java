package prog2Prak2.entities;

//import java.util.Arrays;
import java.util.Enumeration;
//import java.util.List;
import java.util.Random;

//import prog2Prak2.game.EntityContext;

public class EntitySet {

	private Data head;
	private Data tail;
	private int listLength;

	private static class Data {
		private final Entity entity;
		private Data prev;
		private Data next;

		public Data(Entity entity, Data prev, Data next) {
			this.entity = entity;
			this.prev = prev;
			this.next = next;
		}

		public Entity getEntity() {
			return this.entity;
		}

		public Data getPrev() {
			return this.prev;
		}

		public Data getNext() {
			return this.next;
		}

		public void setNext(Data next) {
			this.next = next;
		}

		public void setPrev(Data prev) {
			this.prev = prev;
		}
	}

	public EntitySet() {
		this.head = null;
		this.tail = null;
		listLength = 0;
	}

	public int getListLength() {
		return this.listLength;
	}

	public void addEntity(Entity entity) throws duplicateEntityException {

		if (isThere(entity)) {
			throw new duplicateEntityException("Entity already exists in list");
		}

		if (head == null && tail == null) {// leere liste
			head = new Data(entity, null, null);
			tail = head;
			listLength++;
		} else if (listLength == 1) {// list nur mit 1
			tail = new Data(entity, head, null);
			head.setNext(tail);
			listLength++;
		} else if (listLength >= 2) {// liste mit 2 oder mehr
			tail.setNext(new Data(entity, tail, null));
			tail = tail.getNext();
			listLength++;
		}
	}

	public void removeEntity(Entity entity) throws missingEntityException {
		Data i;

		if (!isThere(entity)) {
			throw new missingEntityException("Entity missing, cannot be removed");
		}

		for (i = head; i != null; i = i.getNext()) {
			if (i.getEntity().isSameEntity(entity)) {
				if (listLength == 1) {// wenn head und tail gleich sind
					head = null;
					listLength--;
				} else if (i == head) { // wenn head gesucht und l�nge nicht 1 ist
					head = head.getNext();
					head.setPrev(null);
					listLength--;
				} else if (i == tail) {// wenn tail selbe ist und l�nge nicht 1 ist
					tail = tail.getPrev();
					tail.setNext(null);
					listLength--;
				} else { // wenn es in der mitte ist
					i.getPrev().setNext(i.getNext());
					i.getNext().setPrev(i.getPrev());
					listLength--;
				}
			}
		}
	}

	public void nextStepCaller() { // EntityContext entityContext
		// Entity.setEntityContext(entityContext);
		for (Enumeration<Entity> e = this.enumerateRandom(); e.hasMoreElements();) {
			e.nextElement().nextStep();
		}
	}

	public String toString() {
		String s = "";
		Data i;
		if (listLength == 0) {
			return "Liste leer :(";
		}
		for (i = head; i != null; i = i.getNext()) {
			s = s + i.getEntity().toString() + "\n";
		}
		return s;
	}

	public boolean isThere(Entity entity) {
		Data i;
		for (i = head; i != null; i = i.getNext()) {
			if (i.getEntity().getId() == entity.getId()) {
				return true;
			}
		}
		return false;
	}

	// unit test, reallyMoved or notSamePosition
	public boolean reallyMoved(Entity entity1, Entity entity2) {

		int a, b, c, d;
		a = entity1.getPos().getX();
		b = entity1.getPos().getY();
		c = entity2.getPos().getX();
		d = entity2.getPos().getY();

		return a != c || b != d;
	}

	public Entity getEntityAt(int index) {
		Data i;
		for (i = head; i != null; i = i.getNext()) {
			if (index-- == 0) {
				return i.getEntity();
			}
		}
		return null;
	}

	public Enumeration<Entity> enumerateForward() {

		return new Enumeration<Entity>() {
			int index = 0;

			@Override
			public boolean hasMoreElements() {
				return index < listLength;
			}

			@Override
			public Entity nextElement() {
				return getEntityAt(index++);
			}
		};
	}

	public Enumeration<Entity> enumerateBackwards() {

		class EnumerateBack implements Enumeration<Entity> {

			Data i = tail;

			@Override
			public boolean hasMoreElements() {
				return i != null;
			}

			@Override
			public Entity nextElement() {
				Entity entity = i.getEntity();
				i = i.getPrev();

				return entity;
			}

		}

		return new EnumerateBack();
	}

	private class EnumerateRandom implements Enumeration<Entity> {
		//private Integer[] used = new Integer[listLength];
		//private int index = 0;
		private final Random random = new Random();
		private int l = listLength;
		int[] indexArr = new int[l];
		
		public EnumerateRandom() {
			for (int j = 0; j < l; j++) {
				indexArr[j] = j;
			}
		}

		public void setSeed(long seed) {
			random.setSeed(seed);
		}

		@Override
		public boolean hasMoreElements() {
			return  l > 0; //index < listLength; 
		}

		@Override
		public Entity nextElement() {
			int index;
			index = indexArr[random.nextInt(l)];

			for (int j = 0; j < l; j++) { 
				if (index == indexArr[j]) {
					indexArr[j] = indexArr[l - 1];
					l--;
				} 
			}
			return getEntityAt(index);
		} 
		
	/*	public Entity nextElement() {
			int i;
			List<Integer> arr = Arrays.asList(used);
			do {
				i = random.nextInt(listLength);
			} while (arr.contains(i));
			used[index++] = i;
			return getEntityAt(i);
		} */
	}

	public Enumeration<Entity> enumerateRandom() {
		return new EnumerateRandom();
	}

	public Enumeration<Entity> enumerateRandom(long seed) {
		EnumerateRandom rand = new EnumerateRandom();
		rand.setSeed(seed);
		return rand;
	}

}

package prog2Prak2;

public class EntitySet {

	private data head;
	private data tail;
	private int listLength;

	public EntitySet() {
		this.head = null;
		this.tail = null;
		listLength = 0;
	}

	public int getListLength() { return this.listLength; }

	public void addEntity(Entity entity) {
		// leere liste
		if (head == null && tail == null) {
			head = new data(entity, null, null);
			listLength++;
		}
		// liste nur mit 1
		else if (head != null && tail == null) {
			tail = new data(entity, head, null);
			head.next = tail;
			listLength++;
		}
		// liste mit mehr als 2
		else if (head != null && tail != null) {
			tail.next = new data(entity, tail, null);
			tail = tail.next;
			listLength++;
		}
	}

	public void removeEntity(Entity entity) {
		data i;
		for (i = head; i != null; i = i.next) {
			if (i.entity.isSameEntity(entity)) {
				if (i == head) {// wenn head selbe ist
					head = i.next;
					head.prev = null;
					listLength--;
				}else if (i == tail) {// wenn tail selbe ist
					tail = tail.prev;
					tail.next = null;
					listLength--;
				} else { // wenn es in der mitte ist
					i.prev.next = i.next;
					i.next.prev = i.prev;
					listLength--;
				}
			}
		}
	}

	public void nextStepCaller() {
		data i;
		for(i = head; i != null; i = i.next) {
			i.entity.nextStep();
		}
	}

	public String toString() {
		data i;
		for(i = head; i != null; i = i.next) {
			System.out.println(i.entity.toString());
		}
		return " ";
	}
}

class data {
	Entity entity;
	data prev;
	data next;

	public data(Entity entity, data prev, data next) {
		this.entity = entity;
		this.prev = prev;
		this.next = next;
	}
}

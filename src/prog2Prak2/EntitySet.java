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
		if (head == null && tail == null) {// leere liste
			head = new data(entity, null, null);
			listLength++;
		} else if (head != null && tail == null) {// liste nur mit 1
			tail = new data(entity, head, null);
			head.setNext(tail);
			listLength++;
		} else if (head != null && tail != null) {// liste mit mehr als 2
			tail.setNext(new data(entity, tail, null));
			tail = tail.getNext();
			listLength++;
		}
	}

	public void removeEntity(Entity entity) {
		data i;
		for (i = head; i != null; i = i.getNext()) {
			if (i.getEntity().isSameEntity(entity)) {
				if (i == head) {// wenn head selbe ist
					head = i.getNext();
					head.setPrev(null);
					listLength--;
				} else if (i == tail) {// wenn tail selbe ist
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
	public void nextStepCaller() {
		data i;
		for(i = head; i != null; i = i.getNext()) {
			i.getEntity().nextStep();
		}
	}

	public String toString() {
		data i;
		for(i = head; i != null; i = i.getNext()) {
			System.out.println(i.getEntity().toString());
		}
		return " ";
	}
}

class data {
	private Entity entity;
	private data prev;
	private data next;

	public data(Entity entity, data prev, data next) {
		this.entity = entity;
		this.prev = prev;
		this.next = next;
	}

	public Entity getEntity() { return this.entity; }

	public data getPrev() { return this.prev; }

	public data getNext() { return this.next; }

	public void setNext(data next) { this.next = next; }

	public void setPrev(data prev) { this.prev = prev; }
}

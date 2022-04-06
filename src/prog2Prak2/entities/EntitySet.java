package prog2Prak2.entities;

//import prog2Prak2.game.EntityContext;

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

	public void addEntity(Entity entity) throws duplicateEntityException{
			
		if(isThere(entity)) {
				throw new duplicateEntityException("Entity already exists in list");
			}
		

		if (head == null && tail == null) {// leere liste
			head = new data(entity, null, null);
			tail = head;
			listLength++;
		} else if (listLength == 1) {// liste nur mit 1
			tail = new data(entity, head, null);
			head.setNext(tail);
			listLength++;
		} else if (listLength >= 2) {// liste mit mehr 2 oder mehr
			tail.setNext(new data(entity, tail, null));
			tail = tail.getNext();
			listLength++;
		}
	}

	public void removeEntity(Entity entity) throws missingEntityException {
		data i;
		
		if(!isThere(entity)) {
			throw new missingEntityException("Entity missing, cannot be removed");
		}

		for (i = head; i != null; i = i.getNext()) {
			if (i.getEntity().isSameEntity(entity)) {
				if (listLength == 1) {// wenn head und tail gleich sind
					head = null;
					listLength--;
				} else if(i == head){ //wenn head gesucht und länge nicht 1 ist
					head = head.getNext();
					head.setPrev(null);
					listLength--;
				} else if (i == tail) {// wenn tail selbe ist und länge nicht 1 ist
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
	
	public void nextStepCaller() { //EntityContext entityContext
		data i;
	//	Entity.setEntityContext(entityContext);
		for(i = head; i != null; i = i.getNext()) {
			i.getEntity().nextStep();
		}
	}

	public String toString() {
		String s = "";
		data i;
		if(listLength == 0) {
			return "Liste leer :(";
		}
		for(i = head; i != null; i = i.getNext()) {
			s = s +  i.getEntity().toString() + "\n";
		}
		return s;
	}

	public boolean isThere(Entity entity) {
		data i;
		for(i = head; i != null; i = i.getNext()) {
			if(i.getEntity().getId() == entity.getId()) {
				return true;
			}
		}
		return false;
	}

	//for unit test
	public boolean reallyMoved(Entity entity1, Entity entity2) {
	
		int a,b,c,d;
		a = entity1.getPos().getX();
		b = entity1.getPos().getY();
		c = entity2.getPos().getX();
		d = entity2.getPos().getY();

		if(a == c && b == d) {
			return false;
		}else {
			return true;
			}
		}

	public Entity getEntityAt(int index) {
		data i;
		for(i = head; i != null; i = i.getNext()) {
			if(index-- == 0) {
				return i.getEntity();
			}
		}
		return null;
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

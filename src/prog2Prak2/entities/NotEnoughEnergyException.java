package prog2Prak2.entities;

public class NotEnoughEnergyException extends Exception{
	
	public NotEnoughEnergyException() {
		super();
	}
	
	public NotEnoughEnergyException(String message) {
		super(message);
	}
}

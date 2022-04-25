package prog2Prak2.SquirrelCommandReader;


public class Command {
	
	CommandTypeInfo commandType;
	Object[] params;
	
	public Command(CommandTypeInfo commandType, Object[] params) {
		this.commandType = commandType;
		this.params = params;
	}
	
	public Object [] getParams() {
		return this.params;
	}
	
	public CommandTypeInfo getCommandType() {
		return commandType;
	}
	
	
	
}

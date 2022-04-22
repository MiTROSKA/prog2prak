package prog2Prak2.CommanProcessor;

public class Command {
	private CommandTypeInfo commandType;
	private Object[] params;
	
	public Command(CommandTypeInfo commandType, Object[] params) {
		if(commandType.getParamTypes().length != params.length) {
			throw new ScanException ("Seems to be the wrong number of params...");
		}
		
		
		this.commandType = commandType;
		this.params = params;
	}
	
	public CommandTypeInfo getCommandType() {
		return commandType;
	}
	
	public Object[] getParams() {
		return params;
	}
}

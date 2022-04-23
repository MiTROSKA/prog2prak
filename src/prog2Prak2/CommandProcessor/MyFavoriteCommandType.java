package prog2Prak2.CommandProcessor;

public enum MyFavoriteCommandType implements CommandTypeInfo{

HELP("help", " * list all commands"),
EXIT("exit", " * exit program"),
ADDI("addi", "<param1> <param2> * simple integer add ", int.class, int.class),
ADDF("addf", "<param1> <param2> * simple float add ", float.class, float.class),
ECHO("echo", "<param1> <param2> * echos param1 string param2 times ", String.class, int.class);

private String commandName;
private String helpText;
private Class<?> []params;

	private MyFavoriteCommandType(String commandName, String helpText, Class<?>...params) {
		this.commandName = commandName;
		this.helpText = helpText;
		this.params = params;
	}

	@Override
	public String getName() {
		return commandName;
	}

	@Override
	public String getHelpText() {
		return helpText;
	}

	@Override
	public Class<?>[] getParamTypes() {
		return params;
	}
	
	public String toString() {
	String allNames = "Available Commands: \n";
		MyFavoriteCommandType [] commands =	MyFavoriteCommandType.values();
		for(int i = 0; i < commands.length; i++) {
			allNames = allNames +  "\""+commands[i].getName() +"\""+ " " +commands[i].getHelpText() + "\n";
	}
		return allNames;
	}

}

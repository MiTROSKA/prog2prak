package prog2Prak2.SquirrelCommandReader;

import java.lang.reflect.Method;
import prog2Prak2.game.GameImpl;

public enum GameCommandType implements CommandTypeInfo{
HELP("help", " * list all commands", "help"),
EXIT("exit", " * exit game", "exit"),
ALL("all", " * list all entities on the board", "all"),
LEFT("left", " * move MasterSquirrel to the left", "goLeft"),
UP("up", " * move MasterSquirrel up", "goUp"),
DOWN("down", " * move MasterSquirrel down", "goDown"),
RIGHT("right", " * move MasterSquirrel to the right", "goRight"),
MASTER_ENERGY("master_energy", " * display energy of MasterSquirrel", "master_energy"),
SPAWN_MINI("spawn_mini", " * <energy> spawn a MiniSquirrel", "spawn_mini", Integer.class);
	
	private String name;
	private String helpText;
	private Method method;
	private Class<?>[] params;
	
	private GameCommandType(String name, String helpText, String methodName, Class<?>...params) {
		this.name = name;
		this.helpText = helpText;
		try {
			this.method = GameImpl.class.getDeclaredMethod(methodName, params);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		this.params = params;
		
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getHelpText() {
		return this.helpText;
	}

	@Override
	public Class<?>[] getParamTypes() {
		return this.params;
	}
	@Override
	public Method getMethod() {
		return this.method;
	}
	
	public String toString() {
		String allNames = "Available Commands: \n";
			GameCommandType [] commands =	GameCommandType.values();
			for(int i = 0; i < commands.length; i++) {
				allNames = allNames +  "\""+commands[i].getName() +"\""+ " " +commands[i].getHelpText() + "\n";
		}
			return allNames;
		}
	
}

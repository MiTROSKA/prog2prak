package prog2Prak2.SquirrelCommandReader;

import java.io.BufferedReader;
import java.io.IOException;

public class CommandScanner {

	private BufferedReader inputReader;
	private CommandTypeInfo[] commandTypes;

	public CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader) {
		this.inputReader = inputReader;
		this.commandTypes = commandTypes;
	}

	public Command next() {
		try {
			String eingabe = inputReader.readLine();
			String[] parts = eingabe.split(" ");
			CommandTypeInfo command = translateCommand(parts[0]);
			if (command == null) {
				throw new ScanException("Invalid Command...");
			}
			Object[] params = new Object[parts.length - 1];

			if (parts.length >= 1) {
				for (int i = 0; i < params.length; i++) {
					params[i] = translateParams(parts[i + 1]);
				}

			} 
			if(command.getParamTypes().length != params.length) {
				throw new ScanException("Wrong Parameters...");
			}
			
			return new Command(command, params);
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private CommandTypeInfo translateCommand(String part) {
		for (int i = 0; i < commandTypes.length; i++) {
			if (commandTypes[i].getName().equals(part)) {
				return commandTypes[i];
			}
		}
		return null;
	}

	private Object translateParams(String part) { 
		if (isInt(part)) {
			return Integer.parseInt(part);
		} else if (isFloat(part)) {
			return Float.parseFloat(part);
		}

		return part;

	}

	private boolean isInt(String part) {
		if (part == null || part.equals("")) {
			return false;
		}

		try {
			Integer.parseInt(part);
			return true;
		} catch (Exception e) {

		}

		return false;
	}

	private boolean isFloat(String part) {
		if (part == null || part.equals("")) {
			return false;
		}

		try {
			Float.parseFloat(part);
			return true;
		} catch (Exception e) {

		}

		return false;
	}

}

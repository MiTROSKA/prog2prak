package prog2Prak2.CommanProcessor;

import java.io.BufferedReader;
import java.io.IOException;

public class CommandScanner {

private CommandTypeInfo[] commandTypes;
private BufferedReader inputReader;

	public CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader) {
		this.commandTypes = commandTypes;
		this.inputReader = inputReader;
		
	}
	
	public Command next() {
		try {
			String eingabe = inputReader.readLine();
			String[] parts = eingabe.split(" ");
			CommandTypeInfo command = translateCommand(parts[0]);
			
			if(command == null) {
				throw new ScanException("Wrong Command, type \"help\" to see available commands...");
			}
			
			Object[] params = new Object[parts.length-1];
			
			if(parts.length > 1) {
				for(int i = 0; i < params.length; i++) {
					params[i] = translateParams(parts[i+1]);
				}
				
			} 
			return new Command(command, params);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private CommandTypeInfo translateCommand(String part) {
		for(CommandTypeInfo command : commandTypes) {
			if(command.getName().equals(part)) {
				return command;
			}
		}
		
		return null;
	}
	
	private Object translateParams(String part) { //?
		try {
			return Integer.parseInt(part);
		} catch(Exception e) {
			
		}
		try {
			return Float.parseFloat(part);
		} catch(Exception e) {
			
		}
		
		return part;
	}

}
 
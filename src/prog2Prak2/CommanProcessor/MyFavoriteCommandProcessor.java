package prog2Prak2.CommanProcessor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class MyFavoriteCommandProcessor {
	private PrintStream outputStream;
	private BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
	private CommandScanner commandScanner;
	
	public MyFavoriteCommandProcessor(CommandTypeInfo[] commandType, BufferedReader inputReader, PrintStream outputStream) {
		commandScanner = new CommandScanner(commandType, inputReader);
		this.outputStream = outputStream;
	}
	
	public void process() {
		Command command = commandScanner.next();
		Object[] params = command.getParams();
		CommandTypeInfo commandType = command.getCommandType();
		
		switch ((MyFavoriteCommandType)commandType) {
		case HELP:  
			outputStream.println(Arrays.toString(MyFavoriteCommandType.values())); //?
			break;
		case ADDI: 
			int sumInt = 0;
			for(int i = 0; i < params.length; i++) {
				sumInt = sumInt + Integer.parseInt(params[i].toString());
			}
			outputStream.println(sumInt);
			break;
		case ADDF: 
			float sumFloat = 0;
			for(int i = 0; i < params.length; i++) {
				sumFloat = sumFloat + Float.parseFloat(params[i].toString());
			}
			outputStream.println(sumFloat);
			break;
		case ECHO:
			int runs = Integer.parseInt(params[1].toString());
			String string = params[0].toString();
			
			for(int i = 0; i < runs; i++)  {
				outputStream.print(string + " ");
			}
			break;
		case EXIT: 
			System.exit(1);
			break;
		default:
			break;
			
		}
		
	}
	
	private void help() {
		
	}


	public static void main(String[] args) {
	MyFavoriteCommandProcessor commandProcessor = new MyFavoriteCommandProcessor(MyFavoriteCommandType.values(), 
			new BufferedReader (new InputStreamReader(System.in)),
            System.out);
		while(true) {
		
			commandProcessor.process(); 
		}
	}
}

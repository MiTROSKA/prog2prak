package prog2Prak2.commands;

public class CommandScanner {
  private CommandTypeInfo[] commandTypeInfos;
  private BufferedReader inputReader;
  private PrintStream outputStream;
  
  public Command next();
  public CommandScanner(CommandTypeInfo[] commandTypes, BufferedReader inputReader);

}
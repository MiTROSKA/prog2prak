package prog2Prak2.commands;

public class MyFavoriteCommandsProcessor {

    private PrintStream outputStream = System.out;
    private BufferedReader inputReader = new BufferdReader(new inputStreamReader(System.in));

    public void process();
    private void help();
    public void main(String[] args){}

    CommandScanner commandScanner = new CommandScanner (MyFavoriteCommandType.values(), inputReader);

    while (true){ // the loop over all commands with one input line for every command
        command = commandScanner.next();

        Object [] params = command.getParams ();
        MyFavoriteCommandType commandType = ( MyFavoriteCommandType ) command. getCommandType ( ) ;
        switch (commandType){
            case EXIT:
                System.exit(0);
            case HELP:
                help ();
                break;
            case ADDI:

                break;
            case ADDF:

                break;
            case ECHO:

                break;
            default:
        }
    }
}

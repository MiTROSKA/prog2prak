package prog2Prak2.CommandProcessor;

public interface CommandTypeInfo {

	public String getName();
	
	public String getHelpText();
	
	public Class<?>[] getParamTypes();

}

package prog2Prak2.CommanProcessor;

public interface CommandTypeInfo {

	public String getName();
	
	public String getHelpText();
	
	public Class<?>[] getParamTypes();

}

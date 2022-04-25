package prog2Prak2.SquirrelCommandReader;

import java.lang.reflect.Method;

public interface CommandTypeInfo {
	public String getName();
	
	public String getHelpText();
	
	public Class<?>[] getParamTypes();
	
	public Method getMethod();
}

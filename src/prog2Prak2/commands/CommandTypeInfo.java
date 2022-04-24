package prog2Prak2.commands;

public interface CommandTypeInfo {
    String getName();
    String getHelpText();
    Class<?> [] getPramTypes();
}
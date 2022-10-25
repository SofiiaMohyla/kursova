package menu;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public abstract class Menu {
    HashMap<String, Method> commands;
    public abstract void execute(String command) throws
            InvocationTargetException, IllegalAccessException, NoSuchMethodException;
    public abstract void help(String command) throws InvocationTargetException,
            IllegalAccessException;
}


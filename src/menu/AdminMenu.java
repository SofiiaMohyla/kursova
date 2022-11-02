package menu;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class AdminMenu extends Menu {
    HashMap<String, Method> commands = new HashMap<>();

    public AdminMenu() throws NoSuchMethodException {
        this.commands.put("salad",
                SaladMenu.class.getMethod("execute", String.class));
        this.commands.put("vegetables", VegetableMenu.class.getMethod("execute",
                String.class));
        this.commands.put("help", AdminMenu.class.getMethod("help",
                String.class));
        this.commands.put("exit", null);
    }

    public void execute(String command) throws InvocationTargetException,
            IllegalAccessException, NoSuchMethodException {
        command = command.toLowerCase();
        String[] objectCommand = command.split(" ");
        if (this.commands.containsKey(objectCommand[0])) {
            try {
                this.commands.get(objectCommand[0]).invoke(new AdminMenu(),
                        command);
            } catch (Exception NullPointerException) {
                return;
            }

        } else {
            System.out.format("Command '%s' is not defined. Type 'help' to get list of defined commands\n", command);
        }
    }

    public void help(String command) throws InvocationTargetException,
            IllegalAccessException {
        String[] additionalCommand = command.split(" ");
        if (additionalCommand.length > 1 && !additionalCommand[1].equals("help")) {
            if (this.commands.containsKey(additionalCommand[1])) {
                this.commands.get(additionalCommand[1]).invoke(null,
                        additionalCommand[1]);
            } else {
                System.out.format("Command '%s' is not defined. Type 'help' to get list of defined commands\n", additionalCommand);
            }
        } else {
            System.out.println();
            System.out.println("************************************************");
            System.out.println("*     █████  ██████  ███    ███ ██ ███    ██   *");
            System.out.println("*    ██   ██ ██   ██ ████  ████ ██ ████   ██   *");
            System.out.println("*    ███████ ██   ██ ██ ████ ██ ██ ██ ██  ██   *");
            System.out.println("*    ██   ██ ██   ██ ██  ██  ██ ██ ██  ██ ██   *");
            System.out.println("*    ██   ██ ██████  ██      ██ ██ ██   ████   *");
            System.out.println("*                                              *");
            System.out.println("*   ███    ███ ███████ ███    ██ ██    ██      *");
            System.out.println("*   ████  ████ ██      ████   ██ ██    ██      *");
            System.out.println("*   ██ ████ ██ █████   ██ ██  ██ ██    ██      *");
            System.out.println("*   ██  ██  ██ ██      ██  ██ ██ ██    ██      *");
            System.out.println("*   ██      ██ ███████ ██   ████  ██████       *");
            System.out.println("************************************************");
            System.out.println("* salad -       Salad menu                     *");
            System.out.println("* vegetables -  Vegetables menu                *");
            System.out.println("* help -        Showing this help message      *");
            System.out.println("* exit -        Exit program                   *");
            System.out.println("************************************************");
        }
        System.out.println();
    }
}


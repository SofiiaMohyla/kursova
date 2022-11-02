package menu;

import Objects.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import menu.file.FileOper;

public class SaladMenu {

    private static final Logger logger = Logger.getLogger(SaladMenu.class.getName());
    static Map<String, Method> commands = null;
    static {
        try {
            commands = new HashMap<>();
            commands.put("edit", SaladMenu.class.getMethod("editSalad"));
            commands.put("show", SaladMenu.class.getMethod("showSalad"));
            commands.put("sort", SaladMenu.class.getMethod("sortByPrice"));
            commands.put("find", SaladMenu.class.getMethod("findByKalory"));
            commands.put("write", SaladMenu.class.getMethod("writeSalad"));
            commands.put("read", SaladMenu.class.getMethod("readSalad"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public static void execute(String command) throws InvocationTargetException,
            IllegalAccessException {
        command = command.toLowerCase();
        String[] splitCommands = command.split(" ");
        if(splitCommands.length>1){
            String specifiedCommand = splitCommands[1];
            if(commands.containsKey(specifiedCommand)){
                commands.get(specifiedCommand).invoke(null);
            } else {
                System.out.format("Command '%s' is not defined. Type 'help' to get list of defined commands\n", splitCommands);
            }
        } else {
            System.out.println();
            System.out.println("******************************************");
            System.out.println("*   ██████╗ █████  ██║     █████   ██████  *");
            System.out.println("*   ██╔═══╝██║  ██ ██║    ██║  ██  ██║  ██ *");
            System.out.println("*   ██████╗██║████ ██║    ██║████  ██║   ██*");
            System.out.println("*       ██║██║  ██╔██║    ██║  ██  ██║  ██ *");
            System.out.println("*   ██████╔██║  ██║██████║██║  ██║ ██████  *");
            System.out.println("*   ╚═════╝╚═╝  ╚═╝╚════╝ ╚═╝  ╚═╝ ╚════╝  *");
            System.out.println("*                                        *");
            System.out.println("* ███╗   ███╗███████╗███╗   ██╗██╗   ██╗ *");
            System.out.println("* ████╗ ████║██╔════╝████╗  ██║██║   ██║ *");
            System.out.println("* ██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║ *");
            System.out.println("* ██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║ *");
            System.out.println("* ██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝ *");
            System.out.println("******************************************");
            System.out.println("* edit - Edit salad info                 *");
            System.out.println("* show - View salad data                 *");
            System.out.println("* sort - Sort vegetable in salad by price*");
            System.out.println("* find - Find kalory concept             *");
            System.out.println("* write - Write salad info into file     *");
            System.out.println("* read - Read salad info from file       *");
            System.out.println("******************************************");
        }
    }
    public static void editSalad() {
        Salad salad = Salad.getInstance();
        Scanner scan = new Scanner(System.in);
        System.out.println("\nEditing salad.\n");
        System.out.print("Enter box-form:");
        String boxform = scan.nextLine();
        salad.setBoxForm(boxform);
        System.out.print("\nEnter salad ID:");
        Integer ID = scan.nextInt();
        salad.setId(ID);
        System.out.println("Info changed");
    }
    public static void showSalad() throws IOException, ParseException {
        Salad salad = Salad.getInstance();
        System.out.println("\nShowing Salad data...\n");
        System.out.println("ID: " + salad.getId());
        System.out.println("Box-form: " + salad.getBoxForm());
        System.out.println("TotalWeight: " + salad.getTotalWeight(logger));
        System.out.println("----------------------------");
        System.out.println("----------------------------\n");
        System.out.println("Vegetables: " + salad.size());
        int i = 0;
        salad.getListOfVegetables(logger);
    }

    public static void sortByPrice() throws IOException, ParseException
    {
        Salad salad = Salad.getInstance();
        if (salad.size() == 0){
            System.out.println("\nThere are no Vegetables to sort.\n");
        }else{
            System.out.print("\nSorting Vegetables...");
            Salad.sortByPrice();
            System.out.println("Done!");
            System.out.println("Sorted Vegetables: ");
            System.out.println("---------------");
            salad.getListOfVegetables(logger);
            System.out.println("---------------\n");
        }
    }
    public static void findByKalory() throws IOException, ParseException {
        Salad salad = Salad.getInstance();
        Scanner scan = new Scanner(System.in);
        if (salad.size() == 0){
            System.out.println("\nThere are no vegetables to find by Kaloryconcept.");
        }else {
            System.out.print("\nEnter diapason - '[min] [max]':");
            String diapason = scan.nextLine();
            String[] splitted = diapason.split(" ");
            System.out.println("Searching vegetable...");
            System.out.println("---------------");

            salad.findByKalory(Integer.parseInt(splitted[0]),
                    Integer.parseInt(splitted[1]),logger);
        }
        System.out.println("---------------");
    }

    public static void flushSalad() throws IOException, ParseException {
        Salad salad = Salad.getInstance();
        salad.flush();
    }

    public static void readSalad() throws Exception {
        System.out.println("\nWrite your file destination:");
        String destination = new Scanner(System.in).nextLine();
        new FileOper().readFile(destination,logger);
    }

    public static void writeSalad() throws Exception {
        System.out.println("\nWrite your file destination:");
        String destination = new Scanner(System.in).nextLine();
        new FileOper().writeFile(destination,logger);
    }
}

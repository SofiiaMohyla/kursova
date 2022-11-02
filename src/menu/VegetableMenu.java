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


public class VegetableMenu {
    private static final Logger logger = Logger.getLogger(VegetableMenu.class.getName());
    static Map<String, Method> commands = null;
    static {
        try {
            commands = new HashMap<>();
            commands.put("create", VegetableMenu.class.getMethod("createVegetable"));
            commands.put("view", VegetableMenu.class.getMethod("showVegetables"));
            commands.put("delete", VegetableMenu.class.getMethod("deleteVegetables"));
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
            System.out.println("*********************************************************");
            System.out.println("*  █║  █ ███  ███  ███  ███   ██   ████   █║    ███ *");
            System.out.println("*  █║  █ █║   █║   █║    █║  █║ █  █║  █  █║    █║  *");
            System.out.println("*   █║█  ███  █║   ███   █║  ████  ████   █║    ███ *");
            System.out.println("*   █║█  █║   █║█║ █║    █║  █║ █  █║  █  █║    █║  *");
            System.out.println("*    █║  ███║ ███║ ███║  █║  █║ █  ████║  ████║ ███║ *");
            System.out.println("*    ╚╝  ╚══╝ ╚══╝ ╚══╝  ═╝   ╚═╝  ╚═══╝  ╚═══╝ ╚══╝ *");
            System.out.println("*       ███╗   ███╗███████╗███╗   ██╗██╗   ██╗       *");
            System.out.println("*       ████╗ ████║██╔════╝████╗  ██║██║   ██║       *");
            System.out.println("*       ██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║       *");
            System.out.println("*       ██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║       *");
            System.out.println("*       ██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝       *");
            System.out.println("*       ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝        *");
            System.out.println("******************************************************");
            System.out.println("* create -     Create new vegetable                  *");
            System.out.println("* view   -     View vegetables in                    *");
            System.out.println("* delete -     Delete vegetable by unique code       *");
            System.out.println("******************************************************");
        }
    }
    public static void createVegetable() {
        Salad salad = Salad.getInstance();
        Scanner scan = new Scanner(System.in);
        System.out.print("\nSelect Vegetable type (onion_type, cabbage_type, root_crops): ");
        String result = scan.nextLine();
        if(result.equals("onion_type")){
            System.out.println("Type vegetable using template:\n");
            System.out.println("[name] [price] [weight] [kalory] [QR code]");
            System.out.println(" Onion   12      40       20      1111\n");
            String params = scan.nextLine();
            String[] paramSplit = params.split(" ");
            if(paramSplit.length != 5) {
                System.out.println("Too few parameters. Try again.");
                createVegetable();
            } else {
                salad.addVegetables(new Onion_type(paramSplit[0],
                        Integer.parseInt(paramSplit[1]), Integer.parseInt(paramSplit[2]), Integer.parseInt(paramSplit[3]), Integer.parseInt(paramSplit[4])),logger);
                System.out.println("Success");
            }
        } else if(result.equals("cabbage_type")) {
            System.out.println("Type vegetable using template:\n");
            System.out.println("[name]   [price] [weight] [kalory] [QR code]");
            System.out.println("Brussel    100     70       31      2333 \n");
            String params = scan.nextLine();
            String[] paramSplit = params.split(" ");
            if(paramSplit.length != 5) {
                System.out.println("Too few parameters. Try again.");
                createVegetable();
            } else {
                salad.addVegetables(new Cabbage_type(paramSplit[0],
                        Integer.parseInt(paramSplit[1]), Integer.parseInt(paramSplit[2]), Integer.parseInt(paramSplit[3]), Integer.parseInt(paramSplit[4])),logger);
                System.out.println("Success");
            }}
        else if(result.equals("root_crops")) {
            System.out.println("Type vegetable using template:\n");
            System.out.println("[name] [price] [weight] [kalory] [QR code]");
            System.out.println("Potato   9       30       67      4444\n");
            String params = scan.nextLine();
            String[] paramSplit = params.split(" ");
            if(paramSplit.length != 5) {
                System.out.println("Too few parameters. Try again.");
                createVegetable();
            } else {
                salad.addVegetables(new Root_crops(paramSplit[0],
                        Integer.parseInt(paramSplit[1]), Integer.parseInt(paramSplit[2]), Integer.parseInt(paramSplit[3]), Integer.parseInt(paramSplit[4])),logger);
                System.out.println("Success");
            }
        } else {
            System.out.println("Unknown type.");
            createVegetable();
        }
        System.out.println();
    }
    public static void showVegetables() throws IOException, ParseException {
        Salad salad = Salad.getInstance();
        if (salad.size() == 0){
            System.out.println("\nThere are no vegetables created.");}
        else {
            System.out.println("\nVegetables in salad:");
            int i = 0;
            salad.getListOfVegetables(logger);}

        System.out.println();
    }

    public static void deleteVegetables() throws IOException, ParseException {
        Salad salad = Salad.getInstance();
        int i = 0;
        Scanner scan = new Scanner(System.in);
        if (salad.size() == 0){
            System.out.println("\nThere are no Vegetables to remove.");
        }else {
            salad.getUniqueId(logger);
            System.out.println("\nEnter Unique Id of Vegetables to delete:");
            int id = scan.nextInt();
            salad.delete(id,logger);
            System.out.println("Success\n");
        }
    }
}


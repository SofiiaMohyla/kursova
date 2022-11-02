package Main;

import menu.AdminMenu;
import menu.Menu;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import static mail.mailSender.sendMessage;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, Exception {
        Scanner scan = new Scanner(System.in);
        String result;
        Menu menu = null;
        do {
            System.out.println("Input access code to run program: ");
            result = scan.nextLine();
            if (result.equals("911")) {
                menu = new AdminMenu();
                System.out.println("You are in Admin menu. Type help for help: ");
            } else {
                System.out.println("Try again.");
                result = "wrong";
            }
        } while (result.equals("wrong"));
        String command;
        do {
            command = scan.nextLine();
            menu.execute(command);
        } while (!command.equals("exit"));
    }
}

package menu.file;

import Objects.Salad;
import Objects.Vegetables;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static mail.mailSender.sendMessage;


public class FileOper {
    PrintStream standart;
    PrintStream fileStream;

    public void readFile(String destination,Logger logger) throws Exception {
        Salad salad = Salad.getInstance();
        salad.getList().clear();
        try {
            FileReader fr = new FileReader(destination);
            Scanner reader = new Scanner(fr);

            while (reader.hasNextLine()) {
                int qr = Integer.parseInt(reader.nextLine());
                String name = reader.nextLine();
                int kalory = Integer.parseInt(reader.nextLine());
                int price = Integer.parseInt(reader.nextLine());
                int weight = Integer.parseInt(reader.nextLine());
                salad.addVegetables(new Vegetables(name,price,weight,kalory,qr),logger);
            }

            fr.close();

        }catch (IOException e) {
            logger.log(Level.WARNING,"Знайдено критичну помилку: ",e);
            sendMessage("Знайдено критичну помилку: " + e);
            System.out.println("The error occurred.\n");
            e.printStackTrace();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void writeFile(String destination, Logger logger) throws Exception {
        Salad salad = Salad.getInstance();
        standart = System.out;
        try {
            File myFile = new File(destination);

            if (myFile.createNewFile())
                System.out.println("Файл створено: " + myFile.getName() + "\n");
            else {
                System.out.println("Файл уже існує.\n");
                myFile.delete();
                myFile.createNewFile();
            }

            System.setOut(fileStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(myFile))));

        } catch (IOException e) {
            logger.log(Level.WARNING,"Знайдено критичну помилку: ",e);
            sendMessage("Знайдено критичну помилку: " + e);
            System.out.println("The error occurred.\n");
            e.printStackTrace();
        }

        List<Vegetables> vegetables = salad.getList();
        for (int i = 0; i < vegetables.size(); i++) {
            System.out.println(vegetables.get(i).getQR() + "\n" + vegetables.get(i).getName() + "\n" + vegetables.get(i).getKalory()
                    + "\n" + vegetables.get(i).getPrice() + "\n" + vegetables.get(i).getWeight());
        }
        System.out.flush();
        System.setOut(standart);
    }

}



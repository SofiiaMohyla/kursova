package file;

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

    public Salad readFile(String destination,Logger logger) throws Exception {
        Salad content = new Salad();
        try {
            FileReader fr = new FileReader(destination);
            Scanner reader = new Scanner(fr);

            while (reader.hasNextLine()) {
                int qr = Integer.parseInt(reader.nextLine());
                String name = reader.nextLine();
                int kalory = Integer.parseInt(reader.nextLine());
                int price = Integer.parseInt(reader.nextLine());
                int weight = Integer.parseInt(reader.nextLine());
                content.addVegetables(new Vegetables(name,price,weight,kalory,qr),logger);
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

        return content;
    }

    public void writeFile(String destination, Salad content, Logger logger) throws Exception {
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

        List<Vegetables> vegetables = content.getList();
        for (int i = 0; i < vegetables.size(); i++) {
            System.out.println("\nQR code: " + vegetables.get(i).getQR() + "\nName of vegetable: " + vegetables.get(i).getName() + "\nKalory concept: " + vegetables.get(i).getKalory()
                    + "\nPrice of vegetable: " + vegetables.get(i).getPrice() + "\nWeight of vegetable: " + vegetables.get(i).getWeight());
        }
        System.out.flush();
        System.setOut(standart);
    }

}



package base;
import java.sql.*;

import objects.Salad;
import objects.Vegetables;

import java.util.List;
import java.util.logging.Logger;


public class Database {

    public static void readFromDB(Logger logger) throws Exception {
        Salad salad = Salad.getInstance();

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Vegetables", "root", "root1111");
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from veg");

            String name = null;
            int price = 0,weight = 0, kalory = 0,QR = 0;
                /*public Vegetables(String name, int price, int weight, int kalory, int QR) {
                    this.QR = QR;
                    this.name = name;
                    this.price = price;
                    this.weight = weight;
                    this.kalory = kalory;
                }*/
            System.out.println("Elements, which was read from db: \n");
            while(resultset.next()){
                System.out.println("Name: " + resultset.getString("name") + "\nPrice: " + resultset.getString("price")
                        + "\nWeight: " + resultset.getString("weight") + "\nKalory: " +  resultset.getString("kalory")
                        + "\nQR: " + resultset.getString("qr") + "\n");

                name = resultset.getString("name");
                price = Integer.parseInt(resultset.getString("price").trim());
                weight = Integer.parseInt(resultset.getString("weight").trim());
                kalory = Integer.parseInt(resultset.getString("kalory").trim());
                QR = Integer.parseInt(resultset.getString("qr").trim());
                salad.addVegetables(new Vegetables(name,price,weight,kalory,QR),logger);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToDB(Logger logger) throws Exception {

        Salad salad = Salad.getInstance();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Vegetables", "root", "root1111");
            Statement statement = connection.createStatement();

            statement.executeUpdate("TRUNCATE TABLE veg");


            List<Vegetables> saladList = salad.getList();

            for(int i = 0;i < saladList.size();i++){
                String insertSql = "Insert into veg Values ('" + saladList.get(i).getName() + "', " + saladList.get(i).getPrice() + ", "
                        + saladList.get(i).getWeight() + ", " + saladList.get(i).getKalory() + ", " + saladList.get(i).getQR() + ")";
                statement.executeUpdate(insertSql);
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }

}


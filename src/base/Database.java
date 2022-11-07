package base;
import Objects.Vegetables;
import com.mysql.cj.protocol.Resultset;

import java.sql.*;

public class Database {
    public static void main(String[] args){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Vegetables", "root", "root1111");
            Statement ststement = connection.createStatement();
            ResultSet resultset = ststement.executeQuery("select * from veg");
            while (resultset.next()) {
                System.out.println(resultset.getString("name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Vegetables> getAll(){    //повертає
        try {
            ArrayList<Vegetables> res = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("тут або скл запит" +
                    " або виклик збереженої процедури, яка буде повертати всі записи з бази");
            while(resultSet.next()){
                res.add(parseVegetable(resultSet));
            }
            return res;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Vegetables parseVegetable(Resultset resultSet){
        try {
            var name = resultSet.getString("номер або назва колонки");
            var price = resultSet.getString("");
            var weight = resultSet.getString("");
            var kalory = resultSet.getString("");
            var QR = resultSet.getString("");
            return new Vegetables(name,price,weight,kalory,QR);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}

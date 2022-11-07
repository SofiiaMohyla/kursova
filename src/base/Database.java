package base;
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
}

package Main;

import Objects.Salad;
import Objects.Vegetables;
import base.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

public class Controller {

    private static final Logger logger = Logger.getLogger(Controller.class.getName());

    @FXML
    private Label totalWeight;  //Рядки виведення

    @FXML
    private Label saladName;
    @FXML
    private Label saladId;
    @FXML
    private TextField minField;   //Рядки введення
    @FXML
    private TextField maxField;
    @FXML
    private TextField boxField;
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField kaloryField;

    @FXML
    private TextField qrField;
    @FXML
    private ListView<String> listOfVegetables; //Список Овочів

    @FXML
    private ListView<String> filteredVegetables; //Список Овочів

    public void UpdateList(){
        List<Vegetables> vegetables = Salad.getInstance().getList();
        listOfVegetables.getItems().clear();
        for (Vegetables vegetable : vegetables) {
            listOfVegetables.getItems().addAll(vegetable.toString());
        }
    }

    public void UpdateWeight(){
        int weight = Salad.getInstance().getTotalWeight(logger);
        totalWeight.setText(String.valueOf(weight));
    }

    public void readDB(javafx.event.ActionEvent e) throws Exception {
        new Database().readFromDB(logger);

        UpdateList();
        UpdateWeight();
    }

    public void writeDB(javafx.event.ActionEvent e) throws Exception {
        new Database().saveToDB(logger);
    }

    public void sortByPrice(javafx.event.ActionEvent e) throws IOException, ParseException
    {
        Salad salad = Salad.getInstance();
        if (salad.size() > 0){
            Salad.sortByPrice();
        }
        UpdateList();
    }

    public void flushSalad(javafx.event.ActionEvent e) throws IOException, ParseException {
        Salad salad = Salad.getInstance();
        salad.flush();

        saladId.setText(String.valueOf(salad.getId()));
        saladName.setText(salad.getBoxForm());
        UpdateList();
        UpdateWeight();
    }

    public void submitFindByKalory(javafx.event.ActionEvent e) throws IOException, ParseException {
        // Покласти кнопку,
        // щоб користувач коли нажав на кнопку "Підтвердити", то зчитало рядки, які він попередньо ввів
        Salad salad = Salad.getInstance();
        List<Vegetables> filtered = null;
        if (salad.size() > 0){
            //Добавити два рядки введення, minField i maxField
            String min = minField.getText();   //Зчитування з рядків введення
            String max = maxField.getText();

            filtered = salad.findByKalory(Integer.parseInt(min),
                    Integer.parseInt(max),logger);

        }

        filteredVegetables.getItems().clear();
        for (Vegetables vegetables : filtered) {
            filteredVegetables.getItems().addAll(vegetables.toString());
        }

    }

    public void submitEditSalad(javafx.event.ActionEvent e) {  //Покласти кнопку,
        // щоб користувач коли нажав на кнопку "Підтвердити", то зчитало рядки, які він попередньо ввів
        Salad salad = Salad.getInstance();

        //Добавити два рядки введення, boxField i idField
        String boxform = boxField.getText();

        if(boxform.equals(""))
            boxform = "Dream salad";

        salad.setBoxForm(boxform);

        Integer ID;

        try {
            ID = Integer.parseInt(idField.getText());
        }catch(NumberFormatException numb){
            ID = 1;
        }

        salad.setId(ID);

        saladId.setText(String.valueOf(salad.getId()));
        saladName.setText(salad.getBoxForm());
    }

    public void submitCreateVegetable(javafx.event.ActionEvent e) {//Покласти кнопку,
        // щоб користувач коли нажав на кнопку "Підтвердити", то зчитало рядки, які він попередньо ввів
        Salad salad = Salad.getInstance();

        //Добавити п'ять рядків введення: nameField, priceField, weightField, kaloryField i qrField
        String name = nameField.getText();

        if(name.equals(""))
            name = "Onion";

        Integer price,weight,kalory,QR;

        try {
            price = Integer.parseInt(priceField.getText());
        }catch(NumberFormatException numb){
            price = 135;
        }

        try {
            weight = Integer.parseInt(weightField.getText());
        }catch(NumberFormatException numb){
            weight = 12;
        }

        try {
            kalory = Integer.parseInt(kaloryField.getText());
        }catch(NumberFormatException numb){
            kalory = 12;
        }

        try{
            QR = Integer.parseInt(qrField.getText());
        }catch(NumberFormatException numb){
            QR = 1111;
        }

        salad.addVegetables(new Vegetables(name,price,weight,kalory,QR),logger);

        UpdateList();
        UpdateWeight();
    }
    /*public void showVegetables() throws IOException, ParseException {
        Salad salad = Salad.getInstance();
        if (salad.size() == 0){
            System.out.println("\nThere are no vegetables created.");}
        else {
            System.out.println("\nVegetables in salad:");
            int i = 0;
            salad.getListOfVegetables(logger);}

        System.out.println();
    }
                                                                                            Ці Два методи можеш переробити, якщо хочеш заморочитися
    public void deleteVegetables() throws IOException, ParseException {
        Salad salad = Salad.getInstance();
        int i = 0;
        Scanner scan = new Scanner(System.in);
        if (salad.size() == 0){
            System.out.println("\nThere are no Vegetables to remove.");
        }else {
            salad.getUniqueId(logger);
            System.out.println("\nEnter Unique  of Vegetables to delete:");
            int id = scan.nextInt();
            salad.delete(id,logger);
            System.out.println("Success\n");
        }
    }*/
}

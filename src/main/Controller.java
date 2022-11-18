package main;

import objects.Salad;
import objects.Vegetables;
import base.Database;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

public class Controller {

    private static final Logger logger = Logger.getLogger(Controller.class.getName());

    @FXML
    private Label totalWeight;  //Рядки виведення
    @FXML
    private Label totalKalory;
    @FXML
    private Label totalPrice;
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

    @FXML
    private AnchorPane vegetablePane;

    @FXML
    private AnchorPane kaloryPane;

    @FXML
    private AnchorPane saladPane;

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

    public void UpdateKalory(){
        int kalory = Salad.getInstance().getTotalKalory(logger);
        totalKalory.setText(String.valueOf(kalory));
    }

    public void UpdatePrice(){
        int price = Salad.getInstance().getTotalPrice(logger);
        totalPrice.setText(String.valueOf(price));
    }

    public void enableSaladEdit(javafx.event.ActionEvent e){
        saladPane.setVisible(true);
        saladPane.setDisable(false);
    }

    public void enableKalorySearch(javafx.event.ActionEvent e){
        kaloryPane.setVisible(true);
        kaloryPane.setDisable(false);
    }

    public void enableVegetableEdit(javafx.event.ActionEvent e){
        vegetablePane.setVisible(true);
        vegetablePane.setDisable(false);
    }
    public void readDB(javafx.event.ActionEvent e) throws Exception {
        new Database().readFromDB(logger);

        UpdateList();
        UpdateWeight();
        UpdateKalory();
        UpdatePrice();
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
        UpdateKalory();
        UpdatePrice();
    }

    public void submitFindByKalory(javafx.event.ActionEvent e) throws IOException, ParseException {

        Salad salad = Salad.getInstance();
        List<Vegetables> filtered = null;
        if (salad.size() > 0){
            String min = minField.getText();   //Зчитування з рядків введення
            String max = maxField.getText();

            filtered = salad.findByKalory(Integer.parseInt(min),
                    Integer.parseInt(max),logger);

            filteredVegetables.getItems().clear();
            for (Vegetables vegetables : filtered) {
                filteredVegetables.getItems().addAll(vegetables.toString());
            }

        }

        kaloryPane.setVisible(false);
        kaloryPane.setDisable(true);
    }

    public void submitEditSalad(javafx.event.ActionEvent e) {
        Salad salad = Salad.getInstance();

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

        saladPane.setVisible(false);
        saladPane.setDisable(true);
    }

    public void submitCreateVegetable(javafx.event.ActionEvent e) {
        Salad salad = Salad.getInstance();

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
        nameField.clear();
        priceField.clear();
        weightField.clear();
        kaloryField.clear();
        qrField.clear();

        salad.addVegetables(new Vegetables(name,price,weight,kalory,QR),logger);

        UpdateList();
        UpdateWeight();
        UpdateKalory();
        UpdatePrice();

        vegetablePane.setVisible(false);
        vegetablePane.setDisable(true);
    }
    /*
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

package test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import objects.Salad;
import objects.Vegetables;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTest extends ApplicationTest {

    private static final Logger logger = Logger.getLogger(MainTest.class.getName());

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/main/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }

    @Test
    public void testCreateSalad(){
        Salad.getList().clear();
        clickOn("#CreateSalad");
        sleep(1500);
        clickOn("#boxField").write("Dream");
        sleep(1500);
        clickOn("#idField").write("013");
        sleep(1000);
        clickOn("#ConfirmSalad");
        sleep(1500);
        String example =  " Its name: " + Salad.getInstance().getBoxForm() + " Its ID: " + Salad.getInstance().getId();
        assertEquals(" Its name: " + "Dream" +
                " Its ID: " + 13 ,example);
    }

    @Test
    public void testCreateVegetable() {
        Salad.getList().clear();
        clickOn("#CreateVegetable");
        sleep(1500);
        clickOn("#nameField").write("Onion");
        sleep(1500);
        clickOn("#priceField").write("13");
        sleep(1500);
        clickOn("#weightField").write("9");
        sleep(1500);
        clickOn("#kaloryField").write("10");
        sleep(1500);
        clickOn("#qrField").write("013");
        sleep(1500);
        clickOn("#ConfirmVegetable");
        sleep(1500);
        String example =  Salad.getList().get(0).toString();

        assertEquals("QR code: " + 13 +
                "  Name of vegetable: " + "Onion" +
                "  Kalory concept: " + 10 +
                "  Price of vegetable: " + 13 +
                "  Weight of vegetable: " + 9 ,example);
    }

    @Test
    public void testFindByKalory() {
        Salad.getList().clear();
        Salad.getInstance().addVegetables(new Vegetables("Onion",13,9,10,13),logger);
        Salad.getInstance().addVegetables(new Vegetables("Carrot",33,20,50,33),logger);

        clickOn("#FindByKalory");
        sleep(1500);
        clickOn("#maxField").write("55");
        sleep(1500);
        clickOn("#minField").write("30");
        sleep(1500);
        clickOn("#ConfirmSearch");
        sleep(1500);

        String example =  Salad.getList().get(1).toString();

        assertEquals("QR code: " + 33 +
                "  Name of vegetable: " + "Carrot" +
                "  Kalory concept: " + 50 +
                "  Price of vegetable: " + 33 +
                "  Weight of vegetable: " + 20 ,example);

    }

    @Test
    public void testFlush() {
        Salad.getList().clear();
        clickOn("#Flush");
        sleep(1500);
        String actual = Salad.getInstance().getBoxForm();
        assertEquals("Unknown",actual);
    }


    @Test
    public void testSortByPrice(){
        Salad.getList().clear();
        Salad.getInstance().addVegetables(new Vegetables("Garlic",24,15,35,22),logger);
        Salad.getInstance().addVegetables(new Vegetables("Onion",13,9,10,13),logger);
        Salad.getInstance().addVegetables(new Vegetables("Carrot",33,20,50,33),logger);
        sleep(1500);
        String expected = Salad.getList().get(1).toString() + Salad.getList().get(0).toString() + Salad.getList().get(2).toString(),actual = "";
        clickOn("#Sort");
        for(int i = 0; i < Salad.getList().size();i++){
            actual += Salad.getList().get(i).toString();
        }
        sleep(1500);
        assertEquals(expected,actual);
    }

    @Test
    public void testReadDB() {
        Salad.getList().clear();
        clickOn("#readDB");
        String expected = new Vegetables("Onion",71, 12,34,11).toString();
        sleep(1500);
        String actual = Salad.getList().get(0).toString();
        assertEquals(expected,actual);
    }

    @Test
    public void testInsertDB() {
        Salad.getList().clear();
        clickOn("#CreateVegetable");
        sleep(1500);
        clickOn("#nameField").write("Onion");
        sleep(1500);
        clickOn("#priceField").write("71");
        sleep(1500);
        clickOn("#weightField").write("12");
        sleep(1500);
        clickOn("#kaloryField").write("34");
        sleep(1500);
        clickOn("#qrField").write("11");
        sleep(1500);
        clickOn("#ConfirmVegetable");
        sleep(1500);
        clickOn("#saveDB");
        sleep(1500);
        String expected = Salad.getInstance().getList().get(0).toString();
        clickOn("#readDB");
        sleep(1500);

        assertEquals(expected,Salad.getInstance().getList().get(0).toString());
    }

}

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
import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextMatchers.hasText;


public class MainTest extends ApplicationTest {

    private static final Logger logger = Logger.getLogger(MainTest.class.getName());

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
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
    public void testReadDB() {
        Salad.getList().clear();
        clickOn("#asc");
        String expected = new Vegetables("Onion",71, 12,34,011).toString();
        clickOn("#Read from database");
        sleep(600);
        String actual = Salad.getList().get(0).toString();
        assertEquals(expected,actual);
    }

    @Test
    public void testInsertDB() {
        Salad.getList().clear();
        clickOn("#asc");
        List<Vegetables> vegetables = Salad.getList();
        vegetables.add(new Vegetables("Onion",71, 12,34,011));
        clickOn("#Save to database");
        sleep(600);
        clickOn("#Read from database");
        sleep(600);

        String actual = null,expected = null;

        for(int i = 0; i < vegetables.size(); i++){
            expected += vegetables.get(i).toString();
            actual += vegetables.getList().get(i).toString();
        }
        assertEquals(expected,actual);
    }

    @Test
    public void testCreateVegetable() {
        Salad.getList().clear();
        clickOn("#Create vegetable");
        sleep(600);
        clickOn("#Name").write("Onion");
        sleep(600);
        clickOn("#Price").write("13");
        sleep(600);
        clickOn("#Weight").write("9");
        sleep(600);
        clickOn("#Kalory").write("10");
        sleep(600);
        clickOn("#ID").write("013");
        sleep(600);
        clickOn("#Confirm");
        sleep(600);
        String example =  Salad.getList().get(0).toString();

        assertEquals(" QR code: " + 13 +
                " Name of vegetable: " + "Onion" +
                " Kalory concept: " + 10 +
                " Price of vegetable: " + 13 +
                " Weight of vegetable: " + 9 ,example);
    }
}

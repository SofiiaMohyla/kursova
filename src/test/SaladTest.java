package test;

import Objects.Salad;
import Objects.Vegetables;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaladTest {

    private Salad test = new Salad();

    private static final Logger logger = Logger.getLogger(SaladTest.class.getName());
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ByteArrayOutputStream outputStreamSecond = new ByteArrayOutputStream();

    @org.junit.jupiter.api.Test
    void testGetTotalWeight() {
        logger.setUseParentHandlers(false);
        test.getList().clear();
        test.addVegetables(new Vegetables("Onion", 12, 3, 13, 122),logger);
        test.addVegetables(new Vegetables("Garlic", 10, 4, 10, 192),logger);
        assertEquals(7, test.getTotalWeight(logger));
    }

    @Test
    void testGetTotalKalory() {
        logger.setUseParentHandlers(false);
        test.getList().clear();
        test.addVegetables(new Vegetables("Onion", 12, 3, 13, 122),logger);
        test.addVegetables(new Vegetables("Garlic", 10, 4, 10, 192),logger);
        assertEquals(23, test.getTotalKalory(logger));
    }

    @Test
    void testGetTotalPrice() {
        logger.setUseParentHandlers(false);
        test.getList().clear();
        test.addVegetables(new Vegetables("Onion", 12, 3, 13, 122),logger);
        test.addVegetables(new Vegetables("Garlic", 10, 4, 10, 192),logger);
        assertEquals(22, test.getTotalPrice(logger));
    }

    @Test
    void testAddVegetables1() {
        logger.setUseParentHandlers(false);
        test.getList().clear();
        test.addVegetables(new Vegetables("Garlic", 10, 4, 10, 192),logger);
        assertEquals(new Vegetables("Garlic", 10, 4, 10, 192).toString(),test.getList().get(0).toString());
    }


    @Test
    void testFindByKalory1() {
        logger.setUseParentHandlers(false);
        test.getList().clear();
        List<Vegetables> found_vegetables = new ArrayList<>();
        test.addVegetables(new Vegetables("Onion", 12, 3, 13, 122),logger);
        test.addVegetables(new Vegetables("Garlic", 10, 4, 10, 192),logger);
        for (Vegetables p : Salad.getList()) {
            if (p.getKalory() >= 11 && p.getKalory() <= 13) {
                found_vegetables.add(p);
                System.out.println("Name of vegetable: " + p.getName() + "\nKalory concept: " +
                        p.getKalory());
            }
        }
        assertEquals(test.getList().get(0).toString(), found_vegetables.get(0).toString());
    }

    @Test
    void testFindByKalory2() {
        logger.setUseParentHandlers(false);
        test.getList().clear();
        List<Vegetables> found_vegetables = new ArrayList<>();
        test.addVegetables(new Vegetables("Onion", 12, 3, 13, 122),logger);
        test.addVegetables(new Vegetables("Garlic", 10, 4, 10, 192),logger);
        test.addVegetables(new Vegetables("Cabbage", 11, 10, 20, 999),logger);
        for (Vegetables p : Salad.getList()) {
            if (p.getKalory() >= 11 && p.getKalory() <= 13) {
                found_vegetables.add(p);
                System.out.println("Name of vegetable: " + p.getName() + "\nKalory concept: " +
                        p.getKalory());
            }
        }
        assertEquals(test.getList().get(0).toString(), found_vegetables.get(0).toString());
    }

    @Test
    void testDelete1() {
        logger.setUseParentHandlers(false);
        test.getList().clear();
        test.addVegetables(new Vegetables("Garlic", 10, 4, 10, 192), logger);
        test.addVegetables(new Vegetables("Cabbage", 11, 10, 20, 999), logger);
        test.getList().remove(1);
        assertEquals(new Vegetables("Garlic", 10, 4, 10, 192).toString(),test.getList().get(0).toString());
    }

    @Test
    void testDelete2() {
        logger.setUseParentHandlers(false);
        test.getList().clear();
        test.addVegetables(new Vegetables("Garlic", 10, 4, 10, 192), logger);
        test.addVegetables(new Vegetables("Cabbage", 11, 10, 20, 999), logger);
        test.addVegetables(new Vegetables("Onion", 12, 3, 13, 122), logger);
        test.getList().remove(2);
        List<Vegetables> found_vegetables = new ArrayList<>();
        found_vegetables.add(new Vegetables("Garlic", 10, 4, 10, 192));
        found_vegetables.add(new Vegetables("Cabbage", 11, 10, 20, 999));
        String actual = null,expected = null;
        for(int i = 0; i < found_vegetables.size();i++){
            actual += test.getList().get(i).toString();
            expected += found_vegetables.get(i).toString();
        }
        assertEquals(expected,actual);
    }

    @Test
    void testSortByPrice() {
        logger.setUseParentHandlers(false);
        test.getList().clear();
        test.addVegetables(new Vegetables("Onion", 12, 3, 13, 122),logger);
        test.addVegetables(new Vegetables("Garlic", 10, 4, 10, 192),logger);
        test.addVegetables(new Vegetables("Cabbage", 19, 20, 30, 999),logger);

        List<Vegetables> found_vegetables = new ArrayList<>();
        found_vegetables.add(new Vegetables("Garlic", 10, 4, 10, 192));
        found_vegetables.add(new Vegetables("Onion", 12, 3, 13, 122));
        found_vegetables.add(new Vegetables("Cabbage", 19, 20, 30, 999));
        Salad.sortByPrice();

        String expected = null,actual = null;

        for (int i = 0;i < found_vegetables.size(); i++){
            expected += found_vegetables.get(i).toString();
            actual += test.getList().get(i).toString();
        }

        assertEquals(expected, actual);
    }

    @Test
    void testFlush() {
        logger.setUseParentHandlers(false);
        test.getList().clear();
        test.addVegetables(new Vegetables("Garlic", 10, 4, 10, 192),logger);
        test.addVegetables(new Vegetables("Cabbage", 11, 10, 20, 999),logger);

        System.setOut(new PrintStream(outputStreamCaptor));
        test.flush();

        System.setOut(new PrintStream(standardOut));

        assertEquals("Salad data flushed!",outputStreamCaptor.toString().trim());
    }


    @Test
    void testGetUniqueId() {
        logger.setUseParentHandlers(false);
        test.getList().clear();
        test.addVegetables(new Vegetables("Garlic", 10, 4, 10, 192),logger);

        System.setOut(new PrintStream(outputStreamCaptor));
        test.getUniqueId(logger);
        System.setOut(new PrintStream(standardOut));

        String expected = "\nUnique code: " + 0 + "\nName of vegetable: " + test.getList().get(0).getName();
        assertEquals( expected.trim() ,outputStreamCaptor.toString().trim());
    }


    @Test
    void testGetListOfVegetables() {
        logger.setUseParentHandlers(false);
        test.getList().clear();
        test.addVegetables(new Vegetables("Onion", 12, 3, 13, 122),logger);
        test.addVegetables(new Vegetables("Garlic", 10, 4, 10, 192),logger);
        test.addVegetables(new Vegetables("Cabbage", 11, 10, 20, 999),logger);
        System.setOut(new PrintStream(outputStreamCaptor));
        test.getListOfVegetables(logger);
        System.setOut(new PrintStream(standardOut));
        System.setOut(new PrintStream(outputStreamSecond));
        for (int i = 0; i < test.getList().size(); i++){
            System.out.println("\nQR code: " + test.getList().get(i).getQR() + "\nName of vegetable: " + test.getList().get(i).getName() + "\nKalory concept: " + test.getList().get(i).getKalory()
                    + "\nPrice of vegetable: " + test.getList().get(i).getPrice()+ "\nWeight of vegetable: " + test.getList().get(i).getWeight());
        }
        System.setOut(new PrintStream(standardOut));

        assertEquals(outputStreamSecond.toString().trim(),outputStreamCaptor.toString().trim());
    }
}
package Objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Salad {
    private int id = 0;
    private String BoxForm = "Unknown";
    private static List<Vegetables> listOfVegetables = new ArrayList<Vegetables>();

    private static Salad instance = null;

    public static Salad getInstance() {
        if (instance == null) {
            instance = new Salad();
        }
        return instance;
    }

    public void addVegetables(Vegetables vegetables) {
        this.listOfVegetables.add(vegetables);
    }

    public static void sortByPrice() {
        Collections.sort(listOfVegetables, new Comparator() {
            public int compare(Object o1, Object o2) {
                Integer x1 = ((Vegetables) o1).getPrice();
                Integer x2 = ((Vegetables) o2).getPrice();
                int sComp = x1.compareTo(x2);
                if (sComp > 0) {
                    return sComp;
                }
                x1 = ((Vegetables) o1).getPrice();
                x2 = ((Vegetables) o2).getPrice();
                return x1.compareTo(x2);
            }
        });
    }

    public List<Vegetables> findByKalory(int min, int max) {
        List<Vegetables> found_vegetables = new ArrayList<>();
        for (Vegetables p : listOfVegetables) {
            if (p.getKalory() >= min && p.getKalory() <= max) {
                found_vegetables.add(p);
                System.out.println("Name of vegetable: " + p.getName() + "\nKalory concept: " +
                        p.getKalory());
            }
        }
        return found_vegetables;
    }


    public int getTotalWeight() {
        int total = 0;
        for (int i = 0; i < listOfVegetables.size(); i++) {
            total = total + listOfVegetables.get(i).getWeight();
        }
        return total;
    }

    public void delete(int index) {
        listOfVegetables.remove(index);
    }

    public int size() {
        return listOfVegetables.size();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBoxForm() {
        return BoxForm;
    }

    public void setBoxForm(String boxForm) {
        BoxForm = boxForm;
    }

    public void getListOfVegetables() {
        for (int i = 0; i < listOfVegetables.size(); i++) {
            System.out.println("\nType " + listOfVegetables.get(i) + "QR code: " + listOfVegetables.get(i).getQR() + "\nName of vegetable: " + listOfVegetables.get(i).getName() + "\nKalory concept: " + listOfVegetables.get(i).getKalory()
                    + "\nPrice of vegetable: " + listOfVegetables.get(i).getPrice() + "\nWeight of vegetable: " + listOfVegetables.get(i).getWeight());
        }
    }

    public void getUniqueId() {
        int i;
        for (i = 0; i < listOfVegetables.size(); i++) {
            System.out.println("\nUnique code: " + i + "\nName of vegetable: " + listOfVegetables.get(i).getName());
            ;
        }
    }


    public void flush() {
        this.id = 0;
        this.BoxForm = "Unknown";
        this.listOfVegetables.clear();
        System.out.println("Salad data flushed!");
    }

    public static List<Vegetables> getList() {
        return listOfVegetables;
    }
}
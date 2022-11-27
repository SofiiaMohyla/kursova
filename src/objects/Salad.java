package objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


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

    public void getListOfVegetables(Logger logger) {
        logger.finest("Виводимо список овочів у нашому салаті. Є вийняток, коли немає доданих овочів у салат.");
        for (int i = 0; i < listOfVegetables.size(); i++) {
            System.out.println("\nQR code: " + listOfVegetables.get(i).getQR() + "\nName of vegetable: " + listOfVegetables.get(i).getName() + "\nKalory concept: " + listOfVegetables.get(i).getKalory()
                    + "\nPrice of vegetable: " + listOfVegetables.get(i).getPrice() + "\nWeight of vegetable: " + listOfVegetables.get(i).getWeight());
        }
    }


    public void addVegetables(Vegetables vegetables,Logger logger) {
        logger.finer("Додаємо овоч до салату. Не можливо вибрати тип, який не існує");
        this.listOfVegetables.add(vegetables);
    }

    //за зростанням
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


    //за спаданням
//    public static void sortByPrice() {
//        Collections.sort(listOfVegetables, new Comparator() {
//            public int compare(Object o1, Object o2) {
//                Integer x1 = ((Vegetables) o1).getPrice();
//                Integer x2 = ((Vegetables) o2).getPrice();
//                int sComp = x2.compareTo(x1);
//                if (sComp < 0) {
//                    return sComp;
//                }
//                x1 = ((Vegetables) o1).getPrice();
//                x2 = ((Vegetables) o2).getPrice();
//                return x2.compareTo(x1);
//            }
//        });
//    }


    public List<Vegetables> findByKalory(int min, int max, Logger logger) {
        logger.fine("Фільтруємо наші овочі у салаті за вмістом калорій , можливим є повернення пустого списку.");
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


    public int getTotalWeight(Logger logger) {
        logger.finer("Занулюємо загальну вагу салату, для очищення від можливої неправдивої інформації" +
                " та знаходимо загальну вагу салату додаючи ваги овочів які у нього входять.");

        int total = 0;
        for (int i = 0; i < listOfVegetables.size(); i++) {
            total = total + listOfVegetables.get(i).getWeight();
        }
        return total;
    }

    public int getTotalKalory(Logger logger) {
        logger.finer("Занулюємо загальну калорійність салату, для очищення від можливої неправдивої інформації" +
                " та знаходимо загальну калорійність салату додаючи калорійність овочів які у нього входять.");

        int total = 0;
        for (int i = 0; i < listOfVegetables.size(); i++) {
            total = total + listOfVegetables.get(i).getKalory();
        }
        return total;
    }

    public int getTotalPrice(Logger logger) {
        logger.finer("Занулюємо загальну вартість салату, для очищення від можливої неправдивої інформації" +
                " та знаходимо загальну вартість салату додаючи вартість овочів які у нього входять.");

        int total = 0;
        for (int i = 0; i < listOfVegetables.size(); i++) {
            total = total + listOfVegetables.get(i).getPrice();
        }
        return total;
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


    //    public void delete(int index, Logger logger) {
//        logger.finer("Видаляємо овоч з салату за вказаним індексом");
//        listOfVegetables.remove(index);
//    }

//    public void getUniqueId(Logger logger) {
//        logger.finest("Виводимо список овочів у нашому салаті з унікальними кодами . Є вийняток, коли немає доданих овочів у салат.");
//        int i;
//        for (i = 0; i < listOfVegetables.size(); i++) {
//            System.out.println("\nUnique code: " + i + "\nName of vegetable: " + listOfVegetables.get(i).getName());
//            ;
//        }
//    }
}
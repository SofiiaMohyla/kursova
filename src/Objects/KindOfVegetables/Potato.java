package Objects.KindOfVegetables;

import Objects.Root_crops;

public class Potato extends Root_crops {
    private static final int productQR = 264;
    private static final String name = "Potato";
    private static final int price = 5;
    private static final int weight = 20;
    private static final int kalory = 90;

    public Potato() {
        super(name, price, weight, kalory, productQR);
    }
}

package Objects.KindOfVegetables;

import Objects.Root_crops;

public class Tomato extends Root_crops {
    private static final int productQR = 888;
    private static final String name = "Tomato";
    private static final int price = 12;
    private static final int weight = 30;
    private static final int kalory = 20;

    public Tomato() {
        super(name, price, weight, kalory, productQR);
    }
}

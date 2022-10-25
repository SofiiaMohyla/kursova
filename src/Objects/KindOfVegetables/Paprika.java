package Objects.KindOfVegetables;

import Objects.Root_crops;

public class Paprika extends Root_crops {
    private static final int productQR = 266;
    private static final String name = "Paprika";
    private static final int price = 10;
    private static final int weight = 20;
    private static final int kalory = 30;

    public Paprika() {
        super(name, price, weight, kalory, productQR);
    }
}

package Objects.KindOfVegetables;

import Objects.Root_crops;

public class Carrot extends Root_crops {
    private static final int productQR = 602;
    private static final String name = "Carrot";
    private static final int price = 15;
    private static final int weight = 30;
    private static final int kalory = 20;

    public Carrot() {
        super(name, price, weight, kalory, productQR);
    }
}

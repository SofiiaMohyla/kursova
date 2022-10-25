package Objects.KindOfVegetables;

import Objects.Root_crops;

public class Cucumber extends Root_crops {
    private static final int productQR = 222;
    private static final String name = "Cucumber";
    private static final int price = 6;
    private static final int weight = 20;
    private static final int kalory = 10;

    public Cucumber() {
        super(name, price, weight, kalory, productQR);
    }
}

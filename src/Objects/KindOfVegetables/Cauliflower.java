package Objects.KindOfVegetables;

import Objects.Cabbage_type;

public class Cauliflower extends Cabbage_type {
    private static final int productQR = 412;
    private static final String name = "Cauliflower";
    private static final int price = 26;
    private static final int weight = 20;
    private static final int kalory = 19;

    public Cauliflower() {
        super(name, price, weight, kalory, productQR);
    }
}

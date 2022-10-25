package Objects.KindOfVegetables;

import Objects.Onion_type;

public class Garlic extends Onion_type {
    private static final int productQR = 900;
    private static final String name = "Garlic";
    private static final int price = 3;
    private static final int weight = 5;
    private static final int kalory = 5;

    public Garlic() {
        super(name, price, weight, kalory, productQR);
    }
}

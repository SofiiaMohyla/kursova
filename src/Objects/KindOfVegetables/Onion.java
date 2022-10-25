package Objects.KindOfVegetables;

import Objects.Onion_type;

public class Onion extends Onion_type {
    private static final int productQR = 112;
    private static final String name = "Onion";
    private static final int price = 3;
    private static final int weight = 10;
    private static final int kalory = 60;

    public Onion() {
        super(name, price, weight, kalory, productQR);
    }
}

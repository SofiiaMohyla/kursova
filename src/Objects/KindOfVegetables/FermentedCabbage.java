package Objects.KindOfVegetables;

import Objects.Cabbage_type;

public class FermentedCabbage extends Cabbage_type {
    private static final int productQR = 302;
    private static final String name = "Fermented Cabbage";
    private static final int price = 6;
    private static final int weight = 20;
    private static final int kalory = 120;

    public FermentedCabbage() {
        super(name, price, weight, kalory, productQR);
    }
}

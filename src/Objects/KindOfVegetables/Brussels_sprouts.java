package Objects.KindOfVegetables;
import Objects.Cabbage_type;

public class Brussels_sprouts extends Cabbage_type {
    private static final int productQR = 432;
    private static final String name = "Brussel sprouts";
    private static final int price = 16;
    private static final int weight = 20;
    private static final int kalory = 7;

    public Brussels_sprouts() {
        super(name, price, weight, kalory, productQR);
    }
}

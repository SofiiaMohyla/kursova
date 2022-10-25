package Objects;

public class Cabbage_type extends Vegetables {
    public Cabbage_type(String name, int price, int weight, int kalory, int QR) {
        super(name, price, weight,  kalory, QR);
    }

    @Override
    public String toString() {
        return "Cabbage_type" +
                super.toString() + '\n';
    }
}

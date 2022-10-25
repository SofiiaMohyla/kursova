package Objects;

public class Onion_type extends Vegetables {
    public Onion_type(String name, int price, int weight, int kalory,int QR) {
        super(name, price, weight, kalory, QR);
    }

    @Override
    public String toString() {
        return "Onion_type" +
                super.toString() + '\n';
    }
}


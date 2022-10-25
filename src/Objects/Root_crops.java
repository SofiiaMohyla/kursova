package Objects;

public class Root_crops extends Vegetables {
    public Root_crops(String name, int price, int weight, int kalory,int QR) {
        super(name, price, weight,  kalory, QR);
    }

    @Override
    public String toString() {
        return "Root_crops" +
                super.toString() + '\n';
    }
}

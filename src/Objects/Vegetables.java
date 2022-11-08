package Objects;

public class Vegetables {
    private int QR;
    private String name;
    private int price;
    private int weight;
    private int kalory;

    @Override
    public String toString() {
        return  "QR code: " + QR + "  Name of vegetable: " + name + "  Kalory concept: " + kalory
                + "  Price of vegetable: " + price + "  Weight of vegetable: " + weight;
    }

    public Vegetables(String name, int price, int weight, int kalory, int QR) {
        this.QR = QR;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.kalory = kalory;
    }

    public int getQR() {return QR;}

    public void setQR(int QR) {this.QR = QR;}

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {return price;}

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getKalory() {
        return kalory;
    }

    public void setKalory(int kalory) {
        this.kalory = kalory;
    }

}

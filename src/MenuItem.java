public class MenuItem implements Cloneable {
    private int id;
    private String name;
    private double price;

    public MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
 public int getid() {
        return id;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", price);
    }

    @Override
    public MenuItem clone() {
        try {
            return (MenuItem) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
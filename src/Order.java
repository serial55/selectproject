import java.util.List;

public class Order {
    private String customerName;
    private int tableNumber;
    private String tableType;
    private List<MenuItem> items;
    private double totalPrice;

    private Order(Builder builder) {
        this.customerName = builder.customerName;
        this.tableNumber = builder.tableNumber;
        this.tableType = builder.tableType;
        this.items = builder.items;
        this.totalPrice = builder.totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getTableType() {
        return tableType;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public static class Builder {
        private String customerName;
        private int tableNumber;
        private String tableType;
        private List<MenuItem> items;
        private double totalPrice;

        public Builder setCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder setTableNumber(int tableNumber) {
            this.tableNumber = tableNumber;
            return this;
        }

        public Builder setTableType(String tableType) {
            this.tableType = tableType;
            return this;
        }

        public Builder setItems(List<MenuItem> items) {
            this.items = items;
            return this;
        }

        public Builder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    @Override
    public String toString() {
        return "Order for " + customerName + " | Table " + tableNumber + " (" + tableType + ") | Total: $" + totalPrice;
    }
}

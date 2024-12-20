import java.util.ArrayList;
import java.util.List;

public class MenuItemFactory {
    public static List<MenuItem> getMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        // Adding a large menu list
        items.add(new MenuItem(1, "Burger", 8.99));
        items.add(new MenuItem(2, "Pizza", 12.50));
        items.add(new MenuItem(3, "Pasta", 10.99));
        items.add(new MenuItem(4, "Salad", 7.25));
        items.add(new MenuItem(5, "Grilled Chicken", 15.00));
        items.add(new MenuItem(6, "Steak", 22.99));
        items.add(new MenuItem(7, "Fish & Chips", 14.50));
        items.add(new MenuItem(8, "Tacos", 9.75));
        items.add(new MenuItem(9, "Sushi", 18.99));
        items.add(new MenuItem(10, "Chocolate Cake", 6.50));
        items.add(new MenuItem(11, "Ice Cream", 5.00));
        items.add(new MenuItem(12, "Apple Pie", 4.75));
        items.add(new MenuItem(13, "Orange Juice", 3.00));
        items.add(new MenuItem(14, "Coffee", 2.50));
        items.add(new MenuItem(15, "Milkshake", 4.25));
        return items;
    }
}

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // GUI Setup
        JFrame frame = new JFrame("Restaurant Management System");
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(0, 1));

        // Customer Name
        JTextField customerNameField = new JTextField();
        customerNameField.setBorder(BorderFactory.createTitledBorder("Customer Name"));
        frame.add(customerNameField);

        // Table Type ComboBox
        JComboBox<String> tableTypeBox = new JComboBox<>(new String[]{"VIP", "Normal"});
        tableTypeBox.setBorder(BorderFactory.createTitledBorder("Select Table Type"));
        frame.add(tableTypeBox);

        // Menu ComboBox
        List<MenuItem> menuItems = MenuItemFactory.getMenuItems();
        JComboBox<MenuItem> menuComboBox = new JComboBox<>(menuItems.toArray(new MenuItem[0]));
        menuComboBox.setBorder(BorderFactory.createTitledBorder("Select Menu Item"));
        frame.add(menuComboBox);

        // Order List
        DefaultListModel<String> orderListModel = new DefaultListModel<>();
        JList<String> orderList = new JList<>(orderListModel);
        frame.add(new JScrollPane(orderList));

        JLabel totalLabel = new JLabel("Total: $0.00");
        frame.add(totalLabel);

        // Buttons
        JButton addButton = new JButton("Add to Order");
        JButton saveButton = new JButton("Save Order");
        JButton clearButton = new JButton("Clear Data");
        JButton paymentButton = new JButton("Proceed to Payment");
        frame.add(addButton);
        frame.add(saveButton);
        frame.add(clearButton);
        frame.add(paymentButton);

        List<MenuItem> selectedItems = new ArrayList<>();
        Random random = new Random();

        // Add to Order Button Logic
        addButton.addActionListener(e -> {
            MenuItem selected = ((MenuItem) menuComboBox.getSelectedItem()).clone();
            selectedItems.add(selected);
            orderListModel.addElement(selected.toString());
            double total = selectedItems.stream().mapToDouble(MenuItem::getPrice).sum();
            totalLabel.setText("Total: $" + String.format("%.2f", total));
        });

        // Save Order Button Logic
        saveButton.addActionListener(e -> {
            String customerName = customerNameField.getText().trim();
            if (customerName.isEmpty() || selectedItems.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter customer name and select items!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String tableType = (String) tableTypeBox.getSelectedItem();
            int tableNumber = random.nextInt(100) + 1;
            double total = selectedItems.stream().mapToDouble(MenuItem::getPrice).sum();

            Order order = new Order.Builder()
                    .setCustomerName(customerName)
                    .setTableNumber(tableNumber)
                    .setTableType(tableType)
                    .setItems(selectedItems)
                    .setTotalPrice(total)
                    .build();

            try {
                OrderProxy.saveOrder(order);
                JOptionPane.showMessageDialog(frame, "Order Saved Successfully!\n" + order, "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Failed to save order!", "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        // Clear Data Button Logic
        clearButton.addActionListener(e -> {
            customerNameField.setText("");
            tableTypeBox.setSelectedIndex(0);
            menuComboBox.setSelectedIndex(0);
            orderListModel.clear();
            selectedItems.clear();
            totalLabel.setText("Total: $0.00");
            JOptionPane.showMessageDialog(frame, "All data cleared!", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        // Payment Button Logic
        paymentButton.addActionListener(e -> {
            double total = selectedItems.stream().mapToDouble(MenuItem::getPrice).sum();
            if (total == 0) {
                JOptionPane.showMessageDialog(frame, "No items selected for payment!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Payment Successful!\nTotal Amount: $" + String.format("%.2f", total), "Payment", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
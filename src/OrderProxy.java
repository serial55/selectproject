import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderProxy {
    public static void saveOrder(Order order) {
        Connection conn = null;
        PreparedStatement orderStmt = null;
        PreparedStatement detailsStmt = null;

        try {
            conn = DatabaseConnection.getInstance().getConnection();

            // Save Order
            String orderQuery = "INSERT INTO orders (customer_name, table_number, table_type, total_price) VALUES (?, ?, ?, ?) RETURNING id";
            orderStmt = conn.prepareStatement(orderQuery, PreparedStatement.RETURN_GENERATED_KEYS);
            orderStmt.setString(1, order.getCustomerName());
            orderStmt.setInt(2, order.getTableNumber());
            orderStmt.setString(3, order.getTableType());
            orderStmt.setDouble(4, order.getTotalPrice());

            int rowsAffected = orderStmt.executeUpdate();
            ResultSet rs = orderStmt.getGeneratedKeys();

            if (rs.next()) {
                int orderId = rs.getInt(1); // Extract the generated order ID

                // Save Order Details
                String detailsQuery = "INSERT INTO orderdetails (order_id, menu_item_id, quantity) VALUES (?, ?, ?)";
                detailsStmt = conn.prepareStatement(detailsQuery);

                for (MenuItem item : order.getItems()) {
                    detailsStmt.setInt(1, orderId);
                    detailsStmt.setInt(2, item.getid());
                    detailsStmt.setInt(3, 1); // Assuming quantity of 1 for each item
                    detailsStmt.addBatch();
                }

                detailsStmt.executeBatch(); // Execute all batch inserts
                System.out.println("Order saved successfully: " + order);
            } else {
                throw new Exception("Failed to retrieve generated order ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (orderStmt != null) orderStmt.close();
                if (detailsStmt != null) detailsStmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
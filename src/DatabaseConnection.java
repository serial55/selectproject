import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/RestaurantDB"; // Update with your database name
            String username = "postgres";
            String password = "123";
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected successfully!");
        } catch (Exception e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

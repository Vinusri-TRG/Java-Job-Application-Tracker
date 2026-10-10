import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/jobtracker";
            String username = System.getenv("DB_USERNAME");
            String password = System.getenv("DB_PASSWORD");

            Connection con = DriverManager.getConnection(url, username, password);

            System.out.println("MySQL Connected Successfully!");

            return con;

        } catch (Exception e) {

            System.out.println("Connection Failed!");
            e.printStackTrace();

            return null;
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}
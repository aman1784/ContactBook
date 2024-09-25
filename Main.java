import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

// Main class to run the application
public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/database_name";
    private static final String username = "root"; // Database username
    private static final String password = "password"; // Database password

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle ClassNotFoundException
        }

        Scanner scanner = new Scanner(System.in); // Create a scanner for user input    
        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection(url, username, password);
            Contact contact = new Contact(connection, scanner); // Create a Contact instanc
            ContactBook contactBook = new ContactBook(contact, scanner); // Create a ContactBook instance
            contactBook.run(); // Run the ContactBook interface


        } catch (SQLException e) {
            e.printStackTrace(); // Handle SQL exceptions
        }
    }



}

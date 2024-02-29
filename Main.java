import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://localhost:3306/amanseth";
    private static final String username = "root";
    private static final String password = "root@Mysql1234";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Contact contact = new Contact(connection, scanner);
            ContactBook contactBook = new ContactBook(contact, scanner);
            contactBook.run();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
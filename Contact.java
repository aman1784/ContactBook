import java.sql.*;
import java.util.Scanner;

// Class representing a Contact with methods to manage a contact book
public class Contact {
    private final Connection connection; // Database connection
    private final Scanner scanner; // Scanner for user input

    // Constructor to initialize Contact with a database connection and scanner
    public Contact(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    // Method to add a new contact
    public void addContact(){
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Number: ");
        String number = scanner.next();
        System.out.print("Enter EmailId: ");
        String emailId = scanner.next();

        // SQL query to insert new contact
        try{
            String query = "INSERT INTO contactBook(name, phoneNumber, emailId) values" +
                    "(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, number);
            preparedStatement.setString(3, emailId);

            int affectedRows = preparedStatement.executeUpdate();

            // Check if contact was added successfully
            if (affectedRows > 0){
                System.out.println("Contact Added Successfully...");
            }else{
                System.err.println("Failed To add contact...");
            }

        } catch (SQLException e){
            e.printStackTrace(); // Handle SQL exceptions
        }
    }

    // Method to view all contacts
    public void viewAllContacts(){
        try{
            String query = "SELECT * FROM contactBook"; // SQL query to select all contacts
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery(); // Execute query
            viewData(resultSet); // Display the result set

        
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Method to find contacts by name
    public void findByName(){
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String findName = scanner.nextLine();
        String query = "SELECT * FROM contactBook WHERE name LIKE ?"; // SQL query to search by name

        // PreparedStatement and ResultSet are declared within the parentheses of the
        // try-with-resources statement. Java will automatically close these resource when the try block
        // is exited, whether normally or due to an exception. This helps in reducing boilerplate code for resource management.
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, "%" + findName + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                viewData(resultSet); // Display the result set
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to find contacts by number
    public void findByNumber(){
        System.out.print("Enter Number: ");
        String findNumber = scanner.next();
        String query = "SELECT * FROM contactBook WHERE phoneNumber LIKE ?"; // SQL query to search by number

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + findNumber + "%");

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                viewData(resultSet); // Display the result set
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to find contacts by name or number
    public void findByNameOrNumber(){
        System.out.print("Enter Name or Number: ");
        String findNameNumber = scanner.next();
        String query = "SELECT * FROM contactBook WHERE phoneNumber LIKE ? OR name LIKE ?"; // SQL query to search by name or number
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + findNameNumber + "%");
            preparedStatement.setString(2, "%" + findNameNumber + "%");
            ResultSet resultSet = preparedStatement.executeQuery(); // Execute query
            viewData(resultSet);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to display contacts from ResultSet
    // viewData() ===> to implement DRY principle
    public void viewData(ResultSet resultSet){
        if (resultSet == null) {
            System.out.println("ResultSet is null. Unable to display data.");
            return;
        }
        System.out.println("==== CONTACTS ====");
        System.out.println("+-----+--------------------+--------------+---------------------------+");
        System.out.println("| ID  |   NAME             |   NUMBER     |   EMAIL ID                |");
        System.out.println("+-----+--------------------+--------------+---------------------------+");
        try(resultSet){
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String number = resultSet.getString("phoneNumber");
                String email = resultSet.getString("emailId");
                System.out.printf("|  %-2s |  %-17s|  %-12s |  %-22s   |\n", id, name, number, email);
                System.out.println("+-----+--------------------+--------------+---------------------------+");
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
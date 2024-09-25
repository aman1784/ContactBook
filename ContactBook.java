
import java.util.Scanner;

// Class to manage the ContactBook interface
public class ContactBook {
    private Contact contact; // Instance of Contact to manage contacts
    private Scanner scanner; // Scanner for user input

    // Constructor to initialize ContactBook with a Contact instance and scanner
    public ContactBook(Contact contact, Scanner scanner) {
        this.contact = contact;
        this.scanner = scanner;
    }

    // Method to run the ContactBook interface
    public void run(){
        while (true){
            System.out.println("==== *** CONTACT BOOK *** ====");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Find By Name");
            System.out.println("4. Find By Number");
            System.out.println("5. Find By Name or Number");
            System.out.println("0. Exit");
            System.out.print("Enter Your choice: ");
            int choice = scanner.nextInt(); // Get user choice
            System.out.println();

            switch (choice){
                case 0:
                    System.out.println("Thank You :)");
                    return; // Exit the application 
                case 1:
                    //Add Contact
                    contact.addContact();
                    System.out.println();
                    break;
                case 2:
                    //View All contacts
                    contact.viewAllContacts();
                    System.out.println();
                    break;
                case 3:
                    //Find contact by name
                    contact.findByName();
                    System.out.println();
                    break;
                case 4:
                    //Find contact by number
                    contact.findByNumber();
                    System.out.println();
                    break;
                case 5:
                    // Find contact by name or number
                    contact.findByNameOrNumber();
                    System.out.println();
                    break;
                default:
                System.out.println("Invalid choice"); // Handle invalid choice
            }
        }
    }
}

import controller.CustomerController;
import controller.InventoryManagerController;
import model.persons.InventoryManager;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        InventoryManager manager = new InventoryManager();
        try {
            manager.loadItemsFromFile("inventory_data.txt");
        } catch (IOException e) {
            System.out.println("Error loading items from file: " + e.getMessage());
            return;
        }


        CustomerController customerController = new CustomerController();
        InventoryManagerController managerController = new InventoryManagerController();

        int roleChoice;

        System.out.println("Welcome to the Inventory Management System!");
        System.out.println("1. Login as Customer");
        System.out.println("2. Login as Manager");
        System.out.print("Choose your role (1 or 2): ");
        roleChoice = scanner.nextInt();
        scanner.nextLine();


        if (roleChoice == 1) {

            customerController.customerMenu(manager.getItems());
        } else if (roleChoice == 2) {

            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (InventoryManager.validateManagerCredentials(username, password)) {
                System.out.println("Login successful!");
                managerController.managerMenu(manager.getItems(), "inventory_data.txt");
            } else {
                System.out.println("Invalid username or password. Access denied.");
            }
        } else {
            System.out.println("Invalid choice. Please restart the program.");
        }
    }

}

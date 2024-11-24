package controller;

import model.persons.InventoryManager;
import model.products.Category;
import model.products.InventoryItem;

import java.util.List;
import java.util.Scanner;

import static model.persons.InventoryManager.addManager;
import static model.persons.InventoryManager.removeManager;

public class InventoryManagerController {

    private InventoryManager manager;

    public InventoryManagerController() {
        this.manager = new InventoryManager();
    }

    public void addItemToFile(InventoryItem item, String filename) {
        manager.addItemToFile(item, filename);
    }


    public void removeItemFromFile(String itemName, String filename) {
        manager.removeItemFromFile(itemName, filename);
    }

    public void displayAllItems(List<InventoryItem> items) {
        manager.displayAllItems(items);
    }

    public void displayItemsByCategory(List<InventoryItem> items, Category category) {
        manager.displayItemsByCategory(items, category);
    }

    public void sortItemsByPrice(List<InventoryItem> items) {
        manager.sortItemsByPrice(items);
    }


    public void managerMenu(List<InventoryItem> items, String filename) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome, Manager! Choose an option:");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Show All Items");
            System.out.println("4. Show Items by Category");
            System.out.println("5. Sort Items by Price");
            System.out.println("6. Add Manager");
            System.out.println("7. Remove Manager");
            System.out.println("8. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();  // Clear the buffer

            switch (choice) {
                case 1:
                    System.out.println("Enter Item Details (Name, Description, Category, Price, IsPerishable, IsBreakable, Quantity): ");
                    String input = scanner.nextLine();
                    InventoryItem item = InventoryItem.fromString(input);
                    addItemToFile(item, filename);
                    break;
                case 2:
                    System.out.println("Enter Item Name to Remove: ");
                    String itemNameToRemove = scanner.nextLine();
                    removeItemFromFile(itemNameToRemove, filename);
                    break;
                case 3:
                    displayAllItems(items);
                    break;
                case 4:
                    System.out.println("Enter Category (1 for GROCERY, 2 for ELECTRONICS, 3 for FRAGILE): ");
                    int categoryChoice = scanner.nextInt();
                    scanner.nextLine();

                    String categoryInput = "";
                    switch (categoryChoice) {
                        case 1:
                            categoryInput = "GROCERY";
                            break;
                        case 2:
                            categoryInput = "ELECTRONICS";
                            break;
                        case 3:
                            categoryInput = "FRAGILE";
                            break;
                        default:
                            System.out.println("Invalid category choice. Please try again.");
                            break;
                    }

                    if (!categoryInput.isEmpty()) {
                        Category selectedCategory = Category.fromString(categoryInput);
                        if (selectedCategory == null) {
                            System.out.println("Invalid category. Please try again.");
                        } else {
                            displayItemsByCategory(items, selectedCategory);
                        }
                    }
                    break;
                case 5:
                    sortItemsByPrice(items);
                    break;
                case 6:
                    System.out.print("Enter new manager username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter new manager password: ");
                    String newPassword = scanner.nextLine();
                    addManager(newUsername, newPassword);
                    break;
                case 7:
                    System.out.print("Enter manager username to remove: ");
                    String removeUsername = scanner.nextLine();
                    removeManager(removeUsername);
                    break;
                case 8:
                    System.out.println("Goodbye, Manager!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 8);

    }

}

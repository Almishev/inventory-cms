package controller;

import model.persons.InventoryManager;
import model.products.Category;
import model.products.InventoryItem;

import java.util.List;
import java.util.Scanner;

import static model.persons.InventoryManager.*;

public class InventoryManagerController {

    private final InventoryManager manager;

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
            System.out.println("8. Edit Item Price");
            System.out.println("9. Edit Item Quantity");
            System.out.println("10. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.println("Enter Description: ");
                    String description = scanner.nextLine();

                    System.out.println("Enter Category (1 for GROCERY, 2 for ELECTRONICS, 3 for FRAGILE): ");
                    int categoryChoice = scanner.nextInt();
                    scanner.nextLine();

                    Category category = null;
                    boolean isPerishable = false;
                    boolean isBreakable = false;

                    switch (categoryChoice) {
                        case 1:
                            category = Category.fromString("GROCERY");
                            isPerishable = true;
                            isBreakable = false;
                            break;
                        case 2:
                            category = Category.fromString("ELECTRONICS");
                            isPerishable = false;
                            isBreakable = true;
                            break;
                        case 3:
                            category = Category.fromString("FRAGILE");
                            isPerishable = false;
                            isBreakable = true;
                            break;
                        default:
                            System.out.println("Invalid category choice. Please try again.");
                            return;
                    }

                    double price;
                    while (true) {
                        System.out.print("Enter Price: ");
                        if (scanner.hasNextDouble()) {
                            price = scanner.nextDouble();
                            break;
                        } else {
                            System.out.println("Invalid price input. Please enter a valid price.");
                            scanner.next();
                        }
                    }



                    int quantity;
                    while (true) {
                        System.out.print("Enter Quantity: ");
                        if (scanner.hasNextInt()) {
                            quantity = scanner.nextInt();
                            break;
                        } else {
                            System.out.println("Invalid quantity. Please enter a valid number.");
                            scanner.next();
                        }
                    }


                    InventoryItem item = new InventoryItem(name, description, category, price, isBreakable, isPerishable, quantity);

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
                    int categoryShow = scanner.nextInt();
                    scanner.nextLine();

                    String categoryInput = "";
                    switch (categoryShow) {
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
                    System.out.println("Enter Item Name to Edit Price: ");
                    String itemNameToEditPrice = scanner.nextLine();
                    InventoryItem itemToEditPrice = findItemByName(items, itemNameToEditPrice);
                    if (itemToEditPrice != null) {
                        double newPrice;
                        while (true) {
                            System.out.print("Enter New Price: ");
                            if (scanner.hasNextDouble()) {
                                newPrice = scanner.nextDouble();
                                itemToEditPrice.setPrice(newPrice);
                                updateItemPriceInFile(itemToEditPrice, filename);
                                System.out.println("Price updated successfully!");
                                break;
                            } else {
                                System.out.println("Invalid price input. Please enter a valid price.");
                                scanner.next();
                            }
                        }
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 9:
                    System.out.println("Enter Item Name to Edit Quantity: ");
                    String itemNameToEditQuantity = scanner.nextLine();
                    InventoryItem itemToEditQuantity = findItemByName(items, itemNameToEditQuantity);
                    if (itemToEditQuantity != null) {
                        int newQuantity;
                        while (true) {
                            System.out.print("Enter New Quantity: ");
                            if (scanner.hasNextInt()) {
                                newQuantity = scanner.nextInt();
                                itemToEditQuantity.setQuantity(newQuantity);
                                updateItemQuantityInFile(itemToEditQuantity, filename);
                                System.out.println("Quantity updated successfully!");
                                break;
                            } else {
                                System.out.println("Invalid quantity input. Please enter a valid number.");
                                scanner.next();
                            }
                        }
                    } else {
                        System.out.println("Item not found.");
                    }
                    break;

                case 10:
                    System.out.println("Goodbye, Manager!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 10);

    }

    private InventoryItem findItemByName(List<InventoryItem> items, String name) {
        for (InventoryItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }


}

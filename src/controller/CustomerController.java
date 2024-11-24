package controller;

import model.persons.Customer;
import model.products.Category;
import model.products.InventoryItem;
import utils.InventoryUpdater;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static model.persons.InventoryManager.updateItemQuantityInFile;

public class CustomerController {

    private final Customer customer;

    public CustomerController() {
        this.customer = new Customer();
    }

    public void displayAllItems(List<InventoryItem> items) {
        customer.displayAllItems(items);
    }

    public void displayItemsByCategory(List<InventoryItem> items, Category category) {
        customer.displayItemsByCategory(items, category);
    }

    public void sortItemsByPrice(List<InventoryItem> items) {
        customer.sortItemsByPrice(items);
    }

    public void customerMenu(List<InventoryItem> items) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome! Choose an option:");
            System.out.println("1. Show All Items");
            System.out.println("2. Show Items by Category");
            System.out.println("3. Sort Items by Price");
            System.out.println("4. View Order");
            System.out.println("5. Add Item to Order");
            System.out.println("6. Exit");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayAllItems(items);
                    break;

                case 2:
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

                case 3:
                    sortItemsByPrice(items);
                    break;

                case 4:
                    customer.viewOrders();
                    break;

                case 5:
                    System.out.println("Enter the name of the item you want to order:");
                    String itemName = scanner.nextLine();


                    InventoryItem selectedItem = findItemByName(items, itemName);

                    if (selectedItem == null) {
                        System.out.println("Item not found. Please try again.");
                        break;
                    }

                    System.out.println("Enter the quantity you want to order:");
                    int quantityToOrder = scanner.nextInt();
                    scanner.nextLine();

                    if (selectedItem.getQuantity() >= quantityToOrder) {

                        selectedItem.setQuantity(selectedItem.getQuantity() - quantityToOrder);

                        updateItemQuantityInFile(selectedItem, "inventory_data.txt");

                        customer.addItemToOrder(selectedItem,quantityToOrder);

                        System.out.println("Order placed successfully for " + quantityToOrder + " of " + itemName + ".");
                    } else {
                        System.out.println("Not enough quantity available. Only " + selectedItem.getQuantity() + " left.");
                    }
                    break;


                case 6:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
    }

    private InventoryItem findItemByName(List<InventoryItem> items, String itemName) {
        for (InventoryItem item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
}

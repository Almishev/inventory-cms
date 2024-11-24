package controller;

import model.persons.Customer;
import model.products.Category;
import model.products.InventoryItem;

import java.util.List;
import java.util.Scanner;

public class CustomerController {

    private Customer customer;

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

    public void customerMenu(List<InventoryItem> items) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome! Choose an option:");
            System.out.println("1. Show All Items");
            System.out.println("2. Show Items by Category");
            System.out.println("3. Sort Items by Price");
            System.out.println("4. Exit");
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
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

}

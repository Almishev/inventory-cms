package inventoryMS.controller;

import inventoryMS.model.persons.Customer;
import inventoryMS.model.persons.InventoryManager;
import inventoryMS.model.products.Category;
import inventoryMS.model.products.InventoryItem;
import inventoryMS.model.products.Order;
import paymentsytem.CreditCardPayment;
import paymentsytem.PayPalPayment;
import paymentsytem.PaymentMethod;
import paymentsytem.PaymentProcessor;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class CustomerController {

    Scanner scanner;
    private final Customer customer;

    public CustomerController() {
        this.customer = new Customer();
        this.scanner = new Scanner(System.in);
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

    public void processPayment(Order order) {
        if (order.getItems().isEmpty()) {
            System.out.println("No items in the order. Please add items before making a payment.");
            return;
        }

        System.out.println("Select payment method: ");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine();

        PaymentMethod paymentMethod;

        if (paymentChoice == 1) {

            System.out.print("Enter card number (16 digits): ");
            String cardNumber = scanner.nextLine();
            System.out.print("Enter cardholder name: ");
            String cardHolderName = scanner.nextLine();
            System.out.print("Enter expiration date (MM/YY): ");
            String expirationDate = scanner.nextLine();
            System.out.print("Enter CVV (3 digits): ");
            String cvv = scanner.nextLine();

            paymentMethod = new CreditCardPayment(cardNumber, cardHolderName, expirationDate, cvv);
        } else if (paymentChoice == 2) {

            System.out.print("Enter PayPal email: ");
            String email = scanner.nextLine();

            paymentMethod = new PayPalPayment(email);
        } else {
            System.out.println("Invalid payment method. Try again.");
            return;
        }

        if (!paymentMethod.validate()) {
            System.out.println("Payment details are invalid. Please try again.");
            return;
        }

        PaymentProcessor processor = new PaymentProcessor();
        boolean paymentSuccess = processor.processPayment(paymentMethod, order.getTotalCost(), order);

        if (paymentSuccess) {
            System.out.println("Payment completed successfully!");
        } else {
            System.out.println("Payment failed. Please try again.");
        }


    }


    public void customerMenu(List<InventoryItem> items) throws IOException {

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

                        InventoryManager.updateItemQuantityInFile(selectedItem, "inventory_data.txt");

                        customer.addItemToOrder(selectedItem, quantityToOrder);

                        System.out.println("Order placed successfully for " + quantityToOrder + " of " + itemName + ".");

                        Order order = new Order();
                        order.addItem(selectedItem, quantityToOrder);
                        processPayment(order);

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

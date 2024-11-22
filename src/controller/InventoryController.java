package controller;

import model.Category;
import model.InventoryItem;
import service.InventoryService;

import java.util.Scanner;

public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Inventory Management System!");
        while (true) {
            System.out.println("1. Add Item");
            System.out.println("2. List Items");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Изчистване на новия ред от буфера

            switch (choice) {
                case 1 -> addItem(scanner);
                case 2 -> service.displayAllItems();
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void addItem(Scanner scanner) {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();

        System.out.print("Enter item description: ");
        String description = scanner.nextLine();

        System.out.print("Enter category (ELECTRONICS, GROCERY, FRAGILE, OTHER): ");
        String categoryInput = scanner.nextLine().toUpperCase();
        Category category;

        try {
            // Извикване на метод от класа Category за създаване на категория
            category = Category.fromString(categoryInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category, setting to OTHER.");
            category = Category.fromString("OTHER"); // Ако има грешка, задаваме категорията на OTHER
        }

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        // Създаване на нов артикул с въведените данни
        InventoryItem item = new InventoryItem(name, description, category, price, false, false, quantity);
        service.addItem(item);
        System.out.println("Item added successfully!");
    }

}

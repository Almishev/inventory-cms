package utils;

import model.products.Category;
import model.products.InventoryItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InventoryLoader {
    public static List<InventoryItem> loadInventoryFromFile(String fileName) {
        List<InventoryItem> inventory = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length == 7) {
                    String name = parts[0];
                    String description = parts[1];
                    Category category = Category.fromString(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    boolean isPerishable = Boolean.parseBoolean(parts[4]);
                    boolean isFragile = Boolean.parseBoolean(parts[5]);
                    int quantity = Integer.parseInt(parts[6]);

                    InventoryItem item = new InventoryItem(name, description, category, price, isPerishable, isFragile, quantity);
                    inventory.add(item);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading inventory file: " + e.getMessage());
        }
        return inventory;
    }
}

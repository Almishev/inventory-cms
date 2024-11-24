package utils;

import model.products.InventoryItem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InventoryUpdater {
    public static void updateInventoryFile(String fileName, List<InventoryItem> inventory) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (InventoryItem item : inventory) {
                writer.write(String.format("%s, %s, %s, %.2f, %b, %b, %d%n",
                        item.getName(),
                        item.getDescription(),
                        item.getCategory().toString(),
                        item.getPrice(),
                        item.isPerishable(),
                        item.isBreakable(),
                        item.getQuantity()));
            }
        }
    }
}

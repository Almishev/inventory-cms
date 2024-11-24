package model.persons;

import model.products.Category;
import model.products.InventoryItem;

import java.util.List;

public class Customer extends ItemViewer {


    @Override
    public void displayAllItems(List<InventoryItem> items) {
        super.displayAllItems(items);
    }


    public void displayItemsByCategory(List<InventoryItem> items, Category category) {
        boolean categoryFound = false;
        for (InventoryItem item : items) {
            if (item.getCategory() != null && item.getCategory().equals(category)) {
                System.out.println(item);
                categoryFound = true;
            }
        }
        if (!categoryFound) {
            System.out.println("No items found in the selected category.");
        }
    }


    @Override
    public void sortItemsByPrice(List<InventoryItem> items) {
        super.sortItemsByPrice(items);
    }
}

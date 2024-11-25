package inventoryMS.model.persons;

import inventoryMS.model.products.Category;
import inventoryMS.model.products.InventoryItem;

import java.util.List;

public abstract class ItemViewer {


    public void displayAllItems(List<InventoryItem> items) {
        if (items.isEmpty()) {
            System.out.println("No items in the inventory.");
        } else {
            items.forEach(InventoryItem::displayItemDetails);
        }
    }


    public void displayItemsByCategory(List<InventoryItem> items, Category category) {
        boolean found = false;
        for (InventoryItem item : items) {
            if (item.getCategory() == category) {
                item.displayItemDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No items found in category: " + category);
        }
    }


    public void sortItemsByPrice(List<InventoryItem> items) {
        items.sort((item1, item2) -> Double.compare(item1.getPrice(), item2.getPrice()));
        displayAllItems(items);
    }
}

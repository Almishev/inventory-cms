package service;

import model.InventoryItem;
import repository.ItemRepository;

import java.util.List;

public class InventoryService {

    private final ItemRepository repository;

    public InventoryService(ItemRepository repository) {
        this.repository = repository;
    }

    public void addItem(InventoryItem item) {
        List<InventoryItem> items = repository.loadItems();
        items.add(item);
        repository.saveItems(items);
    }

    public List<InventoryItem> getAllItems() {
        return repository.loadItems();
    }



    public void displayAllItems() {
        List<InventoryItem> items = getAllItems();
        if (items.isEmpty()) {
            System.out.println("No items in the inventory.");
        } else {
            items.forEach(InventoryItem::displayItemDetails);
        }
    }
}

package repository;

import model.InventoryItem;

import java.util.List;

public interface ItemRepository {


    void saveItems(List<InventoryItem> items);
    List<InventoryItem> loadItems();




}

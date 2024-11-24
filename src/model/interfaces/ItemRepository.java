package model.interfaces;

import model.products.InventoryItem;

import java.util.List;

public interface ItemRepository {


    void saveItems(List<InventoryItem> items);
    List<InventoryItem> loadItems();




}

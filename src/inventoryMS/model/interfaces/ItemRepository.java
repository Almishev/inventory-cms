package inventoryMS.model.interfaces;

import inventoryMS.model.products.InventoryItem;

import java.util.List;

public interface ItemRepository {


    void saveItems(List<InventoryItem> items);
    List<InventoryItem> loadItems();




}

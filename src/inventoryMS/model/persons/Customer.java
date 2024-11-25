package inventoryMS.model.persons;

import inventoryMS.model.products.Category;
import inventoryMS.model.products.InventoryItem;
import inventoryMS.model.products.Order;

import java.util.ArrayList;
import java.util.List;

public class Customer extends ItemViewer {

    private List<Order> orders;
    private Order currentOrder;

    public Customer() {
        this.orders = new ArrayList<>();
        this.currentOrder = new Order();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    public void addItemToOrder(InventoryItem item, int quantity) {
        if (currentOrder == null) {
            currentOrder = new Order();
        }
        currentOrder.addItem(item, quantity);
        System.out.println("Item added to order. Total cost: " + currentOrder.getTotalCost());
    }

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

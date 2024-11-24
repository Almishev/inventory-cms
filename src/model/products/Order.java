package model.products;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int orderCounter = 0;
    private int orderId;
    private Map<InventoryItem, Integer> items;
    private double totalCost;

    public Order() {
        this.orderId = ++orderCounter;
        this.items = new HashMap<>();
        this.totalCost = 0;
    }

    public int getOrderId() {
        return orderId;
    }

    public Map<InventoryItem, Integer> getItems() {
        return items;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void addItem(InventoryItem item, int quantity) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantity);
        } else {
            items.put(item, quantity);
        }
        totalCost += item.getPrice() * quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Items:\n");
        for (Map.Entry<InventoryItem, Integer> entry : items.entrySet()) {
            sb.append(entry.getKey().getName()).append(" - Quantity: ").append(entry.getValue())
                    .append(" - Subtotal: ").append(entry.getKey().getPrice() * entry.getValue()).append("\n");
        }
        sb.append("Total Cost: ").append(totalCost).append("\n");
        return sb.toString();
    }
}

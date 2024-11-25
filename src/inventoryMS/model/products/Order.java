package inventoryMS.model.products;

import inventoryMS.utils.IdGenerator;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private String orderId;
    private Map<InventoryItem, Integer> items;
    private double totalCost;
    private boolean paid;
    public Order() {
        this.orderId = IdGenerator.generateId();
        this.items = new HashMap<>();
        this.totalCost = 0;
        this.paid = false;
    }

    public String getOrderId() {
        return orderId;
    }

    public Map<InventoryItem, Integer> getItems() {
        return items;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
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
        sb.append("Paid: ").append(paid ? "Yes" : "No").append("\n");
        return sb.toString();
    }
}

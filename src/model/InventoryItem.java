package model;

public class InventoryItem extends AbstractItem {
    private boolean isBreakable;
    private boolean isPerishable;
    private int quantity;

    public InventoryItem(String name, String description, Category category, double price, boolean isBreakable, boolean isPerishable,  int quantity) {
        super(name, description, category, price);
        this.isBreakable = isBreakable;
        this.isPerishable = isPerishable;;
        this.quantity = quantity;
    }

    public boolean isBreakable() {
        return isBreakable;
    }

    public boolean isPerishable() {
        return isPerishable;
    }

    public int getQuantity() {
        return quantity;
    }


    public static InventoryItem fromString(String input) {
        String[] parts = input.split(",");
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid data format. Expected 7 fields.");
        }

        String name = parts[0].trim();
        String description = parts[1].trim();

        Category category = Category.fromString(parts[2].trim());

        double price;
        try {
            price = Double.parseDouble(parts[3].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid price value: " + parts[3]);
        }

        boolean isFragile = Boolean.parseBoolean(parts[4].trim());
        boolean isPerishable = Boolean.parseBoolean(parts[5].trim());

        int quantity;
        try {
            quantity = Integer.parseInt(parts[6].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid quantity value: " + parts[6]);
        }

        return new InventoryItem(name, description, category, price, isFragile, isPerishable, quantity);
    }

    @Override
    public String toString() {
        return super.toString() + "," + isBreakable + "," + isPerishable + "," + quantity;
    }

    public void displayItemDetails() {
        System.out.println("Item Name: " + getName());
        System.out.println("Description: " + getDescription());
        System.out.println("Category: " + getCategory());
        System.out.println("Price: " + getPrice());
        System.out.println("Quantity: " + getQuantity());
        System.out.println("Is Fragile: " + isBreakable());
        System.out.println("-------------------------");
    }
}

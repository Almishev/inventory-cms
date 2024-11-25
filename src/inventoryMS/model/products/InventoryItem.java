package inventoryMS.model.products;


public class InventoryItem extends AbstractItem {

    private static long idCounter = 0;
    private final long itemId;
    private int quantity;

    public InventoryItem(String name, String description, Category category, double price, boolean breakable, boolean perishable, int quantity) {
        super(name, description, category, price, breakable, perishable);
        this.itemId = ++idCounter;
        this.quantity = quantity;

    }

    public long getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public double calculateValue() {
        return getPrice() * quantity;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Quantity: " + quantity;
    }


    public static InventoryItem fromString(String line) {

        if (line == null || line.trim().isEmpty()) {
            System.err.println("Invalid item data: Empty or null line.");
            return null;
        }

        String[] parts = line.split(",\\s*");

        if (parts.length != 7) {

            System.err.println("Invalid item data: " + line);
            return null;
        }
        try {
            String name = parts[0];
            String description = parts[1];
            Category category = Category.fromString(parts[2].trim());
            double price = Double.parseDouble(parts[3]);
            boolean isPerishable = Boolean.parseBoolean(parts[4]);
            boolean isBreakable = Boolean.parseBoolean(parts[5]);
            int quantity = Integer.parseInt(parts[6]);

            return new InventoryItem(name, description, category, price, isPerishable, isBreakable, quantity);

        } catch (NumberFormatException e) {

            System.err.println("Invalid numeric value in line: " + line);
            return null;
        } catch (Exception e) {

            System.err.println("Unexpected error while parsing line: " + line);
            e.printStackTrace();
            return null;
        }
    }


    public void displayItemDetails() {
        System.out.println("ID: " + itemId + " Name: " + getName());
        System.out.println("Description: " + getDescription());
        System.out.println("Category: " + getCategory());
        System.out.println("Price: " + getPrice());
        System.out.println("Quantity: " + getQuantity());
    }


}

package model.products;

import java.time.LocalDate;

public class GroceryItem extends InventoryItem {
    private LocalDate expirationDate;

    public GroceryItem(int id, String name, String description, Category category, double price, int quantity, LocalDate expirationDate) {
        super(name, description, category, price, false, true, quantity);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public void handleExpiration() {
        if (isPerishable() && expirationDate.isBefore(LocalDate.now())) {
            System.out.println(getName() + " has expired!");
        }
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Expiration Date: " + expirationDate;
    }
}

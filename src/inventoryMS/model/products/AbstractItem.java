package inventoryMS.model.products;

import inventoryMS.model.interfaces.*;

public abstract class AbstractItem implements Item, Categorizable, Breakable, Perishable, Sellable {
    private String name;
    private String description;
    private Category category;
    private double price;
    private boolean breakable;
    private boolean perishable;

    public AbstractItem(String name, String description, Category category, double price, boolean breakable, boolean perishable) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.breakable = breakable;
        this.perishable = perishable;
    }

    @Override
    public String getDetails() {
        return name + " - " + description + " (" + category + ")";
    }

    @Override
    public double calculateValue() {
        return price; // Може да се override-ва в конкретни класове
    }

    @Override
    public void displayDescription() {
        System.out.println("Item: " + name);
        System.out.println("Description: " + description);
        System.out.println("Category: " + category);
        System.out.println("Price: " + price);
    }

    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public Category getCategory() {
        return category;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBreakable(boolean breakable) {
        this.breakable = breakable;
    }

    public void setPerishable(boolean perishable) {
        this.perishable = perishable;
    }

    @Override
    public boolean isBreakable() {
        return breakable;
    }

    @Override
    public void handleBreakage() {
        if (breakable) {
            System.out.println(name + " is broken!");
        }
    }


    @Override
    public boolean isPerishable() {
        return perishable;
    }

    @Override
    public void handleExpiration() {
        if (perishable) {
            System.out.println(name + " has expired!");
        }
    }


    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + ", " + description + ", " + category + ", " + price;
    }
}

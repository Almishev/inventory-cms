package model;

public abstract class AbstractItem implements Item {
    private String name;
    private String description;
    private Category category;
    private double price;

    public AbstractItem(String name, String description, Category category, double price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Category getCategory() {
        return category;
    }


    @Override
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + "," + description + "," + category + "," + price;
    }
}

package model;

public interface Item {

    String getName();
    String getDescription();
    Category getCategory();
    double getPrice();

    void setCategory(Category category);

    String toString();
}

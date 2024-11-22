package model;

public class Category {
    private String name;


    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public static Category fromString(String categoryString) {

        switch (categoryString.toUpperCase()) {
            case "ELECTRONICS":
                return new Category("ELECTRONICS");
            case "GROCERY":
                return new Category("GROCERY");
            case "FRAGILE":
                return new Category("FRAGILE");
            case "OTHER":
                return new Category("OTHER");
            default:
                throw new IllegalArgumentException("Invalid category: " + categoryString);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

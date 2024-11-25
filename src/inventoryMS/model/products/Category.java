package inventoryMS.model.products;

import java.util.HashMap;
import java.util.Map;

public class Category {
    private static final Map<String, Category> REGISTRY = new HashMap<>();


    static {
        new Category("GROCERY");
        new Category("ELECTRONICS");
        new Category("FRAGILE");
    }
    private final String name;

    public Category(String name) {
        this.name = name.toUpperCase();

        REGISTRY.put(this.name, this);
    }



    public static Category fromString(String name) {
        String upperName = name.toUpperCase().trim();
        Category category = REGISTRY.get(upperName);
        if (category == null) {
            System.out.println("Invalid category: " + name);
        }
        return category;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Map<String, Category> getAllCategories() {
        return new HashMap<>(REGISTRY);
    }
}

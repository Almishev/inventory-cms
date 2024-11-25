package inventoryMS.model.products;

public class FragileItem extends InventoryItem {
    private double weight;

    public FragileItem(int id, String name, String description, Category category, double price, int quantity, double weight) {
        super(name, description, category, price, true, false, quantity);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void handleBreakage() {
        if (isBreakable()) {
            System.out.println(getName() + " has been broken!");
        }
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Weight: " + weight + " kg";
    }
}

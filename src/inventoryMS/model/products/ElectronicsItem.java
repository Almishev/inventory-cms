package inventoryMS.model.products;

public class ElectronicsItem extends InventoryItem {
    private int warrantyInMonths;

    public ElectronicsItem(int id, String name, String description, Category category, double price, int quantity, int warrantyInMonths) {
        super(name, description, category, price, false, false, quantity);
        this.warrantyInMonths = warrantyInMonths;
    }

    public int getWarrantyInMonths() {
        return warrantyInMonths;
    }

    public void setWarrantyInMonths(int warrantyInMonths) {
        this.warrantyInMonths = warrantyInMonths;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + " | Warranty: " + warrantyInMonths + " months";
    }
}

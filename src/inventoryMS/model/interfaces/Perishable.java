package inventoryMS.model.interfaces;

public interface Perishable {

    boolean isPerishable();
    void handleExpiration();
}

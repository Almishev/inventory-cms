package model.interfaces;

public interface Perishable {

    boolean isPerishable();
    void handleExpiration();
}

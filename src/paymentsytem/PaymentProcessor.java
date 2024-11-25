package paymentsytem;

import inventoryMS.model.products.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PaymentProcessor {

    public boolean processPayment(PaymentMethod paymentMethod, double amount, Order order) {
        if (paymentMethod.authorizePayment(amount)) {
            order.setPaid(true);
            System.out.println("Payment successful for Order ID: " + order.getOrderId());
            savePaymentToFile(order.getOrderId(), amount);
            return true;
        } else {
            System.out.println("Payment failed for Order ID: " + order.getOrderId());
            return false;
        }
    }



    private void savePaymentToFile(String orderId, double amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("account.txt", true))) {
            writer.write("Order ID: " + orderId + ", Amount: " + amount);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving payment to file: " + e.getMessage());
        }
    }
}

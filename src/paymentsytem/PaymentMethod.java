package paymentsytem;

public interface PaymentMethod {

    boolean validate();
    boolean authorizePayment(double amount);
    String getPaymentType();
}

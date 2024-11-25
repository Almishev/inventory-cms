package paymentsytem;

public class PayPalPayment implements PaymentMethod {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public boolean validate() {
        return email.contains("@");
    }

    @Override
    public boolean authorizePayment(double amount) {
        return Math.random() > 0.2;
    }


    @Override
    public String getPaymentType() {
        return "PayPal";
    }
}
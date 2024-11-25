package paymentsytem;

public class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardHolderName, String expirationDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    @Override
    public boolean validate() {
        return cardNumber.matches("\\d{16}") && cvv.matches("\\d{3}") && expirationDate.matches("\\d{2}/\\d{2}");
    }

    @Override
    public boolean authorizePayment(double amount) {
        return Math.random() > 0.1; // Симулиране на проверка за наличност на средства
                                    // 90% шанс за успешно плащане
    }



    @Override
    public String getPaymentType() {
        return "Credit Card";
    }
}

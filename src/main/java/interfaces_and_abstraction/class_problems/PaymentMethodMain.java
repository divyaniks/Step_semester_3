package interfaces_and_abstraction.class_problems;

abstract class PaymentMethod {

    private String accountId;

    public PaymentMethod(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public abstract void pay(double amount);
}

class CreditCardPayment extends PaymentMethod {

    public CreditCardPayment(String accountId) {
        super(accountId);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Credit Card Payment: " + amount);
    }
}

class CashPayment extends PaymentMethod {

    public CashPayment(String accountId) {
        super(accountId);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Cash Payment: " + amount);
    }
}

public class PaymentMethodMain {

    public static void main(String[] args) {

        PaymentMethod payment1 =
                new CreditCardPayment("CC101");

        PaymentMethod payment2 =
                new CashPayment("CA102");

        payment1.pay(1500.0);
        payment2.pay(500.0);

        System.out.println(payment1.getAccountId());
        System.out.println(payment2.getAccountId());
    }
}
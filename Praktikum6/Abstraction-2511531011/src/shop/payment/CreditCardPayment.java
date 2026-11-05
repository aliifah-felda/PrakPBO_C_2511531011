package shop.payment;

public class CreditCardPayment implements RefundablePayment, InstallmentPayment{
    private final String cardHolder;
    private final long creditLimit;
    private long usedCredit;

    public CreditCardPayment(String cardHolder, long creditLimit) {
        this.cardHolder = cardHolder;
        this.creditLimit = creditLimit;
    }

    public String getCardHolder() {return cardHolder;}
    public long getUsedCredit() {return usedCredit;}

    @Override
    public String getName() {
        return "Kartu Kredit (" + getCardHolder() + ")";
    }

    @Override
    public boolean pay(long amount) {
        if (amount <= 0 || usedCredit + amount > creditLimit) {
            return false;
        }

        usedCredit += amount;
        return true;
    }

    @Override
    public boolean refund(long amount) {
        if (amount <= 0 || amount > usedCredit) {
            return false;
        }

        usedCredit -= amount;
        return true;
    }

    @Override
    public int maxInstallmentMonths() {
        return 12;
    }

}

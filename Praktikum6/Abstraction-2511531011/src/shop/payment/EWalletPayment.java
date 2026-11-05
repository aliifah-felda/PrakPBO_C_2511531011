package shop.payment;

public class EWalletPayment implements RefundablePayment {
    private final String provider;
    long balance;

    public EWalletPayment(String provider, long balance) {
        if (provider == null) {
            throw new IllegalArgumentException("Provider must not be blank.");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Balace must not be negative.");
        }
        this.provider = provider;
        this.balance = balance;
    }

    public String getProvider() {return provider;}
    public long getBalance() {return balance;}

    @Override
    public String getName() {
        return "E-Wallet " + provider;
    }

    @Override
    public boolean pay(long amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
        
        
    }

    @Override
    public boolean refund(long amount) {
        if (amount <= 0) return false;
        balance += amount;
        return true;
    }

}

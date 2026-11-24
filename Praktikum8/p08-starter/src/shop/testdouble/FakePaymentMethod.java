package shop.testdouble;

import shop.exception.PaymentFailedException;
import shop.payment.PaymentMethod;

public class FakePaymentMethod implements PaymentMethod {
    private long lastAmount;
    private int callCount;

    public FakePaymentMethod() {
        this.lastAmount = lastAmount;
        this.callCount = callCount;
        
    }

    @Override
    public String name() {
        return "Fake";
    }

    @Override
    public void pay(long amount) throws PaymentFailedException {
        lastAmount = amount;
        callCount++;
    }

    public int getCallCount(){return callCount;}
    
    public long getLastAmount(){return lastAmount;}

}
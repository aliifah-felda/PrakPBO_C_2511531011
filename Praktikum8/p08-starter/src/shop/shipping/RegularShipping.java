
package shop.shipping;

public class RegularShipping implements ShippingRate {
    private final long ratePerKg;

    public RegularShipping(long ratePerKg) {
        this.ratePerKg = ratePerKg;
    }

    @Override
    public long costFor(int grams) {
        long kg = (grams + 999L) / 1000L;
        return kg * ratePerKg;
    }
}

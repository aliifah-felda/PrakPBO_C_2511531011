
package shop.shipping;

public class SameDayRate implements ShippingRate {

    @Override
    public long costFor(int grams) {
        if (grams < 0) {
            throw new IllegalArgumentException("Berat tidak boleh negatif");
        }

        if (grams <= 3000) {
            return 25000;
        }

        long extraKg = (grams - 3000L + 999L) / 1000L;
        return 25000 + (extraKg * 10000);
    }
}

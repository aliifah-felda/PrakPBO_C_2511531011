
package shop.service;

import shop.discount.DiscountStrategy;
import shop.model.Order;
import shop.model.Shippable;
import shop.shipping.ShippingRate;
import shop.shipping.RegularShipping;

public class OrderCalculator {
    private final ShippingRate shippingRate;

    // Constructor lama tetap dipertahankan
    public OrderCalculator(long ratePerKg) {
        this(new RegularShipping(ratePerKg));
    }

    // Constructor baru menerima metode pengiriman
    public OrderCalculator(ShippingRate shippingRate) {
        this.shippingRate = shippingRate;
    }

    public Bill calculate(Order order, DiscountStrategy discountStrategy) {
        long subtotal = 0;
        int grams = 0;

        for (Order.Line line : order.lines()) {
            subtotal += line.total();

            if (line.product() instanceof Shippable item) {
                grams += item.getWeightGram() * line.qty();
            }
        }

        long discount = discountStrategy.discountFor(subtotal);

        long shipping = grams == 0 ? 0 : shippingRate.costFor(grams);

        // [R5]
        long total = subtotal - discount + shipping;

        return new Bill(subtotal, discount, shipping, total);
    }
}

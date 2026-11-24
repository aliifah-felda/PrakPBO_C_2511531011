
package shop.tantangan;

import shop.discount.NoDiscount;
import shop.model.Address;
import shop.model.Order;
import shop.model.PhysicalProduct;
import shop.service.Bill;
import shop.service.OrderCalculator;
import shop.shipping.SameDayRate;

public class TantanganA {

    public static void main(String[] args) {
        PhysicalProduct kopi = new PhysicalProduct(
                "KOPI", "Kopi Gayo 250g", 85000, 1000);

        Order order = new Order(
                "ORD-SD-01", new Address("Sari", "Medan"));

        order.add(kopi, 4);

        OrderCalculator calculator = new OrderCalculator(new SameDayRate());

        Bill bill = calculator.calculate(order, new NoDiscount());

        System.out.println("=== DEMO SAME DAY ===");
        System.out.println("Subtotal : Rp" + bill.subtotal());
        System.out.println("Diskon   : Rp" + bill.discount());
        System.out.println("Ongkir   : Rp" + bill.shipping());
        System.out.println("Total    : Rp" + bill.total());
    }
}

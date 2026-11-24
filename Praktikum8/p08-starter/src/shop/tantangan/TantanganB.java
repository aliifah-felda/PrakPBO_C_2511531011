
package shop.tantangan;

import java.util.List;

import shop.model.Address;
import shop.model.Order;
import shop.notify.SmsChannel;
import shop.notify.WhatsAppChannel;
import shop.service.Notifier;

public class TantanganB {

    public static void main(String[] args) {

        // Membuat Notifier dengan dua saluran
        Notifier notifier = new Notifier(
            List.of(
                new SmsChannel(),
                new WhatsAppChannel()
            )
        );

        // Membuat pesanan
        Order order = new Order(
            "ORD-B01",
            new Address("Sari", "Medan")
        );

        // Mengirim notifikasi
        notifier.notifyPaid(order, 150000);
    }
}

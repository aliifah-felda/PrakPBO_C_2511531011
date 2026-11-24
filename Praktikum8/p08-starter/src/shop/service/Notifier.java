
package shop.service;

import java.util.List;

import shop.model.Order;
import shop.util.Rupiah;
import shop.notify.NotificationChannel;
import shop.notify.SmsChannel;

public class Notifier {

    private final List<NotificationChannel> channels;

    // Constructor lama tetap menggunakan SMS
    public Notifier() {
        this(List.of(new SmsChannel()));
    }

    // Constructor baru menerima beberapa saluran
    public Notifier(List<NotificationChannel> channels) {
        this.channels = List.copyOf(channels);
    }

    public void notifyPaid(Order order, long amount) {
        String recipient = order.getAddress().getRecipient();

        String message = "pesanan " + order.getOrderId()
                + " sudah dibayar (" + Rupiah.format(amount) + ")";

        for (NotificationChannel channel : channels) {
            channel.send(recipient, message);
        }
    }
}

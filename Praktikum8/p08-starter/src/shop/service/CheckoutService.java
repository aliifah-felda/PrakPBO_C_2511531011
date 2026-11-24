package shop.service;

import shop.discount.DiscountStrategy;
import shop.exception.PaymentFailedException;
import shop.model.Order;
import shop.payment.CreditCardPayment;
import shop.payment.EWalletPayment;
import shop.payment.PaymentMethod;
import shop.util.Rupiah;

/**
 * Memproses satu pesanan dari awal sampai selesai.
 * Program ini BERJALAN BENAR, tetapi sulit diubah. Tugas Anda: membenahinya.
 */
public class CheckoutService {
    private OrderCalculator calculator;
    private InvoicePrinter printer;
    private Notifier notifier;

    public CheckoutService(OrderCalculator calculator, InvoicePrinter printer, Notifier notifier) {
        this.calculator = calculator;
        this.printer = printer;
        this.notifier = notifier;
    }

    public long checkout(Order order, DiscountStrategy discount, PaymentMethod payment) throws PaymentFailedException {
        Bill bill = calculator.calculate(order, discount);
        payment.pay(bill.total());
        printer.print(order, bill, payment.name());
        notifier.notifyPaid(order, bill.total());
        return bill.total();
    }
}

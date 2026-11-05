package shop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final String orderId;
    private final Address shippingAddress;
    private final List<Line> lines = new ArrayList<>();

    public Order(String orderId, Address shippingAddress) {
        if (orderId == null || orderId.isBlank() || shippingAddress == null) {
            throw new IllegalArgumentException("Order id and address are required");
        }
        this.orderId = orderId;
        this.shippingAddress = shippingAddress;
    }

    public String getOrderId() {return orderId;}
    public Address getShippingAddress() {return shippingAddress;}

    public void addLine(Product product, int quantity) {
        lines.add(new Line(product, quantity));
    }

    public List<Line> getLines() {return Collections.unmodifiableList(lines);}

    public static class Line {
        private final Product product;
        private final int quantity;

        public Line(Product product, int quantity) {
            if (product == null) {
                throw new IllegalArgumentException("Product must not be blank.");
            }
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be over 0");
            }
            this.product = product;
            this.quantity = quantity;
        }

        public long subtotal() {
            return product.getFinalPrice() * quantity;
        }
    }

    public long getItemsTotal() {
        long total = 0;
        for (Line line : lines) {
            total += line.subtotal();
        }
        return total;
    }

    public class Receipt {
        public String toText() {
            String hasil = "\nSTRUK PESANAN " + orderId + "\n";

            for (Line line : lines) {
                hasil += "- " + line.product.getName() + " x" + line.quantity + " = Rp" + line.subtotal() + "\n";
            }

            hasil += "Total barang: Rp" + getItemsTotal() + "\n";
            hasil += "Kirim ke: " + shippingAddress.getCity();

            return hasil;
        }
    }

    public Receipt createReceipt() {
        return new Receipt();
    }

}

package shop.discount;

// aturan diskon yang bisa ditukar
public interface DiscountStrategy {
    // Besar potongan (rupiah) untuk subtotal tertentu
    long discountFor(long subtotal);
}

package net.socketconnection.jva.models.shop.item;

public final class BundleItem extends Item {

    private final int amount;
    private final int discountPercent;
    private final int basePrice;
    private final int discountedPrice;
    private final boolean promoItem;

    public BundleItem(String skinId, String name, String image, Type type, int amount, int discountPercent, int basePrice,
                      int discountedPrice, boolean promoItem) {
        super(skinId, name, image, type);

        this.amount = amount;
        this.discountPercent = discountPercent;
        this.basePrice = basePrice;
        this.discountedPrice = discountedPrice;
        this.promoItem = promoItem;
    }

    public boolean isPromoItem() {
        return promoItem;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getAmount() {
        return amount;
    }
}

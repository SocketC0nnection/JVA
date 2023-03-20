package net.socketconnection.jva.models.shop;

import net.socketconnection.jva.models.shop.item.Item;

public final class Bundle {

    private final String bundleId;
    private final int bundlePrice;
    private final boolean wholeSaleOnly;
    private final Item[] items;
    private final long secondsRemaining;
    private final String expiresAt;

    public Bundle(String bundleId, int bundlePrice, boolean wholeSaleOnly, Item[] items, long secondsRemaining, String expiresAt) {
        this.bundleId = bundleId;
        this.bundlePrice = bundlePrice;
        this.wholeSaleOnly = wholeSaleOnly;
        this.items = items;
        this.secondsRemaining = secondsRemaining;
        this.expiresAt = expiresAt;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public long getSecondsRemaining() {
        return secondsRemaining;
    }

    public Item[] getItems() {
        return items;
    }

    public boolean isWholeSaleOnly() {
        return wholeSaleOnly;
    }

    public int getBundlePrice() {
        return bundlePrice;
    }

    public String getBundleId() {
        return bundleId;
    }
}

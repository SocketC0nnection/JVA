package net.socketconnection.jva.models.shop.item;

import net.socketconnection.jva.models.shop.ContentTier;

public final class OfferItem extends Item {

    private final String offerId;
    private final int cost;
    private final ContentTier contentTier;

    public OfferItem(String skinId, String name, String image, Type type, String offerId, int cost, ContentTier contentTier) {
        super(skinId, name, image, type);

        this.offerId = offerId;
        this.cost = cost;
        this.contentTier = contentTier;
    }

    public ContentTier getContentTier() {
        return contentTier;
    }

    public int getCost() {
        return cost;
    }

    public String getOfferId() {
        return offerId;
    }
}

package net.socketconnection.jva.models.player;

public final class PlayerCard {

    private final String small;
    private final String large;
    private final String wide;
    private final String id;

    public PlayerCard(String small, String large, String wide, String id) {
        this.small = small;
        this.large = large;
        this.wide = wide;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getWide() {
        return wide;
    }

    public String getLarge() {
        return large;
    }

    public String getSmall() {
        return small;
    }
}

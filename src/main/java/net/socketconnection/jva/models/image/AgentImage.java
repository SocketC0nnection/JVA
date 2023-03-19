package net.socketconnection.jva.models.image;

public final class AgentImage {

    private final String small;
    private final String bust;
    private final String full;
    private final String killFeed;

    public AgentImage(String small, String bust, String full, String killFeed) {
        this.small = small;
        this.bust = bust;
        this.full = full;
        this.killFeed = killFeed;
    }

    public String getKillFeed() {
        return killFeed;
    }

    public String getFull() {
        return full;
    }

    public String getBust() {
        return bust;
    }

    public String getSmall() {
        return small;
    }
}

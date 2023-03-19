package net.socketconnection.jva.models.image;

public final class RankImage {

    private final String small;
    private final String large;
    private final String triangleDown;
    private final String triangleUp;

    public RankImage(String small, String large, String triangleDown, String triangleUp) {
        this.small = small;
        this.large = large;
        this.triangleDown = triangleDown;
        this.triangleUp = triangleUp;
    }

    public String getTriangleUp() {
        return triangleUp;
    }

    public String getTriangleDown() {
        return triangleDown;
    }

    public String getLarge() {
        return large;
    }

    public String getSmall() {
        return small;
    }

}

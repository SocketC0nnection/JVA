package net.socketconnection.jva.models;

public final class Location {

    private final int x;
    private final int y;
    private final int viewRadians;

    public Location(int x, int y, int viewRadians) {
        this.x = x;
        this.y = y;
        this.viewRadians = viewRadians;
    }

    public int getViewRadians() {
        return viewRadians;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}

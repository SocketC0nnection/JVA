package net.socketconnection.jva.models;

public final class Location {

    private final int x;
    private final int y;
    private final double viewRadians;

    public Location(int x, int y, double viewRadians) {
        this.x = x;
        this.y = y;
        this.viewRadians = viewRadians;
    }

    public double getViewRadians() {
        return viewRadians;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}

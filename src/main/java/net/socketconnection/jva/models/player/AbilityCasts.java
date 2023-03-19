package net.socketconnection.jva.models.player;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

public final class AbilityCasts {

    private final int c;
    private final int q;
    private final int e;
    private final int x;

    public AbilityCasts(int c, int q, int e, int x) {
        this.c = c;
        this.q = q;
        this.e = e;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getE() {
        return e;
    }

    public int getQ() {
        return q;
    }

    public int getC() {
        return c;
    }
}

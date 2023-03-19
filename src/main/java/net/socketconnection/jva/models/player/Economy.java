package net.socketconnection.jva.models.player;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

public final class Economy {

    private final int overallSpent;
    private final int averageSpent;

    private final int overallLoadout;
    private final int averageLoadout;

    public Economy(int overallSpent, int averageSpent, int overallLoadout, int averageLoadout) {
        this.overallSpent = overallSpent;
        this.averageSpent = averageSpent;

        this.overallLoadout = overallLoadout;
        this.averageLoadout = averageLoadout;
    }

    public int getAverageLoadout() {
        return averageLoadout;
    }

    public int getOverallLoadout() {
        return overallLoadout;
    }

    public int getAverageSpent() {
        return averageSpent;
    }

    public int getOverallSpent() {
        return overallSpent;
    }
}

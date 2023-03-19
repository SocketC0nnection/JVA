package net.socketconnection.jva.models.player;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

public final class Stats {

    private final int score;
    private final int kills;
    private final int deaths;
    private final int assists;
    private final int bodyshots;
    private final int headshots;
    private final int legshots;

    public Stats(int score, int kills, int deaths, int assists, int bodyshots, int headshots, int legshots) {
        this.score = score;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.bodyshots = bodyshots;
        this.headshots = headshots;
        this.legshots = legshots;
    }

    public int getLegshots() {
        return legshots;
    }

    public int getHeadshots() {
        return headshots;
    }

    public int getBodyshots() {
        return bodyshots;
    }

    public int getAssists() {
        return assists;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getKills() {
        return kills;
    }

    public int getScore() {
        return score;
    }
}

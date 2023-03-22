package net.socketconnection.jva.match;

import net.socketconnection.jva.player.MatchPlayer;

import java.util.List;

public class MatchTeam {

    private final List<MatchPlayer> players;
    private final boolean won;
    private final int roundsWon;
    private final int roundsLost;

    public MatchTeam(List<MatchPlayer> players, boolean won, int roundsWon, int roundsLost) {
        this.players = players;
        this.won = won;
        this.roundsWon = roundsWon;
        this.roundsLost = roundsLost;
    }

    public int getRoundsLost() {
        return roundsLost;
    }

    public int getRoundsWon() {
        return roundsWon;
    }

    public boolean hasWon() {
        return won;
    }

    public List<MatchPlayer> getPlayers() {
        return players;
    }
}

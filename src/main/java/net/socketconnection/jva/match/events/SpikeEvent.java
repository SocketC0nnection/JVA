package net.socketconnection.jva.match.events;

import com.google.gson.JsonObject;
import net.socketconnection.jva.match.MatchRound;
import net.socketconnection.jva.models.Location;
import net.socketconnection.jva.player.RoundPlayer;

import java.util.Map;

public abstract class SpikeEvent implements Event {

    final MatchRound matchRound;
    Location location;
    RoundPlayer interactor;
    long timeInRound;
    Map<RoundPlayer, Location> playerLocations;

    public SpikeEvent(MatchRound matchRound) {
        this.matchRound = matchRound;
    }

    @Override
    public Event fetchData(JsonObject object) {
        return this;
    }

    public Map<RoundPlayer, Location> getPlayerLocations() {
        return playerLocations;
    }

    public long getTimeInRound() {
        return timeInRound;
    }

    public RoundPlayer getInteractor() {
        return interactor;
    }

    public Location getLocation() {
        return location;
    }

    public MatchRound getMatchRound() {
        return matchRound;
    }
}

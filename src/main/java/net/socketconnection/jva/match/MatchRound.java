package net.socketconnection.jva.match;

import com.google.gson.JsonObject;
import net.socketconnection.jva.match.events.DefuseEvent;
import net.socketconnection.jva.match.events.PlantEvent;
import net.socketconnection.jva.player.MatchPlayer;
import net.socketconnection.jva.player.RoundPlayer;
import net.socketconnection.jva.utils.GsonUtils;

import java.util.List;

public class MatchRound {

    final Match match;
    String winningTeam;
    String endType;
    boolean bombPlanted;
    boolean bombDefused;
    List<RoundPlayer> players;
    PlantEvent plantEvent;
    DefuseEvent defuseEvent;

    public MatchRound(Match match) {
        this.match = match;
    }

    public MatchRound fetchData(JsonObject object) {
        winningTeam = GsonUtils.getAsString(object.get("winning_team"));
        endType = GsonUtils.getAsString(object.get("end_type"));
        bombPlanted = GsonUtils.getAsBoolean(object.get("bomb_planted"));
        bombDefused = GsonUtils.getAsBoolean(object.get("bomb_defused"));

        if(bombPlanted) {
            plantEvent = (PlantEvent) new PlantEvent(this).fetchData(object.getAsJsonObject("plant_events"));
        }

        if(bombDefused) {
            defuseEvent = (DefuseEvent) new DefuseEvent(this).fetchData(object.getAsJsonObject("defuse_events"));
        }

        return this;
    }

    public RoundPlayer getPlayer(String playerId) {
        for(RoundPlayer player : players) {
            if(!player.getPlayerId().equalsIgnoreCase(playerId)) {
                continue;
            }

            return player;
        }

        return null;
    }

    public RoundPlayer getPlayer(MatchPlayer matchPlayer) {
        return getPlayer(matchPlayer.getPlayerId());
    }

    public DefuseEvent getDefuseEvent() {
        return defuseEvent;
    }

    public PlantEvent getPlantEvent() {
        return plantEvent;
    }

    public List<RoundPlayer> getPlayers() {
        return players;
    }

    public boolean isBombDefused() {
        return bombDefused;
    }

    public boolean isBombPlanted() {
        return bombPlanted;
    }

    public String getEndType() {
        return endType;
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    public Match getMatch() {
        return match;
    }
}

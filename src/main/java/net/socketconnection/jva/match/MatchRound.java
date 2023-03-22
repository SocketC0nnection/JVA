package net.socketconnection.jva.match;

import com.google.gson.JsonObject;
import net.socketconnection.jva.match.events.PlantEvent;
import net.socketconnection.jva.utils.GsonUtils;

public class MatchRound {

    String winningTeam;
    String endType;
    boolean bombPlanted;
    boolean bombDefused;
    PlantEvent plantEvent;

    public MatchRound fetchData(JsonObject object) {
        winningTeam = GsonUtils.getAsString(object.get("winning_team"));
        endType = GsonUtils.getAsString(object.get("end_type"));
        bombPlanted = GsonUtils.getAsBoolean(object.get("bomb_planted"));
        bombDefused = GsonUtils.getAsBoolean(object.get("bomb_defused"));
        plantEvent = (PlantEvent) new PlantEvent().fetchData(object.getAsJsonObject("plant_events"));

        return this;
    }

    public PlantEvent getPlantEvent() {
        return plantEvent;
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
}

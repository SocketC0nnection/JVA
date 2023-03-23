package net.socketconnection.jva.match.events;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.socketconnection.jva.match.MatchRound;
import net.socketconnection.jva.models.Location;
import net.socketconnection.jva.utils.GsonUtils;

import java.util.LinkedHashMap;

public class DefuseEvent extends SpikeEvent {

    public DefuseEvent(MatchRound matchRound) {
        super(matchRound);
    }

    @Override
    public Event fetchData(JsonObject object) {
        if(!object.get("defuse_location").isJsonNull()) {
            JsonObject plantLocationData = object.getAsJsonObject("defuse_location");

            location = new Location(GsonUtils.getAsInt(plantLocationData.get("x")), GsonUtils.getAsInt(plantLocationData.get("y")), 0);
        }

        if(!object.get("defused_by").isJsonNull()) {
            interactor = matchRound.getPlayer(GsonUtils.getAsString(object.getAsJsonObject("defused_by").get("puuid")));
        }

        timeInRound = GsonUtils.getAsLong(object.get("defuse_time_in_round"));

        if(!object.get("player_locations_on_defuse").isJsonNull()) {
            JsonArray playerLocationData = object.getAsJsonArray("player_locations_on_defuse");

            playerLocations = new LinkedHashMap<>();

            for(JsonElement playerLocationElement : playerLocationData) {
                JsonObject playerLocationObject = playerLocationElement.getAsJsonObject();

                playerLocations.put(matchRound.getPlayer(GsonUtils.getAsString(playerLocationObject.get("player_puuid"))),
                        new Location(GsonUtils.getAsInt(playerLocationObject.getAsJsonObject("location").get("x")),
                                GsonUtils.getAsInt(playerLocationObject.getAsJsonObject("location").get("y")),
                                GsonUtils.getAsDouble(playerLocationObject.get("view_radians"))));
            }
        }

        return this;
    }
}

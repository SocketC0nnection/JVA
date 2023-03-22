package net.socketconnection.jva.match.events;

import com.google.gson.JsonObject;
import net.socketconnection.jva.models.Location;
import net.socketconnection.jva.utils.GsonUtils;

public class PlantEvent implements Event {

    Location location;

    @Override
    public Event fetchData(JsonObject object) {
        if(!object.get("plant_location").isJsonNull()) {
            JsonObject plantLocationData = object.getAsJsonObject("plant_location");

            location = new Location(GsonUtils.getAsInt(plantLocationData.get("x")), GsonUtils.getAsInt(plantLocationData.get("y")), 0);
        }

        return this;
    }

    public Location getLocation() {
        return location;
    }
}

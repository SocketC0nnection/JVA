package net.socketconnection.jva.match.events;

import com.google.gson.JsonObject;

public interface Event {

    Event fetchData(JsonObject object);

}

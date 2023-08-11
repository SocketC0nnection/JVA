package net.socketconnection.jva.player;

import net.socketconnection.jva.api.ValorantAPI;
import net.socketconnection.jva.utils.RiotUtils;

public abstract class Player {

    ValorantAPI valorantAPI;

    String username;
    String tag;
    String playerId;

    boolean fetched;

    public Player(ValorantAPI valorantAPI) {
        this.valorantAPI = valorantAPI;
    }

    public boolean isFetched() {
        return fetched;
    }

    public String getRiotId() {
        return RiotUtils.toRiotId(username, tag);
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getTag() {
        return tag;
    }

    public String getUsername() {
        return username;
    }

    public ValorantAPI getValorantAPI() {
        return valorantAPI;
    }
}

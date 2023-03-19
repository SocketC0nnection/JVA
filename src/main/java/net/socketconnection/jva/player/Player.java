package net.socketconnection.jva.player;

import net.socketconnection.jva.ValorantAPI;
import net.socketconnection.jva.enums.Rank;
import net.socketconnection.jva.utils.RiotUtils;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

public abstract class Player {

    ValorantAPI valorantAPI;

    String username;
    String tag;
    String puuid;
    Rank rank;

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

    public Rank getRank() {
        return rank;
    }

    public String getPuuid() {
        return puuid;
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

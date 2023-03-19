package net.socketconnection.jva.enums;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

public enum GameMode {

    UNRATED("unrated", "Unrated"),
    COMPETITIVE("competitive", "Competitive"),
    DEATHMATCH("deathmatch", "Deathmatch"),
    SWIFTPLAY("swiftplay", "Swiftplay"),
    SPIKE_RUSH("spikerush", "Spike Rush"),
    REPLICATION("replication", "Replication"),
    ESCALATION("escalation", "Escalation");

    private final String gameModeQuery;
    private final String gameModeName;

    GameMode(String gameModeQuery, String gameModeName) {
        this.gameModeQuery = gameModeQuery;
        this.gameModeName = gameModeName;
    }

    public static GameMode getFromGameModeQuery(String gameModeQuery) {
        for(GameMode gameMode : values()) {
            if(!gameMode.gameModeQuery.equalsIgnoreCase(gameModeQuery)) {
                continue;
            }

            return gameMode;
        }

        return null;
    }

    public static GameMode getFromGameModeName(String gameModeName) {
        for(GameMode gameMode : values()) {
            if(!gameMode.gameModeName.equalsIgnoreCase(gameModeName)) {
                continue;
            }

            return gameMode;
        }

        return null;
    }

    public String getGameModeName() {
        return gameModeName;
    }

    public String getGameModeQuery() {
        return gameModeQuery;
    }
}

package net.socketconnection.jva.enums;

public enum GameMode {

    UNRATED("unrated", "Unrated"),
    COMPETITIVE("competitive", "Competitive"),
    DEATHMATCH("deathmatch", "Deathmatch"),
    TEAM_DEATHMATCH("teamdeathmatch", "Team Deathmatch"),
    SWIFTPLAY("swiftplay", "Swiftplay"),
    SPIKE_RUSH("spikerush", "Spike Rush"),
    REPLICATION("replication", "Replication"),
    ESCALATION("escalation", "Escalation");

    private final String query;
    private final String name;

    GameMode(String query, String name) {
        this.query = query;
        this.name = name;
    }

    public static GameMode getFromQuery(String query) {
        for(GameMode gameMode : values()) {
            if(!gameMode.query.equalsIgnoreCase(query)) {
                continue;
            }

            return gameMode;
        }

        return null;
    }

    public static GameMode getFromName(String name) {
        for(GameMode gameMode : values()) {
            if(!gameMode.name.equalsIgnoreCase(name)) {
                continue;
            }

            return gameMode;
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }
}

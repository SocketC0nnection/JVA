package net.socketconnection.jva.enums;

public enum Region {

    EUROPE("eu", "Europe"),
    NORTH_AMERICA("na", "North America"),
    ASIA_PACIFIC("ap", "Asia Pacific"),
    KOREA("kr", "Korea");

    private final String query;
    private final String name;

    Region(String query, String name) {
        this.query = query;
        this.name = name;
    }

    public static Region getFromQuery(String query) {
        for(Region region : values()) {
            if(!region.query.equalsIgnoreCase(query)) {
                continue;
            }

            return region;
        }

        return null;
    }

    public static Region getFromName(String name) {
        for(Region region : values()) {
            if(!region.name.equalsIgnoreCase(name)) {
                continue;
            }

            return region;
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

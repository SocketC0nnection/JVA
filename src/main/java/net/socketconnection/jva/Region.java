package net.socketconnection.jva;

public enum Region {

    EUROPE("eu"),
    NORTH_AMERICA("na"),
    ASIA_PACIFIC("ap"),
    KOREA("kr");

    private final String query;

    Region(String query) {
        this.query = query;
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

    public String getQuery() {
        return query;
    }

}

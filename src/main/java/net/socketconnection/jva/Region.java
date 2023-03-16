package net.socketconnection.jva;

public enum Region {

    EUROPE("eu", "Europe"),
    NORTH_AMERICA("na", "North America"),
    ASIA_PACIFIC("ap", "Asia Pacific"),
    KOREA("kr", "Korea");

    private final String query;
    private final String regionName;

    Region(String query, String regionName) {
        this.query = query;
        this.regionName = regionName;
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

    public static Region getFromName(String regionName) {
        for(Region region : values()) {
            if(!region.regionName.equalsIgnoreCase(regionName)) {
                continue;
            }

            return region;
        }

        return null;
    }

    public String getRegionName() {
        return regionName;
    }

    public String getQuery() {
        return query;
    }

}

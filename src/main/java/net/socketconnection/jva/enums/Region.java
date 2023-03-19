package net.socketconnection.jva.enums;

public enum Region {

    EUROPE("eu", "Europe"),
    NORTH_AMERICA("na", "North America"),
    ASIA_PACIFIC("ap", "Asia Pacific"),
    KOREA("kr", "Korea");

    private final String regionQuery;
    private final String regionName;

    Region(String regionQuery, String regionName) {
        this.regionQuery = regionQuery;
        this.regionName = regionName;
    }

    public static Region getFromRegionQuery(String query) {
        for(Region region : values()) {
            if(!region.regionQuery.equalsIgnoreCase(query)) {
                continue;
            }

            return region;
        }

        return null;
    }

    public static Region getFromRegionName(String regionName) {
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

    public String getRegionQuery() {
        return regionQuery;
    }
}

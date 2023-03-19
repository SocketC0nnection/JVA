package net.socketconnection.jva.enums;

public enum Map {

    BIND("Bind"),
    SPLIT("Split"),
    HAVEN("Haven"),
    ICEBOX("Icebox"),
    ASCENT("Ascent"),
    LOTUS("Lotus"),
    BREEZE("Breeze"),
    FRACTURE("Fracture"),
    PEARL("Pearl");

    private final String mapName;

    Map(String mapName) {
        this.mapName = mapName;
    }

    public static Map getFromMapName(String mapName) {
        for(Map map : values()) {
            if(!map.mapName.equalsIgnoreCase(mapName)) {
                continue;
            }

            return map;
        }

        return null;
    }

    public String getMapName() {
        return mapName;
    }

}

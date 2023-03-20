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

    private final String name;

    Map(String name) {
        this.name = name;
    }

    public static Map getFromName(String name) {
        for(Map map : values()) {
            if(!map.name.equalsIgnoreCase(name)) {
                continue;
            }

            return map;
        }

        return null;
    }

    public String getName() {
        return name;
    }
}

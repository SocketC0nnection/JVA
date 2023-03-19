package net.socketconnection.jva.models.player;

public final class Platform {

    private final String type;
    private final String operatingSystem;
    private final String operatingSystemVersion;

    public Platform(String type, String operatingSystem, String operatingSystemVersion) {
        this.type = type;
        this.operatingSystem = operatingSystem;
        this.operatingSystemVersion = operatingSystemVersion;
    }

    public String getOperatingSystemVersion() {
        return operatingSystemVersion;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getType() {
        return type;
    }
}

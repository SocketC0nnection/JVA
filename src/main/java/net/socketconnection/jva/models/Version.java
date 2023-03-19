package net.socketconnection.jva.models;

import net.socketconnection.jva.enums.Region;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

public final class Version {

    private final String version;
    private final String clientVersion;
    private final String brand;
    private final Region region;

    public Version(String version, String clientVersion, String brand, Region region) {
        this.version = version;
        this.clientVersion = clientVersion;
        this.brand = brand;
        this.region = region;
    }

    public Region getRegion() {
        return region;
    }

    public String getBrand() {
        return brand;
    }

    public String getClientVersion() {
        return clientVersion;
    }

    public String getVersion() {
        return version;
    }
}

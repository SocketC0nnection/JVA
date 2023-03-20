package net.socketconnection.jva.models.shop;

public final class ContentTier {

    private final String name;
    private final String devName;
    private final String icon;

    public ContentTier(String name, String devName, String icon) {
        this.name = name;
        this.devName = devName;
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public String getDevName() {
        return devName;
    }

    public String getName() {
        return name;
    }
}

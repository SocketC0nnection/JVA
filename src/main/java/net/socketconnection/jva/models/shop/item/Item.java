package net.socketconnection.jva.models.shop.item;

public abstract class Item {

    private final String skinId;
    private final String name;
    private final String image;
    private final Type type;

    public Item(String skinId, String name, String image, Type type) {
        this.skinId = skinId;
        this.name = name;
        this.image = image;
        this.type = type;
    }

    public enum Type {

        BUDDY("buddy", "Buddy"),
        PLAYER_CARD("player_card", "Player Card"),
        SKIN_LEVEL("skin_level", "Skin Level");

        private final String query;
        private final String name;

        Type(String query, String name) {
            this.query = query;
            this.name = name;
        }

        public static Type getFromName(String name) {
            for(Type type : values()) {
                if(!type.name.equalsIgnoreCase(name)) {
                    continue;
                }

                return type;
            }

            return null;
        }

        public static Type getFromQuery(String query) {
            for(Type type : values()) {
                if(!type.query.equalsIgnoreCase(query)) {
                    continue;
                }

                return type;
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

    public Type getType() {
        return type;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getSkinId() {
        return skinId;
    }
}

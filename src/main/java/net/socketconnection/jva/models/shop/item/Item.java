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

        private final String typeQuery;
        private final String typeName;

        Type(String typeQuery, String typeName) {
            this.typeQuery = typeQuery;
            this.typeName = typeName;
        }

        public static Type getFromTypeName(String typeName) {
            for(Type type : values()) {
                if(!type.typeName.equalsIgnoreCase(typeName)) {
                    continue;
                }

                return type;
            }

            return null;
        }

        public static Type getFromTypeQuery(String typeQuery) {
            for(Type type : values()) {
                if(!type.typeQuery.equalsIgnoreCase(typeQuery)) {
                    continue;
                }

                return type;
            }

            return null;
        }

        public String getTypeName() {
            return typeName;
        }

        public String getTypeQuery() {
            return typeQuery;
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

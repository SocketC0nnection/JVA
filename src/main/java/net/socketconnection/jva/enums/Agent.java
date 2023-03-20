package net.socketconnection.jva.enums;

public enum Agent {

    JETT("Jett", Category.DUELIST),
    RAZE("Raze", Category.DUELIST),
    BREACH("Breach", Category.INITIATOR),
    OMEN("Omen", Category.CONTROLLER),
    BRIMSTONE("Brimstone", Category.CONTROLLER),
    PHOENIX("Phoenix", Category.DUELIST),
    SAGE("Sage", Category.SENTINEL),
    SOVA("Sova", Category.INITIATOR),
    VIPER("Viper", Category.CONTROLLER),
    CYPHER("Cypher", Category.SENTINEL),
    REYNA("Reyna", Category.DUELIST),
    KILLJOY("Killjoy", Category.SENTINEL),
    SKYE("Skye", Category.INITIATOR),
    YORU("Yoru", Category.DUELIST),
    ASTRA("Astra", Category.CONTROLLER),
    KAYO("KAY/O", Category.INITIATOR),
    CHAMBER("Chamber", Category.SENTINEL),
    NEON("Neon", Category.DUELIST),
    FADE("Fade", Category.INITIATOR),
    HARBOR("Harbor", Category.CONTROLLER),
    GEKKO("Gekko", Category.INITIATOR);

    private final String name;
    private final Category category;

    Agent(String name, Category category) {
        this.name = name;
        this.category = category;
    }

    public static Agent getFromCategory(Category category) {
        for(Agent agent : values()) {
            if(!agent.category.equals(category)) {
                continue;
            }

            return agent;
        }

        return null;
    }

    public static Agent getFromName(String name) {
        for(Agent agent : values()) {
            if(!agent.name.equalsIgnoreCase(name)) {
                continue;
            }

            return agent;
        }

        return null;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public enum Category {

        DUELIST("Duelist"),
        CONTROLLER("Controller"),
        INITIATOR("Initiator"),
        SENTINEL("Sentinel");

        private final String name;

        Category(String name) {
            this.name = name;
        }

        public static Category getFromName(String name) {
            for(Category category : values()) {
                if(!category.name.equalsIgnoreCase(name)) {
                    continue;
                }

                return category;
            }

            return null;
        }

        public String getName() {
            return name;
        }
    }
}

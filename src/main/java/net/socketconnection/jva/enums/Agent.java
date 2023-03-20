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

    private final String agentName;
    private final Category agentCategory;

    Agent(String agentName, Category agentCategory) {
        this.agentName = agentName;
        this.agentCategory = agentCategory;
    }

    public static Agent getFromAgentCategory(Category agentCategory) {
        for(Agent agent : values()) {
            if(!agent.agentCategory.equals(agentCategory)) {
                continue;
            }

            return agent;
        }

        return null;
    }

    public static Agent getFromAgentName(String agentName) {
        for(Agent agent : values()) {
            if(!agent.agentName.equalsIgnoreCase(agentName)) {
                continue;
            }

            return agent;
        }

        return null;
    }

    public Category getAgentCategory() {
        return agentCategory;
    }

    public String getAgentName() {
        return agentName;
    }

    public enum Category {

        DUELIST("Duelist"),
        CONTROLLER("Controller"),
        INITIATOR("Initiator"),
        SENTINEL("Sentinel");

        private final String categoryName;

        Category(String categoryName) {
            this.categoryName = categoryName;
        }

        public static Category getFromCategoryName(String categoryName) {
            for(Category category : values()) {
                if(!category.categoryName.equalsIgnoreCase(categoryName)) {
                    continue;
                }

                return category;
            }

            return null;
        }

        public String getCategoryName() {
            return categoryName;
        }
    }
}

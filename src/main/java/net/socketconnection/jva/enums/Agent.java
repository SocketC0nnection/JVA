package net.socketconnection.jva.enums;

public enum Agent {

    JETT("Jett"),
    RAZE("Raze"),
    BREACH("Breach"),
    OMEN("Omen"),
    BRIMSTONE("Brimstone"),
    PHOENIX("Phoenix"),
    SAGE("Sage"),
    SOVA("Sova"),
    VIPER("Viper"),
    CYPHER("Cypher"),
    REYNA("Reyna"),
    KILLJOY("Killjoy"),
    SKYE("Skye"),
    YORU("Yoru"),
    ASTRA("Astra"),
    KAYO("KAY/O"),
    CHAMBER("Chamber"),
    NEON("Neon"),
    FADE("Fade"),
    HARBOR("Harbor"),
    GEKKO("Gekko");

    private final String agentName;

    Agent(String agentName) {
        this.agentName = agentName;
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

    public String getAgentName() {
        return agentName;
    }
}

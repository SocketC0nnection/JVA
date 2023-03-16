package net.socketconnection.jva;

public enum Rank {

    RADIANT("Radiant", 27),
    IMMORTAL_3("Immortal 3", 26),
    IMMORTAL_2("Immortal 2", 25),
    IMMORTAL_1("Immortal 1", 24),
    ASCENDANT_3("Ascendant 3", 23),
    ASCENDANT_2("Ascendant 2", 22),
    ASCENDANT_1("Ascendant 1", 21),
    DIAMOND_3("Diamond 3", 20),
    DIAMOND_2("Diamond 2", 19),
    DIAMOND_1("Diamond 1", 18),
    PLATINUM_3("Platinum 3", 17),
    PLATINUM_2("Platinum 2", 16),
    PLATINUM_1("Platinum 1", 15),
    GOLD_3("Gold 3", 14),
    GOLD_2("Gold 2", 13),
    GOLD_1("Gold 1", 12),
    SILVER_3("Silver 3", 11),
    SILVER_2("Silver 2", 10),
    SILVER_1("Silver 1", 9),
    BRONZE_3("Bronze 3", 8),
    BRONZE_2("Bronze 2", 7),
    BRONZE_1("Bronze 1", 6),
    IRON_3("Iron 3", 5),
    IRON_2("Iron 2", 4),
    IRON_1("Iron 1", 3),
    UNUSED_2("Unused 2", 2),
    UNUSED_1("Unused 1", 1),
    UNRANKED("Unranked", 0);

    private final String rankName;
    private final int rankId;

    Rank(String rankName, int rankId) {
        this.rankName = rankName;
        this.rankId = rankId;
    }

    public static Rank getFromName(String rankName) {
        for(Rank rank : values()) {
            if(!rank.getRankName().equalsIgnoreCase(rankName)) {
                continue;
            }

            return rank;
        }

        return null;
    }

    public static Rank getFromId(int rankId) {
        for(Rank rank : values()) {
            if(rank.getRankId() != rankId) {
                continue;
            }

            return rank;
        }

        return null;
    }

    public int getRankId() {
        return rankId;
    }

    public String getRankName() {
        return rankName;
    }
}

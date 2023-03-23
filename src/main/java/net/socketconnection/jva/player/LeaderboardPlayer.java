package net.socketconnection.jva.player;

import com.google.gson.JsonObject;
import net.socketconnection.jva.ValorantAPI;
import net.socketconnection.jva.enums.Rank;
import net.socketconnection.jva.exceptions.FetchException;
import net.socketconnection.jva.utils.GsonUtils;

import java.io.IOException;

public class LeaderboardPlayer extends Player {

    String playerCardId;
    String titleId;
    boolean banned;
    boolean anonymized;
    int leaderboardRank;
    int rankRating;
    int amountOfWins;
    Rank rank;

    public LeaderboardPlayer(ValorantAPI valorantAPI) {
        super(valorantAPI);
    }

    public LeaderboardPlayer fetchData(JsonObject object) {
        playerCardId = GsonUtils.getAsString(object.get("PlayerCardID"));
        titleId = GsonUtils.getAsString(object.get("TitleID"));
        banned = GsonUtils.getAsBoolean(object.get("IsBanned"));
        anonymized = GsonUtils.getAsBoolean(object.get("IsAnonymized"));
        playerId = GsonUtils.getAsString(object.get("puuid"));
        username = GsonUtils.getAsString(object.get("gameName"));
        tag = GsonUtils.getAsString(object.get("tagLine"));
        leaderboardRank = GsonUtils.getAsInt(object.get("leaderboardRank"));
        rankRating = GsonUtils.getAsInt(object.get("rankedRating"));
        amountOfWins = GsonUtils.getAsInt(object.get("numberOfWins"));
        rank = Rank.getFromId(GsonUtils.getAsInt(object.get("competitiveTier")));

        fetched = true;

        return this;
    }

    public ValorantPlayer getValorantPlayer() throws IOException {
        if (!fetched) {
            throw new FetchException("Not fetched yet (no riot-id information");
        }

        return new ValorantPlayer(valorantAPI).fetchData(username, tag);
    }

    @Override
    public String getRiotId() {
        if (username.isEmpty() || tag.isEmpty()) {
            return null;
        }

        return super.getRiotId();
    }

    public Rank getRank() {
        return rank;
    }

    public int getAmountOfWins() {
        return amountOfWins;
    }

    public int getRankRating() {
        return rankRating;
    }

    public int getLeaderboardRank() {
        return leaderboardRank;
    }

    public boolean isAnonymized() {
        return anonymized;
    }

    public boolean isBanned() {
        return banned;
    }

    public String getTitleId() {
        return titleId;
    }

    public String getPlayerCardId() {
        return playerCardId;
    }
}

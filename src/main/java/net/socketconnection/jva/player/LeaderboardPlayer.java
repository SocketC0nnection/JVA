package net.socketconnection.jva.player;

import com.google.gson.JsonObject;
import net.socketconnection.jva.ValorantAPI;
import net.socketconnection.jva.enums.Rank;
import net.socketconnection.jva.exceptions.FetchException;

import java.io.IOException;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

public class LeaderboardPlayer extends Player {

    String playerCardId;
    String titleId;
    boolean banned;
    boolean anonymized;
    int leaderboardRank;
    int rankRating;
    int amountOfWins;

    public LeaderboardPlayer(ValorantAPI valorantAPI) {
        super(valorantAPI);
    }

    public LeaderboardPlayer fetchData(JsonObject object) {
        playerCardId = object.get("PlayerCardID").getAsString();
        titleId = object.get("TitleID").getAsString();
        banned = object.get("IsBanned").getAsBoolean();
        anonymized = object.get("IsAnonymized").getAsBoolean();
        puuid = object.get("puuid").getAsString();
        username = object.get("gameName").getAsString();
        tag = object.get("tagLine").getAsString();
        leaderboardRank = object.get("leaderboardRank").getAsInt();
        rankRating = object.get("rankedRating").getAsInt();
        amountOfWins = object.get("numberOfWins").getAsInt();
        rank = Rank.getFromRankId(object.get("competitiveTier").getAsInt());

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

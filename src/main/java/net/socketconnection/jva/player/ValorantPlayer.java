package net.socketconnection.jva.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.socketconnection.jva.api.ValorantAPI;
import net.socketconnection.jva.enums.GameMode;
import net.socketconnection.jva.enums.Rank;
import net.socketconnection.jva.enums.Region;
import net.socketconnection.jva.exceptions.InvalidRiotIdentificationException;
import net.socketconnection.jva.match.Match;
import net.socketconnection.jva.models.image.RankImage;
import net.socketconnection.jva.models.player.PlayerCard;
import net.socketconnection.jva.utils.GsonUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ValorantPlayer extends Player {

    Region region;
    int level;
    PlayerCard playerCard;
    String lastUpdate;
    Rank rank;
    RankImage rankImage;
    int rankRating;
    int mmrChange;
    int elo;

    public ValorantPlayer(ValorantAPI valorantAPI) {
        super(valorantAPI);
    }

    public ValorantPlayer fetchData(String username, String tag) throws IOException {
        this.username = username;
        this.tag = tag;

        JsonObject accountData = valorantAPI.sendRestRequest("/v1/account/" + username + "/" + tag).getAsJsonObject().getAsJsonObject("data");

        playerId = GsonUtils.getAsString(accountData.get("puuid"));
        region = Region.getFromQuery(GsonUtils.getAsString(accountData.get("region")));
        level = GsonUtils.getAsInt(accountData.get("account_level"));

        JsonObject cards = accountData.getAsJsonObject("card");

        playerCard = new PlayerCard(GsonUtils.getAsString(cards.get("small")), GsonUtils.getAsString(cards.get("large")),
                GsonUtils.getAsString(cards.get("wide")), GsonUtils.getAsString(cards.get("id")));
        lastUpdate = GsonUtils.getAsString(accountData.get("last_update"));

        JsonObject mmrData = valorantAPI.sendRestRequest("/v1/mmr/" + region.getQuery() + "/" + username + "/" + tag).getAsJsonObject().getAsJsonObject("data");

        rank = Rank.getFromId(GsonUtils.getAsInt(mmrData.get("currenttier")));

        if(!mmrData.get("images").isJsonNull()) {
            JsonObject ranks = mmrData.getAsJsonObject("images");

            rankImage = new RankImage(GsonUtils.getAsString(ranks.get("small")), GsonUtils.getAsString(ranks.get("large")),
                    GsonUtils.getAsString(ranks.get("triangle_down")), GsonUtils.getAsString(ranks.get("triangle_up")));
        }

        rankRating = GsonUtils.getAsInt(mmrData.get("ranking_in_tier"));
        mmrChange = GsonUtils.getAsInt(mmrData.get("mmr_change_to_last_game"));
        elo = GsonUtils.getAsInt(mmrData.get("elo"));

        fetched = true;

        return this;
    }

    public ValorantPlayer fetchData(String riotId) throws IOException {
        if (!riotId.contains("#") || riotId.split("#").length < 2) {
            throw new InvalidRiotIdentificationException("Unknown format (right format: NAME#TAG)");
        }

        String[] data = riotId.split("#");

        return fetchData(data[0], data[1]);
    }

    public Match[] getMatchHistory(GameMode gameMode) throws IOException {
        JsonArray matchHistoryData;

        if (gameMode == null) {
            matchHistoryData = valorantAPI.sendRestRequest("/v3/matches/" + region.getQuery() + "/" + username + "/" + tag).getAsJsonObject().getAsJsonArray("data");
        } else {
            matchHistoryData = valorantAPI.sendRestRequest("/v3/matches/" + region.getQuery() + "/" + username + "/" + tag + "?filter=" + gameMode.getQuery()).getAsJsonObject().getAsJsonArray("data");
        }

        List<Match> matches = new LinkedList<>();

        for (JsonElement element : matchHistoryData) {
            matches.add(new Match(valorantAPI).fetchData(element.getAsJsonObject()));
        }

        return matches.toArray(new Match[0]);
    }

    public Match[] getMatchHistory() throws IOException {
        return getMatchHistory(null);
    }

    public MatchPlayer getMatchPlayer(String matchId) throws IOException {
        Match match = new Match(valorantAPI).fetchData(matchId);

        for (MatchPlayer player : match.getPlayers()) {
            if (!player.getPlayerId().equals(playerId)) {
                continue;
            }

            return player;
        }

        return null;
    }

    public int getElo() {
        return elo;
    }

    public int getMmrChange() {
        return mmrChange;
    }

    public int getRankRating() {
        return rankRating;
    }

    public RankImage getRankImage() {
        return rankImage;
    }

    public Rank getRank() {
        return rank;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public PlayerCard getPlayerCard() {
        return playerCard;
    }

    public int getLevel() {
        return level;
    }

    public Region getRegion() {
        return region;
    }

}

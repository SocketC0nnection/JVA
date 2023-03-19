package net.socketconnection.jva.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.socketconnection.jva.ValorantAPI;
import net.socketconnection.jva.enums.GameMode;
import net.socketconnection.jva.enums.Rank;
import net.socketconnection.jva.enums.Region;
import net.socketconnection.jva.exceptions.InvalidRiotIdentificationException;
import net.socketconnection.jva.match.Match;
import net.socketconnection.jva.models.player.PlayerCard;
import net.socketconnection.jva.models.image.RankImage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ValorantPlayer extends Player {

    Region region;
    int level;
    PlayerCard playerCard;
    String lastUpdate;
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

        puuid = accountData.get("puuid").getAsString();
        region = Region.getFromRegionQuery(accountData.get("region").getAsString());
        level = accountData.get("account_level").getAsInt();

        JsonObject cards = accountData.getAsJsonObject("card");

        playerCard = new PlayerCard(cards.get("small").getAsString(), cards.get("large").getAsString(),
                cards.get("wide").getAsString(), cards.get("id").getAsString());
        lastUpdate = accountData.get("last_update").getAsString();

        JsonObject mmrData = valorantAPI.sendRestRequest("/v1/mmr/" + region.getRegionQuery() + "/" + username + "/" + tag).getAsJsonObject().getAsJsonObject("data");

        rank = Rank.getFromRankId(mmrData.get("currenttier").getAsInt());

        JsonObject ranks = mmrData.getAsJsonObject("images");

        rankImage = new RankImage(ranks.get("small").getAsString(), ranks.get("large").getAsString(),
                ranks.get("triangle_down").getAsString(), ranks.get("triangle_up").getAsString());
        rankRating = mmrData.get("ranking_in_tier").getAsInt();
        mmrChange = mmrData.get("mmr_change_to_last_game").getAsInt();
        elo = mmrData.get("elo").getAsInt();

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
            matchHistoryData = valorantAPI.sendRestRequest("/v3/matches/" + region.getRegionQuery() + "/" + username + "/" + tag).getAsJsonObject().getAsJsonArray("data");
        } else {
            matchHistoryData = valorantAPI.sendRestRequest("/v3/matches/" + region.getRegionQuery() + "/" + username + "/" + tag + "?filter=" + gameMode.getGameModeQuery()).getAsJsonObject().getAsJsonArray("data");
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
            if (!player.getPuuid().equals(puuid)) {
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

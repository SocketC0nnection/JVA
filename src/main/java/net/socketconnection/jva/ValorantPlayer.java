package net.socketconnection.jva;

import com.google.gson.JsonObject;
import net.socketconnection.jva.exceptions.InvalidRiotIdentificationException;
import net.socketconnection.jva.utils.RiotUtils;

import java.io.IOException;

public class ValorantPlayer {

    private final ValorantAPI valorantAPI;

    private String username;
    private String tag;
    private String puuid;
    private Region region;
    private int level;
    private PlayerCard playerCard;
    private String lastUpdate;
    private Rank rank;
    private RankImage rankImage;
    private int rankRating;
    private int mmrChange;
    private int elo;

    public ValorantPlayer(ValorantAPI valorantAPI) {
        this.valorantAPI = valorantAPI;
    }

    public ValorantPlayer fetchData(String username, String tag) throws IOException {
        this.username = username;
        this.tag = tag;

        JsonObject accountData = valorantAPI.sendRestRequest("/account/" + username + "/" + tag).getAsJsonObject("data");

        puuid = accountData.get("puuid").getAsString();
        region = Region.getFromQuery(accountData.get("region").getAsString());
        level = accountData.get("account_level").getAsInt();

        JsonObject cards = accountData.getAsJsonObject("card");

        playerCard = new PlayerCard(cards.get("small").getAsString(), cards.get("large").getAsString(),
                cards.get("wide").getAsString(), cards.get("id").getAsString());
        lastUpdate = accountData.get("last_update").getAsString();

        JsonObject mmrData = valorantAPI.sendRestRequest("/mmr/" + region.getQuery() + "/" + username + "/" + tag).getAsJsonObject("data");

        rank = Rank.getFromId(mmrData.get("currenttier").getAsInt());

        JsonObject ranks = mmrData.getAsJsonObject("images");

        rankImage = new RankImage(ranks.get("small").getAsString(), ranks.get("large").getAsString(),
                ranks.get("triangle_down").getAsString(), ranks.get("triangle_up").getAsString());
        rankRating = mmrData.get("ranking_in_tier").getAsInt();
        mmrChange = mmrData.get("mmr_change_to_last_game").getAsInt();
        elo = mmrData.get("elo").getAsInt();

        return this;
    }

    public ValorantPlayer fetchData(String riotId) throws InvalidRiotIdentificationException, IOException {
        if(!riotId.contains("#") || riotId.split("#").length < 2) {
            throw new InvalidRiotIdentificationException("Unknown format (right format: NAME#TAG)");
        }

        String[] data = riotId.split("#");

        return fetchData(data[0], data[1]);
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

    public String getPuuid() {
        return puuid;
    }

    public String getTag() {
        return tag;
    }

    public String getUsername() {
        return username;
    }

    public String getRiotId() {
        return RiotUtils.toRiotId(username, tag);
    }

    public ValorantAPI getValorantAPI() {
        return valorantAPI;
    }
}

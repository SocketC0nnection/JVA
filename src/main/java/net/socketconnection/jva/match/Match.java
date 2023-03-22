package net.socketconnection.jva.match;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.socketconnection.jva.ValorantAPI;
import net.socketconnection.jva.enums.GameMode;
import net.socketconnection.jva.enums.Map;
import net.socketconnection.jva.enums.Region;
import net.socketconnection.jva.player.MatchPlayer;
import net.socketconnection.jva.player.ValorantPlayer;
import net.socketconnection.jva.utils.GsonUtils;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Match {

    final ValorantAPI valorantAPI;

    String matchId;
    Map map;
    String gameVersion;
    long gameLength;
    String gameStart;
    int roundsPlayed;
    GameMode gameMode;
    String seasonId;
    String platform;
    Region region;
    String server;
    List<MatchPlayer> players;

    boolean fetched;

    public Match(ValorantAPI valorantAPI) {
        this.valorantAPI = valorantAPI;
    }

    public Match fetchData(JsonObject object) {
        JsonObject metaData = object.getAsJsonObject("metadata");

        map = Map.getFromName(GsonUtils.getAsString(metaData.get("map")));
        gameVersion = GsonUtils.getAsString(metaData.get("game_version"));
        gameLength = GsonUtils.getAsLong(metaData.get("game_length"));
        gameStart = GsonUtils.getAsString(metaData.get("game_start_patched"));
        roundsPlayed = GsonUtils.getAsInt(metaData.get("rounds_played"));
        gameMode = GameMode.getFromName(GsonUtils.getAsString(metaData.get("mode")));
        seasonId = GsonUtils.getAsString(metaData.get("season_id"));
        platform = GsonUtils.getAsString(metaData.get("platform"));
        matchId = GsonUtils.getAsString(metaData.get("matchid"));
        region = Region.getFromQuery(GsonUtils.getAsString(metaData.get("region")));
        server = GsonUtils.getAsString(metaData.get("cluster"));

        JsonArray playerData = object.getAsJsonObject("players").getAsJsonArray("all_players");

        players = new LinkedList<>();

        for (JsonElement element : playerData) {
            players.add(new MatchPlayer(valorantAPI).fetchData(region, element.getAsJsonObject()));
        }

        fetched = true;

        return this;
    }

    public Match fetchData(String matchId) throws IOException {
        return fetchData(valorantAPI.sendRestRequest("/v2/match/" + matchId).getAsJsonObject().getAsJsonObject("data"));
    }

    public MatchPlayer getMatchPlayer(ValorantPlayer valorantPlayer) {
        for (MatchPlayer player : players) {
            if (!player.getPlayerId().equals(valorantPlayer.getPlayerId())) {
                continue;
            }

            return player;
        }

        return null;
    }

    public enum Team {

        BLUE("Blue"),
        RED("Red");

        private final String name;

        Team(String name) {
            this.name = name;
        }

        public static Team getFromName(String name) {
            for (Team team : values()) {
                if (!team.name.equalsIgnoreCase(name)) {
                    continue;
                }

                return team;
            }

            return null;
        }

        public String getName() {
            return name;
        }
    }

    public boolean isFetched() {
        return fetched;
    }

    public List<MatchPlayer> getPlayers() {
        return players;
    }

    public String getServer() {
        return server;
    }

    public Region getRegion() {
        return region;
    }

    public String getPlatform() {
        return platform;
    }

    public String getSeasonId() {
        return seasonId;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public int getRoundsPlayed() {
        return roundsPlayed;
    }

    public String getGameStart() {
        return gameStart;
    }

    public long getGameLength() {
        return gameLength;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public Map getMap() {
        return map;
    }

    public String getMatchId() {
        return matchId;
    }

    public ValorantAPI getValorantAPI() {
        return valorantAPI;
    }
}

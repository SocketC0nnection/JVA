package net.socketconnection.jva.match;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.socketconnection.jva.api.ValorantAPI;
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
    MatchTeam red;
    MatchTeam blue;
    List<MatchRound> rounds;

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

        List<MatchPlayer> redPlayers = new LinkedList<>();
        List<MatchPlayer> bluePlayers = new LinkedList<>();

        for (JsonElement playerElement : playerData) {
            MatchPlayer matchPlayer = new MatchPlayer(valorantAPI).fetchData(region, playerElement.getAsJsonObject());

            switch (matchPlayer.getTeam()) {
                case "Red":
                    redPlayers.add(matchPlayer);

                    break;
                case "Blue":
                    bluePlayers.add(matchPlayer);

                    break;
            }

            players.add(matchPlayer);
        }

        JsonObject redData = object.getAsJsonObject("teams").getAsJsonObject("red");
        JsonObject blueData = object.getAsJsonObject("teams").getAsJsonObject("blue");

        red = new MatchTeam(redPlayers, GsonUtils.getAsBoolean(redData.get("has_won")), GsonUtils.getAsInt(redData.get("rounds_won")),
                GsonUtils.getAsInt(redData.get("rounds_lost")));
        blue = new MatchTeam(bluePlayers, GsonUtils.getAsBoolean(blueData.get("has_won")), GsonUtils.getAsInt(blueData.get("rounds_won")),
                GsonUtils.getAsInt(blueData.get("rounds_lost")));

        JsonArray roundsData = object.getAsJsonArray("rounds");

        rounds = new LinkedList<>();

        for(JsonElement roundElement : roundsData) {
            rounds.add(new MatchRound(this).fetchData(roundElement.getAsJsonObject()));
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

    public boolean isFetched() {
        return fetched;
    }

    public List<MatchRound> getRounds() {
        return rounds;
    }

    public MatchTeam getBlue() {
        return blue;
    }

    public MatchTeam getRed() {
        return red;
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

    @Override
    public String toString() {
        return "Match{" +
                "valorantAPI=" + valorantAPI +
                ", matchId='" + matchId + '\'' +
                ", map=" + map +
                ", gameVersion='" + gameVersion + '\'' +
                ", gameLength=" + gameLength +
                ", gameStart='" + gameStart + '\'' +
                ", roundsPlayed=" + roundsPlayed +
                ", gameMode=" + gameMode +
                ", seasonId='" + seasonId + '\'' +
                ", platform='" + platform + '\'' +
                ", region=" + region +
                ", server='" + server + '\'' +
                ", players=" + players +
                ", red=" + red +
                ", blue=" + blue +
                ", rounds=" + rounds +
                ", fetched=" + fetched +
                '}';
    }
}

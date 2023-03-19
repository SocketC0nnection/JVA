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

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

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

    public Match(ValorantAPI valorantAPI) {
        this.valorantAPI = valorantAPI;
    }

    public Match fetchData(JsonObject object) {
        JsonObject metaData = object.getAsJsonObject("metadata");

        map = Map.getFromMapName(metaData.get("map").getAsString());
        gameVersion = metaData.get("game_version").getAsString();
        gameLength = metaData.get("game_length").getAsLong();
        gameStart = metaData.get("game_start_patched").getAsString();
        roundsPlayed = metaData.get("rounds_played").getAsInt();
        gameMode = GameMode.getFromGameModeName(metaData.get("mode").getAsString());
        seasonId = metaData.get("season_id").getAsString();
        platform = metaData.get("platform").getAsString();
        matchId = metaData.get("matchid").getAsString();
        region = Region.getFromRegionQuery(metaData.get("region").getAsString());
        server = metaData.get("cluster").getAsString();

        JsonArray playerData = object.getAsJsonObject("players").getAsJsonArray("all_players");

        players = new LinkedList<>();

        for (JsonElement element : playerData) {
            players.add(new MatchPlayer(valorantAPI).fetchData(region, element.getAsJsonObject()));
        }

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

        private final String teamName;

        Team(String teamName) {
            this.teamName = teamName;
        }

        public static Team getFromTeamName(String teamName) {
            for (Team team : values()) {
                if (!team.teamName.equalsIgnoreCase(teamName)) {
                    continue;
                }

                return team;
            }

            return null;
        }

        public String getTeamName() {
            return teamName;
        }
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

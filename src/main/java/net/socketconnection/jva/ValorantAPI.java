package net.socketconnection.jva;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.socketconnection.jva.enums.Region;
import net.socketconnection.jva.exceptions.IncorrectDataException;
import net.socketconnection.jva.exceptions.InvalidAuthenticationException;
import net.socketconnection.jva.exceptions.InvalidRiotIdentificationException;
import net.socketconnection.jva.exceptions.RateLimitedException;
import net.socketconnection.jva.player.LeaderboardPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public class ValorantAPI {

    private final URL baseUrl;
    private final String apiKey;

    public ValorantAPI(String apiKey) throws IOException {
        baseUrl = new URL("https://api.henrikdev.xyz/valorant");
        this.apiKey = apiKey;
    }

    public ValorantAPI() throws IOException {
        this(null);
    }

    public List<LeaderboardPlayer> getLeaderboard(Region region, String riotId) throws IOException {
        JsonArray leaderboardData;

        if(riotId == null) {
            leaderboardData = sendRestRequest("/v1/leaderboard/" + region.getRegionQuery()).getAsJsonArray();
        } else {
            if(!riotId.contains("#") || riotId.split("#").length < 2) {
                throw new InvalidRiotIdentificationException("Unknown format (right format: NAME#TAG)");
            }

            String[] data = riotId.split("#");

            leaderboardData = sendRestRequest("/v1/leaderboard/" + region.getRegionQuery() + "?name=" + data[0] + "&tag=" + data[1]).getAsJsonArray();
        }

        List<LeaderboardPlayer> leaderboard = new LinkedList<>();

        for(JsonElement element : leaderboardData) {
            leaderboard.add(new LeaderboardPlayer(this).fetchData(element.getAsJsonObject()));
        }

        return leaderboard;
    }

    public List<LeaderboardPlayer> getLeaderboard(Region region) throws IOException {
        return getLeaderboard(region, null);
    }

    public void getServerStatus(Region region) {

    }

    public JsonElement sendRestRequest(String uriPath) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(baseUrl + uriPath).openConnection();

        if(apiKey != null) {
            connection.setRequestProperty("Authorization", apiKey);
        }

        connection.setRequestProperty("User-Agent", "Java Valorant API (JVA)");
        connection.setDoInput(true);

        switch (connection.getResponseCode()) {
            case 403:
                throw new InvalidAuthenticationException(connection.getResponseMessage());
            case 404:
                throw new IncorrectDataException(connection.getResponseMessage());
            case 429:
                throw new RateLimitedException(connection.getResponseMessage());
        }

        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String msg;

        while ((msg = reader.readLine()) != null) {
            builder.append(msg).append("\n");
        }

        return new Gson().fromJson(builder.toString(), JsonElement.class);
    }

    public String getApiKey() {
        return apiKey;
    }

    public URL getBaseUrl() {
        return baseUrl;
    }
}

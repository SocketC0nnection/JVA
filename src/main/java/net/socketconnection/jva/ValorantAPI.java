package net.socketconnection.jva;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.socketconnection.jva.exceptions.InvalidRiotIdentificationException;
import net.socketconnection.jva.exceptions.RateLimitedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ValorantAPI {

    private final URL baseUrl;

    public ValorantAPI() throws IOException {
        baseUrl = new URL("https://api.henrikdev.xyz/valorant/v1");
    }

    public static void main(String[] args) throws IOException, InvalidRiotIdentificationException {
        ValorantAPI valorantAPI = new ValorantAPI();
        ValorantPlayer player = new ValorantPlayer(valorantAPI).fetchData("Socket#0312");

        System.out.println(player.getPlayerCard().getLarge());
    }

    public JsonObject sendRestRequest(String uriPath) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(baseUrl + uriPath).openConnection();

        connection.setRequestProperty("User-Agent", "Java Valorant API (JVA)");
        connection.setDoInput(true);

        if(connection.getResponseCode() == 429) {
            throw new RateLimitedException(connection.getResponseMessage());
        }

        StringBuilder builder = new StringBuilder();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String msg;

        while ((msg = reader.readLine()) != null) {
            builder.append(msg).append("\n");
        }

        return new Gson().fromJson(builder.toString(), JsonObject.class);
    }

}

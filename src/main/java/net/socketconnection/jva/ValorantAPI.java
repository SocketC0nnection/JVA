package net.socketconnection.jva;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.socketconnection.jva.enums.Language;
import net.socketconnection.jva.enums.Region;
import net.socketconnection.jva.exceptions.*;
import net.socketconnection.jva.models.Version;
import net.socketconnection.jva.models.WebsiteArticle;
import net.socketconnection.jva.models.shop.Bundle;
import net.socketconnection.jva.models.shop.ContentTier;
import net.socketconnection.jva.models.shop.item.BundleItem;
import net.socketconnection.jva.models.shop.item.Item;
import net.socketconnection.jva.models.shop.item.OfferItem;
import net.socketconnection.jva.models.status.ServerStatus;
import net.socketconnection.jva.models.status.StatusEntry;
import net.socketconnection.jva.models.status.Update;
import net.socketconnection.jva.player.LeaderboardPlayer;
import net.socketconnection.jva.utils.GsonUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
            leaderboardData = sendRestRequest("/v1/leaderboard/" + region.getQuery()).getAsJsonArray();
        } else {
            if(!riotId.contains("#") || riotId.split("#").length < 2) {
                throw new InvalidRiotIdentificationException("Unknown format (right format: NAME#TAG)");
            }

            String[] data = riotId.split("#");

            leaderboardData = sendRestRequest("/v1/leaderboard/" + region.getQuery() + "?name=" + data[0] + "&tag=" + data[1]).getAsJsonArray();
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

    public ServerStatus getServerStatus(Region region) throws IOException {
        JsonObject statusData = sendRestRequest("/v1/status/" + region.getQuery()).getAsJsonObject().getAsJsonObject("data");

        JsonArray maintenancesData = statusData.getAsJsonArray("maintenances");
        JsonArray incidentsData = statusData.getAsJsonArray("incidents");

        List<StatusEntry> maintenances = new LinkedList<>();
        List<StatusEntry> incidents = new LinkedList<>();

        for(JsonElement maintenanceElement : maintenancesData) {
            JsonObject maintenanceObject = maintenanceElement.getAsJsonObject();
            List<Update> updates = new LinkedList<>();

            for(JsonElement updateElement : maintenanceObject.getAsJsonArray("updates")) {
                JsonObject updateObject = updateElement.getAsJsonObject();
                Map<Language, String> translations = new LinkedHashMap<>();

                for(JsonElement translationElement : updateObject.getAsJsonArray("translations")) {
                    JsonObject translationObject = translationElement.getAsJsonObject();

                    translations.put(Language.getFromLocale(GsonUtils.getAsString(translationObject.get("locale"))),
                            GsonUtils.getAsString(translationObject.get("content")));
                }

                List<String> publishLocations = new LinkedList<>();

                for(JsonElement publishElement : updateObject.getAsJsonArray("publish_locations")) {
                    publishLocations.add(GsonUtils.getAsString(publishElement));
                }

                updates.add(new Update(GsonUtils.getAsString(updateObject.get("created_at")), GsonUtils.getAsString(updateObject.get("updated_at")),
                        GsonUtils.getAsBoolean(updateObject.get("publish")), GsonUtils.getAsInt(updateObject.get("id")), translations,
                        publishLocations.toArray(new String[0]), GsonUtils.getAsString(updateObject.get("author"))));
            }

            List<String> platforms = new LinkedList<>();

            for(JsonElement platformElement : maintenanceObject.getAsJsonArray("platforms")) {
                platforms.add(GsonUtils.getAsString(platformElement));
            }

            Map<Language, String> titles = new LinkedHashMap<>();

            for(JsonElement titleElement : maintenanceObject.getAsJsonArray("titles")) {
                JsonObject titleObject = titleElement.getAsJsonObject();

                titles.put(Language.getFromLocale(GsonUtils.getAsString(titleObject.get("locale"))), GsonUtils.getAsString(titleObject.get("content")));
            }

            maintenances.add(new StatusEntry(GsonUtils.getAsString(maintenanceObject.get("created_at")), GsonUtils.getAsString(maintenanceObject.get("archive_at")),
                    updates.toArray(new Update[0]), platforms.toArray(new String[0]), GsonUtils.getAsString(maintenanceObject.get("updated_at")),
                    GsonUtils.getAsInt(maintenanceObject.get("id")), titles, GsonUtils.getAsString(maintenanceObject.get("maintenance_status")),
                    GsonUtils.getAsString(maintenanceObject.get("incident_severity"))));
        }

        for(JsonElement incidentElement : incidentsData) {
            JsonObject incidentObject = incidentElement.getAsJsonObject();
            List<Update> updates = new LinkedList<>();

            for(JsonElement updateElement : incidentObject.getAsJsonArray("updates")) {
                JsonObject updateObject = updateElement.getAsJsonObject();
                Map<Language, String> translations = new LinkedHashMap<>();

                for(JsonElement translationElement : updateObject.getAsJsonArray("translations")) {
                    JsonObject translationObject = translationElement.getAsJsonObject();

                    translations.put(Language.getFromLocale(GsonUtils.getAsString(translationObject.get("locale"))), GsonUtils.getAsString(translationObject.get("content")));
                }

                List<String> publishLocations = new LinkedList<>();

                for(JsonElement publishElement : updateObject.getAsJsonArray("publish_locations")) {
                    publishLocations.add(GsonUtils.getAsString(publishElement));
                }

                updates.add(new Update(GsonUtils.getAsString(updateObject.get("created_at")), GsonUtils.getAsString(updateObject.get("updated_at")),
                        GsonUtils.getAsBoolean(updateObject.get("publish")), GsonUtils.getAsInt(updateObject.get("id")), translations,
                        publishLocations.toArray(new String[0]), GsonUtils.getAsString(updateObject.get("author"))));
            }

            List<String> platforms = new LinkedList<>();

            for(JsonElement platformElement : incidentObject.getAsJsonArray("platforms")) {
                platforms.add(GsonUtils.getAsString(platformElement));
            }

            Map<Language, String> titles = new LinkedHashMap<>();

            for(JsonElement titleElement : incidentObject.getAsJsonArray("titles")) {
                JsonObject titleObject = titleElement.getAsJsonObject();

                titles.put(Language.getFromLocale(GsonUtils.getAsString(titleObject.get("locale"))), GsonUtils.getAsString(titleObject.get("content")));
            }

            incidents.add(new StatusEntry(GsonUtils.getAsString(incidentObject.get("created_at")), GsonUtils.getAsString(incidentObject.get("archive_at")),
                    updates.toArray(new Update[0]), platforms.toArray(new String[0]), GsonUtils.getAsString(incidentObject.get("updated_at")),
                    GsonUtils.getAsInt(incidentObject.get("id")), titles, GsonUtils.getAsString(incidentObject.get("maintenance_status")),
                    GsonUtils.getAsString(incidentObject.get("incident_severity"))));
        }

        return new ServerStatus(maintenances.toArray(new StatusEntry[0]), incidents.toArray(new StatusEntry[0]));
    }

    public Version getVersion(Region region) throws IOException {
        JsonObject versionData = sendRestRequest("/v1/version/" + region.getQuery()).getAsJsonObject().getAsJsonObject("data");

        return new Version(GsonUtils.getAsString(versionData.get("version")), GsonUtils.getAsString(versionData.get("clientVersion")),
                GsonUtils.getAsString(versionData.get("branch")), Region.getFromQuery(GsonUtils.getAsString(versionData.get("region"))));
    }

    public List<WebsiteArticle> getWebsiteArticles(Language language) throws IOException {
        JsonArray articleData = sendRestRequest("/v1/website/" + language.getLocaleUrl()).getAsJsonObject().getAsJsonArray("data");

        List<WebsiteArticle> websiteArticles = new LinkedList<>();

        for(JsonElement articleElement : articleData) {
            JsonObject articleObject = articleElement.getAsJsonObject();

            websiteArticles.add(new WebsiteArticle(GsonUtils.getAsString(articleObject.get("banner_url")), WebsiteArticle.Category.getFromQuery(GsonUtils.getAsString(articleObject.get("category"))),
                    GsonUtils.getAsString(articleObject.get("date")), GsonUtils.getAsString(articleObject.get("external_link")),
                    GsonUtils.getAsString(articleObject.get("title")), GsonUtils.getAsString(articleObject.get("url"))));
        }

        return websiteArticles;
    }

    public List<Bundle> getStoreBundles() throws IOException {
        JsonArray bundlesData = sendRestRequest("/v2/store-featured").getAsJsonObject().getAsJsonArray("data");

        List<Bundle> bundles = new LinkedList<>();

        for(JsonElement bundleElement : bundlesData) {
            JsonObject bundleObject = bundleElement.getAsJsonObject();
            JsonArray itemData = bundleObject.getAsJsonArray("items");

            List<BundleItem> items = new LinkedList<>();

            for(JsonElement itemElement : itemData) {
                JsonObject itemObject = itemElement.getAsJsonObject();

                items.add(new BundleItem(GsonUtils.getAsString(itemObject.get("uuid")), GsonUtils.getAsString(itemObject.get("name")),
                        GsonUtils.getAsString(itemObject.get("image")), Item.Type.getFromQuery(GsonUtils.getAsString(itemObject.get("type"))),
                        GsonUtils.getAsInt(itemObject.get("amount")), GsonUtils.getAsInt(itemObject.get("discount_percent")),
                        GsonUtils.getAsInt(itemObject.get("base_price")), GsonUtils.getAsInt(itemObject.get("discounted_price")),
                        GsonUtils.getAsBoolean(itemObject.get("promo_item"))));
            }

            bundles.add(new Bundle(GsonUtils.getAsString(bundleObject.get("bundle_uuid")), GsonUtils.getAsInt(bundleObject.get("bundle_price")),
                    GsonUtils.getAsBoolean(bundleObject.get("whole_sale_only")), items.toArray(new BundleItem[0]), GsonUtils.getAsLong(bundleObject.get("seconds_remaining")),
                    GsonUtils.getAsString(bundleObject.get("expires_at"))));
        }

        return bundles;
    }

    public List<OfferItem> getStoreOffers() throws IOException {
        JsonArray offersData = sendRestRequest("/v2/store-offers").getAsJsonObject().getAsJsonObject("data").getAsJsonArray("offers");

        List<OfferItem> items = new LinkedList<>();

        for(JsonElement offerElement : offersData) {
            JsonObject offerObject = offerElement.getAsJsonObject();

            ContentTier contentTier = null;

            if(!offerObject.get("content_tier").isJsonNull()) {
                JsonObject contentTierObject = offerObject.getAsJsonObject("content_tier");

                contentTier = new ContentTier(GsonUtils.getAsString(contentTierObject.get("name")),
                        GsonUtils.getAsString(contentTierObject.get("dev_name")), GsonUtils.getAsString(contentTierObject.get("icon")));
            }

            items.add(new OfferItem(GsonUtils.getAsString(offerObject.get("skin_id")), GsonUtils.getAsString(offerObject.get("name")),
                    GsonUtils.getAsString(offerObject.get("icon")), Item.Type.getFromQuery(GsonUtils.getAsString(offerObject.get("type"))),
                    GsonUtils.getAsString(offerObject.get("offer_id")), GsonUtils.getAsInt(offerObject.get("cost")), contentTier));
        }

        return items;
    }

    public JsonElement sendRestRequest(String uriPath) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(baseUrl + uriPath).openConnection();

        if(apiKey != null) {
            connection.setRequestProperty("Authorization", apiKey);
        }

        connection.setRequestProperty("User-Agent", "Java Valorant API (JVA)");
        connection.setDoInput(true);

        switch (connection.getResponseCode()) {
            case 200:
                break;
            case 403:
                throw new InvalidAuthenticationException(connection.getResponseMessage());
            case 404:
                throw new IncorrectDataException(connection.getResponseMessage());
            case 429:
                throw new RateLimitedException(connection.getResponseMessage());
            default:
                throw new FetchException("Rest API returned unknown error code: " + connection.getResponseMessage());
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

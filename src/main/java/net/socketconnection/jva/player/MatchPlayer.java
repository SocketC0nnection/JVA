package net.socketconnection.jva.player;

import com.google.gson.JsonObject;
import net.socketconnection.jva.ValorantAPI;
import net.socketconnection.jva.enums.Agent;
import net.socketconnection.jva.enums.Rank;
import net.socketconnection.jva.enums.Region;
import net.socketconnection.jva.match.MatchTeam;
import net.socketconnection.jva.models.image.AgentImage;
import net.socketconnection.jva.models.player.*;
import net.socketconnection.jva.utils.GsonUtils;

public class MatchPlayer extends ValorantPlayer {

    String team;
    Agent agent;
    String playerTitle;
    String partyId;
    long playtime;
    Behavior behavior;
    Platform platform;
    AbilityCasts abilityCasts;
    AgentImage agentImage;
    Stats stats;
    Economy economy;
    int damageMade;
    int damageReceived;

    public MatchPlayer(ValorantAPI valorantAPI) {
        super(valorantAPI);
    }

    public MatchPlayer fetchData(Region region, JsonObject object) {
        this.region = region;

        playerId = GsonUtils.getAsString(object.get("puuid"));
        username = GsonUtils.getAsString(object.get("name"));
        tag = GsonUtils.getAsString(object.get("tag"));
        team = GsonUtils.getAsString(object.get("team"));
        level = GsonUtils.getAsInt(object.get("level"));
        agent = Agent.getFromName(GsonUtils.getAsString(object.get("character")));
        rank = Rank.getFromId(GsonUtils.getAsInt(object.get("currenttier")));
        playerTitle = GsonUtils.getAsString(object.get("player_title"));
        partyId = GsonUtils.getAsString(object.get("party_id"));
        playtime = GsonUtils.getAsLong(object.getAsJsonObject("session_playtime").get("milliseconds"));

        JsonObject behaviorData = object.getAsJsonObject("behavior");
        JsonObject friendlyFireData = behaviorData.getAsJsonObject("friendly_fire");

        behavior = new Behavior(GsonUtils.getAsInt(behaviorData.get("afk_rounds")), GsonUtils.getAsInt(friendlyFireData.get("incoming")),
                GsonUtils.getAsInt(friendlyFireData.get("outgoing")), GsonUtils.getAsInt(behaviorData.get("rounds_in_spawn")));

        JsonObject platformData = object.getAsJsonObject("platform");
        JsonObject osData = platformData.getAsJsonObject("os");

        platform = new Platform(GsonUtils.getAsString(platformData.get("type")), GsonUtils.getAsString(osData.get("name")),
                GsonUtils.getAsString(osData.get("version")));

        JsonObject abilityCastData = object.getAsJsonObject("ability_casts");

        abilityCasts = new AbilityCasts(GsonUtils.getAsInt(abilityCastData.get("c_cast")), GsonUtils.getAsInt(abilityCastData.get("q_cast")),
                GsonUtils.getAsInt(abilityCastData.get("e_cast")), GsonUtils.getAsInt(abilityCastData.get("x_cast")));

        JsonObject assetsData = object.getAsJsonObject("assets");
        JsonObject cardData = assetsData.getAsJsonObject("card");
        JsonObject agentData = assetsData.getAsJsonObject("agent");

        playerCard = new PlayerCard(GsonUtils.getAsString(cardData.get("small")), GsonUtils.getAsString(cardData.get("large")),
                GsonUtils.getAsString(cardData.get("wide")), GsonUtils.getAsString(object.get("player_card")));
        agentImage = new AgentImage(GsonUtils.getAsString(agentData.get("small")), GsonUtils.getAsString(agentData.get("bust")),
                GsonUtils.getAsString(agentData.get("full")), GsonUtils.getAsString(agentData.get("killfeed")));

        JsonObject statsData = object.getAsJsonObject("stats");

        stats = new Stats(GsonUtils.getAsInt(statsData.get("score")), GsonUtils.getAsInt(statsData.get("kills")), GsonUtils.getAsInt(statsData.get("deaths")),
                GsonUtils.getAsInt(statsData.get("assists")), GsonUtils.getAsInt(statsData.get("bodyshots")), GsonUtils.getAsInt(statsData.get("headshots")),
                GsonUtils.getAsInt(statsData.get("legshots")));

        JsonObject economyData = object.getAsJsonObject("economy");
        JsonObject spentData = economyData.getAsJsonObject("spent");
        JsonObject loadoutData = economyData.getAsJsonObject("loadout_value");

        economy = new Economy(GsonUtils.getAsInt(spentData.get("overall")), GsonUtils.getAsInt(spentData.get("average")),
                GsonUtils.getAsInt(loadoutData.get("overall")), GsonUtils.getAsInt(loadoutData.get("average")));

        damageMade = GsonUtils.getAsInt(object.get("damage_made"));
        damageReceived = GsonUtils.getAsInt(object.get("damage_received"));

        fetched = true;

        return this;
    }

    public int getDamageReceived() {
        return damageReceived;
    }

    public int getDamageMade() {
        return damageMade;
    }

    public Economy getEconomy() {
        return economy;
    }

    public Stats getStats() {
        return stats;
    }

    public AgentImage getAgentImage() {
        return agentImage;
    }

    public AbilityCasts getAbilityCasts() {
        return abilityCasts;
    }

    public Platform getPlatform() {
        return platform;
    }

    public Behavior getBehavior() {
        return behavior;
    }

    public long getPlaytime() {
        return playtime;
    }

    public String getPartyId() {
        return partyId;
    }

    public String getPlayerTitle() {
        return playerTitle;
    }

    public Agent getAgent() {
        return agent;
    }

    public String getTeam() {
        return team;
    }
}

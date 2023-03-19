package net.socketconnection.jva.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.socketconnection.jva.ValorantAPI;
import net.socketconnection.jva.enums.Agent;
import net.socketconnection.jva.enums.Rank;
import net.socketconnection.jva.enums.Region;
import net.socketconnection.jva.match.Match;
import net.socketconnection.jva.models.image.AgentImage;
import net.socketconnection.jva.models.player.*;

public class MatchPlayer extends ValorantPlayer {

    Match.Team team;
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

        playerId = object.get("puuid").getAsString();
        username = object.get("name").getAsString();
        tag = object.get("tag").getAsString();
        team = Match.Team.getFromTeamName(object.get("team").getAsString());
        level = object.get("level").getAsInt();
        agent = Agent.getFromAgentName(object.get("character").getAsString());
        rank = Rank.getFromRankId(object.get("currenttier").getAsInt());
        playerTitle = object.get("player_title").getAsString();
        partyId = object.get("party_id").getAsString();

        JsonElement playtimeElement = object.getAsJsonObject("session_playtime").get("milliseconds");

        if(playtimeElement.isJsonNull()) {
            playtime = 0;
        } else {
            playtime = playtimeElement.getAsLong();
        }

        JsonObject behaviorData = object.getAsJsonObject("behavior");
        JsonObject friendlyFireData = behaviorData.getAsJsonObject("friendly_fire");

        behavior = new Behavior(behaviorData.get("afk_rounds").getAsInt(), friendlyFireData.get("incoming").getAsInt(),
                friendlyFireData.get("outgoing").getAsInt(), behaviorData.get("rounds_in_spawn").getAsInt());

        JsonObject platformData = object.getAsJsonObject("platform");
        JsonObject osData = platformData.getAsJsonObject("os");

        platform = new Platform(platformData.get("type").getAsString(), osData.get("name").getAsString(),
                osData.get("version").getAsString());

        JsonObject abilityCastData = object.getAsJsonObject("ability_casts");

        abilityCasts = new AbilityCasts(abilityCastData.get("c_cast").getAsInt(), abilityCastData.get("q_cast").getAsInt(),
                abilityCastData.get("e_cast").getAsInt(), abilityCastData.get("x_cast").getAsInt());

        JsonObject assetsData = object.getAsJsonObject("assets");
        JsonObject cardData = assetsData.getAsJsonObject("card");
        JsonObject agentData = assetsData.getAsJsonObject("agent");

        playerCard = new PlayerCard(cardData.get("small").getAsString(), cardData.get("large").getAsString(),
                cardData.get("wide").getAsString(), object.get("player_card").getAsString());
        agentImage = new AgentImage(agentData.get("small").getAsString(), agentData.get("bust").getAsString(),
                agentData.get("full").getAsString(), agentData.get("killfeed").getAsString());

        JsonObject statsData = object.getAsJsonObject("stats");

        stats = new Stats(statsData.get("score").getAsInt(), statsData.get("kills").getAsInt(), statsData.get("deaths").getAsInt(),
                statsData.get("assists").getAsInt(), statsData.get("bodyshots").getAsInt(), statsData.get("headshots").getAsInt(),
                statsData.get("legshots").getAsInt());

        JsonObject economyData = object.getAsJsonObject("economy");
        JsonObject spentData = economyData.getAsJsonObject("spent");
        JsonObject loadoutData = economyData.getAsJsonObject("loadout_value");

        economy = new Economy(spentData.get("overall").getAsInt(), spentData.get("average").getAsInt(),
                loadoutData.get("overall").getAsInt(), loadoutData.get("average").getAsInt());

        damageMade = object.get("damage_made").getAsInt();
        damageReceived = object.get("damage_received").getAsInt();

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

    public Match.Team getTeam() {
        return team;
    }
}

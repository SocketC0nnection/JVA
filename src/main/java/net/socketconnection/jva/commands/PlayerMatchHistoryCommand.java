package net.socketconnection.jva.commands;

import net.socketconnection.jva.api.ValorantAPI;
import net.socketconnection.jva.enums.Map;
import net.socketconnection.jva.enums.Region;
import net.socketconnection.jva.match.Match;
import net.socketconnection.jva.player.MatchPlayer;
import net.socketconnection.jva.player.ValorantPlayer;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "PlayerMatchHistory", mixinStandardHelpOptions = true, version = "1.0",
        description = "Get a players match history.")
public class PlayerMatchHistoryCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-u", "--username"}, paramLabel = "Username", description = "The player username", required = true)
    private String username;

    @CommandLine.Option(names = {"-t", "--tagname"}, paramLabel = "Tagname", description = "The player tag name", required = true)
    private String tagName;

    @CommandLine.Option(names = {"-ak", "--apikey"}, description = "The api key")
    private String apiKey;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display a help message")
    private boolean helpRequested = false;

    @Override
    public Integer call() throws Exception {

        // Initialize the main instance (API key is NOT required)
        var valorantAPI = new ValorantAPI(apiKey);
        // Pass the main instance to the player instance and fill the player with an username and tag or riot id
        var valorantPlayer = new ValorantPlayer(valorantAPI)
                .fetchData(username, tagName);

        // Saves the last 5 matches in an array of the Match object
        Match[] matches = valorantPlayer.getMatchHistory();
        // Saves the last one in a variable
        Match match = matches[0];

        // Saves the map in a variable using the Map enum
        Map map = match.getMap();
        // Print out the map using the getName() method
        System.out.println(map.getName());

        // Get match id and region of a match using the Region enum
        String matchId = match.getMatchId();
        Region region = match.getRegion();

        // Get other players and their values from a match using the MatchPlayer instances
        List<MatchPlayer> matchPlayers = match.getPlayers();
        MatchPlayer matchPlayer = matchPlayers.get(0);

        // Print out stats of a match player
        System.out.println(matchPlayer.getStats().getKills());
        // Print out informations about a match players behavior and economy
        System.out.println(matchPlayer.getBehavior().getAfkRounds());
        System.out.println(matchPlayer.getEconomy().getOverallSpent());
        return 0;
    }
}

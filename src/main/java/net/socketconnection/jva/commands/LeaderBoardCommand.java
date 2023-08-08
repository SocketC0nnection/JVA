package net.socketconnection.jva.commands;

import net.socketconnection.jva.api.ValorantAPI;
import net.socketconnection.jva.enums.Region;
import net.socketconnection.jva.player.LeaderboardPlayer;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "LeaderBoard", mixinStandardHelpOptions = true, version = "1.0",
        description = "Get the leaderboard for a region.")
public class LeaderBoardCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-r", "--region"}, paramLabel = "region", description = "Region", required = true)
    private String region;

    @CommandLine.Option(paramLabel = "apikey", names = {"-ak", "--apikey"}, description = "The api key")
    private String apiKey;

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "display a help message")
    private boolean helpRequested = false;

    @Override
    public Integer call() throws Exception {
        var valorantAPI = new ValorantAPI(apiKey);
        // Get the first 1000 leaderboard entries in a list using the Leaderboard class
        List<LeaderboardPlayer> leaderboardPlayers = valorantAPI.getLeaderboard(Region.getFromQuery(region));
        LeaderboardPlayer leaderboardPlayer = leaderboardPlayers.get(0);
        // Print out the current placement and rank rating of the first player
        System.out.println(leaderboardPlayer.getLeaderboardRank());
        System.out.println(leaderboardPlayer.getRankRating());
        return 0;
    }

}

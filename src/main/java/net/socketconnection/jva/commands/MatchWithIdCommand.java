package net.socketconnection.jva.commands;

import net.socketconnection.jva.api.ValorantAPI;
import net.socketconnection.jva.match.Match;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "getMatch", mixinStandardHelpOptions = true, version = "1.0",
        description = "Get a match using the match id.")
public class MatchWithIdCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"--id"}, paramLabel = "MatchId", description = "The Match id", required = true)
    private String matchId;

    @CommandLine.Option(names = {"-ak", "--apikey"}, description = "The api key")
    private String apiKey;

    @Override
    public Integer call() throws Exception {
        // Initialize the main instance (API key is NOT required)
        ValorantAPI valorantAPI = new ValorantAPI(apiKey);
        // Pass the main instance to the match instance and fill the match with a match id
        Match match = new Match(valorantAPI).fetchData(matchId);

        System.out.println(match);

        return 0;
    }
}

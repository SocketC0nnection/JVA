package net.socketconnection.jva.commands;

import net.socketconnection.jva.api.ValorantAPI;
import net.socketconnection.jva.models.player.PlayerCard;
import net.socketconnection.jva.player.ValorantPlayer;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "playerRank", mixinStandardHelpOptions = true, version = "1.0",
        description = "Get player informations.")
public class PlayerCommand implements Callable<Integer> {

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

        // Read out the rank using the Rank enum for example
        var rank = valorantPlayer.getRank();
        // Print out the rank using the getName() method
        System.out.println(rank.getName());

        // Read out the level
        int level = valorantPlayer.getLevel();
        // Read out the last amount of RR he got or lost
        int mmrChange = valorantPlayer.getMmrChange();

        // Get the players banner using the PlayerCard model
        PlayerCard playerCard = valorantPlayer.getPlayerCard();
        // Print out the banners url in different sizes
        System.out.println(playerCard.getSmall());
        System.out.println(playerCard.getLarge());
        System.out.println("Player leve  = " + level);
        System.out.println("Player MMR = " + mmrChange);
        return 0;
    }
}

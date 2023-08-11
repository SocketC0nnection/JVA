package net.socketconnection.jva.commands;

import net.socketconnection.jva.api.ValorantAPI;
import net.socketconnection.jva.enums.Language;
import net.socketconnection.jva.enums.Region;
import net.socketconnection.jva.models.status.ServerStatus;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "RegionUpdates", mixinStandardHelpOptions = true, version = "1.0",
        description = "Get current maintenances and incidents for a specific region.")
public class RegionUpdatesCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-r", "--region"}, paramLabel = "region", description = "Region", required = true)
    private String region;

    @CommandLine.Option(names = {"-lg", "--language"}, description = "The language of the article", defaultValue = "en-en")
    private String language;

    @CommandLine.Option(paramLabel = "apikey", names = {"-ak", "--apikey"}, description = "The api key")
    private String apiKey;

    @Override
    public Integer call() throws Exception {

        ValorantAPI valorantAPI = new ValorantAPI(apiKey);
        // Saves data about versions of a region in a Version model
        ServerStatus serverStatus = valorantAPI.getServerStatus(Region.getFromQuery(region));

        // Print out a current incident in a specific language using the Language enum
        System.out.println(serverStatus.getIncidents()[0].getTitles().get(Language.getFromName(language)));
        return 0;
    }
}

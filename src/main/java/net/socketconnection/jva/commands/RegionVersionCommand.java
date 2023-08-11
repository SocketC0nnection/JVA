package net.socketconnection.jva.commands;

import net.socketconnection.jva.api.ValorantAPI;
import net.socketconnection.jva.enums.Region;
import net.socketconnection.jva.models.Version;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "RegionVersion", mixinStandardHelpOptions = true, version = "1.0",
        description = "Get player informations.")
public class RegionVersionCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-r", "--region"}, paramLabel = "region", description = "Region", required = true)
    private String region;

    @CommandLine.Option(paramLabel = "apikey", names = {"-ak", "--apikey"}, description = "The api key")
    private String apiKey;

    @Override
    public Integer call() throws Exception {
        ValorantAPI valorantAPI = new ValorantAPI(apiKey);
        // Saves data about versions of a region in a Version model
        Version version = valorantAPI.getVersion(Region.getFromQuery(region));

        // Print out client and server version
        System.out.println(version.getVersion());
        System.out.println(version.getClientVersion());

        return 0;
    }
}

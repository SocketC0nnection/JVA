package net.socketconnection.jva.commands;


import net.socketconnection.jva.api.ValorantAPI;
import net.socketconnection.jva.models.shop.Bundle;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "StoreBundle", mixinStandardHelpOptions = true, version = "1.0",
        description = "Get all available store bundles.")
public class StoreBundleCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-ak", "--apikey"}, description = "The api key")
    private String apiKey;

    @Override
    public Integer call() throws Exception {

        // Initialize the main instance (API key is NOT required)
        ValorantAPI valorantAPI = new ValorantAPI(apiKey);

        // Get all available store bundles
        List<Bundle> offerItems = valorantAPI.getStoreBundles();
        // Print out first bundles price
        System.out.println(offerItems.get(0).getBundlePrice());
        // Print ouf second bundles expiring date
        System.out.println(offerItems.get(1).getExpiresAt());
        return 0;
    }
}

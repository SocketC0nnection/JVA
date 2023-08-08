package net.socketconnection.jva.commands;


import net.socketconnection.jva.api.ValorantAPI;
import net.socketconnection.jva.models.shop.item.OfferItem;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "StoreItem", mixinStandardHelpOptions = true, version = "1.0",
        description = "Get all store items.")
public class StoreItemCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-ak", "--apikey"}, description = "The api key")
    private String apiKey;


    @Override
    public Integer call() throws Exception {

        // Initialize the main instance (API key is NOT required)
        ValorantAPI valorantAPI = new ValorantAPI(apiKey);

        // Get all store items
        List<OfferItem> offerItems = valorantAPI.getStoreOffers();
        // Print out first items name
        System.out.println(offerItems.get(0).getName());

        return 0;
    }
}

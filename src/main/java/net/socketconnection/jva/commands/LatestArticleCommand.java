package net.socketconnection.jva.commands;


import net.socketconnection.jva.api.ValorantAPI;
import net.socketconnection.jva.enums.Language;
import net.socketconnection.jva.models.WebsiteArticle;
import picocli.CommandLine;

import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "LatestArticle", mixinStandardHelpOptions = true, version = "1.0",
        description = "Get the last articles from the official VALORANT page.")
public class LatestArticleCommand implements Callable<Integer> {

    @CommandLine.Option(names = {"-lg", "--language"}, description = "The language of the article", defaultValue = "en-en")
    private String language;
    @CommandLine.Option(names = {"-ak", "--apikey"}, description = "The api key")
    private String apiKey;

    @Override
    public Integer call() throws Exception {

        // Initialize the main instance (API key is NOT required)
        ValorantAPI valorantAPI = new ValorantAPI(apiKey);
        // Get the last articles from the valorant page in a specific language using the Language enu
        List<WebsiteArticle> websiteArticles = valorantAPI.getWebsiteArticles(Language.getFromName(language));
        WebsiteArticle websiteArticle = websiteArticles.get(0);

        // Print out title and url of the last article
        System.out.println(websiteArticle.getTitle());
        System.out.println(websiteArticle.getUrl());
        return 0;
    }
}

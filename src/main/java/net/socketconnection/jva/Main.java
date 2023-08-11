package net.socketconnection.jva;

import net.socketconnection.jva.commands.*;
import picocli.CommandLine;


public class Main {
    public static void main(String... args) {

        /**Commands**/
        CommandLine cmd = new CommandLine(new MainCommands())
                .addSubcommand("PlayerRank", new PlayerCommand())
                .addSubcommand("PlayerMatchHistory", new PlayerMatchHistoryCommand())
                .addSubcommand("MatchById", new MatchWithIdCommand())
                .addSubcommand("LeaderBoard", new LeaderBoardCommand())
                .addSubcommand("RegionVersion", new RegionVersionCommand())
                .addSubcommand("LatestValorantArticle", new LatestArticleCommand())
                .addSubcommand("RegionUpdates", new RegionUpdatesCommand())
                .addSubcommand("ValorantStoreItems", new StoreItemCommand())
                .addSubcommand("ValorantStoreBundle", new StoreBundleCommand());

        cmd.setExecutionStrategy(new CommandLine.RunAll());
        int exitCode = cmd.execute(args);
        System.exit(exitCode);

    }
}

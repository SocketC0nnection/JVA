package net.socketconnection.jva.commands;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "Valorant CLI", mixinStandardHelpOptions = true, version = "1.0",
        description = "Welcome to Valorant CLI use the following commands.")
public class MainCommands implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 0;
    }
}

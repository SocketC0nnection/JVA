package net.socketconnection.jva;

import net.socketconnection.jva.player.ValorantPlayer;

import java.io.IOException;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

public class Test {

    public static void main(String[] args) throws IOException {
        ValorantAPI valorantAPI = new ValorantAPI();
        ValorantPlayer player = new ValorantPlayer(valorantAPI).fetchData("Keno08#mieft");

        System.out.println("UUID: " + player.getPuuid());
        System.out.println("Rank: " + player.getRank().getRankName());
        System.out.println("Level: " + player.getLevel());
    }

}

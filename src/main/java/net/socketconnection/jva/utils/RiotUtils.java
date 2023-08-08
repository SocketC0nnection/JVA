package net.socketconnection.jva.utils;

public class RiotUtils {

    public static final String BASE_URL = "https://api.henrikdev.xyz/";

    private RiotUtils() {
    }

    public static String toRiotId(String username, String tag) {
        return username + "#" + tag;
    }

}

package net.socketconnection.jva.utils;

import com.google.gson.JsonElement;

public class GsonUtils {

    public static String getAsString(JsonElement element) {
        if(element.isJsonNull()) {
            return null;
        }

        return element.getAsString();
    }

    public static int getAsInt(JsonElement element) {
        if(element.isJsonNull()) {
            return 0;
        }

        return element.getAsInt();
    }

    public static double getAsDouble(JsonElement element) {
        if(element.isJsonNull()) {
            return 0;
        }

        return element.getAsDouble();
    }

    public static long getAsLong(JsonElement element) {
        if(element.isJsonNull()) {
            return 0;
        }

        return element.getAsLong();
    }

    public static boolean getAsBoolean(JsonElement element) {
        if(element.isJsonNull()) {
            return false;
        }

        return element.getAsBoolean();
    }

}

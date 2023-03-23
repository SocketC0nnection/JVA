package net.socketconnection.jva.player;

import net.socketconnection.jva.ValorantAPI;
import net.socketconnection.jva.exceptions.FetchException;

import java.io.IOException;

public class RoundPlayer extends Player {



    public RoundPlayer(ValorantAPI valorantAPI) {
        super(valorantAPI);
    }

    public ValorantPlayer getValorantPlayer() throws IOException {
        if (!fetched) {
            throw new FetchException("Not fetched yet (no riot-id information");
        }

        return new ValorantPlayer(valorantAPI).fetchData(username, tag);
    }

}

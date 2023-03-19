package net.socketconnection.jva.models.player;

public final class Behavior {

    private final int afkRounds;
    private final int incomingFriendlyFire;
    private final int outgoingFriendlyFire;
    private final int roundsInSpawn;

    public Behavior(int afkRounds, int incomingFriendlyFire, int outgoingFriendlyFire, int roundsInSpawn) {
        this.afkRounds = afkRounds;
        this.incomingFriendlyFire = incomingFriendlyFire;
        this.outgoingFriendlyFire = outgoingFriendlyFire;
        this.roundsInSpawn = roundsInSpawn;
    }

    public int getRoundsInSpawn() {
        return roundsInSpawn;
    }

    public int getOutgoingFriendlyFire() {
        return outgoingFriendlyFire;
    }

    public int getIncomingFriendlyFire() {
        return incomingFriendlyFire;
    }

    public int getAfkRounds() {
        return afkRounds;
    }
}

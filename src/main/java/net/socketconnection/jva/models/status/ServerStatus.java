package net.socketconnection.jva.models.status;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

public final class ServerStatus {

    private final StatusEntry[] maintenances;
    private final StatusEntry[] incidents;

    public ServerStatus(StatusEntry[] maintenances, StatusEntry[] incidents) {
        this.maintenances = maintenances;
        this.incidents = incidents;
    }

    public StatusEntry[] getIncidents() {
        return incidents;
    }

    public StatusEntry[] getMaintenances() {
        return maintenances;
    }
}

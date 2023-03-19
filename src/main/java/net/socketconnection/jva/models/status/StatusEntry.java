package net.socketconnection.jva.models.status;

import net.socketconnection.jva.enums.Language;

import java.util.Map;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

public final class StatusEntry {

    private final String createdAt;
    private final String archiveAt;
    private final Update[] updates;
    private final String[] platforms;
    private final String updatedAt;
    private final int id;
    private final Map<Language, String> titles;
    private final String maintenanceStatus;
    private final String incidentSeverity;

    public StatusEntry(String createdAt, String archiveAt, Update[] updates, String[] platforms, String updatedAt, int id,
                       Map<Language, String> titles, String maintenanceStatus, String incidentSeverity) {
        this.createdAt = createdAt;
        this.archiveAt = archiveAt;
        this.updates = updates;
        this.platforms = platforms;
        this.updatedAt = updatedAt;
        this.id = id;
        this.titles = titles;
        this.maintenanceStatus = maintenanceStatus;
        this.incidentSeverity = incidentSeverity;
    }

    public String getIncidentSeverity() {
        return incidentSeverity;
    }

    public String getMaintenanceStatus() {
        return maintenanceStatus;
    }

    public Map<Language, String> getTitles() {
        return titles;
    }

    public int getId() {
        return id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String[] getPlatforms() {
        return platforms;
    }

    public Update[] getUpdates() {
        return updates;
    }

    public String getArchiveAt() {
        return archiveAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}

package net.socketconnection.jva.models.status;

import net.socketconnection.jva.enums.Language;

import java.util.Map;

public final class Update {

    private final String createdAt;
    private final String updatedAt;
    private final boolean published;
    private final int id;
    private final Map<Language, String> translations;
    private final String[] publishLocations;
    private final String author;

    public Update(String createdAt, String updatedAt, boolean published, int id, Map<Language, String> translations, String[] publishLocations, String author) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.published = published;
        this.id = id;
        this.translations = translations;
        this.publishLocations = publishLocations;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String[] getPublishLocations() {
        return publishLocations;
    }

    public Map<Language, String> getTranslations() {
        return translations;
    }

    public int getId() {
        return id;
    }

    public boolean isPublished() {
        return published;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}

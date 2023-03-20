package net.socketconnection.jva.models;

public final class WebsiteArticle {

    private final String bannerUrl;
    private final Category category;
    private final String date;
    private final String externalLink;
    private final String title;
    private final String url;

    public WebsiteArticle(String bannerUrl, Category category, String date, String externalLink, String title, String url) {
        this.bannerUrl = bannerUrl;
        this.category = category;
        this.date = date;
        this.externalLink = externalLink;
        this.title = title;
        this.url = url;
    }

    public enum Category {

        PATCH_NOTES("patch_notes", "Patch Notes"),
        DEV("dev", "Dev"),
        GAME_UPDATES("game_updates", "Game Updates"),
        ESPORTS("esports", "eSports"),
        ANNOUNCEMENTS("announcements", "Announcements"),
        COMMUNITY("community", "Community");

        private final String query;
        private final String name;

        Category(String query, String name) {
            this.query = query;
            this.name = name;
        }

        public static Category getFromName(String name) {
            for(Category category : values()) {
                if(!category.name.equalsIgnoreCase(name)) {
                    continue;
                }

                return category;
            }

            return null;
        }

        public static Category getFromQuery(String query) {
            for(Category category : values()) {
                if(!category.query.equalsIgnoreCase(query)) {
                    continue;
                }

                return category;
            }

            return null;
        }

        public String getName() {
            return name;
        }

        public String getQuery() {
            return query;
        }
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public String getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }
}

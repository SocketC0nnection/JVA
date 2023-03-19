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

        private final String categoryQuery;
        private final String categoryName;

        Category(String categoryQuery, String categoryName) {
            this.categoryQuery = categoryQuery;
            this.categoryName = categoryName;
        }

        public static Category getFromCategoryName(String categoryName) {
            for(Category category : values()) {
                if(!category.categoryName.equalsIgnoreCase(categoryName)) {
                    continue;
                }

                return category;
            }

            return null;
        }

        public static Category getFromCategoryQuery(String categoryQuery) {
            for(Category category : values()) {
                if(!category.categoryQuery.equalsIgnoreCase(categoryQuery)) {
                    continue;
                }

                return category;
            }

            return null;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public String getCategoryQuery() {
            return categoryQuery;
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

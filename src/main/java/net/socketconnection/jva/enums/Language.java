package net.socketconnection.jva.enums;

public enum Language {

    ENGLISH("en_US", "en-en", "English"),
    GERMAN("de_DE", "de-de", "German"),
    SPANISH_SPAIN("es_ES", "es-es", "Spain Spanish"),
    SPANISH_MEXICO("es_MX", "es-mx", "Mexican Spanish"),
    FRENCH("fr_FR", "fr-fr", "French"),
    INDONESIAN("id_ID", "id-id", "Indonesian"),
    ITALIAN("it_IT", "it-it", "Italian"),
    PORTUGUESE("pt_BR", "pt-br", "Portuguese"),
    ARABIC("ar_AE", "ar-ae", "Arabic"),
    POLISH("pl_PL", "pl-pl", "Polish"),
    RUSSIAN("ru_RU", "ru-ru", "Russian"),
    TURKISH("tr_TR", "tr-tr", "Turkish"),
    CHINESE("zh_TW", "zh-tw", "Chinese"),
    VIETNAMESE("vi_VN", "vi-vn", "Vietnamese"),
    THAI("th_TH", "th-th", "Thai"),
    JAPANESE("ja_JP", "ja-jp", "Japanese"),
    KOREAN("ko_KR", "ko-kr", "Korean");

    private final String locale;
    private final String localeUrl;
    private final String name;

    Language(String locale, String localeUrl, String name) {
        this.locale = locale;
        this.localeUrl = localeUrl;
        this.name = name;
    }

    public static Language getFromName(String name) {
        for(Language language : values()) {
            if(!language.name.equalsIgnoreCase(name)) {
                continue;
            }

            return language;
        }

        return null;
    }

    public static Language getFromLocaleUrl(String localeUrl) {
        for(Language language : values()) {
            if(!language.localeUrl.equalsIgnoreCase(localeUrl)) {
                continue;
            }

            return language;
        }

        return null;
    }

    public static Language getFromLocale(String locale) {
        for(Language language : values()) {
            if(!language.locale.equalsIgnoreCase(locale)) {
                continue;
            }

            return language;
        }

        return null;
    }

    public String getName() {
        return name;
    }

    public String getLocaleUrl() {
        return localeUrl;
    }

    public String getLocale() {
        return locale;
    }
}

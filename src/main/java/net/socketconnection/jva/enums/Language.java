package net.socketconnection.jva.enums;

/**
 * @author SocketConnection
 * @github https://github.com/socketc0nnection
 **/

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

    private final String languageLocale;
    private final String languageLocaleUrl;
    private final String languageName;

    Language(String languageLocale, String languageLocaleUrl, String languageName) {
        this.languageLocale = languageLocale;
        this.languageLocaleUrl = languageLocaleUrl;
        this.languageName = languageName;
    }

    public static Language getFromLanguageName(String languageName) {
        for(Language language : values()) {
            if(!language.languageName.equalsIgnoreCase(languageName)) {
                continue;
            }

            return language;
        }

        return null;
    }

    public static Language getFromLanguageLocaleUrl(String languageLocaleUrl) {
        for(Language language : values()) {
            if(!language.languageLocaleUrl.equalsIgnoreCase(languageLocaleUrl)) {
                continue;
            }

            return language;
        }

        return null;
    }

    public static Language getFromLanguageLocale(String languageLocale) {
        for(Language language : values()) {
            if(!language.languageLocale.equalsIgnoreCase(languageLocale)) {
                continue;
            }

            return language;
        }

        return null;
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getLanguageLocaleUrl() {
        return languageLocaleUrl;
    }

    public String getLanguageLocale() {
        return languageLocale;
    }
}

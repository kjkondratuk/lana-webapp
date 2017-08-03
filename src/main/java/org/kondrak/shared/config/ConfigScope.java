package org.kondrak.shared.config;

/**
 * Describes the scope of a generic configuration.
 */
public enum ConfigScope {
    GUILD("GUILD"), CHANNEL("CHANNEL"), USER("USER");

    String value;

    ConfigScope(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static String anyOfRegex() {
        return "(?>" + pipeDelimited() + ")";
    }

    public static String pipeDelimited() {
        return GUILD + "|" + CHANNEL + "|" + USER;
    }
}

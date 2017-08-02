package org.kondrak.lana.data;

public enum ConfigType {
    ARCHERISM_COMMAND("ARCHERISM_COMMAND"),
    DICE_COMMAND("DICE_COMMAND"),
    TIMER_COMMAND("TIMER_COMMAND"),
    WORD_COMMAND("WORD_COMMAND");

    String value;

    ConfigType(String value) {
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
        return ARCHERISM_COMMAND + "|" + DICE_COMMAND + "|" + TIMER_COMMAND + "|" + WORD_COMMAND;
    }
}

package org.kondrak.shared.config;

public enum ConfigCommand {
    ADD("add"), REMOVE("remove"), SHOW("show");

    private String value;

    ConfigCommand(String command) {
        this.value = command;
    }

    @Override
    public String toString() {
        return value;
    }

    public static String anyOfRegex() {
        return "(?>" + pipeDelimited() + ")";
    }

    public static String pipeDelimited() {
        return ADD + "|" + REMOVE + "|" + SHOW;
    }
}

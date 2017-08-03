package org.kondrak.shared.config;

public enum ConfigDatatype {
    BOOLEAN("BOOLEAN");

    String value;

    ConfigDatatype(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

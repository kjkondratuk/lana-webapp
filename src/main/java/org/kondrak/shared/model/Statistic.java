package org.kondrak.shared.model;

public class Statistic {
    private String key;
    private Integer value;

    public Statistic() {
        // empty for mybatis
    }

    public Statistic(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

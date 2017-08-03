package org.kondrak.shared.archerism;

/**
 * Created by Administrator on 2/24/2017.
 */
public class Archerism {
    private final String trigger;
    private final String text;

    public Archerism(String trigger, String text) {
        this.trigger = trigger;
        this.text = text;
    }

    public String getTrigger() {
        return trigger;
    }

    public String getText() {
        return text;
    }
}

package org.kondrak.shared.model;


/**
 * Created by nosferatu on 7/14/17.
 */
public class User {

    private final String name;
    private final String id;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}

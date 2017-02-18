package io.muic.ooc.webapp.service;

/**
 * Created by Apple on 2/16/2017 AD.
 */
public class Tracker {
    private String username;
    private String password;
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

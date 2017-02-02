package com.tarashor.siteparser.models;

/**
 * Created by Taras on 03.02.2017.
 */
public class DBConfig {
    private String server;
    private String user;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}

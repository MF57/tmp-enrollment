package org.tmp.enrollment.plumbing.config.security;

import java.util.List;

public class User {

    private String username;
    private List<String> privileges;

    public User(String username, List<String> privileges) {
        this.username = username;
        this.privileges = privileges;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getPrivileges() {
        return privileges;
    }
}

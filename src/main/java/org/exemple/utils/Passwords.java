package org.exemple.utils;

import java.util.HashMap;
import java.util.Map;

public class Passwords {
    private final Map<String, String> passwords = new HashMap<>();

    {
        passwords.put("admin", "admin");
        passwords.put("jim", "123");
        passwords.put("jack", "234");
    }

    public boolean checkPassword(String username, String password) {
        return passwords.containsKey(username) && passwords.get(username).equals(password);
    }

}
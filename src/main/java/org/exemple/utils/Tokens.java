package org.exemple.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class Tokens {

    private final Map<UUID, String> tokens = new HashMap<>();

    public UUID login(String username) {
        UUID token = UUID.randomUUID();
        tokens.put(token, username);
        return token;
    }

    public void logout(UUID token) {
        tokens.remove(token);
    }

    public Optional<String> check(UUID token) {
        return Optional.ofNullable(tokens.get(token));
    }

    public String getUnsafe(UUID token) {
        return tokens.get(token);
    }

}

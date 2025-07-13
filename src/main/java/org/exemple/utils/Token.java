package org.exemple.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class Token {

    private static final String COOKIE_NAME = "J_ID";

    public static Optional<UUID> get(HttpServletRequest request) {
        return Optional.ofNullable(request.getCookies())
                .stream()
                .flatMap(Arrays::stream)
                .filter(cookie -> cookie.getName().equals(COOKIE_NAME))
                .findFirst()
                .map(Cookie::getValue)
                .map(UUID::fromString);
    }

    public static UUID getUnsafe(HttpServletRequest request) {
        return get(request)
                .orElseThrow(() -> new IllegalArgumentException("Token should exist!"));
    }

    public static void set(UUID token, HttpServletResponse resp) {
        Cookie c = new Cookie(COOKIE_NAME, token.toString());
        c.setPath("/");
        resp.addCookie(c);
    }

    public static void logout(HttpServletResponse resp) {
        Cookie c = new Cookie(COOKIE_NAME, "");
        c.setPath("/");
        c.setMaxAge(0);
        resp.addCookie(c);
    }


}

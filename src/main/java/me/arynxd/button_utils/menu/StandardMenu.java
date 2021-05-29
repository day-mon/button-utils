package me.arynxd.button_utils.menu;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Random;
import me.arynxd.button_utils.builder.menu.StandardMenuBuilder;

public class StandardMenu implements Menu {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final Random random = new Random();

    public StandardMenu(StandardMenuBuilder builder) {

    }

    @Override
    public void start() {

    }
}

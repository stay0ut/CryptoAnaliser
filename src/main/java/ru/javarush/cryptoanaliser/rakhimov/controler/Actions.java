package ru.javarush.cryptoanaliser.rakhimov.controler;

import ru.javarush.cryptoanaliser.rakhimov.commands.*;

public enum Actions {
    ENCODE(new Encoder()),
    DECODE(new Decoder()),
    BRUTE(new Brute());

    private final Action action;

    Actions(Action action) {
        this.action = action;
    }

    public static Action find(String command) {
        return Actions.valueOf(command.toUpperCase()).action;
    }
}
